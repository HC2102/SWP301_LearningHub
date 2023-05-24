package swp391.group2.learninghub.Service;

import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.UserDAO;
import swp391.group2.learninghub.Model.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(User newUser) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String emailRegex = "^(.+)@(\\S+)$";
        String phoneRegex = "^\\d{10}$";
        String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        newUser.setActive(true);
        newUser.setRoleId("USER");
        newUser.setSignup_date(Date.from(now.toInstant(ZoneOffset.UTC)));
        //check if fields are valid
        if(userDAO.existsById(newUser.getEmail())){
            throw new Exception("email is already in use");
        }
        if(!newUser.getEmail().matches(emailRegex)||!newUser.getPhone_num().matches(phoneRegex)){
            throw new Exception("input field are not in right format");
        }
        if(!newUser.getPassword().trim().matches(passRegex)){
            throw new Exception("password is not in right format");
        }
        return userDAO.save(newUser);
    }
    @Override
    public List<User> showUsers() {
        return userDAO.findAll();
    }
}
