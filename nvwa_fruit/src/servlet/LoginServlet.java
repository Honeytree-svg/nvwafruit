package servlet;

import bean.Tools;
import dao.factory.FactoryDao;
import valuebean.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String path="login.jsp";
        String name=request.getParameter("loginName");
        String password=null;
        int identification=-1;
        if(request.getParameter("role").equals("admin"))identification=0;
        if(request.getParameter("role").equals("user"))identification=1;
        if(request.getParameter("role").equals("seller"))identification=2;
       // System.out.println(identification);
        try{
            password= Tools.md5Encrypt(request.getParameter("loginPassword"));
        }catch (Exception e){
            e.printStackTrace();
        }
        String validationCode=request.getParameter("validationCode").toLowerCase();
        Person person=new Person();
        person.setName(name);
        person.setIdentification(identification);
        person.setPassword(password);
        try{
            if(!validationCode.equals(request.getSession().getAttribute("validation_code").toString()))
                request.setAttribute("err","验证码错误！");
            else {
                Person person1=FactoryDao.getPersonDaoInstance().Login(person);
                if(person1.getPassword().equals(person.getPassword())){
                    request.setAttribute("user",person1);
                    request.getSession().setAttribute("user",person1);
                    //System.out.println(request.getSession().getAttribute("user").toString());
                    if(identification==1)path="index.jsp";
                    if(identification==2)path="/Backstage/index.jsp";
                    if(identification==0)path="/Backstage/index.jsp";
                    request.getSession().setAttribute("identification",identification);
                }else {
                    request.setAttribute("err","用户名或密码错误！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            request.getRequestDispatcher(path).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
    }
}
