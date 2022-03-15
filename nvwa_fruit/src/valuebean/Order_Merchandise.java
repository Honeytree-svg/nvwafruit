package valuebean;

import dao.factory.FactoryDao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order_Merchandise {
    private Merchandise merchandise=new Merchandise();
    private int quantity=-1;
    private double sum=0;

    public Order_Merchandise(){
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        sum=Double.parseDouble(getSum());
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
        sum=Double.parseDouble(getSum());
    }

    public String getSum(){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(merchandise.getPrice()*merchandise.getDisconut()*quantity);
    }

    public double getDoubleSum(){
        return merchandise.getPrice()*merchandise.getDisconut()*quantity;
    }


}
