package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import valuebean.Note;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        }else if(type.equals("update")){
            update(request,response);
        }else if(type.equals("update_do")) {
            updateDo(request,response);
        }

    }

    private void updateDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String noteId=request.getParameter("noteId");
        String title=request.getParameter("notetitle");
        title=new String(title.getBytes("iso-8859-1"),"utf-8");
        String content=request.getParameter("notecontent");
        content=new String(content.getBytes("iso-8859-1"),"utf-8");
        String personName=(String)request.getSession().getAttribute("user");
        if(FactoryDao.getNoteDaoInstance().updateNote(title,content,noteId)) {
            response.getWriter().print("<script>alert('修改成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('修改失败');</script>");
        }
        response.getWriter().print("<script>location.href='/SZ234/Note'</script>");

    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String id=request.getParameter("noteId");
        Note note=FactoryDao.getNoteDaoInstance().selectSingle(id);
        request.getSession().setAttribute("updateNote",note);
        try {
            response.sendRedirect("updatenote.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String page=request.getParameter("page");
        if(page==null) {
            page="1";
        }
        List list= FactoryDao.getNoteDaoInstance().seletAll();
        PageCount pageCount=new PageCount(list,2);
        list=pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("noteList",list);
        request.setAttribute("pageCount",pageCount.PageCount());
        request.getRequestDispatcher("note_list.jsp").forward(request,response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        int id=Integer.parseInt(request.getParameter("noteId"));
        if(FactoryDao.getNoteDaoInstance().delete(id)){
            response.getWriter().print("<script>alert('删除成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        response.getWriter().print("<script>location.href='/SZ234/Note'</script>");
    }
    public void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String title=request.getParameter("notetitle");
        title=new String(title.getBytes("iso-8859-1"),"utf-8");
        String content=request.getParameter("notecontent");
        content=new String(content.getBytes("iso-8859-1"),"utf-8");
        String personName=(String)request.getSession().getAttribute("user");
        if(FactoryDao.getNoteDaoInstance().addNote(title,content,personName)) {
            response.getWriter().print("<script>alert('添加成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='/SZ234/Note'</script>");
    }
}
