package valuebean;

import java.text.DecimalFormat;

public class Merchandise {
    private int idmerchandise=-1;
    private String name="";
    private int category=-1;
    private double price=0.00;
    private int idseller=-1;
    private double disconut=-1;
    private String photo="";
    private int audit=0;//0为未审计，1为审计

    public Merchandise() {
    }

    public int getIdmerchandise() {
        return idmerchandise;
    }

    public void setIdmerchandise(int idmerchandise) {
        this.idmerchandise = idmerchandise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdseller() {
        return idseller;
    }

    public void setIdseller(int idseller) {
        this.idseller = idseller;
    }

    public double getDisconut() {
        return disconut;
    }

    public void setDisconut(double disconut) {
        this.disconut = disconut;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public  double getPriceDiscount(){
        return price*(1-disconut);
    }

    public String getDiscountPrice(){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price*disconut);
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return "Merchandise{" +
                "idmerchandise=" + idmerchandise +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", idseller=" + idseller +
                ", disconut=" + disconut +
                ", photo='" + photo + '\'' +
                ", audit=" + audit +
                '}';
    }
//    public static void main(String []s){
//        DecimalFormat df = new DecimalFormat("0.00");
//        double d1 = 12.00,d2=0.10;
//        System.out.println(df.format(d1*d2));
//    }
}
