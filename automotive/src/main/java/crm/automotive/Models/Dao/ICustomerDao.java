package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Customer;

public interface ICustomerDao {

    public List<Customer> findAll();

    public void save(Customer customer);

    public Customer findOne(Long id);

    public void delete(Long id);

}
