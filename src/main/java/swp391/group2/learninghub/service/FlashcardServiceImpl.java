package swp391.group2.learninghub.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import swp391.group2.learninghub.dao.FlashcardDAO;
import swp391.group2.learninghub.dao.FlashcardSetDAO;
import swp391.group2.learninghub.model.FlashcardSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
    public FlashcardServiceImpl(FlashcardDAO flashcardDAO, FlashcardSetDAO setDAO, HttpSession session, FeatureService featureService) {
        this.flashcardDAO = flashcardDAO;
        this.setDAO = setDAO;
        this.session = session;
        this.featureService = featureService;
    }

    public String test(){
        return "service connect success";
    }
    @Override
    public List<FlashcardSet> showUserFlashcardSetByEmail(String email) {
        return setDAO.showUserFlashcardSetById(email);
    }

    @Override
    public void deleteByCardById(int id) throws Exception {
        try{
            //find if this card is valid
            flashcardDAO.deleteById(id);
        }catch (Exception e){
            throw new Exception("cannot delete card, reason: "+e.getMessage());
        }
    }

    @Override
    public void archiveSetById(int id) throws Exception {
        //get user information
        User sessionUser = (User)session.getAttribute("user");
        //find if that set is valid
        FlashcardSet target = setDAO.findSetById(id,sessionUser.getEmail());
        if(target == null){
            throw new Exception("Set can not be found");
        }else{
            target.setActive(false);
            try{
                setDAO.save(target);
            }catch(Exception e){
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
    public Flashcard create(Flashcard newfc) throws Exception {
        if (newfc.getSetId() == 0) {
            throw new Exception("set_id not null");
        }
        newfc.setLearned(false);
        return flashcardDAO.save(newfc);
    }

    @Override
    public List<Flashcard> showFlashCard(int set_id) throws Exception {
        if (featureService.findFeatureById(1).isActive()) {
            //lây User từ session
            User user = (User) session.getAttribute("user");

            List<Flashcard> list = flashcardDAO.findFlashCardWithCardSetsAndUser(user.getEmail(), set_id);
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
        User user = (User)session.getAttribute("user");
        FlashcardSet set = setDAO.findSetById(id,user.getEmail());
        if(user.getEmail() == null){
            throw new Exception("can not find users");
        }
        if(set == null){
            throw new Exception("can not find set");
        }
        set.setLearned(!set.isLearned());
        setDAO.save(set);
        return(set.isLearned());
    }
}
