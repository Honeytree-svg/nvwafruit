package dao.impl;

import dao.PersonDao;
import dbc.DatabaseConnection;
import valuebean.Person;
import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDaoImpl implements PersonDao {

    private DatabaseConnection dbc=null;
    public PersonDaoImpl(){
        dbc=new DatabaseConnection();
    }
    public boolean login(Person person)throws SQLException{
        boolean flag=false;
        String sql="select * from user where name=? and password=? and identification=?";

        ResultSet rs=dbc.exeQuery(sql,person.getName(),person.getPassword(),person.getIdentification());
        if(rs.next()){
            flag=true;
        }
        rs.close();
        return flag;
    }

    public Person Login(Person person)throws SQLException{
        String sql="select * from user where name=? and password=? and identification=?";

        ResultSet rs=dbc.exeQuery(sql,person.getName(),person.getPassword(),person.getIdentification());
        if(rs.next()){
            person.setId(rs.getInt(1));
            person.setName(rs.getString(2));
            person.setPassword(rs.getString(3));
            person.setIdentification(rs.getInt(4));
            person.setPhone(rs.getString(5));
            person.setSex(rs.getString(6));
            return person;
        }
        rs.close();
        return null;
    }

    public boolean register(Person person){
        String sql="insert into user(name,password,identification) values(?,?,?)";
        return dbc.exeUpdate(sql,person.getName(),person.getPassword(),person.getIdentification());
    }

    public List selectAllPerson(int identification){
        List list=new ArrayList();
        String sql="select * from user where identification=?";
        ResultSet rs=dbc.exeQuery(sql,identification);
        Person person=null;
        try{
            while(rs.next()){
                person=new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setPassword(rs.getString(3));
                person.setIdentification(rs.getInt(4));
                person.setPhone(rs.getString(5));
                list.add(person);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(int iduser){
        String sql="delete from user where idUser="+ iduser;
        return dbc.exeUpdate(sql);
    }

    public boolean update(Person person) {
        String sql="update user set name=?,password=?,identification=?,phone=?,sex=? where idUser=?";
        return dbc.exeUpdate(sql,person.getName(),person.getPassword(),person.getIdentification(),person.getPhone(),person.getSex(),person.getId());
    }

    public Person selectSingle(String id){
        String sql="select * from user where idUser="+id;
        ResultSet rs=dbc.exeQuery(sql);
        Person person=null;
        try{
            if(rs.next()){
                person=new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setPassword(rs.getString(3));
                person.setIdentification(rs.getInt(4));
                person.setPhone(rs.getString(5));
            }
            System.out.println("555555");
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return person;
    }
}
