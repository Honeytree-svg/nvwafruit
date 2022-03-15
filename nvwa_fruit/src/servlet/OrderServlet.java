package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import dbc.DatabaseConnection;
import valuebean.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")==null){
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        String type=request.getParameter("type");
        if(type==null){
            select(request,response);
        }else if(type.equals("delete")) {
            delete(request,response);
        }else if(type.equals("add")){
            add(request,response);
        }else if(type.equals("update_do")) {
            updateDo(request,response);
        }else if (type.equals("detail")){
            singleselect(request,response);
        }else if (type.equals("pay")){
            pay(request,response);
        }else if (type.equals("success_jsp")){
            add(request,response);
        }else if (type.equals("orderState")){
            orderState(request,response);
        }else if(type.equals("updateOrderPayState")){
            updateOrderPayState(request,response);
        }
    }

    private void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        Person perosn=(Person)request.getSession().getAttribute("user");
        List<Order> list=new ArrayList<>();
        if (perosn.getIdentification()==1){
            list= FactoryDao.getOrderDaoInstance().selectUserOrder(perosn.getId());
            int wait_out_of_stock=0,wait_recrive=0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getState()==0)wait_out_of_stock++;
                if(list.get(i).getState()==1)wait_recrive++;
            }
            int []state={wait_out_of_stock,wait_recrive};
            request.setAttribute("orderState",state);

            PageCount pageCount=new PageCount(list,5);
            list=pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("orderList",list);
            request.setAttribute("pageCount",pageCount.PageCount());
            request.getRequestDispatcher("vipOrder.jsp").forward(request,response);
        }else if(perosn.getIdentification()==2){
            list= FactoryDao.getOrderDaoInstance().selectSellerOrder(perosn.getId());
            PageCount pageCount=new PageCount(list,8);
            list=pageCount.getPageList(Integer.parseInt(page));
            int state=Integer.parseInt(request.getParameter("orderState"));
            request.setAttribute("orderList",list);
            request.setAttribute("pageCount",pageCount.PageCount());
            if (state==0)request.getRequestDispatcher("Backstage/orderSeller.jsp").forward(request,response);
            if (state==2)request.getRequestDispatcher("Backstage/orderMatterSeller.jsp").forward(request,response);

        }else {
            list= FactoryDao.getOrderDaoInstance().selectMatterOrder(2);

            PageCount pageCount=new PageCount(list,8);
            list=pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("orderList",list);
            request.setAttribute("pageCount",pageCount.PageCount());
            request.getRequestDispatcher("Backstage/orderAdmin.jsp").forward(request,response);
        }

    }
