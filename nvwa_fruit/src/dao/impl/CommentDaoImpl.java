package dao.impl;

import dao.CommentDao;
import dbc.DatabaseConnection;
import valuebean.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private DatabaseConnection dbc=null;
    public CommentDaoImpl(){
        dbc=new DatabaseConnection();
    }

    @Override
    public boolean addComment(int idorder,int iduser,int idmerchandise,String commnet,String date,String evaluate) {
        String sql="insert into nvwa_fruit.comment(idorder,iduser,idmerchandise,comment,date,evaluate)"+"values(?,?,?,?,?,?)";
        return dbc.exeUpdate(sql,idorder,iduser,idmerchandise,commnet,date,evaluate);
    }


    @Override
    public boolean deleteComment(int idcomment) {
        String sql="delete from comment where idcomment="+idcomment;
        return dbc.exeUpdate(sql);
    }

    @Override
    public List selectAll() {
        List list=new ArrayList();
        String sql="select * from comment";
        ResultSet rs=dbc.exeQuery(sql);
        Comment comment=null;
        try{
            while(rs.next()){
                comment=new Comment();
                comment.setIdcomment(rs.getInt(1));
                comment.setIdorder(rs.getInt(2));
                comment.setIduser(rs.getInt(3));
                comment.setIdmerchandise(rs.getInt(4));
                comment.setComment(rs.getString(5));
                comment.setDate(rs.getString(6));
                comment.setEvaluate(rs.getString(7));
                list.add(comment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectIdorder(int idorder) {
        List list=new ArrayList();
        String sql="select * from comment where idorder="+idorder;
        ResultSet rs=dbc.exeQuery(sql);
        Comment comment=null;
        try{
            while(rs.next()){
                comment=new Comment();
                comment.setIdcomment(rs.getInt(1));
                comment.setIdorder(rs.getInt(2));
                comment.setIduser(rs.getInt(3));
                comment.setIdmerchandise(rs.getInt(4));
                comment.setComment(rs.getString(5));
                comment.setDate(rs.getString(6));
                comment.setEvaluate(rs.getString(7));
                list.add(comment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectIdMerchandise(int idmerchandise) {
        List list=new ArrayList();
        String sql="select * from comment where idmerchandise="+idmerchandise;
        ResultSet rs=dbc.exeQuery(sql);
        Comment comment=null;
        try{
            while(rs.next()){
                comment=new Comment();
                comment.setIdcomment(rs.getInt(1));
                comment.setIdorder(rs.getInt(2));
                comment.setIduser(rs.getInt(3));
                comment.setIdmerchandise(rs.getInt(4));
                comment.setComment(rs.getString(5));
                comment.setDate(rs.getString(6));
                comment.setEvaluate(rs.getString(7));
                list.add(comment);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
