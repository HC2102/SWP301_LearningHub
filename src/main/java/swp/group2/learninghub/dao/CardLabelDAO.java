package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.model.CardLabel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardLabelDAO extends JpaRepository<BoardLabel,Integer> {
    void deleteById(Long id);
    CardLabel save(CardLabel label);
    Optional<CardLabel> findById(Long id);

    List<CardLabel> findAllByCardId(Long cardId);
}
