package swp391.group2.learninghub.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import swp391.group2.learninghub.dao.FlashcardDAO;
import swp391.group2.learninghub.dao.FlashcardSetDAO;
import swp391.group2.learninghub.model.FlashcardSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import swp391.group2.learninghub.model.Flashcard;
import swp391.group2.learninghub.model.User;

@Service
public class FlashcardServiceImpl implements FlashcardService {
    @Autowired
    private FlashcardDAO flashcardDAO;
    @Autowired
    private FlashcardSetDAO setDAO;
    @Autowired
    HttpSession session;
    @Autowired
    private FeatureService featureService;

    @Autowired
    public FlashcardServiceImpl(FlashcardDAO flashcardDAO, FlashcardSetDAO setDAO, HttpSession session,
            FeatureService featureService) {
        this.flashcardDAO = flashcardDAO;
        this.setDAO = setDAO;
        this.session = session;
        this.featureService = featureService;
    }

    @Override
    public List<FlashcardSet> showUserFlashcardSetByEmail(String email) {
        return setDAO.showUserFlashcardSetById(email);
    }

    @Override
    public void deleteByCardById(int id) throws Exception {
        try {
            // find if this card is valid
            flashcardDAO.deleteById(id);
        } catch (Exception e) {
            throw new Exception("cannot delete card, reason: " + e.getMessage());
        }
    }

    @Override
    public void archiveSetById(int id) throws Exception {
        // get user information
        User sessionUser = (User) session.getAttribute("user");
        // find if that set is valid
        FlashcardSet target = setDAO.findSetById(id, sessionUser.getEmail());
        if (target == null) {
            throw new Exception("Set can not be found");
        } else {
            target.setActive(false);
            try {
                setDAO.save(target);
            } catch (Exception e) {
                throw new Exception("Unable to update set information");
            }
        }
    }

    @Override
    public FlashcardSet createFlashCardSet(FlashcardSet flashCardSet) {
        // Set the created date to the current date
        flashCardSet.setCreatedDate(Date.valueOf(LocalDate.now()));
        flashCardSet.setActive(true);
        // Set other default values if needed
        // Save the flash card set to the database
        return setDAO.save(flashCardSet);
    }

    @Override
    public Flashcard createUpdate(Flashcard newfc) throws Exception {
        Optional<Flashcard> flashcard = flashcardDAO.findById(newfc.getId());
        if (flashcard.isPresent() && newfc.getSetId() == 0) {
            throw new Exception("set_id not null");
        }
        return flashcardDAO.save(newfc);
    }

    @Override
    public FlashcardSet updateFlashCardSet(FlashcardSet flashCardSet) {
        if (flashCardSet.getId() != 0) {
            Optional<FlashcardSet> f = setDAO.findById(flashCardSet.getId());
            if (f.isEmpty()) {
                return null;
            }
            FlashcardSet newf = f.get();
            flashCardSet.setUserId(newf.getUserId());
            flashCardSet.setCreatedDate(newf.getCreatedDate());
            return setDAO.save(flashCardSet);
        }
        return null;
    }

    @Override
    public List<Flashcard> showFlashCard(int setId) throws Exception {
        if (featureService.findFeatureById(1).isActive()) {
            // lây User từ session
            User user = (User) session.getAttribute("user");

            List<Flashcard> list = flashcardDAO.findFlashCardWithCardSetsAndUser(user.getEmail(), setId);
            if (list == null) {
                throw new Exception("List null");
            }
            return list;

        } else {
            throw new Exception("Feature Card not active");
        }
    }

    @Override
    public boolean setLearn(int id) throws Exception {
        User user = (User) session.getAttribute("user");
        FlashcardSet set = setDAO.findSetById(id, user.getEmail());
        if (user.getEmail() == null) {
            throw new Exception("can not find users");
        }
        if (set == null) {
            throw new Exception("can not find set");
        }
        set.setLearned(!set.isLearned());
        setDAO.save(set);
        return (set.isLearned());
    }

    public void Learn() {
        throw new UnsupportedOperationException();
    }
}
