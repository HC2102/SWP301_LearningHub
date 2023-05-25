package swp391.group2.learninghub.API;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.DAO.UserDAO;
import swp391.group2.learninghub.Model.ChangePass;
import swp391.group2.learninghub.Model.LoginRequest;
import swp391.group2.learninghub.Model.ResponseObject;
import swp391.group2.learninghub.Model.User;
import swp391.group2.learninghub.Service.UserService;
import swp391.group2.learninghub.Service.UserServiceImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    HttpSession session ;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> showAll(){
        return userService.showUsers();
    }
    @PostMapping("/register")
    ResponseEntity<ResponseObject> userRegister(@RequestBody User newUser){
        try{

            userService.register(newUser);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","account registed successfull",newUser)
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject("fail","account fail to add, reason: "+e.getMessage(),null)
            );
        }
    }
    @GetMapping ("/login")
    ResponseEntity<ResponseObject> userLogin(@RequestBody LoginRequest loginRequest) {
        List<User> u1=userService.findByEmail(loginRequest.getEmail().trim());
        if(u1.get(0).getPassword().trim().equals(loginRequest.getPass().trim())) {
            session.setAttribute("user",u1.get(0));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Login Successful!", u1)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Email or PassWord invalid!",
                        "")
        );
    }

    @PutMapping("/changepassword")
    String changePass(@RequestBody ChangePass changePass) {
        User u=(User) session.getAttribute("user");
        System.out.println(u.toString());
        if(u.getPassword().trim().equals(changePass.getOldpass().trim())) {
            if(changePass.getNewpass().trim().equals("")||changePass.getVerpass().trim().equals("")){
                return  "verification password and new password are not blank";
            }
            else if(changePass.getNewpass().trim().equals(changePass.getVerpass().trim())) {
                User userUpdate=new User(u.getEmail(),u.getReal_name(),u.getPhone_num(),changePass.getNewpass(),u.getRoleId(),
                        u.isActive(),u.getSignup_date());
                userService.save(userUpdate);
                session.setAttribute("user",userUpdate);
                return "change password successful!";
            }else {
                return "verification password and new password are not the same";
            }
        }else {
            return "Password not correct!";
        }
    }
}
