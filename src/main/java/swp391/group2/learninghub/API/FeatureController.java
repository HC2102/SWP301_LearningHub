package swp391.group2.learninghub.API;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.Model.Feature;
import swp391.group2.learninghub.Model.ResponseObject;
import swp391.group2.learninghub.Model.User;
import swp391.group2.learninghub.Service.FeatureService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feature")
public class FeatureController {
    @Autowired
    HttpSession session ;
    @Autowired
    private final FeatureService service;
    @Autowired
    public FeatureController(FeatureService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test(){
        try{
            User testUser = (User)session.getAttribute("user");
            return "connection success" + testUser.toString();
        }catch (Exception e){
            return "connection success";
        }
    }
    @GetMapping("")
    public List<Feature> showAll(){
        try{
            User testUser = (User)session.getAttribute("user");
            return service.showAll();
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping("/active")
    ResponseEntity<ResponseObject> setActive(@RequestParam("id") int featureId, @RequestParam("mess")String message){
        try {
            service.setActive(featureId,message);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject
                    ("Success","Active status changed successfully feature id: "+featureId,message.toString()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject
                    ("Fail","Fail to change active status reason: "+e.getMessage(),message.toString()));
        }
    }

}
