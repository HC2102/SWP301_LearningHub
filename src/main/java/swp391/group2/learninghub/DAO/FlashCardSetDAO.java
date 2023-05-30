package swp391.group2.learninghub.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.group2.learninghub.Model.FlashcardSet;
@Repository
public interface FlashCardSetDAO extends JpaRepository<FlashcardSet,Integer> {
}
