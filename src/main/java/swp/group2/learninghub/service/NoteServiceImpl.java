package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.NoteDAO;
import swp.group2.learninghub.model.Note;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{
    @Autowired
    public NoteDAO noteDAO;
    @Override
    public void createNote(Note note) {
        noteDAO.save(note);
    }

    @Override
    public List<Note> showUserNotesByEmail(String email) {
        return noteDAO.showUserNotesByEmail(email);
    }
}
