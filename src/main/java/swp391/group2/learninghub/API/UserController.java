package swp391.group2.learninghub.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
