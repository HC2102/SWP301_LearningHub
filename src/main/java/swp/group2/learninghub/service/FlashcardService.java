package swp.group2.learninghub.service;

import java.util.List;

import swp.group2.learninghub.model.Flashcard;
import swp.group2.learninghub.model.FlashcardSet;

public interface FlashcardService {
    public List<FlashcardSet> showUserFlashcardSetByEmail(String email);

    public void deleteByCardById(int id) throws Exception;

    FlashcardSet createFlashCardSet(FlashcardSet flashCardSet);

    public List<Flashcard> showFlashCard(int setId) throws Exception;

    public Flashcard createUpdate(Flashcard newfc) throws Exception;

    FlashcardSet updateFlashCardSet(FlashcardSet flashCardSet);

    public void archiveSetById(int id) throws Exception;

    public boolean setLearn(int id) throws Exception;
}
