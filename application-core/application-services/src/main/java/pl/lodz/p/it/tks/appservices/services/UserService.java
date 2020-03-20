package pl.lodz.p.it.tks.appservices.services;

import pl.lodz.p.it.tks.domainmodel.users.Admin;
import pl.lodz.p.it.tks.domainmodel.users.Client;
import pl.lodz.p.it.tks.domainmodel.users.Manager;
import pl.lodz.p.it.tks.domainmodel.users.User;
import pl.lodz.p.it.tks.ports.aggregates.UserAdapter;
import pl.lodz.p.it.tks.ports.aggregates.UserRepoCrudAdapter;
import pl.lodz.p.it.tks.ports.aggregates.UserRepoFilterAdapter;
import pl.lodz.p.it.tks.ports.aggregates.UserRepoGetAdapter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Named
@Dependent
public class UserService implements Serializable {

    @Inject
    private UserRepoCrudAdapter userRepoCrudAdapter;
    @Inject
    private UserRepoFilterAdapter userRepoFilterAdapter;
    @Inject
    private UserRepoGetAdapter userRepoGetAdapter;

    private String sha256(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = new byte[0];
        if (digest != null) {
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        }
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void addAdmin(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepoCrudAdapter.addUser(new Admin(username, sha256(password), firstName, lastName, active));
        }
    }

    public void addManager(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepoCrudAdapter.addUser(new Manager(username, sha256(password), firstName, lastName, active));
        }
    }

    public void addClient(String username, boolean active, String firstName, String lastName, String password) {
        if (getUser(username) == null) {
            userRepoCrudAdapter.addUser(new Client(username, sha256(password), firstName, lastName, active));
        }
    }

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

    public List<Admin> filterAdmins(String adminFilter) {
        return userRepoFilterAdapter.filterAdmins(adminFilter);
    }

    public List<Manager> filterManagers(String managerFilter) {
        return userRepoFilterAdapter.filterManagers(managerFilter);
    }

    public List<Client> filterClients(String clientFilter) {
        return userRepoFilterAdapter.filterClients(clientFilter);
    }

    public void updateUser(String username, String firstName, String lastName, boolean active) {
        if (getUser(username) != null) {
            User temp = getUser(username);
            temp.setFirstName(firstName);
            temp.setLastName(lastName);
            temp.setActive(active);
            userRepoCrudAdapter.updateUser(username, temp);
        }
    }
}
