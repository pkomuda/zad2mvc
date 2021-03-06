package pl.lodz.p.it.tks.rest.ports.aggregates.user.impl;

import pl.lodz.p.it.tks.rest.adapters.repositories.UserRepository;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;
import pl.lodz.p.it.tks.rest.ports.aggregates.user.UserRepoCrudAdapter;
import pl.lodz.p.it.tks.rest.ports.infrastructure.UserOutput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserRepoCrudAdapterImpl implements UserRepoCrudAdapter {

    @Inject
    private UserRepository userRepository;
    @Inject
    private UserOutput userOutput;

    @Override
    public void addUser(User user) {
        userRepository.addUser(userOutput.convert(user));
    }

    @Override
    public void updateUser(String username, User user) {
        userRepository.updateUser(username, userOutput.convert(user));
    }
}
