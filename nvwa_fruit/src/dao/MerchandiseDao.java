package dao;
import valuebean.Merchandise;
import valuebean.Person;

import java.util.List;

public interface MerchandiseDao {
    public abstract boolean addMerchandise(Merchandise merchandise, Person person);
    public abstract boolean deleteMerchandise(int idmerchandise);
    public abstract boolean updateMerchandise(Merchandise merchandise);
    public abstract Merchandise selectSingle(int idmerchandise);
    public abstract List selectAll();
    public List selectIdAll(int idseller);
    public List selectCategory(int category);
    public List selectIdMerchandise(int idmerchandise);
    public Merchandise selectName(String name);
    public List selectLike(String name);
    public  abstract List<Merchandise> selectAuditState(int audit);
    public  abstract boolean updateAuditState(int audit,int idmerchandise);
}
