package swp.group2.learninghub.service;

import swp.group2.learninghub.model.Note;

import java.util.List;

public interface NoteService {
    public Note createNote(Note note);
    public List<Note> showUserNotesByEmail(String email);

    public Note updateNote(Note note);

    public void removeNoteById(int id);

    public Note getNoteById(int noteId);

    public int getMaxBoardIdByEmail(String email);
}
