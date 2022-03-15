package dao;

import valuebean.Address;

import java.util.List;

public interface AddressDao {
    abstract public boolean addAddress(Address address);
    abstract public boolean deleteAddress(Address address);
    abstract public boolean updateAddress(Address address);
    abstract public List selectAllAddress(int iduser);
    abstract public Address singleAddress(int iduser,int idaddress);
}
