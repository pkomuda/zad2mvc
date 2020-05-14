package pl.lodz.p.it.webapplication.controllers.users;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.webapplication.controllers.ViewAccessController;
import uiports.aggregates.userweb.UserWebCrudAdapter;
import uiports.aggregates.userweb.UserWebGetAdapter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public @Data class EditUserController {

    @Inject
    private UserWebCrudAdapter userCrudAdapter;
    @Inject
    private UserWebGetAdapter userGetAdapter;
    @Inject
    private ViewAccessController viewAccessController;
    private String username;
    private boolean active;
    private String firstName;
    private String lastName;
    private String password;
    private UserWeb user;

    public String confirmEditUser() {
        if (user instanceof AdminWeb) {
            userCrudAdapter.updateUser(username, new AdminWeb(username, password, firstName, lastName, active));
        } else if (user instanceof ManagerWeb) {
            userCrudAdapter.updateUser(username, new ManagerWeb(username, password, firstName, lastName, active));
        } else if (user instanceof ClientWeb) {
            userCrudAdapter.updateUser(username, new ClientWeb(username, password, firstName, lastName, active));
        }
        return "admin";
    }

    public String cancel() {
        return "cancel";
    }

    @PostConstruct
    public void loadUserInfo() {
        username = viewAccessController.getSelectedUsername();
        active = userGetAdapter.getUser(username).isActive();
        password = userGetAdapter.getUser(username).getPassword();
        user = userGetAdapter.getUser(username);
    }
}