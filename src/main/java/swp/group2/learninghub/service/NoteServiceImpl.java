package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.NoteDAO;
import swp.group2.learninghub.model.Note;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{
    @Autowired
    public NoteDAO noteDAO;
    @Override
    public Note createNote(Note note) {
        note.setCreatedDate(Date.valueOf(LocalDate.now()));
        return noteDAO.save(note);
    }

    @Override
    public List<Note> showUserNotesByEmail(String email) {
        return noteDAO.showUserNotesByEmail(email);
    }
}
