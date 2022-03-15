package dao;

import valuebean.Category;
import java.util.List;

public interface CategoryDao {
    public abstract boolean addCategory(String name ,String remark);
    public abstract boolean deleteCategory(int idcategory);
    public abstract boolean updateCategory(String name ,String remark,int idcategory);
    public abstract Category selectSingle(int idcategory);
    public abstract List selectAll();
}