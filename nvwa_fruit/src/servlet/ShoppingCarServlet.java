package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import valuebean.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCarServlet extends HttpServlet {
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
        }else if (type.equals("upOrder")){
            upOrder(request,response);
        }
    }

    private void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        Person person=(Person)request.getSession().getAttribute("user");
        Shopping_Car shopping_car = FactoryDao.getShopping_CarDaoInstance().selectUserShoppingCar(person.getId());

        request.setAttribute("ShoppingCar",shopping_car);
        request.getSession().setAttribute("ShoppingCar",shopping_car);
        request.getRequestDispatcher("shoppingCar.jsp").forward(request,response);
        /*else if(tmp.equals("vip")){
            request.getSession().setAttribute("ShoppingCarList",shopping_car);
            request.getRequestDispatcher("vipOrder.jsp").forward(request,response);
        }else if (tmp.equals("upOrder")){
            request.getSession().setAttribute("ShoppingCarList",shopping_car);

            List<Address> addressList= FactoryDao.getAddressDaoInstance().selectAllAddress(person.getId());
            PageCount pageCount=new PageCount(addressList,4);
            addressList=pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("addressList",addressList);
            request.setAttribute("pageCount",pageCount.PageCount());

            request.getRequestDispatcher("upOrder.jsp").forward(request,response);
        }*/

    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String pageLabel=request.getParameter("pageLabel");

        int idmerchandise=Integer.parseInt(request.getParameter("idmerchandise"));
        Person person=(Person)request.getSession().getAttribute("user");
        if (pageLabel.equals("upOrder")){
            Shopping_Car shopping_car=(Shopping_Car)request.getSession().getAttribute("ShoppingCar");
            for (int i = 0; i < shopping_car.getOrder_merchandises().size(); i++) {
                if(shopping_car.getOrder_merchandises().get(i).getMerchandise().getIdmerchandise()==idmerchandise){
                    shopping_car.getOrder_merchandises().remove(i);
                }
            }
            List<Address> addressList=FactoryDao.getAddressDaoInstance().selectAllAddress(person.getId());
            request.setAttribute("ShoppingCar",shopping_car);
            request.getSession().setAttribute("ShoppingCar",shopping_car);
            request.setAttribute("AddressList",addressList);
            request.getRequestDispatcher("upOrder.jsp").forward(request,response);
        }else  if (pageLabel.equals("shoppingCar")){
            if (FactoryDao.getShopping_CarDaoInstance().deleteShoppingCar(person.getId(),idmerchandise)){
                response.getWriter().print("<script>alert('删除成功');</script>");
            } else {
                response.getWriter().print("<script>alert('删除失败');</script>");
            }
            response.getWriter().print("<script>location.href='ShoppingCar'</script>");
        }



    }

    private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        int idmerchandise=Integer.parseInt(request.getParameter("idmerchandise"));
        Person person=(Person)request.getSession().getAttribute("user");

        if(FactoryDao.getShopping_CarDaoInstance().addShoppingCar(person.getId(),idmerchandise,quantity)){
            response.getWriter().print("<script>alert('购物车加入成功');</script>");
        }else {
            response.getWriter().print("<script>alert('购物车加入失败');</script>");
        }

        List<Category> categoryList=FactoryDao.getCategoryDaoInstance().selectAll();
        List<Merchandise> list =FactoryDao.getMerchandiseDaoInstance().selectIdMerchandise(idmerchandise);
        Merchandise merchandise=list.get(0);

        request.setAttribute("Merchandise",merchandise);
        request.setAttribute("CategoryList", categoryList);
        request.getRequestDispatcher("proinfo.jsp").forward(request, response);
    }

    private void upOrder(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        System.out.println("fsdfs");
        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        Person person=(Person)request.getSession().getAttribute("user");
        List<Address> addressList=FactoryDao.getAddressDaoInstance().selectAllAddress(person.getId());
        PageCount pageCount=new PageCount(addressList,4);
        addressList=pageCount.getPageList(Integer.parseInt(page));

        String idmer_q[]=request.getParameterValues("idmerchandise");
        List<Integer> quantity =new ArrayList<>();
        List<Integer> idmerchandise =new ArrayList<>();
        for (int i = 0; i < idmer_q.length; i++) {
            System.out.println(idmer_q[i]);
            int j=idmer_q[i].indexOf("+");
            idmerchandise.add(Integer.parseInt(idmer_q[i].substring(0,j)));
            quantity.add(Integer.parseInt(idmer_q[i].substring(j+1,idmer_q[i].length())));
        }
        Shopping_Car shopping_car=new Shopping_Car();
        shopping_car.setIduser(person.getId());
        List<Order_Merchandise> order_merchandiseList=new ArrayList<>();
        for (int i = 0; i < idmer_q.length; i++) {
            Order_Merchandise order_merchandise=new Order_Merchandise();
            Merchandise merchandise=FactoryDao.getMerchandiseDaoInstance().selectSingle(idmerchandise.get(i));
            order_merchandise.setMerchandise(merchandise);
            order_merchandise.setQuantity(quantity.get(i));
            order_merchandiseList.add(order_merchandise);
        }
        shopping_car.setOrder_merchandises(order_merchandiseList);

        request.setAttribute("AddressList",addressList);
        request.getSession().setAttribute("ShoppingCar",shopping_car);
        request.setAttribute("pageCount",pageCount.PageCount());
        request.getRequestDispatcher("upOrder.jsp").forward(request,response);
    }

    public static void main(String []s){
        Shopping_Car shopping_car=new Shopping_Car();
        shopping_car.setIduser(1);
        String []idmer_q={"1+64","2+254"};
        List<Integer> quantity =new ArrayList<>();
        List<Integer> idmerchandise =new ArrayList<>();
        for (int i = 0; i < idmer_q.length; i++) {
            int j=idmer_q[i].indexOf("+");
            System.out.println(j);
            System.out.println(idmer_q[i]);
            idmerchandise.add(Integer.parseInt(idmer_q[i].substring(0,j)));
            quantity.add(Integer.parseInt(idmer_q[i].substring(j+1,idmer_q[i].length())));
            System.out.println(idmerchandise.get(i));
        }
        List<Order_Merchandise> order_merchandiseList=new ArrayList<>();
        Order_Merchandise order_merchandise=new Order_Merchandise();
        for (int i = 0; i < idmer_q.length; i++) {
            System.out.println(idmerchandise.get(i));
            Merchandise merchandise=FactoryDao.getMerchandiseDaoInstance().selectSingle(idmerchandise.get(i));
            System.out.println(merchandise);
            order_merchandise.setMerchandise(merchandise);
            order_merchandise.setQuantity(quantity.get(i));
            order_merchandiseList.add(order_merchandise);
            System.out.println(merchandise.getDiscountPrice());
        }
        shopping_car.setOrder_merchandises(order_merchandiseList);
    }
}
