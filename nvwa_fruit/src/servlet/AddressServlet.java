package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import valuebean.Address;
import valuebean.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddressServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        String type = request.getParameter("type");
        if (type == null) {
            select(request, response);
        } else if (type.equals("delete")) {
            delete(request, response);
        } else if (type.equals("add")) {
            add(request, response);
        } else if (type.equals("update")) {
            update(request, response);
        } else if (type.equals("update_do")) {
            updateDo(request, response);
        }
    }

    private void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String page=request.getParameter("page");
        Person person=(Person) request.getSession().getAttribute("user");
        int iduser=person.getId();
        // System.out.println(identification);
        if(page==null) {
            page="1";
        }
        List<Address> list= FactoryDao.getAddressDaoInstance().selectAllAddress(iduser);
        PageCount pageCount=new PageCount(list,5);
        list=pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("AddressList",list);
        //request.getSession().setAttribute("PersonList",list);
        request.setAttribute("pageCount",pageCount.PageCount());
        //request.getSession().setAttribute("pageCount",pageCount.PageCount());

        System.out.println(list.size());
        System.out.println(list.get(0).getAddress());
        request.getRequestDispatcher("vipAdress.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        int idaddress=Integer.parseInt(request.getParameter("idaddress"));
        int iduser=Integer.parseInt(request.getParameter("iduser"));
        Address address=new Address();
        address.setIduser(iduser);
        address.setIdaddress(idaddress);

        if(FactoryDao.getAddressDaoInstance().deleteAddress(address)){
            response.getWriter().print("<script>alert('添加成功');</script>");
        }else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='Address'</script>");
    }

    private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Person person=(Person)request.getSession().getAttribute("user");
        int iduser=person.getId();

        Address address=new Address();

        String address1=request.getParameter("address");
        address1=new String(address1.getBytes("iso-8859-1"),"utf-8");

        String postcode=request.getParameter("postcode");
        postcode=new String(postcode.getBytes("iso-8859-1"),"utf-8");

        String phone=request.getParameter("phone");
        phone=new String(phone.getBytes("iso-8859-1"),"utf-8");

        String receiver=request.getParameter("receiver");
        receiver=new String(receiver.getBytes("iso-8859-1"),"utf-8");

        address.setAddress(address1);
        address.setPhone(phone);
        address.setPostcode(postcode);
        address.setReceiver(receiver);
        address.setIduser(iduser);

        if(FactoryDao.getAddressDaoInstance().addAddress(address)){
            response.getWriter().print("<script>alert('添加成功');</script>");
        }else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='Address'</script>");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int iduser=Integer.parseInt(request.getParameter("iduser"));
        int idaddress=Integer.parseInt(request.getParameter("idaddress"));
        Address address=FactoryDao.getAddressDaoInstance().singleAddress(iduser,idaddress);
        request.getSession().setAttribute("ADDRESS",address);
        try {
            response.sendRedirect("vipUpAdress.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Address address=new Address();

        int iduser=Integer.parseInt(request.getParameter("iduser"));
        int idaddress=Integer.parseInt(request.getParameter("idaddress"));

        String address1=request.getParameter("address");
        address1=new String(address1.getBytes("iso-8859-1"),"utf-8");

        String postcode=request.getParameter("postcode");
        postcode=new String(postcode.getBytes("iso-8859-1"),"utf-8");

        String phone=request.getParameter("phone");
        phone=new String(phone.getBytes("iso-8859-1"),"utf-8");

        String receiver=request.getParameter("receiver");
        receiver=new String(receiver.getBytes("iso-8859-1"),"utf-8");

        address.setAddress(address1);
        address.setPhone(phone);
        address.setPostcode(postcode);
        address.setReceiver(receiver);
        address.setIduser(iduser);
        address.setIdaddress(idaddress);

        if(FactoryDao.getAddressDaoInstance().updateAddress(address)){
            response.getWriter().print("<script>alert('添加成功');</script>");
        }else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='Address'</script>");

    }

    public static void main(String []s){
        List<Address> list= FactoryDao.getAddressDaoInstance().selectAllAddress(14);
        System.out.println(list.get(0));
        Address address=list.get(0);
        System.out.println(list);
    }
}
