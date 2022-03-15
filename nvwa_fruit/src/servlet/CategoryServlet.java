package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import valuebean.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        }else if(type.equals("updateSingle")) {
            updateSingle(request,response);
        }else if(type.equals("addMerchandise")){
            selectCategory(request,response);
        }
    }
    private void updateDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int idcategory=Integer.parseInt(request.getParameter("idcategory"));
        String name=request.getParameter("categoryname");
        String remark=request.getParameter("categoryremark");
        name=new String(name.getBytes("iso-8859-1"),"utf-8");
        remark=new String(remark.getBytes("iso-8859-1"),"utf-8");
        if(FactoryDao.getCategoryDaoInstance().updateCategory(name,remark,idcategory)) {
            response.getWriter().print("<script>alert('修改成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('修改失败');</script>");
        }
        response.getWriter().print("<script>location.href='Category'</script>");

    }

    private void updateSingle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int idcategory=Integer.parseInt(request.getParameter("idcategory"));
        Category category=FactoryDao.getCategoryDaoInstance().selectSingle(idcategory);
        request.getSession().setAttribute("updateCategory",category);
        try {
            response.sendRedirect("Backstage/updateCategory.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        List list= FactoryDao.getCategoryDaoInstance().selectAll();
        PageCount pageCount=new PageCount(list,10);
        list=pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("CategoryList",list);
        request.setAttribute("pageCount",pageCount.PageCount());
        request.getRequestDispatcher("Backstage/type.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        int idcategory=Integer.parseInt(request.getParameter("idcategory"));
        if(FactoryDao.getCategoryDaoInstance().deleteCategory(idcategory)){
            response.getWriter().print("<script>alert('删除成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        response.getWriter().print("<script>location.href='Category'</script>");
    }
    public void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String categoryName=request.getParameter("categoryname");
        String categoryRemark=request.getParameter("categoryremark");
        categoryName=new String(categoryName.getBytes("iso-8859-1"),"utf-8");
        categoryRemark=new String(categoryRemark.getBytes("iso-8859-1"),"utf-8");
        if(FactoryDao.getCategoryDaoInstance().addCategory(categoryName,categoryRemark)) {
            response.getWriter().print("<script>alert('添加成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='Category'</script>");
    }
    public void selectCategory(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List list= FactoryDao.getCategoryDaoInstance().selectAll();;
        request.setAttribute("CategoryList",list);
        System.out.println("CCCCCC");
        request.getRequestDispatcher("Backstage/addMerchandise.jsp").forward(request,response);
    }
}
