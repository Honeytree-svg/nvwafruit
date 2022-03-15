package dao;

import java.util.List;

public interface CommentDao {
    public abstract boolean addComment(int idorder,int iduser,int idmerchandise,String commnet,String date,String evaluate);
    public abstract boolean deleteComment(int idcomment);
    public abstract List selectAll();
    public List selectIdorder(int idorder);
    public List selectIdMerchandise(int idmerchandise);
}
