package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.Card;
@Repository
public interface CardDAO extends JpaRepository<Card,Integer> {
}
