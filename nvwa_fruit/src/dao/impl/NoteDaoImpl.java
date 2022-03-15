package dao.impl;

import dao.NoteDao;
import dbc.DatabaseConnection;
import valuebean.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoteDaoImpl implements NoteDao {
    DatabaseConnection dbc=null;
    public NoteDaoImpl(){
        dbc=new DatabaseConnection();
    }
    public List seletAll(){
        List list=new ArrayList();
        String sql="select * from note";
        ResultSet rs=dbc.exeQuery(sql);
        Note note=null;
        try{
            while(rs.next()){
                note=new Note();
                note.setNoteId(rs.getInt(1));
                note.setPersonName(rs.getString(2));
                note.setTitle(rs.getString(3));
                note.setContent(rs.getString(4));
                note.setPublishdate(rs.getString(5));
                list.add(note);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(int id){
        String sql="delete from note where noteId="+id;
        return dbc.exeUpdate(sql);
    }

    public boolean addNote(String title,String content,String personName){
        String sql="insert into note(personName,title,content,publishdate)"+"values(?,?,?,?)";
        Calendar calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        return dbc.exeUpdate(sql,personName,title,content,pdate);
    }

    public Note selectSingle(String id){
        String sql="select * from note where noteId="+id;
        ResultSet rs=dbc.exeQuery(sql);
        Note note=null;
        try{
            if(rs.next()){
                note=new Note();
                note.setNoteId(rs.getInt(1));
                note.setPersonName(rs.getString(2));
                note.setTitle(rs.getString(3));
                note.setContent(rs.getString(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return note;
    }

    public boolean updateNote(String title,String content,String noteId){
        String sql="update note set title=?,content=? where noteId=?";
        return dbc.exeUpdate(sql,title,content,noteId);
    }
}
