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
        return  noteDAO.save(note);
    }
    @Override
    public Note findNoteById(int id) {
        Optional<Note> n=noteDAO.findById(id);
        if (n.isEmpty()) {
            return null;
        }
        Note note=n.get();
        return note;
    }

    @Override
    public void updateNote(Note note) {
        Optional<Note> n=noteDAO.findById(note.getId());
        if(n.isEmpty()) {
            throw new IllegalArgumentException("Not found note");
        }
        Note newNote=n.get();
        newNote.setDescription(note.getDescription());
        newNote.setTitle(note.getTitle());
        noteDAO.save(newNote);
    }

    @Override
    public List<Note> showUserNotesByEmail(String email) {
        return noteDAO.showUserNotesByEmail(email);
    }

    @Override
    public void removeNoteById(int id) {
        noteDAO.removeNoteById(id);
    }

    public Note getNoteById(int noteId) {return noteDAO.getNoteById(noteId);}

    @Override
    public int getMaxBoardIdByEmail(String email) {
        return noteDAO.getMaxNoteIdByUsername(email);
    }
}
