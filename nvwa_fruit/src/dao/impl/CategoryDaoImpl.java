package dao.impl;

import dao.CategoryDao;
import dbc.DatabaseConnection;
import valuebean.Category;
import valuebean.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private DatabaseConnection dbc=null;
    public CategoryDaoImpl(){
        dbc=new DatabaseConnection();
    }

    @Override
    public boolean addCategory(String name, String remark) {
        String sql="insert into category(name,remark)"+"values(?,?)";
        return dbc.exeUpdate(sql,remark,name);
    }

    @Override
    public boolean deleteCategory(int idcategory) {
        String sql= "";
        sql="delete from category where idcategory="+idcategory;
        return dbc.exeUpdate(sql);
    }

    @Override
    public boolean updateCategory(String name, String remark,int idcategory) {
        String sql="update category set name=?,remark=? where idcategory=?";
        return dbc.exeUpdate(sql,name,remark,idcategory);
    }

    @Override
    public Category selectSingle(int idcategory) {
        String sql="select * from category where idcategory="+idcategory;
        ResultSet rs=dbc.exeQuery(sql);
        Category category=null;
        try{
            if(rs.next()){
                category=new Category();
                category.setIdcategory(rs.getInt(1));
                category.setName(rs.getString(2));
                category.setRemark(rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List selectAll() {
        List list=new ArrayList();
        String sql="select * from category";
        ResultSet rs=dbc.exeQuery(sql);
        Category category=null;
        try{
            while(rs.next()){
                category=new Category();
                category.setIdcategory(rs.getInt(1));
                category.setName(rs.getString(2));
                category.setRemark(rs.getString(3));

                list.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
