package swp391.group2.learninghub.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp391.group2.learninghub.Model.FlashcardSet;
import swp391.group2.learninghub.Model.ResponseObject;
import swp391.group2.learninghub.Service.FlashCardSetService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/flashcard-set")
public class FlashCardSetController {
    @Autowired
    private FlashCardSetService flashCardSetService;
    @GetMapping("/test")
    public String test(){
        return "success";
    }
    @PostMapping("/")
    public ResponseEntity<ResponseObject> createFlashCardSet(@RequestBody FlashcardSet flashCardSet) {
        try {
            FlashcardSet createdSet = flashCardSetService.createFlashCardSet(flashCardSet);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","ok to create Flash Card Set",createdSet));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail","Failed to create Flash Card Set: " + e.getMessage(),null));
        }
    }
}
