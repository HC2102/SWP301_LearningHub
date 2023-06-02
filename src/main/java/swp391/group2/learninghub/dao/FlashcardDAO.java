package swp391.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391.group2.learninghub.model.Flashcard;

import java.util.List;


public interface FlashcardDAO extends JpaRepository<Flashcard,Integer> {
//    List<Flashcard> findBySet_id(int set_id);
//
    @Query(value = "SELECT * FROM flashcard fl WHERE fl.set_id = :set_id AND " +
            "fl.set_id IN (SELECT id FROM flashcard_set f WHERE f.user_id = :userId)", nativeQuery = true)
    List<Flashcard> findFlashCardWithCardSetsAndUser(@Param("userId") String userId, @Param("set_id") int setId);



}
