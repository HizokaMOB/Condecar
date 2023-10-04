package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Suppliers;

public interface ISuppliersDao {

    public List<Suppliers> findAll();

    public void save(Suppliers suppliers);

    public Suppliers findOne(Long id);

    public void delete(Long id);

}
