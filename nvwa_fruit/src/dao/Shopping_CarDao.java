package dao;

import valuebean.Shopping_Car;

public interface Shopping_CarDao {
    abstract public boolean addShoppingCar(int iduser,int idmerchandise,int quantity);
    abstract public boolean deleteShoppingCar(int iduser,int idmerchandise);
    abstract public boolean deleteAllShoppingCar(int iduser);
    abstract public Shopping_Car selectUserShoppingCar(int iduser);
}
