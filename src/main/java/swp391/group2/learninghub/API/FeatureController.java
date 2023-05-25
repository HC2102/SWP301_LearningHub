package swp391.group2.learninghub.API;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.group2.learninghub.Model.Feature;
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

}
