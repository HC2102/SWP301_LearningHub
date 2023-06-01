package swp391.group2.learninghub.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.group2.learninghub.Model.FlashcardSet;

import java.util.List;
@Repository
public interface FlashcardSetDAO extends JpaRepository<FlashcardSet,Integer> {
    @Query("select s from FlashcardSet s where s.user_id= :email AND s.isActive= true")
    public List<FlashcardSet> showUserFlashcardSetById(@Param("email")String email);

    FlashcardSet createFlashCardSet(FlashcardSet flashCardSet);
}
