package swp.group2.learninghub.service;

import swp.group2.learninghub.model.Note;

import java.util.List;

public interface NoteService {
    public Note createNote(Note note);
    public List<Note> showUserNotesByEmail(String email);
}
