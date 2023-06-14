package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.Card;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CardDAO extends JpaRepository<Card,Integer> {

    public List<Card> findCardsByColumnId(int ColumnId);
}

