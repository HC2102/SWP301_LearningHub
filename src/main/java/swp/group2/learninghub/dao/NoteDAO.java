package swp.group2.learninghub.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp.group2.learninghub.model.Note;

import java.util.List;

@Repository
public interface NoteDAO extends JpaRepository<Note,Integer> {
    @Query("SELECT n FROM Note n where n.userId=:email")
    public List<Note> showUserNotesByEmail(@Param("email") String email);

    public void removeNoteById(int id);
}
