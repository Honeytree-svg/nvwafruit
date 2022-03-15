package servlet;

import bean.PageCount;
import bean.Tools;
import dao.factory.FactoryDao;
import valuebean.Note;
import valuebean.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonServlet extends HttpServlet {

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
        } else if (type.equals("update")) {
            update(request, response);
        } else if (type.equals("update_do")) {
            updateDo(request, response);
        }else if (type.equals("addadmin")) {
            try {
                request.getRequestDispatcher("Backstage/addAdmin.jsp").forward(request,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("updatePwd")) {
            updatePwd(request, response);
        }else if (type.equals("singleselect")){
            singleselect(request, response);
        }else if (type.equals("vip_jsp")){
            request.getRequestDispatcher("vip.jsp").forward(request,response);
        }


    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    private void updatePwd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        Person tmp=(Person) request.getSession().getAttribute("user");
        String id=Integer.toString(tmp.getId());
        String old_pwd=request.getParameter("old_pwd");
        //System.out.println(old_pwd);
        String new_pwd=request.getParameter("new_pwd");
        //System.out.println(new_pwd);
        new_pwd=new String(new_pwd.getBytes("iso-8859-1"),"utf-8");
        //System.out.println(new_pwd);
        System.out.println(System.getProperty("file.encoding"));
        Person person=FactoryDao.getPersonDaoInstance().selectSingle(id);
        try{
            if (person.getPassword().equals(Tools.md5Encrypt(old_pwd))){
                person.setPassword(Tools.md5Encrypt(new_pwd));
                System.out.println(Tools.md5Encrypt(new_pwd));
                FactoryDao.getPersonDaoInstance().update(person);
                response.getWriter().print("<script>alert('修改成功！');</script>");

            }else {
                response.getWriter().print("<script>alert('原密码错误！');</script>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(person.getIdentification()==1)response.sendRedirect("vipPwd.jsp");
        if(person.getIdentification()==0)response.sendRedirect("Backstage/updatePwd.jsp");
        if(person.getIdentification()==2)response.sendRedirect("Backstage/updatePwd.jsp");
    }

    private void updateDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        Person person=(Person)request.getSession().getAttribute("user");
        String name=request.getParameter("name");
        name=new String(name.getBytes("iso-8859-1"),"utf-8");
        System.out.println("dsfsfsfd");
        String phone=request.getParameter("phone");
        phone=new String(phone.getBytes("iso-8859-1"),"utf-8");

        String sex=request.getParameter("sex");
        sex=new String(sex.getBytes("iso-8859-1"),"utf-8");

        person.setName(name);
        person.setPhone(phone);
        person.setSex(sex);

        if(FactoryDao.getPersonDaoInstance().update(person)) {
            request.getSession().setAttribute("user",person);
            response.getWriter().print("<script>alert('修改成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('修改失败');</script>");
        }
        response.getWriter().print("<script>location.href='vip.jsp'</script>");

    }

    private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id=request.getParameter("noteId");
        Note note=FactoryDao.getNoteDaoInstance().selectSingle(id);
        request.getSession().setAttribute("updateNote",note);
        try {
            response.sendRedirect("updatenote.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String page=request.getParameter("page");
        int identification=Integer.parseInt(request.getParameter("identification"));
       // System.out.println(identification);
        if(page==null) {
            page="1";
        }


        List list= FactoryDao.getPersonDaoInstance().selectAllPerson(identification);
        PageCount pageCount=new PageCount(list,10);
        list=pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("PersonList",list);
        //request.getSession().setAttribute("PersonList",list);
        request.setAttribute("pageCount",pageCount.PageCount());
        //request.getSession().setAttribute("pageCount",pageCount.PageCount());
        if(identification==0)request.getRequestDispatcher("Backstage/deleteAdmin.jsp").forward(request,response);
        if(identification==1)request.getRequestDispatcher("Backstage/deleteUser.jsp").forward(request,response);
        if(identification==2)request.getRequestDispatcher("Backstage/deleteSeller.jsp").forward(request,response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        int id=Integer.parseInt(request.getParameter("iduser"));
        if(FactoryDao.getPersonDaoInstance().delete(id)){
            response.getWriter().print("<script>alert('删除成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        int identification=Integer.parseInt(request.getParameter("identification"));
        if(identification==1)response.getWriter().print("<script>location.href='Person?identification=1'</script>");
        if(identification==2)response.getWriter().print("<script>location.href='Person?identification=2'</script>");
        if(identification==0)response.getWriter().print("<script>location.href='Person?identification=0'</script>");
    }
    private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        int identification=Integer.parseInt(request.getParameter("identification"));
        Person person =new Person();
        try {
            person.setName(name);
            person.setPassword(Tools.md5Encrypt(password));
            person.setIdentification(identification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(FactoryDao.getPersonDaoInstance().register(person)) {
            response.getWriter().print("<script>alert('添加成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='Person?identification=0'</script>");
    }

    private void singleselect(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    }
}
