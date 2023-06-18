package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.NoteDAO;
import swp.group2.learninghub.model.Note;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    public NoteDAO noteDAO;

    @Override
    public Note createNote(Note note) {
        note.setCreatedDate(Date.valueOf(LocalDate.now()));
        return noteDAO.save(note);
    }
    @Override
    public Note updateNote(Note note) {
        if (note.getId() != 0) {
            Optional<Note> n = noteDAO.findById(note.getId());
            if (n.isEmpty()) {
                return null;
            }
            Note newNote = n.get();
            note.setTitle(newNote.getTitle());
            note.setDescription(newNote.getDescription());
            note.setCreatedDate(Date.valueOf(LocalDate.now()));
            return noteDAO.save(note);
        }
        return null;
    }

    @Override
    public List<Note> showUserNotesByEmail(String email) {
        return noteDAO.showUserNotesByEmail(email);
    }

    @Override
    public void removeNoteById(int id) {
        noteDAO.removeNoteById(id);
    }
}
