package dao.impl;

import dao.MerchandiseDao;
import dbc.DatabaseConnection;
import valuebean.Merchandise;
import valuebean.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseDaoImpl implements MerchandiseDao {
    private DatabaseConnection dbc=null;
    public MerchandiseDaoImpl(){
        dbc=new DatabaseConnection();
    }

    @Override
    public boolean addMerchandise(Merchandise merchandise, Person person) {
        String sql="insert into merchandise(name,category,price,idseller,discount,photo) values(?,?,?,?,?,?)";
        return dbc.exeUpdate(sql,merchandise.getName(),merchandise.getCategory(),merchandise.getPrice(),person.getId(),merchandise.getDisconut(),merchandise.getPhoto());
    }

    @Override
    public boolean deleteMerchandise(int idmerchandise) {
        String sql="delete from merchandise where idmerchandise="+idmerchandise;
        return dbc.exeUpdate(sql);
    }

    @Override
    public boolean updateMerchandise(Merchandise merchandise) {
        String sql="update merchandise set name=?,category=?,price=?,disconut=?,photo=? where idcategory=?";
        return dbc.exeUpdate(sql,merchandise.getName(),merchandise.getCategory(),merchandise.getPrice(),merchandise.getDisconut(),merchandise.getPhoto());

    }

    @Override
    public Merchandise selectSingle(int idmerchandise) {
        String sql="select * from merchandise where idmerchandise="+idmerchandise;
        ResultSet rs=dbc.exeQuery(sql);
        Merchandise merchandise=null;
        try{
            if(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getDouble(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return merchandise;
    }

    @Override
    public List selectAll() {
        List list=new ArrayList();
        String sql="select * from merchandise";
        ResultSet rs=dbc.exeQuery(sql);
        Merchandise merchandise=null;
        try{
            while(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getDouble(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
                list.add(merchandise);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectIdAll(int idseller) {
        List list=new ArrayList();
        String sql="select * from merchandise where idseller="+idseller;
        ResultSet rs=dbc.exeQuery(sql);
        Merchandise merchandise=null;
        try{
            while(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getDouble(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
                list.add(merchandise);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectCategory(int category) {
        List list=new ArrayList();
        String sql="select * from merchandise where category="+category;
        ResultSet rs=dbc.exeQuery(sql);
        Merchandise merchandise=null;
        try{
            while(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getDouble(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
                list.add(merchandise);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectIdMerchandise(int idmerchandise) {
        List list=new ArrayList();
        String sql="select * from merchandise where idmerchandise="+idmerchandise;
        ResultSet rs=dbc.exeQuery(sql);
        Merchandise merchandise=null;
        try{
            while(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getDouble(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
                list.add(merchandise);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Merchandise selectName(String name) {
        String sql="select * from merchandise where name=?";
        ResultSet rs=dbc.exeQuery(sql,name);
        Merchandise merchandise=null;
        try{
            if(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getDouble(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return merchandise;
    }

    @Override
    public List selectLike(String name) {
        List list=new ArrayList();
        String sql="select * from merchandise where name like '%"+name+"%'";
        ResultSet rs=dbc.exeQuery(sql);
        Merchandise merchandise=null;
        try{
            while(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getInt(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
                list.add(merchandise);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Merchandise> selectAuditState(int audit) {
        List list=new ArrayList();
        String sql="select * from merchandise where audit=?";
        ResultSet rs=dbc.exeQuery(sql,audit);
        Merchandise merchandise=null;
        try{
            while(rs.next()){
                merchandise=new Merchandise();
                merchandise.setIdmerchandise(rs.getInt(1));
                merchandise.setName(rs.getString(2));
                merchandise.setCategory(rs.getInt(3));
                merchandise.setPrice(rs.getDouble(4));
                merchandise.setIdseller(rs.getInt(5));
                merchandise.setDisconut(rs.getInt(6));
                merchandise.setPhoto(rs.getString(7));
                merchandise.setAudit(rs.getInt(8));
                list.add(merchandise);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateAuditState(int audit,int idmerchandise) {
        String sql="update merchandise set audit=? where idmerchandise=?";
        return dbc.exeUpdate(sql,audit,idmerchandise);
    }
}
