package dao;

import valuebean.Note;

import java.util.List;

public interface NoteDao {
    public abstract boolean updateNote(String title, String content, String noteId);
    public abstract Note selectSingle(String id);
    public abstract boolean addNote(String title, String content, String personName);
    public abstract boolean delete(int id);
    public abstract List seletAll();
}
