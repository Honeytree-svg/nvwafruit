package dao.impl;

import dao.AddressDao;
import dbc.DatabaseConnection;
import valuebean.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    private DatabaseConnection dbc=null;
    public AddressDaoImpl(){
        dbc=new DatabaseConnection();
    }

    @Override
    public boolean addAddress(Address address) {
        String sql="insert into address(iduser,address,postcode,phone,receiver) values(?,?,?,?,?)";
        return dbc.exeUpdate(sql,address.getIduser(),address.getAddress(),address.getPostcode(),address.getPhone(),address.getReceiver());
    }

    @Override
    public boolean deleteAddress(Address address) {
        String sql="delete from address where iduser=? and idaddress=?";
        return dbc.exeUpdate(sql,address.getIduser(),address.getIdaddress());
    }


    @Override
    public boolean updateAddress(Address address) {
        String sql="update address set address=?,postcode=?,phone=?,receiver=? where iduser=? and idaddress=?";
        return dbc.exeUpdate(sql,address.getAddress(),address.getPostcode(),address.getPhone(),address.getReceiver(),address.getIduser(),address.getIdaddress());
    }

    @Override
    public List selectAllAddress(int iduser) {
        List list=new ArrayList();
        String sql="select * from address where iduser=?";
        ResultSet rs=dbc.exeQuery(sql,iduser);
        Address address=null;
        try{
            while(rs.next()){
                address=new Address();
                address.setIdaddress(rs.getInt(1));
                address.setIduser(rs.getInt(2));
                address.setAddress(rs.getString(3));
                address.setPostcode(rs.getString(4));
                address.setPhone(rs.getString(5));
                address.setReceiver(rs.getString(6));
                list.add(address);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Address singleAddress(int iduser, int idaddress) {
        String sql="select * from address where iduser=? and idaddress=?";
        ResultSet rs=dbc.exeQuery(sql,iduser,idaddress);
        Address address=null;
        try{
            if (rs.next()){
                address=new Address();
                address.setIdaddress(rs.getInt(1));
                address.setIduser(rs.getInt(2));
                address.setAddress(rs.getString(3));
                address.setPostcode(rs.getString(4));
                address.setPhone(rs.getString(5));
                address.setReceiver(rs.getString(6));
            }
            System.out.println("4444444");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return address;
    }


}
