package swp391.group2.learninghub.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.FlashCardSetDAO;
import swp391.group2.learninghub.Model.FlashcardSet;

<<<<<<< Updated upstream
import java.sql.Date;
import java.time.LocalDate;

@Service
public class FlashCardServiceImpl implements FlashCardSetService {
    @Autowired
    private FlashCardSetDAO flashCardSetDAO;
    @Autowired
    public FlashCardServiceImpl(FlashCardSetDAO flashCardSetDAO) {
        this.flashCardSetDAO = flashCardSetDAO;
    }

    @Override
    public FlashcardSet createFlashCardSet(FlashcardSet flashCardSet) {
        // Set the created date to the current date
        flashCardSet.setCreatedDate(Date.valueOf(LocalDate.now()));
        flashCardSet.setActive(true);
        // Set other default values if needed
        // Save the flash card set to the database
        return flashCardSetDAO.save(flashCardSet);
=======
import swp391.group2.learninghub.Model.Flashcard;
import swp391.group2.learninghub.Model.User;


import java.util.List;

@Service
public class FlashCardServiceImpl implements  FlashCardService {
    @Autowired
    private FlashCardDAO fl;

    @Autowired
    HttpSession session;

    @Autowired
    private FeatureService featureService;

    @Override
    public Flashcard create(Flashcard newfc) throws Exception {
        if (newfc.getSet_id() == 0) {
            throw new Exception("set_id not null");
        }
        newfc.set_Learned(false);
        return fl.save(newfc);
    }

    @Override
    public List<Flashcard> showAllFlashCard() {
        return fl.findAll();
    }

    @Override
    public List<Flashcard> showFlashCard(int set_id) throws Exception {
        if (featureService.findFeature(1).isIs_Active()) {
            //lây User từ session
            User user = (User) session.getAttribute("user");

            List<Flashcard> list = fl.findFlashCardWithCardSetsAndUser(user.getEmail(), set_id);
            if (list == null) {
                throw new Exception("List null");
            }
            return list;

        } else {
            throw new Exception("Feature Card not active");
        }

>>>>>>> Stashed changes
    }
}
