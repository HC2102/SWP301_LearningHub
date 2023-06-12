package swp391.group2.learninghub.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.dao.UserDAO;
import swp391.group2.learninghub.model.DataMailDTO;
import swp391.group2.learninghub.model.User;
import swp391.group2.learninghub.model.sdi.ClientSdi;
import swp391.group2.learninghub.utils.Const;
import swp391.group2.learninghub.utils.DataUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    @Autowired
    private MailService mailService;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(User newUser) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String emailRegex = "^(.+)@(\\S+)$";
        String phoneRegex = "^\\d{10}$";
        String passRegex = "^(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        newUser.setActive(true);
        newUser.setRoleId("USER");
        newUser.setSignupDate(Date.from(now.toInstant(ZoneOffset.UTC)));
        // check if fields are valid
        if (userDAO.existsById(newUser.getEmail())) {
            throw new Exception("email is already in use");
        }
        if (!newUser.getEmail().matches(emailRegex) || !newUser.getPhoneNum().matches(phoneRegex)) {
            throw new Exception("input field are not in right format");
        }
        if (!newUser.getPassword().trim().matches(passRegex)) {
            throw new Exception("password is not in right format");
        }
        return userDAO.save(newUser);
    }

    @Override
    public List<User> showUsers() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findByEmail(String name) {
        return userDAO.findByEmail(name);
    }

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
            String pass = DataUtils.generateTempPwd(6);
            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            props.put("password", pass);
            dataMail.setProps(props);
            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            List<User> u = findByEmail(sdi.getUsername());
            u.get(0).setPassword(pass);
            save(u.get(0));
            return true;
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(User newUser) {
        userDAO.save(newUser);
    }

    @Override
    public void deactivate(String target) throws Exception {
        Optional<User> optionalUser = userDAO.findById(target);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
             try{
            userDAO.save(user);
             }catch(Exception e){
             throw new Exception("unable to change status: "+e.getMessage());
             }
        } else {
            throw new Exception("email can not be found");
        }
    }
}
