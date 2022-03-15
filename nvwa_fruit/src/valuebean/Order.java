package valuebean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int iduser=-1;
    private List<Order_Merchandise> order_merchandises=new ArrayList<>();
    private int idorder=-1;
    private int state=0;//0为准备状态，1为发货状态，2为退货状态，3为完结状态,4为取消状态
    private String CreateTime="";
    private String PayTime="";
    private String DispatchTime="";
    private String OverTime="";
    private int PayState=0;//0为未付款，1为付款
    private int idaddress=-1;
    private String idStringOrder="";

    public Order() {
    }

    public int getIdorder() {
        return idorder;
    }
    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    public String getPayTime() {
        return PayTime;
    }
    public void setPayTime(String payTime) {
        PayTime = payTime;
    }
    public String getDispatchTime() {
        return DispatchTime;
    }
    public void setDispatchTime(String dispatchTime) {
        DispatchTime = dispatchTime;
    }
    public String getOverTime() {
        return OverTime;
    }
    public void setOverTime(String overTime) {
        OverTime = overTime;
    }
    public double getSum() {
        double sum=0;
        for (Order_Merchandise o : order_merchandises) {
            sum+=o.getDoubleSum();
        }
        return sum;
    }
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public List<Order_Merchandise> getOrder_merchandises() {
        return order_merchandises;
    }

    public void setOrder_merchandises(List<Order_Merchandise> order_merchandises) {
        this.order_merchandises = order_merchandises;
    }

    public int getPayState() {
        return PayState;
    }

    public void setPayState(int payState) {
        PayState = payState;
    }

    public String getMerchandiseName(){
        String string="";
        for (Order_Merchandise tmp:order_merchandises) {
            string=string+"+"+tmp.getMerchandise().getName();
        }
        return  string;
    }

    public int getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(int idaddress) {
        this.idaddress = idaddress;
    }

    public double getSumDiscount(){
        double sum=0;
        for (Order_Merchandise tmp:order_merchandises) {
            sum=sum+tmp.getMerchandise().getPriceDiscount();
        }
        return sum;
    }

    public String getIdStringOrder() {
        return idStringOrder;
    }

    public void setIdStringOrder(String idStringOrder) {
        this.idStringOrder = idStringOrder;
    }

    public String getBehindDiscoundPrice(){
        DecimalFormat df=new DecimalFormat("0.00");
        return df.format(getSum()-getSumDiscount());
    }
}
