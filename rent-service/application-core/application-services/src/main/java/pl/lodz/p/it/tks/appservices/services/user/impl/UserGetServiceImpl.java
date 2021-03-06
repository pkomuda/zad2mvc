package pl.lodz.p.it.tks.appservices.services.user.impl;

import pl.lodz.p.it.tks.appservices.services.user.UserGetService;
import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;
import pl.lodz.p.it.tks.rest.ports.aggregates.user.UserRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class UserGetServiceImpl implements Serializable, UserGetService {

    @Inject
    private UserRepoGetAdapter userRepoGetAdapter;

    public User getUser(String username) {
        return userRepoGetAdapter.getUser(username);
    }

    public List<Admin> getAdmins() {
        return userRepoGetAdapter.getAdmins();
    }

    public List<Manager> getManagers() {
        return userRepoGetAdapter.getManagers();
    }

    public List<Client> getClients() {
        return userRepoGetAdapter.getClients();
    }
}
