package swp391.group2.learninghub.Service;


import swp391.group2.learninghub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import swp391.group2.learninghub.Model.sdi.ClientSdi;

import java.util.List;

public interface UserService{
    List<User> showUsers();
    User register(User newUser) throws Exception;
    void save(User newUser);
    List<User> findByEmail(String name);
    public Boolean create(ClientSdi sdi);

}
