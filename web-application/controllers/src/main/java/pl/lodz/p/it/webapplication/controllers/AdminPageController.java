package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import uiports.aggregates.UserAdapter;

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
    private UserAdapter userAdapter;
    @Inject
    private ViewAccessController viewAccessController;

    private List<AdminWeb> admins;
    private List<ManagerWeb> managers;
    private List<ClientWeb> clients;
    private String userFilter;

    public String addUser() {
        return "add";
    }

    public String prepareUserInfo(UserWeb selectedUser) {
        viewAccessController.setSelectedUsername(selectedUser.getUsername());
        return "edit";
    }

    public String prepareRentsInfo(ClientWeb selectedClient) {
        viewAccessController.setSelectedUsername(selectedClient.getUsername());
        return "rentsForClient";
    }

    public void filterUsers() {

//        admins = UserConverter.domainToWebAdmins(userFilterService.filterAdmins(userFilter));
//        managers =UserConverter.domainToWebManagers(userFilterService.filterManagers(userFilter));
//        clients =UserConverter.domainToWebClients(userFilterService.filterClients(userFilter));

    }

    @PostConstruct
    public void loadUsers() {
        admins = userAdapter.getAdmins();
        managers = userAdapter.getManagers();
        clients = userAdapter.getClients();
    }
}