package swp.group2.learninghub.api;

import jakarta.servlet.http.HttpSession;
import swp.group2.learninghub.model.*;
import swp.group2.learninghub.service.FeatureService;
import swp.group2.learninghub.service.FlashcardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping("/api/v1/flashcard")
public class FlashcardController {
    /* Variable and constructor section */
    private static final int FEATURE_ID = 1; /* Feature code */
    private static final String SUCCESSMSG = "Success";
    private static final String FAILMSG = "Fail";
    @Autowired
    HttpSession session;
    @Autowired
    private FlashcardService flashcardService; /* Calling flashcard service */
    @Autowired
    private FeatureService featureService; /* Calling feature service to check if this service is enabled */

    /* testing the connection */
    @GetMapping("/test")
    public String test() {
        return SUCCESSMSG;
    }

    /* for flashcard set services */
    @GetMapping("/set")
    public ResponseEntity<ResponseObject> showAllSet() {
        try {
            /* user service information */
            User userSession = (User) session.getAttribute("user");
            /* check if user information still in the session */
            if (userSession == null) {
                throw new IllegalArgumentException("can not find user information for this feature");
            }
            /* Check to see if the feature is active */
            isFeatureActive();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    SUCCESSMSG, "retrieve flashcard set of " +
                            userSession.getEmail(),
                    flashcardService.showUserFlashcardSetByEmail(userSession.getEmail())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, "account fail to connect: " + e.getMessage(), null));
        }
    }

    @PostMapping("/set")
    public ResponseEntity<ResponseObject> createFlashCardSet(@RequestBody FlashcardSet flashCardSet) {
        try {
            FlashcardSet createdSet = flashcardService.createFlashCardSet(flashCardSet);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(SUCCESSMSG,
                    "ok to create Flash Card Set", createdSet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject(FAILMSG,
                    "Failed to create Flash Card Set: " + e.getMessage(), null));
        }
    }

    @PutMapping("/set")
    public ResponseEntity<ResponseObject> updateFlashCardSet(@RequestBody FlashcardSet flashCardSet) {
        try {
            FlashcardSet updatedSet = flashcardService.updateFlashCardSet(flashCardSet);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(SUCCESSMSG, "ok to update Flash Card Set", updatedSet));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject(FAILMSG, "Failed to update Flash Card Set: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/set")
    ResponseEntity<ResponseObject> archiveSet(@RequestParam(name = "id") int setId) {
        try {
            flashcardService.archiveSetById(setId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(SUCCESSMSG, "archive set successfull", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(FAILMSG, "Can not archive set", e.getMessage()));
        }
    }

    @PutMapping("/learn")
    ResponseEntity<ResponseObject> setLearned(@RequestParam("id") int setId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(SUCCESSMSG, "change status successfully",
                            flashcardService.setLearn(setId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(FAILMSG, "can not set the status to set", e.getMessage()));
        }
    }

    /* for flashcard card services */
    @GetMapping("/card")
    ResponseEntity<ResponseObject> showFlashCard(
            @RequestParam(name = "id", required = false, defaultValue = "") int setId) {
        try {
            List<Flashcard> fl = flashcardService.showFlashCard(setId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(SUCCESSMSG, "Show FlashCard successfull", fl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, "Show fail, reason: " + e.getMessage(), null));
        }
    }

    @PostMapping("/card")
    ResponseEntity<ResponseObject> flashCardCreate(@RequestBody Flashcard flashcard) {
        try {
            flashcardService.createUpdate(flashcard);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(SUCCESSMSG, "Create or Update FlashCard successfull", flashcard));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, "Create or Update fail, reason: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/card")
    public ResponseEntity<ResponseObject> deleteById(@RequestParam int id) {
        try {
            flashcardService.deleteByCardById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(SUCCESSMSG, "deleted card: " + id, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseObject(FAILMSG, "cannot deleted card: " + id + " reason: " + e.getMessage(), null));
        }
    }

    /* Miscellaneous function */
    private void isFeatureActive() {
        Feature feature = featureService.findFeatureById(FEATURE_ID);
        if (!feature.isActive()) {
            throw new IllegalArgumentException("Feature is disable: " + feature.getDescription());
        }
    }

}
