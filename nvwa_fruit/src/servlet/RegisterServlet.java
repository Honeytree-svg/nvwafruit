package servlet;

import bean.Tools;
import dao.factory.FactoryDao;
import valuebean.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="reg.jsp";
        System.out.println(System.getProperty("file.encoding"));
        String name=request.getParameter("registName");
        name=new String(name.getBytes("iso-8859-1"),"utf-8");
        String password=request.getParameter("registPassword");
        //System.out.println(password);
        password=new String(password.getBytes("iso-8859-1"),"utf-8");
        //System.out.println(password);
        String password2=request.getParameter("registPassword2");
        password2=new String(password2.getBytes("iso-8859-1"),"utf-8");
        String validationCode=request.getParameter("validationCode").toLowerCase();
        int identification=Integer.parseInt(request.getParameter("identification"));
        //String location=request.getParameter("protocal");
        if(validationCode.equals(request.getSession().getAttribute("validation_code").toString())) {
            try {
                Person person =new Person();
                person.setName(name);
                person.setPassword(Tools.md5Encrypt(password));
               // System.out.println(Tools.md5Encrypt(password));
                person.setIdentification(identification);
                if(FactoryDao.getPersonDaoInstance().register(person)) {
                    path="login.jsp";
                } else {
                    request.setAttribute("err","系统错误，注册失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("err","验证码错误！");
        }
        request.getRequestDispatcher(path).forward(request,response);

    }

}
