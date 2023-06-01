package swp391.group2.learninghub.API;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.DAO.SessionManager;
import swp391.group2.learninghub.DAO.UserDAO;
import swp391.group2.learninghub.Model.ChangePass;
import swp391.group2.learninghub.Model.LoginRequest;
import swp391.group2.learninghub.Model.ResponseObject;
import swp391.group2.learninghub.Model.User;
import swp391.group2.learninghub.Service.UserService;
import swp391.group2.learninghub.Service.UserServiceImpl;
import swp391.group2.learninghub.Model.UserSession;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        System.out.println(u.toString());
=======

>>>>>>> Stashed changes
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



@GetMapping("/{email}")
public ResponseEntity<ResponseObject> getUserProfile(@PathVariable("email") String email) {
    try {
        List<User> users = userService.findByEmail(email);
        if (!users.isEmpty()) {
            User user = users.get(0);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Get user profile success", user)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "User not found", null)
            );
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("error", "Failed to retrieve user profile: " + e.getMessage(), null)
        );
    }
}

    @Autowired
    private SessionManager sessionManager;

    @GetMapping("/logout")
    public ResponseEntity<ResponseObject> logout() {
        String sessionId = session.getId();
        sessionManager.invalidateSession(sessionId);

        session.invalidate();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Logout successful", null)
        );
    }

    @GetMapping("/protected")
    public ResponseEntity<ResponseObject> protectedRoute() {
        String sessionId = session.getId();
        UserSession userSession = sessionManager.getSession(sessionId);
        if (userSession != null && userSession.isValid()) {
            userSession.setLastActivityTime(LocalDateTime.now());

            // Perform protected operations here
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Protected Route - User: " + userSession.getEmail(), null)
            );
        } else {
            session.invalidate();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("fail", "Session expired. Please login again.", null)
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> userlogin(@RequestBody LoginRequest loginRequest) {
        List<User> users = userService.findByEmail(loginRequest.getEmail().trim());
        if (!users.isEmpty() && users.get(0).getPassword().trim().equals(loginRequest.getPass().trim())) {
            User user = users.get(0);

            UserSession userSession = new UserSession();
            userSession.setEmail(user.getEmail());
            userSession.setFeatureStatus(user.getRole());
            userSession.setLastActivityTime(LocalDateTime.now());

            String sessionId = session.getId();
            sessionManager.createSession(sessionId, userSession);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Login Successful!", user)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Email or Password invalid!", null)
            );
        }
    }


}
