package valuebean;

public class Person {
    private int id=-1;
    private String name="";
    private String password="";
    private int identification=-1;//0管理员，1用户，2商人
    private String phone="";
    private String sex="";
    public Person(){

    }

    public Person(int id,String name,String password,int identification,String sex){
        this.id=id;
        this.name=name;
        this.password=password;
        this.identification=identification;
        this.sex=sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
