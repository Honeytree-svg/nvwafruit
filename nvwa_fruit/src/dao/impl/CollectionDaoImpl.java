package dao.impl;

import dao.CollectionDao;
import dbc.DatabaseConnection;
import valuebean.Collection;
import valuebean.Merchandise;
import valuebean.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CollectionDaoImpl implements CollectionDao {
    DatabaseConnection dbc=null;
    public CollectionDaoImpl(){
        dbc=new DatabaseConnection();
    }
    public List seletAll(int iduser) {
        List<Merchandise> list = new ArrayList();
        String sql = "SELECT * FROM nvwa_fruit.merchandise WHERE idmerchandise=any(select idmerchandise from nvwa_fruit.conllection where iduser=?)";
        System.out.println(iduser);
        ResultSet rs = dbc.exeQuery(sql,iduser);
        Merchandise merchandise = null;
        try{
            while (rs.next()){
                merchandise= new Merchandise();
                merchandise.setCategory(rs.getInt("category"));
                merchandise.setDisconut(rs.getDouble("discount"));
                merchandise.setIdmerchandise(rs.getInt("idmerchandise"));
                merchandise.setName(rs.getString("name"));
                merchandise.setIdseller(rs.getInt("idseller"));
                merchandise.setPrice(rs.getDouble("price"));
                merchandise.setPhoto(rs.getString("photo"));
                merchandise.setAudit(rs.getInt("audit"));
                list.add(merchandise);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public boolean addCollection(int iduser,int idmerchandise){
        String sql="insert into conllection(iduser,idmerchandise)"+"values(?,?)";
        return dbc.exeUpdate(sql,iduser,idmerchandise);
    }

    public boolean delete(int iduser,int idmerchandise){
        String sql="delete from conllection where iduser=? and idmerchandise=?";
        return dbc.exeUpdate(sql,iduser,idmerchandise);
    }
}
