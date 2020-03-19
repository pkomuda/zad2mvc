package pl.lodz.p.it.tks.appservices.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.UserService;
import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class AdminPageController implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Admin> admins;
    private List<Manager> managers;
    private List<Client> clients;
    private String userFilter;

    public String addUser() {
        return "add";
    }

    public String prepareUserInfo(User selectedUser) {
        viewAccessController.setSelectedUsername(selectedUser.getUsername());
        return "edit";
    }

    public String prepareRentsInfo(Client selectedClient) {
        viewAccessController.setSelectedUsername(selectedClient.getUsername());
        return "rentsForClient";
    }

    public void filterUsers() {
        admins = userService.filterAdmins(userFilter);
        managers = userService.filterManagers(userFilter);
        clients = userService.filterClients(userFilter);
    }

    @PostConstruct
    public void loadUsers() {
        admins = userService.getAdmins();
        managers = userService.getManagers();
        clients = userService.getClients();
    }
}