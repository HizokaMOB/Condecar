package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Sales;

public interface ISalesDao {
    
    public List<Sales> findAll();

    public void save(Sales sales);

    public Sales findOne(Long id);

    public void delete(Long id);
}
