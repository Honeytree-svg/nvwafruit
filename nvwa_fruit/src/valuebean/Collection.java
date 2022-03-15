package valuebean;

public class Collection {
    private int iduser =-1;
    private int idmerchandise=-1;

    public Collection(int iduser, int idmerchandise) {
        this.iduser = iduser;
        this.idmerchandise = idmerchandise;
    }

    public Collection() {
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdmerchandise() {
        return idmerchandise;
    }

    public void setIdmerchandise(int idmerchandise) {
        this.idmerchandise = idmerchandise;
    }
}
