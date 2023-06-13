package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.BoardLabel;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardLabelDAO extends JpaRepository<BoardLabel,Integer> {
    void deleteById(Long id);

    Optional<BoardLabel> findById(Long id);

    List<BoardLabel> findAllByBoardId(Long boardId);
}
