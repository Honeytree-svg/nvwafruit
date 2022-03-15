package servlet;

import bean.PageCount;
import dao.factory.FactoryDao;
import valuebean.Category;
import valuebean.Merchandise;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.jspsmart.upload.*;
import valuebean.Person;

public class MerchandiseServlet extends HttpServlet {
    private ServletConfig config;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
    }

    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) {
            select(request, response);
        } else if (type.equals("delete")) {
            delete(request, response);
        } else if (type.equals("add")) {
            add(request, response);
        } else if (type.equals("update_do")) {
            updateDo(request, response);
        } else if (type.equals("updateSingle")) {
            updateSingle(request, response);
        }else if (type.equals("selectCategory")){
            selectCategory(request,response);
        }else if (type.equals("idmerchandise")){
            selectIdMerchandise(request,response);
        }else if (type.equals("selectName")){
            selectName(request,response);
        }else if (type.equals("selectLike")){
            selectLike(request,response);
        }else if(type.equals("productAll")){
            productAll(request,response);
        }else if (type.equals("updateAuditState")){
            auditState(request,response);
        }else if (type.equals("audit")){
            audit(request,response);
        }
    }

    private void updateDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        int idmerchandise = Integer.parseInt(request.getParameter("idmerchandise"));
        String name = request.getParameter("merchandiseName");
        int category = Integer.parseInt(request.getParameter("merchandiseCategory"));
        double price = Double.parseDouble(request.getParameter("merchandisePrice"));
        int idseller = Integer.parseInt(request.getParameter("merchandiseIdseller"));
        int discount = Integer.parseInt(request.getParameter("merchandiseDiscount"));
        String photo = request.getParameter("merchandisePhoto");
        name = new String(name.getBytes("iso-8859-1"), "utf-8");
        photo = request.getParameter("merchandisePhoto");
        Merchandise merchandise = new Merchandise();
        merchandise.setPhoto(photo);
        merchandise.setDisconut(discount);
        merchandise.setPrice(price);
        merchandise.setCategory(category);
        merchandise.setName(name);
        merchandise.setIdmerchandise(idmerchandise);
        if (FactoryDao.getMerchandiseDaoInstance().updateMerchandise(merchandise)) {
            response.getWriter().print("<script>alert('修改成功');</script>");
        } else {
            response.getWriter().print("<script>alert('修改失败');</script>");
        }
        response.getWriter().print("<script>location.href='Category'</script>");

    }

    private void updateSingle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        int idmerchandise = Integer.parseInt(request.getParameter("idmerchandise"));
        Merchandise merchandise = FactoryDao.getMerchandiseDaoInstance().selectSingle(idmerchandise);
        request.getSession().setAttribute("updateMerchandise", merchandise);
        try {
            response.sendRedirect("Backstage/updateMerchandise.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        List<Merchandise> list=new ArrayList<>();

        Person person=(Person)request.getSession().getAttribute("user");
        if(person==null||person.getIdentification()==1){
            list=FactoryDao.getMerchandiseDaoInstance().selectAll();
            PageCount pageCount = new PageCount(list, 12);
            request.setAttribute("pageCount", pageCount.PageCount());
            list = pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("MerchandiseList", list);
            request.getRequestDispatcher("productAll.jsp").forward(request, response);

        }else if(person.getIdentification()==0){
            list =FactoryDao.getMerchandiseDaoInstance().selectAll();
            PageCount pageCount = new PageCount(list, 8);
            request.setAttribute("pageCount", pageCount.PageCount());
            list = pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("MerchandiseList", list);
            request.getRequestDispatcher("Backstage/inventory.jsp").forward(request, response);
        }else if (person.getIdentification()==2){
            list=FactoryDao.getMerchandiseDaoInstance().selectIdAll(person.getId());
            PageCount pageCount = new PageCount(list, 8);
            request.setAttribute("pageCount", pageCount.PageCount());
            list = pageCount.getPageList(Integer.parseInt(page));
            request.setAttribute("MerchandiseList", list);
            request.getRequestDispatcher("Backstage/inventory.jsp").forward(request, response);
        }

    }
//    public static void main(String []s){
//        List<Merchandise> list =new ArrayList();
//        list=FactoryDao.getMerchandiseDaoInstance().selectAll();
//        DecimalFormat df = new DecimalFormat("0.00");
//
//        System.out.println(list.get(0).getPrice());
//        System.out.println(list.get(0).getDisconut());
//        System.out.println(list.get(0).getDisconut()*list.get(0).getPrice());
//    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        int idmerchandise = Integer.parseInt(request.getParameter("idmerchandise"));
        if (FactoryDao.getMerchandiseDaoInstance().deleteMerchandise(idmerchandise)) {
            response.getWriter().print("<script>alert('删除成功');</script>");
        } else {
            response.getWriter().print("<script>alert('删除失败');</script>");
        }
        response.getWriter().print("<script>location.href='Merchandise'</script>");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.setCharacterEncoding("GB2312");
        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().print("<script>location.href='login.jsp'</script>");
            return;
        }
        // 传输过程乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 存储乱码
        response.setContentType("text/html;charset=utf-8");
        String imageName = "";
        String fileString = getServletContext().getRealPath("/") + "upload";
        System.out.println(fileString);
        java.io.File file = new java.io.File(fileString);
        // 判断文件夹是否存在，否则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        // 声明上传对象
        System.out.println("AAAAAAAAAAAAAA");
        SmartUpload smartUpload = new SmartUpload();
        System.out.println("BBBBBBBBBBBB");
        // 初始化，因为PageContext是JSP对象无法使用，调用ServletConfig初始化
        smartUpload.initialize(getServletConfig(), request, response);
        // 文件上传大小
        smartUpload.setMaxFileSize(1020 * 1024 * 100);
        // 总文件大小
        smartUpload.setTotalMaxFileSize(1020 * 1024 * 100);
        // 允许上传文件类型
        smartUpload.setAllowedFilesList("jpg,png,gif,jpeg");
        try {
            // 执行上传
            smartUpload.upload();
            // 获得上传文件名字
            imageName = smartUpload.getFiles().getFile(0).getFileName();
            // 保存到真实目录
            smartUpload.save(fileString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person person=(Person)request.getSession().getAttribute("user");
        int idseller=person.getId();
        Person person1=new Person();
        person1.setId(idseller);
        String name=smartUpload.getRequest().getParameter("merchandiseName");
        int category=Integer.parseInt(smartUpload.getRequest().getParameter("merchandiseCategory"));
        double price=Double.parseDouble(smartUpload.getRequest().getParameter("merchandisePrice"))*0.1;
        int discount=Integer.parseInt(smartUpload.getRequest().getParameter("merchandiseDiscount"));
        Merchandise merchandise=new Merchandise();
        merchandise.setName(name);
        merchandise.setCategory(category);
        merchandise.setPrice(price);
        merchandise.setDisconut(discount);
        merchandise.setPhoto(imageName);
        person1.setId(idseller);

        if(FactoryDao.getMerchandiseDaoInstance().addMerchandise(merchandise,person1)) {
            response.getWriter().print("<script>alert('添加成功');</script>");
        }
        else {
            response.getWriter().print("<script>alert('添加失败');</script>");
        }
        response.getWriter().print("<script>location.href='Merchandise'</script>");

    }

    public void selectCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AAAAAAAAAA");
        int idcategory = Integer.parseInt(request.getParameter("idcategory"));
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        Person person=(Person)request.getSession().getAttribute("user");
        List<Merchandise> list=FactoryDao.getMerchandiseDaoInstance().selectCategory(idcategory);
        PageCount pageCount = new PageCount(list, 12);
        list = pageCount.getPageList(Integer.parseInt(page));

        List<Category> categoryList=FactoryDao.getCategoryDaoInstance().selectAll();

        request.setAttribute("MerchandiseList", list);
        request.setAttribute("CategoryList", categoryList);
        request.setAttribute("pageCount", pageCount.PageCount());
        System.out.println("BBBBBBB");
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    public void selectIdMerchandise(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idmerchandise = Integer.parseInt(request.getParameter("idmerchandise"));

        List<Category> categoryList=FactoryDao.getCategoryDaoInstance().selectAll();
        List<Merchandise> list =FactoryDao.getMerchandiseDaoInstance().selectIdMerchandise(idmerchandise);
        Merchandise merchandise=list.get(0);

        request.setAttribute("Merchandise",merchandise);
        request.setAttribute("CategoryList", categoryList);
        request.getRequestDispatcher("proinfo.jsp").forward(request, response);


    }


    public void selectName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println(name);
        List<Category> categoryList=FactoryDao.getCategoryDaoInstance().selectAll();
        Merchandise merchandise = FactoryDao.getMerchandiseDaoInstance().selectName(name);
        System.out.println(merchandise.toString());
        request.setAttribute("Merchandise", merchandise);
        request.setAttribute("CategoryList", categoryList);
        request.getRequestDispatcher("proinfo.jsp").forward(request, response);
    }

    public void selectLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String page = request.getParameter("page");
        String namelike = request.getParameter("namelike");
        System.out.println(namelike);
        namelike = new String(namelike.getBytes("iso-8859-1"),"utf-8");
        if (page == null) {
            page = "1";
        }
        List<Merchandise> list =new ArrayList();
        list=FactoryDao.getMerchandiseDaoInstance().selectLike(namelike);
        PageCount pageCount = new PageCount(list, 12);
        list = pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("MerchandiseList", list);
        request.setAttribute("pageCount", pageCount.PageCount());
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    public void productAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        List<Merchandise> list=FactoryDao.getMerchandiseDaoInstance().selectAll();
        PageCount pageCount = new PageCount(list, 12);
        list = pageCount.getPageList(Integer.parseInt(page));

        List<Category> categoryList=FactoryDao.getCategoryDaoInstance().selectAll();

        request.setAttribute("MerchandiseList", list);
        request.setAttribute("CategoryList", categoryList);
        request.setAttribute("pageCount", pageCount.PageCount());
        request.getRequestDispatcher("productAll.jsp").forward(request, response);
    }

    public void auditState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int auditState=Integer.parseInt(request.getParameter("AuditState"));
        int idmerchandise=Integer.parseInt(request.getParameter("idmerchandise"));
        if (FactoryDao.getMerchandiseDaoInstance().updateAuditState(auditState,idmerchandise)){
            response.getWriter().print("<script>alert('修改成功');</script>");
        } else {
            response.getWriter().print("<script>alert('修改失败');</script>");
        }
        List<Merchandise> merchandiseList=FactoryDao.getMerchandiseDaoInstance().selectAuditState(0);
        PageCount pageCount = new PageCount(merchandiseList, 10);
        merchandiseList = pageCount.getPageList(Integer.parseInt(page));

        request.setAttribute("pageCount", pageCount.PageCount());
        request.setAttribute("MerchandiseList",merchandiseList);
        request.getRequestDispatcher("Backstage/audit.jsp").forward(request, response);
    }

    public void audit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int auditState;
        if (request.getParameter("AuditState")==null){
            auditState=0;
        }else {
            auditState=Integer.parseInt(request.getParameter("AuditState"));
        }
        List<Merchandise> merchandiseList=FactoryDao.getMerchandiseDaoInstance().selectAuditState(auditState);
        PageCount pageCount = new PageCount(merchandiseList, 10);
        merchandiseList = pageCount.getPageList(Integer.parseInt(page));
        request.setAttribute("pageCount", pageCount.PageCount());
        request.setAttribute("MerchandiseList",merchandiseList);
        request.getRequestDispatcher("Backstage/audit.jsp").forward(request, response);
    }

    public static void main(String []s){
        Merchandise merchandise = FactoryDao.getMerchandiseDaoInstance().selectName("葡萄");
        System.out.println(merchandise.toString());
    }
}

