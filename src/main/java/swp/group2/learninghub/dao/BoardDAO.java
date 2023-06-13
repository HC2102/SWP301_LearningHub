package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.group2.learninghub.model.Board;

public interface BoardDAO extends JpaRepository<Board,Integer> {
}
