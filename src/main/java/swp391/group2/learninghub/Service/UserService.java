package swp391.group2.learninghub.Service;


import swp391.group2.learninghub.Model.User_Table;
import swp391.group2.learninghub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService extends JpaRepository<User_Table,String> {
    List<User_Table> findByEmail(String email);
    List<User_Table> findByPassword(String pass);
    List<User> showUsers();
    User register(User newUser) throws Exception;

}
