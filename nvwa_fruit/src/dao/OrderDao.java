package dao;

import valuebean.Order;
import java.util.List;

public interface OrderDao {
    abstract public boolean addOrder(Order order);
    abstract public boolean updateOrder(Order order);
    abstract public boolean deleteOrder(int idorder);
    abstract public List selectUserOrder(int iduser);
    abstract public List selectUserStateOrder(int iduser,int state);
    abstract public List selectAllOrder();
    abstract public Order selectIdOrder(int idOrder);
    abstract public Order selectSingleOrder(String idStringOrder);
    abstract public List selectSellerOrder(int seller);
    abstract public List selectMatterOrder(int state);
}
