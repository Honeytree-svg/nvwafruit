package dao;

import valuebean.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    abstract public boolean login(Person person)throws SQLException;
    abstract public boolean register(Person person);
    abstract public List selectAllPerson(int identification);
    abstract public boolean delete(int iduser);
    abstract public boolean update(Person person);
    public Person selectSingle(String id);
    public Person Login(Person person)throws SQLException;
}
