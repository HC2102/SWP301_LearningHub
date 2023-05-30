package swp391.group2.learninghub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.group2.learninghub.DAO.FlashCardSetDAO;
import swp391.group2.learninghub.Model.FlashcardSet;

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
    }
}
