package swp391.group2.learninghub.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.group2.learninghub.Model.Flashcard;
import swp391.group2.learninghub.Model.FlashcardSet;

@Repository
public interface FlashcardDAO extends JpaRepository<Flashcard,Integer> {
    public boolean deleteById(int id);
}
