package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import swp.group2.learninghub.model.CoreLabel;

public interface CoreLabelDAO extends JpaRepository<CoreLabel,Integer> {
}
