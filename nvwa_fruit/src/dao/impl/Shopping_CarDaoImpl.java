package dao.impl;

import dao.Shopping_CarDao;
import dao.factory.FactoryDao;
import dbc.DatabaseConnection;
import valuebean.Shopping_Car;
import valuebean.Order_Merchandise;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shopping_CarDaoImpl implements Shopping_CarDao {
    private DatabaseConnection dbc=null;
    public Shopping_CarDaoImpl(){
        dbc=new DatabaseConnection();
    }

    @Override
    public boolean addShoppingCar(int iduser,int idmerchandise, int quantity) {
        String sql="insert into shoppingcar(iduser,idmerchandise,quantity) values(?,?,?)";
        return dbc.exeUpdate(sql,iduser,idmerchandise,quantity);
    }

    @Override
    public boolean deleteShoppingCar(int iduser, int idmerchandise) {
        String sql="delete from nvwa_fruit.shoppingcar where iduser=? and idmerchandise=?";
        return dbc.exeUpdate(sql,iduser,idmerchandise);
    }

    @Override
    public boolean deleteAllShoppingCar(int iduser) {
        String sql="delete from shoppingcar where iduser=?";
        return dbc.exeUpdate(sql,iduser);
    }

    @Override
    public Shopping_Car selectUserShoppingCar(int iduser) {
        String sql="select * from nvwa_fruit.merchandise,nvwa_fruit.shoppingcar where merchandise.idmerchandise=shoppingcar.idmerchandise and shoppingcar.iduser=?";
        ResultSet rs=dbc.exeQuery(sql,iduser);
        Shopping_Car shopping_car=null;
        try{
            shopping_car=new Shopping_Car();
            shopping_car.setIduser(iduser);
            while(rs.next()){
                Order_Merchandise order_merchandise=new Order_Merchandise();
                order_merchandise.getMerchandise().setIdmerchandise(rs.getInt("idmerchandise"));
                order_merchandise.getMerchandise().setName(rs.getString("name"));
                order_merchandise.getMerchandise().setCategory(rs.getInt("category"));
                order_merchandise.getMerchandise().setPrice(rs.getDouble("price"));
                order_merchandise.getMerchandise().setIdseller(rs.getInt("idseller"));
                order_merchandise.getMerchandise().setDisconut(rs.getDouble("discount"));
                order_merchandise.setQuantity(rs.getInt("quantity"));
                order_merchandise.getMerchandise().setPhoto(rs.getString("photo"));
                shopping_car.getOrder_merchandises().add(order_merchandise);
            }
            rs.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return shopping_car;
    }

//    public static void main(String []s){
//        Shopping_Car shopping_car=FactoryDao.getShopping_CarDaoInstance().selectUserShoppingCar(1);
//        System.out.println(shopping_car.getOrder_merchandises().size());
//    }
}
