package swp.group2.learninghub.service;

import java.util.List;

import swp.group2.learninghub.model.User;
import swp.group2.learninghub.model.sdi.ClientSdi;

public interface UserService {
    List<User> showUsers();

    User register(User newUser);

    void save(User newUser);

    List<User> findByEmail(String name);

    public Boolean create(ClientSdi sdi);

    public void deactivate(String target);
}
