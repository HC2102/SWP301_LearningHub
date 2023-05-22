package swp391.group2.learninghub.Service;

import swp391.group2.learninghub.Model.User;

import java.util.List;

public interface UserService {
    List<User> showUsers();
    User register(User newUser);
}
