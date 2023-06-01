package swp391.group2.learninghub.Service;

import swp391.group2.learninghub.Model.FlashcardSet;

import java.util.List;

public interface FlashcardService {
    public String test();
    public List<FlashcardSet> showUserFlashcardSetById(String email);
    public boolean deleteByCardById(int id) throws Exception;
    FlashcardSet createFlashCardSet(FlashcardSet flashCardSet);
}
