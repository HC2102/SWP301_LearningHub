package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.Column;

@Repository
public interface ColumnDAO extends JpaRepository<Column,Integer> {
}
