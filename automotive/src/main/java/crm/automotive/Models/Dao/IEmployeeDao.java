package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Employee;

public interface IEmployeeDao
{
    public List<Employee> findAll();
    public void save(Employee employee);
    public Employee findOne(Long id);
    public void delete(Long id);  
}
