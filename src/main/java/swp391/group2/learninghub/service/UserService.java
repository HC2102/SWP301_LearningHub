package swp391.group2.learninghub.service;


import swp391.group2.learninghub.model.User;
import swp391.group2.learninghub.model.sdi.ClientSdi;

import java.util.List;

public interface UserService{
    List<User> showUsers();
    User register(User newUser) throws Exception;
    void save(User newUser);
    List<User> findByEmail(String name);
    public Boolean create(ClientSdi sdi);
    public void deactivate(String target) throws Exception;
}
