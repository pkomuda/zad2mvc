package com.pas.zad2mvc.controllers.users;

import com.pas.zad2mvc.services.UserService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public class AddUserController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private Conversation conversation;
    private String userType;
    private String username;
    private String password;
    private boolean active;
    private String firstName;
    private String lastName;

    public String add() {
        beginConversation();
        return "add";
    }

    public String confirm(String userType) {
        switch (userType) {
            case "admin":
                userService.addAdmin(username, active, firstName, lastName, password);
                break;
            case "manager":
                userService.addManager(username, active, firstName, lastName, password);
                break;
            case "client":
                userService.addClient(username, active, firstName, lastName, password);
                break;
        }
        endConversation();
        return "admin";
    }

    public String cancel() {
        return "cancel";
    }

    public String cancelConfirm() {
        return "cancelConfirm";
    }

    //region conversation
    private void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    private void endConversation() {
        conversation.end();
    }
    //endregion

    //region getters
    public String getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    //endregion

    //region setters
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //endregion
}