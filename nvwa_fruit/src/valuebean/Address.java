package valuebean;

public class Address {
    private int iduser=-1;
    private int idaddress=-1;
    private String address="";
    private String postcode="";
    private String receiver="";
    private String phone="";

    public Address(int iduser, int idaddress, String address, String postcode, String receiver, String phone) {
        this.iduser = iduser;
        this.idaddress = idaddress;
        this.address = address;
        this.postcode = postcode;
        this.receiver = receiver;
        this.phone = phone;
    }

    public Address() {
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(int idaddress) {
        this.idaddress = idaddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
