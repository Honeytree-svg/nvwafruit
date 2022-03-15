package valuebean;

public class Comment {
    private int idcomment=-1;
    private int iduser=-1;
    private int idmerchandise=-1;
    private String comment="";
    private String date="";
    private String evaluate="";
    private int idorder=-1;

    public Comment() {
    }

    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }
}
