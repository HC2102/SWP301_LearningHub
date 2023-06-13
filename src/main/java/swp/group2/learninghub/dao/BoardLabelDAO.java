package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.group2.learninghub.model.BoardLabel;

public interface BoardLabelDAO extends JpaRepository<BoardLabel,Integer> {
}
