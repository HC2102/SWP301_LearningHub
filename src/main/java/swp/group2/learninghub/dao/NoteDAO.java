package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.Note;
@Repository
public interface NoteDAO extends JpaRepository<Note,Integer> {
}
