package pl.lodz.p.it.model.users;

public class ManagerWeb extends UserWeb {

    public ManagerWeb() {
        super();
    }

    public ManagerWeb(String username, String password, String firstName, String lastName, boolean active) {
        super(username, password, firstName, lastName, active);
    }
}
