package swp391.group2.learninghub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.UserDAO;
import swp391.group2.learninghub.Model.User;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(User newUser) {
        return userDAO.save(newUser);
    }

    @Override
    public List<User> showUsers() {
        return userDAO.findAll();
    }
}
