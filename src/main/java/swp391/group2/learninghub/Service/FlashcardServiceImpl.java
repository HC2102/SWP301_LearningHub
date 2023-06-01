package swp391.group2.learninghub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.FlashcardDAO;
import swp391.group2.learninghub.DAO.FlashcardSetDAO;
import swp391.group2.learninghub.Model.FlashcardSet;

import java.util.List;

@Service
public class FlashcardServiceImpl implements FlashcardService{
    @Autowired
    private FlashcardDAO flashcardDAO;
    @Autowired
    private FlashcardSetDAO setDAO;

    public FlashcardServiceImpl(FlashcardDAO flashcardDAO, FlashcardSetDAO setDAO) {
        this.flashcardDAO = flashcardDAO;
        this.setDAO = setDAO;
    }

    @Autowired
    public String test(){
        return "service connect success";
    }
    @Override
    public List<FlashcardSet> showUserFlashcardSetById(String email) {
        return setDAO.showUserFlashcardSetById(email);
    }

    @Override
    public boolean deleteByCardById(int id) throws Exception {
        try{
            //find if this card is valid
            flashcardDAO.deleteById(id);
            return true;
        }catch (Exception e){
            throw new Exception("cannot delete card, reason: "+e.getMessage());
        }
    }

    @Override
    public FlashcardSet createFlashCardSet(FlashcardSet flashCardSet) {
        return setDAO.createFlashCardSet(flashCardSet);
    }
}
