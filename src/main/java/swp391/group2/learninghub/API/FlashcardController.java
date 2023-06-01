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
import swp391.group2.learninghub.Service.FlashcardService;

@RestController
@RequestMapping("/api/v1/flashcard")
public class FlashcardController {
    private final int FEATURE_ID = 1;
    @Autowired
    HttpSession session;
    @Autowired
    private FlashcardService flashcardService;
    @Autowired
    private FeatureService featureService;
    @GetMapping("/dashboard")
    public ResponseEntity<ResponseObject> dashboard(){
        try{
            //user service information
            User userSession = (User)session.getAttribute("user");
            //check if user information still in the session
            if(userSession== null){
                throw new Exception("can not find user information for this feature");
            }
            if(!isFeatureActive().isActive()){
                throw new Exception("feature is disable because "+isFeatureActive().getDescription());
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "Success","retrieve flashcard set of "+userSession.getEmail(),flashcardService.showUserFlashcardSetById(userSession.getEmail())));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject("fail","account fail to connect: "+e.getMessage(),null)
            );
        }
    }
    private Feature isFeatureActive(){
        return featureService.findFeatureById(FEATURE_ID);
    }
    @DeleteMapping("/card")
    public ResponseEntity<ResponseObject> deleteById(@RequestParam int id){
        try{
            flashcardService.deleteByCardById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "deleted card: " + id, null));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseObject("fail","cannot deleted card: "+id+" reason: "+e.getMessage(),null)
            );
        }

    }
}
