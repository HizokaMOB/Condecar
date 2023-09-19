package crm.automotive.Models.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crm.automotive.Models.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeDaoImp implements IEmployeeDao
{
    @PersistenceContext
    private EntityManager em; //Interfaz que proporciona metodos para manipular la BD

    @Override
    @Transactional(readOnly = true) //transacción. Si algo falla durante la actualización del usuario, se revierte la transacción y se asegura que los cambios no se guarden en la base de datos.
    @SuppressWarnings("unchecked")
    public List<Employee> findAll()
    { 
        return em.createQuery("from Employee").getResultList();
    }

    @Override //sobrescribiendo un metodo de una super clase (IUsersDao)
    @Transactional
    public void save(Employee employee) 
    {
        if(employee.getId() != null && employee.getId()>0)
        {
            em.merge(employee); //Entidad existente, hay id, actualizar - cambios
        }
        else
        {
            em.persist(employee); //Nueva entidad, no hay id
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findOne(Long id) 
    {
        return em.find(Employee.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) 
    {
        Employee employee = findOne(id);
        em.remove(employee);
    }
}
