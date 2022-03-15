package dao.factory;

import dao.*;
import dao.impl.*;

public class FactoryDao {
    public static PersonDao getPersonDaoInstance(){
        return  new PersonDaoImpl();
    }

    public static NoteDao getNoteDaoInstance(){
        return  new NoteDaoImpl();
    }

    public static AddressDao getAddressDaoInstance(){
        return  new AddressDaoImpl();
    }

    public static CollectionDao getCollectionDaoInstance(){
        return  new CollectionDaoImpl();
    }

    public static CommentDao getCommentDaoInstance(){
        return  new CommentDaoImpl();
    }

    public static MerchandiseDao getMerchandiseDaoInstance(){
        return  new MerchandiseDaoImpl();
    }

    public static OrderDao getOrderDaoInstance(){
        return  new OrderDaoImpl();
    }

    public static Shopping_CarDao getShopping_CarDaoInstance(){ return  new Shopping_CarDaoImpl(); }

    public static CategoryDao getCategoryDaoInstance(){ return  new CategoryDaoImpl(); }

}