//    public static  void main(String []s){
//        List<Order> list=new ArrayList<>();
//        list= FactoryDao.getOrderDaoInstance().selectUserOrder(1);
//        System.out.println(list.get(0).getMerchandiseName());
//    }
    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        if(FactoryDao.getOrderDaoInstance().deleteOrder(idorder)){
            response.getWriter().print("<script>alert('删除成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        Person person=(Person)request.getAttribute("user");
        int identification=person.getIdentification();
        if(identification==1)response.getWriter().print("<script>location.href='Order'</script>");
        if(identification==2)response.getWriter().print("<script>location.href='Person?identification=2'</script>");
        if(identification==0)response.getWriter().print("<script>location.href='Person?identification=0'</script>");
    }

    private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Person person=(Person)request.getSession().getAttribute("user");
        int idaddress=Integer.parseInt(request.getParameter("idaddress"));
        String idmer_q[]=request.getParameterValues("idmerchandise");
        List<Integer> quantity =new ArrayList<>();
        List<Integer> idmerchandise =new ArrayList<>();
        for (int i = 0; i < idmer_q.length; i++) {
            int j=idmer_q[i].indexOf("+");
            idmerchandise.add(Integer.parseInt(idmer_q[i].substring(0,j)));
            quantity.add(Integer.parseInt(idmer_q[i].substring(j+1,idmer_q[i].length())));
        }
        Order order=new Order();
        List<Order_Merchandise> order_merchandiseList=new ArrayList<>();
        for (int i = 0; i < idmerchandise.size(); i++) {
            int temp=idmerchandise.get(i);
            Order_Merchandise order_merchandise=new Order_Merchandise();
            order_merchandise.setMerchandise((Merchandise) FactoryDao.getMerchandiseDaoInstance().selectIdMerchandise(temp).get(0));
            order_merchandise.setQuantity(quantity.get(i));
            order_merchandiseList.add(order_merchandise);
        }
        order.setIduser(person.getId());
        order.setIdaddress(idaddress);
        order.setPayState(0);
        order.setOrder_merchandises(order_merchandiseList);
        //System.out.println();

        Random random=new Random();
        Calendar calendar=Calendar.getInstance();
        String idStringOrder=Integer.toString(calendar.get(Calendar.YEAR))+(calendar.get(Calendar.MARCH)+1)+calendar.get(Calendar.DAY_OF_MONTH)+ calendar.get(Calendar.HOUR_OF_DAY)+calendar.get(Calendar.MINUTE)+calendar.get(Calendar.SECOND)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10);
        order.setIdStringOrder(idStringOrder);

        calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        order.setCreateTime(pdate);

        for (int i=0;i<idmerchandise.size();i++){
            FactoryDao.getShopping_CarDaoInstance().deleteShoppingCar(person.getId(),idmerchandise.get(i));
        }

        if(FactoryDao.getOrderDaoInstance().addOrder(order)){
            response.getWriter().print("<script>alert('下单成功');</script>");
        }else {
            response.getWriter().print("<script>alert('下单失败');</script>");
        }
        Order order2=FactoryDao.getOrderDaoInstance().selectSingleOrder(idStringOrder);
        request.setAttribute("order",order2);
        request.getRequestDispatcher("success.jsp").forward(request,response);
    }

    private void updateOrderPayState(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int payState=Integer.parseInt(request.getParameter("payState"));
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        Calendar calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        DatabaseConnection dbc=new DatabaseConnection();;
        String sql="update nvwa_fruit.`order` set paystate=?,PayTime=? where idorder=?";
        if(dbc.exeUpdate(sql,payState,pdate,idorder)){
            response.getWriter().print("<script>alert('支付成功');</script>");
        }else {
            response.getWriter().print("<script>alert('支付失败');</script>");
        }
        response.getWriter().print("<script>location.href='Order'</script>");
    }

    private void updateDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        int state=Integer.parseInt(request.getParameter("state"));

        Order order = FactoryDao.getOrderDaoInstance().selectIdOrder(idorder);
        Calendar calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        order.setDispatchTime(pdate);
        order.setState(state);
        if (FactoryDao.getOrderDaoInstance().updateOrder(order)){
            if (state==1)response.getWriter().print("<script>alert('发货成功');</script>");
            if (state==4)response.getWriter().print("<script>alert('退货成功');</script>");
        }else {
            if (state==1)response.getWriter().print("<script>alert('发货失败');</script>");
            if (state==4)response.getWriter().print("<script>alert('退货失败');</script>");
        }
        response.getWriter().print("<script>location.href='Order?idmerchandise=2&&orderState=0'</script>");
    }

    private void singleselect(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int idorder=Integer.parseInt(request.getParameter("idorder"));

        Order order=FactoryDao.getOrderDaoInstance().selectIdOrder(idorder);
        Person person=FactoryDao.getPersonDaoInstance().selectSingle(Integer.toString(order.getIduser()));
        Address address=FactoryDao.getAddressDaoInstance().singleAddress(order.getIduser(),order.getIdaddress());

        request.setAttribute("order",order);
        request.setAttribute("orderPerson",person);
        request.setAttribute("Address",address);
        request.getRequestDispatcher("orderDetails.jsp").forward(request,response);
    }

    private void pay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        Calendar calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        DatabaseConnection dbc=new DatabaseConnection();;
        String sql="update nvwa_fruit.`order` set paystate=?,PayTime=? where idorder=?";
        if (dbc.exeUpdate(sql,1,pdate,idorder)){
            response.getWriter().print("<script>alert('付款成功');</script>");
        }else {
            response.getWriter().print("<script>alert('付款失败');</script>");
        }

        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        Person perosn=(Person)request.getSession().getAttribute("user");
        List<Order> list=new ArrayList<>();
        if (perosn.getIdentification()==1){
            list= FactoryDao.getOrderDaoInstance().selectUserOrder(perosn.getId());
            int wait_out_of_stock=0,wait_recrive=0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getState()==0)wait_out_of_stock++;
                if(list.get(i).getState()==1)wait_recrive++;
            }
            int []state={wait_out_of_stock,wait_recrive};
            request.setAttribute("orderState",state);

            PageCount pageCount=new PageCount(list,8);
            list=pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("orderList",list);
            request.setAttribute("pageCount",pageCount.PageCount());
            request.getRequestDispatcher("vipOrder.jsp").forward(request,response);
        }

    }


    private void orderState(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Person person=(Person)request.getSession().getAttribute("user");
        int orderState=Integer.parseInt(request.getParameter("orderState"));
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        Calendar calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        DatabaseConnection dbc=new DatabaseConnection();;
        String sql="update nvwa_fruit.`order` set state=?,OverTime=? where idorder=?";
        if (person.getIdentification()==1){
            String sql2="update nvwa_fruit.`order` set state=? where idorder=?";
            if(dbc.exeUpdate(sql2,orderState,idorder)){
                response.getWriter().print("<script>alert('订单处理中');</script>");
            }else {
                response.getWriter().print("<script>alert('服务器异常');</script>");
            }
            request.getRequestDispatcher("vip.jsp").forward(request,response);
        }
        if(dbc.exeUpdate(sql,orderState,pdate,idorder)){
            response.getWriter().print("<script>alert('修改成功');</script>");
        }else {
            response.getWriter().print("<script>alert('修改失败');</script>");
        }
        List<Order> list=new ArrayList<>();
        list= FactoryDao.getOrderDaoInstance().selectSellerOrder(person.getId());
        PageCount pageCount=new PageCount(list,12);
        list=pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("orderList",list);
        request.setAttribute("pageCount",pageCount.PageCount());
        if (orderState==0) request.getRequestDispatcher("Backstage/orderSeller.jsp").forward(request,response);
        if (orderState==2) request.getRequestDispatcher("Backstage/orderMatterSeller.jsp").forward(request,response);
        response.getWriter().print("<script>location.href='Order'</script>");
    }

    public static void main(String []s){
        List<Order>list= FactoryDao.getOrderDaoInstance().selectSellerOrder(3);
        System.out.println(list.size());

    }

}
