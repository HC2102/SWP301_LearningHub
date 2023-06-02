package swp391.group2.learninghub.api;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.model.ChangePass;
import swp391.group2.learninghub.model.LoginRequest;
import swp391.group2.learninghub.model.ResponseObject;
import swp391.group2.learninghub.model.User;
import swp391.group2.learninghub.model.sdi.ClientSdi;
import swp391.group2.learninghub.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    HttpSession session;
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public User showCurrentUser(){
        return (User)session.getAttribute("user");
    }
    @GetMapping("/profile")
    public ResponseEntity<ResponseObject> getUserProfile(@Param("email") String email) {
        try {
            User sessionUser = (User) session.getAttribute("user"); /*Session user*/
            if(sessionUser.getEmail().compareToIgnoreCase(email)!=0
                    || sessionUser.getRoleId().compareToIgnoreCase("ADMIN")!=0){
                throw new Exception("Action unauthorized");
            }
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

    @PutMapping("/profile")
    public ResponseEntity<ResponseObject> updateUserProfile(@RequestBody User updatedUser){
        try{
            User sessionUser = (User) session.getAttribute("user"); /*Session user*/
            /*check if updated user is match with session user or session user is admin*/
            if(sessionUser.getEmail().compareToIgnoreCase(updatedUser.getEmail())!=0
                    || sessionUser.getRoleId().compareToIgnoreCase("ADMIN")!=0){
                throw new Exception("Action unauthorized");
            }
            userService.save(updatedUser);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","update user profile successfully",updatedUser));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("fail","can not update user profile",e.getMessage()));
        }
    }
    /*only admin can use this api to show User list*/
    @GetMapping
    public ResponseEntity<ResponseObject> showAll(){
        try{
            User sessionUser = (User) session.getAttribute("user"); /*Session user*/
            if(sessionUser.getRoleId().compareToIgnoreCase("ADMIN")!=0){
                throw new Exception("Action unauthorized");
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","retrieve users information ", userService.showUsers())
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("fail","can not retrieves user information",e.getMessage())
            );
        }
    }

    /*Create new user account based on json sent by client site*/
    @PostMapping("/login")
    ResponseEntity<ResponseObject> userLogin(@RequestBody LoginRequest loginRequest) {
        List<User> u1=userService.findByEmail(loginRequest.getEmail().trim());
        if(u1.get(0).getPassword().trim().equals(loginRequest.getPassword().trim())) {
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

    /*Invalidate current session*/
    @PostMapping("/logout")
    ResponseEntity<ResponseObject> logout(){
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Logout success!",null)
        );
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

    @GetMapping ("/login2")
        ResponseEntity<ResponseObject> userLogin2(@RequestParam("email")String email, @RequestParam("password")String password) {
        List<User> u1=userService.findByEmail(email.trim());
        if(u1.get(0).getPassword().trim().equals(password.trim())) {
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

    @PutMapping("/password")
    String changePass(@RequestBody ChangePass changePass) {
        User u=(User) session.getAttribute("user");
        if(u.getPassword().trim().equals(changePass.getOldpass().trim())) {
            if(changePass.getNewpass().trim().equals("")||changePass.getVerpass().trim().equals("")){
                return  "verification password and new password are not blank";
            }
            else if(changePass.getNewpass().trim().equals(changePass.getVerpass().trim())) {
                User userUpdate=new User(u.getEmail(),u.getRealName(),u.getPhoneNum(),changePass.getNewpass(),u.getRoleId(),
                        u.isActive(),u.getSignupDate());
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
    @PostMapping(value = "/password")
    public ResponseEntity<ResponseObject> forgetPassWord(
            @RequestParam(name="email",required = false,defaultValue = "") String email) {
        List<User> u=userService.findByEmail(email);
        if(u.size()>0) {
            ClientSdi sdi=new ClientSdi(u.get(0).getRealName(),email,email);
            userService.create(sdi);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Send email success!",sdi)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Email not found!",""));
        }
    }
}