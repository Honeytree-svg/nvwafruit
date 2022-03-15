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
import java.util.Calendar;
import java.util.List;
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String type=request.getParameter("type");
        if(type==null){
            select(request,response);
        }else if(type.equals("delete")) {
            delete(request,response);
        }else if(type.equals("add")){
            add(request,response);
        }else if(type.equals("selectIdorderComment")) {
            selectIdorderComment(request,response);
        }else if(type.equals("selectIdMerchandiseCommnet")) {
            selectIdMerchandiseCommnet(request,response);
        }else if(type.equals("evaluateJsp")){
            evaluateJsp(request,response);
        }else if(type.equals("commentJsp")){
            int idorder=Integer.parseInt(request.getParameter("idorder"));
            int idmerchandise=Integer.parseInt(request.getParameter("idorder"));
            System.out.println(idmerchandise);
            request.setAttribute("idorder",idorder);
            request.setAttribute("idmerchandise",idmerchandise);
            request.getRequestDispatcher("comment.jsp").forward(request,response);
        }
    }

    private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        List<Comment> list= FactoryDao.getCommentDaoInstance().selectAll();
        PageCount pageCount=new PageCount(list,10);
        list=pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("CommentList",list);
        request.setAttribute("pageCount",pageCount.PageCount());
        request.getRequestDispatcher("Backstage/warning.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idcomment=Integer.parseInt(request.getParameter("idcomment"));
        if(FactoryDao.getCommentDaoInstance().deleteComment(idcomment)){
            response.getWriter().print("<script>alert('删除成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        response.getWriter().print("<script>location.href='Comment'</script>");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user")==null){
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        int iduser=Integer.parseInt(request.getParameter("iduser"));
        int idMerchandise=Integer.parseInt(request.getParameter("idMerchandise"));
        String comment=request.getParameter("comment");
        String evaluate=request.getParameter("bmon");
        Calendar calendar=Calendar.getInstance();
        String pdate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MARCH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        comment=new String(comment.getBytes("iso-8859-1"),"utf-8");
        evaluate=new String(evaluate.getBytes("iso-8859-1"),"utf-8");
        pdate=new String(pdate.getBytes("iso-8859-1"),"utf-8");
        if(FactoryDao.getCommentDaoInstance().addComment(idorder,iduser,idMerchandise,comment,pdate,evaluate)){
            response.getWriter().print("<script>alert('添加成功');</script>");
        }
        else{
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='vip.jsp'</script>");
    }

    private void selectIdorderComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idorder = Integer.parseInt(request.getParameter("idorder"));
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        List list =new ArrayList();
        Person person=(Person)request.getSession().getAttribute("user");
        if(person.getIdentification()==2){
            list =FactoryDao.getMerchandiseDaoInstance().selectIdAll(person.getId());
        }else {
            list=FactoryDao.getCommentDaoInstance().selectIdorder(idorder);
        }
        PageCount pageCount = new PageCount(list, 10);
        list = pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("CommentList", list);
        request.setAttribute("pageCount", pageCount.PageCount());

        request.getRequestDispatcher("comment.jsp").forward(request, response);
    }

    private void selectIdMerchandiseCommnet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idmerchandise = Integer.parseInt(request.getParameter("idmerchandise"));
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        List<Category>categoryList=FactoryDao.getCategoryDaoInstance().selectAll();
        request.setAttribute("CategoryList",categoryList);

        List<Comment> list=FactoryDao.getCommentDaoInstance().selectIdMerchandise(idmerchandise);
        PageCount pageCount = new PageCount(list, 10);
        list = pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("CommentList", list);
        request.setAttribute("pageCount", pageCount.PageCount());

        List<Merchandise> merchandiseList=FactoryDao.getMerchandiseDaoInstance().selectIdMerchandise(idmerchandise);
        Merchandise merchandise=merchandiseList.get(0);
        request.setAttribute("Merchandise", merchandise);

        List<Person> personList=new ArrayList<>();
        Person person=new Person();
        for (int i = 0; i < list.size(); i++) {
            person=FactoryDao.getPersonDaoInstance().selectSingle(Integer.toString(list.get(i).getIduser()));
            personList.add(person);
        }
        request.setAttribute("PersonList",personList);

        request.getRequestDispatcher("proinfo.jsp").forward(request, response);
    }

    private void evaluateJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idorder=Integer.parseInt(request.getParameter("idorder"));
        Order order=FactoryDao.getOrderDaoInstance().selectIdOrder(idorder);
        request.setAttribute("ORDER",order);
        request.getRequestDispatcher("evaluate.jsp").forward(request,response);
    }
}
