package swp391.group2.learninghub.API;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.Model.ChangePass;
import swp391.group2.learninghub.Model.LoginRequest;
import swp391.group2.learninghub.Model.ResponseObject;
import swp391.group2.learninghub.Model.User_Table;
import swp391.group2.learninghub.Service.UserService;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user")
public class ControllerUser {
    @Autowired
    private UserService service;
    @Autowired
    HttpSession session ;

    @GetMapping("")
    List<User_Table> getAllUsers() {
        return  service.findAll();
        //you must save this to database,Now we have H2 database=In-memory Database
    }
    @GetMapping ("/login")
    ResponseEntity<ResponseObject> userLogin(@RequestBody LoginRequest loginRequest) {

        List<User_Table> u1=service.findByEmail(loginRequest.getEmail().trim());
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
        User_Table u=(User_Table) session.getAttribute("user");
        if(u.getPassword().trim().equals(changePass.getOldpass().trim())) {
            if(changePass.getNewpass().trim().equals("")||changePass.getVerpass().trim().equals("")){
                return  "verification password and new password are not blank";
            }
            else if(changePass.getNewpass().trim().equals(changePass.getVerpass().trim())) {
                    User_Table userUpdate=new User_Table(u.getEmail(),u.getReal_name(),u.getPhone_num(),u.getRole_id(),
                            changePass.getNewpass(),u.is_Active(),u.getSignup_date());
                    service.save(userUpdate);
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
