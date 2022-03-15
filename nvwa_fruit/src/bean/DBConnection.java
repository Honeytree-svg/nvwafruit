package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String DBDRIVER="org.gjt.mm.mysql.Driver";
    private String DBURL="jdbc:mysql://localhost:3306/nvwa_fruit?characterEncoding=utf-8";
    private String USER="root";
    private String PASSWORD="123456";
    private Connection conn=null;
    public DBConnection(){
        try {
            Class.forName(DBDRIVER).newInstance();
            conn= DriverManager.getConnection(DBURL,USER,PASSWORD);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Connection getConn(){
        return conn;
    }
}
