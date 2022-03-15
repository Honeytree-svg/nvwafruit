package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import valuebean.Merchandise;
import valuebean.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CollectionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
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
        }
    }

    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Person person=(Person)request.getSession().getAttribute("user");
        System.out.println(person.getId());
        List<Merchandise> merchandiseList = FactoryDao.getCollectionDaoInstance().seletAll(person.getId());
        PageCount pageCount = new PageCount(merchandiseList, 5);
        merchandiseList = pageCount.getPageList(Integer.parseInt(page));
        System.out.println("select");
        request.setAttribute("pageCount", pageCount.PageCount());
        request.setAttribute("MerchandiseList",merchandiseList);
        request.getRequestDispatcher("vipCollection.jsp").forward(request, response);
    }


    public void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        int idmerchandise=Integer.parseInt(request.getParameter("idmerchandise"));
        Person person=(Person)request.getSession().getAttribute("user");
        if(FactoryDao.getCollectionDaoInstance().delete(person.getId(),idmerchandise)){
            response.getWriter().print("<script>alert('删除成功');</script>");
        } else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        List<Merchandise> merchandiseList = FactoryDao.getCollectionDaoInstance().seletAll(person.getId());
        PageCount pageCount = new PageCount(merchandiseList, 5);
        merchandiseList = pageCount.getPageList(Integer.parseInt(page));
        System.out.println("delete");
        request.setAttribute("pageCount", pageCount.PageCount());
        request.setAttribute("MerchandiseList",merchandiseList);
        request.getRequestDispatcher("vipCollection.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        int idmerchandise=Integer.parseInt(request.getParameter("idmerchandise"));
        Person person=(Person)request.getSession().getAttribute("user");
        if(FactoryDao.getCollectionDaoInstance().addCollection(person.getId(),idmerchandise)) {
            response.getWriter().print("<script>alert('收藏成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('收藏失败');</script>");
        }
        request.getRequestDispatcher("Collection").forward(request, response);
    }
}
