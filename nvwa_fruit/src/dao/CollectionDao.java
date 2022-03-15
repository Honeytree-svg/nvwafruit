package dao;

import java.util.List;
public interface CollectionDao {
    public abstract boolean addCollection(int iduser,int idmerchandise);
    public abstract boolean delete(int iduser,int idmerchandise);
    public abstract List seletAll(int iduser);
}

