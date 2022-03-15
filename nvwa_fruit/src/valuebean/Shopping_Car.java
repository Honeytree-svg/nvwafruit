package valuebean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Shopping_Car{
    private int iduser=-1;
    private List<Order_Merchandise> order_merchandises=new ArrayList<>();

    public Shopping_Car() {
    }

    public List<Order_Merchandise> getOrder_merchandises() {
        return order_merchandises;
    }

    public void setOrder_merchandises(List<Order_Merchandise> order_merchandises) {
        this.order_merchandises = order_merchandises;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getSum(){
        DecimalFormat df=new DecimalFormat("0.00");
        double sum=0;
        for (Order_Merchandise o:order_merchandises) {
            sum+=o.getDoubleSum();
        }
        return df.format(sum);
    }

}
