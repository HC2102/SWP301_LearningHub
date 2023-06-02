package swp391.group2.learninghub.service;

import swp391.group2.learninghub.model.Flashcard;
import swp391.group2.learninghub.model.FlashcardSet;

import java.util.List;

public interface FlashcardService {
    public List<FlashcardSet> showUserFlashcardSetById(String email);
    public void deleteByCardById(int id) throws Exception;
    FlashcardSet createFlashCardSet(FlashcardSet flashCardSet);
    public List<Flashcard> showFlashCard(int set_id) throws Exception;
    public List<Flashcard> showAllFlashCard();
    public Flashcard create(Flashcard newfc) throws Exception;

    public void archiveSetById(int id)throws Exception;
}
