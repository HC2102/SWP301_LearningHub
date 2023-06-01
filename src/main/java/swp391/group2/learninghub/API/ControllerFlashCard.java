package swp391.group2.learninghub.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.group2.learninghub.Model.Flashcard;
import swp391.group2.learninghub.Model.ResponseObject;
import swp391.group2.learninghub.Service.FlashCardService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flashcard")
public class ControllerFlashCard {

    @Autowired
    private FlashCardService  flashCardService;

    @GetMapping
    public List<Flashcard> showAll(){
        return flashCardService.showAllFlashCard();
    }

    @PostMapping("/create")
    ResponseEntity<ResponseObject> flashCardCreate(@RequestBody Flashcard flashcard){
        try{

            flashCardService.create(flashcard);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","Create FlashCard successfull",flashcard)
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject("fail","create fail, reason: "+e.getMessage(),null)
            );
        }
    }

    @GetMapping("/show")
    ResponseEntity<ResponseObject> showFlashCard( @RequestParam(name="set_id",required = false,defaultValue = "")
                                                  int set_id,@RequestParam(name="email",required = false,
            defaultValue = "") String email){
        try{

            List<Flashcard> fl=flashCardService.showFlashCard(set_id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","Show FlashCard successfull",fl)
            );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject("fail","Show fail, reason: "+e.getMessage(),null)
            );
        }
    }


}
