package dao.impl;

import dao.OrderDao;
import dbc.DatabaseConnection;
import valuebean.Order;
import valuebean.Order_Merchandise;
import valuebean.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private DatabaseConnection dbc=null;
    public OrderDaoImpl(){
        dbc=new DatabaseConnection();
    }

    @Override
    public boolean addOrder(Order order) {
        boolean flag=false;
        String sql="insert into nvwa_fruit.`order`(iduser,state,CreateTime,idStringOrder,idaddress) values(?,?,?,?,?)";
        if (dbc.exeUpdate(sql,order.getIduser(),order.getState(),order.getCreateTime(),order.getIdStringOrder(),order.getIdaddress())){
            Order order1=selectSingleOrder(order.getIdStringOrder());
            int n=0;
            for (int i = 0; i < order.getOrder_merchandises().size(); i++) {
                sql = "insert into nvwa_fruit.order_merchandise(idorder,idmerchandise,quantity,sum) values(?,?,?,?)";
                if (dbc.exeUpdate(sql, order1.getIdorder(), order.getOrder_merchandises().get(i).getMerchandise().getIdmerchandise(), order.getOrder_merchandises().get(i).getQuantity(), order.getOrder_merchandises().get(i).getSum()))
                    n++;
                System.out.println("order_merchandise表插入成功");
            }
            if(n==order1.getOrder_merchandises().size())flag=true;
        }else {
            System.out.println("订单表插入式出错！！！！");
        }
        return flag;
    }

    @Override
    public boolean updateOrder(Order order) {
        String sql="update nvwa_fruit.`order` set state=?,CreateTime=?,PayTime=?,DispatchTime=?,OverTime=? where idorder=?";
        return dbc.exeUpdate(sql,order.getState(),order.getCreateTime(),order.getPayTime(),order.getDispatchTime(),order.getOverTime(),order.getIdorder());
    }

    @Override
    public boolean deleteOrder(int idorder) {
        boolean flag=false;
        String sql="delete from order_merchandise where idorder="+ idorder;
        if (!dbc.exeUpdate(sql)){
            System.out.println("no order");
        }
        sql="delete from nvwa_fruit.`order` where idorder="+ idorder;
        if (dbc.exeUpdate(sql))flag=true;
        return flag;
    }

    @Override
    public List selectUserOrder(int iduser) {
        List<Order> list=new ArrayList<>();
        String sql="select * from nvwa_fruit.`order` where iduser=?";
        ResultSet rs=dbc.exeQuery(sql,iduser);
        Order order=null;
        try{
            while(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt(1));
                order.setIduser(rs.getInt(2));
                order.setState(rs.getInt(3));
                order.setCreateTime(rs.getString(4));
                order.setPayTime(rs.getString(5));
                order.setDispatchTime(rs.getString(6));
                order.setOverTime(rs.getString(7));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));
                list.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            for (int i = 0; i < list.size(); i++) {
                sql="select * from nvwa_fruit.merchandise,nvwa_fruit.order_merchandise where merchandise.idmerchandise=order_merchandise.idmerchandise and idorder="+list.get(i).getIdorder();
                rs=dbc.exeQuery(sql);
                while(rs.next()){
                    Order_Merchandise order_merchandise=new Order_Merchandise();
                    order_merchandise.getMerchandise().setIdmerchandise(rs.getInt("idmerchandise"));
                    order_merchandise.getMerchandise().setName(rs.getString("name"));
                    order_merchandise.getMerchandise().setCategory(rs.getInt("category"));
                    order_merchandise.getMerchandise().setPrice(rs.getDouble("price"));
                    order_merchandise.getMerchandise().setIdseller(rs.getInt("idseller"));
                    order_merchandise.getMerchandise().setDisconut(rs.getDouble("discount"));
                    order_merchandise.setQuantity(rs.getInt("quantity"));
                    order_merchandise.getMerchandise().setPhoto("photo");
                    list.get(i).getOrder_merchandises().add(order_merchandise);
                }
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List selectUserStateOrder(int iduser, int state) {
        List<Order> list=new ArrayList<>();
        String sql="select * from order where iduser=? and state=?";
        ResultSet rs=dbc.exeQuery(sql,iduser,state);
        Order order=null;
        try{
            while(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt(1));
                order.setIduser(rs.getInt(2));
                order.setState(rs.getInt(3));
                order.setCreateTime(rs.getString(4));
                order.setPayTime(rs.getString(5));
                order.setDispatchTime(rs.getString(6));
                order.setOverTime(rs.getString(7));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));
                list.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            for (int i = 0; i < list.size(); i++) {
                sql="select * from nvwa_fruit.merchandise,nvwa_fruit.order_merchandise where merchandise.idmerchandise=order_merchandise.idmerchandise and idorder="+list.get(i).getIdorder();
                rs=dbc.exeQuery(sql);
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
                    list.get(i).getOrder_merchandises().add(order_merchandise);
                }
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List selectAllOrder() {
        List<Order> list=new ArrayList<>();
        String sql="select * from nvwa_fruit.`order`";
        ResultSet rs=dbc.exeQuery(sql);
        Order order=null;
        try{
            while(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt(1));
                order.setIduser(rs.getInt(2));
                order.setState(rs.getInt(3));
                order.setCreateTime(rs.getString(4));
                order.setPayTime(rs.getString(5));
                order.setDispatchTime(rs.getString(6));
                order.setOverTime(rs.getString(7));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));
                list.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            for (int i = 0; i < list.size(); i++) {
                sql="select * from nvwa_fruit.merchandise,nvwa_fruit.order_merchandise where merchandise.idmerchandise=order_merchandise.idmerchandise and idorder="+list.get(i).getIdorder();
                rs=dbc.exeQuery(sql);
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
                    list.get(i).getOrder_merchandises().add(order_merchandise);
                }
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Order selectIdOrder(int idOrder) {
        String sql="select * from nvwa_fruit.`order` where idorder=?";
        ResultSet rs=dbc.exeQuery(sql,idOrder);
        Order order=null;
        try{
            if(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt(1));
                order.setIduser(rs.getInt(2));
                order.setState(rs.getInt(3));
                order.setCreateTime(rs.getString(4));
                order.setPayTime(rs.getString(5));
                order.setDispatchTime(rs.getString(6));
                order.setOverTime(rs.getString(7));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));
                rs.close();
                sql="select * from order_merchandise where idorder="+order.getIdorder();
                rs=dbc.exeQuery(sql);
                while(rs.next()){
                    Order_Merchandise order_merchandise=new Order_Merchandise();
                    order_merchandise.getMerchandise().setIdmerchandise(rs.getInt("idmerchandise"));
                    order_merchandise.setQuantity(rs.getInt("quantity"));
                    order.getOrder_merchandises().add(order_merchandise);
                }
                rs.close();
            }
            for (int i = 0; i < order.getOrder_merchandises().size(); i++) {
                sql="select * from merchandise where idmerchandise="+order.getOrder_merchandises().get(i).getMerchandise().getIdmerchandise();
                rs=dbc.exeQuery(sql);
                rs.next();
                order.getOrder_merchandises().get(i).getMerchandise().setIdmerchandise(rs.getInt("idmerchandise"));
                order.getOrder_merchandises().get(i).getMerchandise().setName(rs.getString("name"));
                order.getOrder_merchandises().get(i).getMerchandise().setCategory(rs.getInt("category"));
                order.getOrder_merchandises().get(i).getMerchandise().setPrice(rs.getDouble("price"));
                order.getOrder_merchandises().get(i).getMerchandise().setIdseller(rs.getInt("idseller"));
                order.getOrder_merchandises().get(i).getMerchandise().setDisconut(rs.getDouble("discount"));
                order.getOrder_merchandises().get(i).getMerchandise().setPhoto(rs.getString("photo"));
            }
            System.out.println("OrderDaoSingleIdSelect is over");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order selectSingleOrder(String idStringOrder) {
        String sql="select * from nvwa_fruit.`order` where idStringOrder="+idStringOrder;
        ResultSet rs=dbc.exeQuery(sql);
        Order order=null;
        try{
            if(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt(1));
                order.setIduser(rs.getInt(2));
                order.setState(rs.getInt(3));
                order.setCreateTime(rs.getString(4));
                order.setPayTime(rs.getString(5));
                order.setDispatchTime(rs.getString(6));
                order.setOverTime(rs.getString(7));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));

                rs.close();
                sql="select * from order_merchandise where idorder="+order.getIdorder();
                rs=dbc.exeQuery(sql);
                List<Order_Merchandise> list=new ArrayList<>();
                while(rs.next()){
                    Order_Merchandise order_merchandise=new Order_Merchandise();
                    order_merchandise.getMerchandise().setIdmerchandise(rs.getInt(2));
                    order_merchandise.setQuantity(rs.getInt(3));
                    list.add(order_merchandise);
                }
                rs.close();
                order.setOrder_merchandises(list);
            }
            for (int i = 0; i < order.getOrder_merchandises().size(); i++) {
                sql="select * from merchandise where idmerchandise="+order.getOrder_merchandises().get(i).getMerchandise().getIdmerchandise();
                rs=dbc.exeQuery(sql);
                rs.next();
                order.getOrder_merchandises().get(i).getMerchandise().setIdmerchandise(rs.getInt("idmerchandise"));
                order.getOrder_merchandises().get(i).getMerchandise().setName(rs.getString("name"));
                order.getOrder_merchandises().get(i).getMerchandise().setCategory(rs.getInt("category"));
                order.getOrder_merchandises().get(i).getMerchandise().setPrice(rs.getDouble("price"));
                order.getOrder_merchandises().get(i).getMerchandise().setIdseller(rs.getInt("idseller"));
                order.getOrder_merchandises().get(i).getMerchandise().setDisconut(rs.getDouble("discount"));
                order.getOrder_merchandises().get(i).getMerchandise().setPhoto(rs.getString("photo"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List selectSellerOrder(int seller) {
        List<Order> list=new ArrayList<>();
        String sql="select * from nvwa_fruit.merchandise,nvwa_fruit.order_merchandise,nvwa_fruit.order where merchandise.idmerchandise=order_merchandise.idmerchandise and order_merchandise.idorder=order.idorder and idseller=?";
        ResultSet rs=dbc.exeQuery(sql,seller);
        Order order=null;
        int j=0;
        try{
            while(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt("idorder"));
                order.setIduser(rs.getInt("iduser"));
                order.setState(rs.getInt("state"));
                order.setCreateTime(rs.getString("CreateTime"));
                order.setPayTime(rs.getString("PayTime"));
                order.setDispatchTime(rs.getString("DispatchTime"));
                order.setOverTime(rs.getString("OverTime"));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));
                list.add(order);
                Order_Merchandise order_merchandise=new Order_Merchandise();
                order_merchandise.getMerchandise().setIdmerchandise(rs.getInt("idmerchandise"));
                order_merchandise.getMerchandise().setName(rs.getString("name"));
                order_merchandise.getMerchandise().setCategory(rs.getInt("category"));
                order_merchandise.getMerchandise().setPrice(rs.getDouble("price"));
                order_merchandise.getMerchandise().setIdseller(rs.getInt("idseller"));
                order_merchandise.getMerchandise().setDisconut(rs.getDouble("discount"));
                order_merchandise.setQuantity(rs.getInt("quantity"));
                order_merchandise.getMerchandise().setPhoto(rs.getString("photo"));

                list.get(j).getOrder_merchandises().add(order_merchandise);
                j++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectMatterOrder(int state) {
        List<Order> list=new ArrayList<>();
        String sql="select * from nvwa_fruit.`order` where state=?";
        ResultSet rs=dbc.exeQuery(sql,state);
        Order order=null;
        try{
            while(rs.next()){
                order=new Order();
                order.setIdorder(rs.getInt(1));
                order.setIduser(rs.getInt(2));
                order.setState(rs.getInt(3));
                order.setCreateTime(rs.getString(4));
                order.setPayTime(rs.getString(5));
                order.setDispatchTime(rs.getString(6));
                order.setOverTime(rs.getString(7));
                order.setIdStringOrder(rs.getString("idStringOrder"));
                order.setIdaddress(rs.getInt("idaddress"));
                order.setPayState(rs.getInt("paystate"));
                list.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            for (int i = 0; i < list.size(); i++) {
                sql="select * from nvwa_fruit.merchandise,nvwa_fruit.order_merchandise where merchandise.idmerchandise=order_merchandise.idmerchandise and idorder="+list.get(i).getIdorder();
                rs=dbc.exeQuery(sql);
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
                    list.get(i).getOrder_merchandises().add(order_merchandise);
                }
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

}
