package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.BoardLabel;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardLabelDAO extends JpaRepository<BoardLabel,Integer> {

    void deleteById(int id);
    Optional<BoardLabel> findById(int id);
    List<BoardLabel> findAllByBoardId(int boardId);
    @Query("SELECT bl FROM BoardLabel bl JOIN bl.cards c WHERE c.id = :cardId")
    List<BoardLabel> findAllByCardId(int cardId);
    @Query("SELECT COUNT(bl) FROM BoardLabel bl WHERE bl.cards IS NOT EMPTY")
    int countLabelsByCard();
}
