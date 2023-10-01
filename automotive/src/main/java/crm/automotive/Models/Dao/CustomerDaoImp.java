package crm.automotive.Models.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crm.automotive.Models.Entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomerDaoImp implements ICustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {

        return em.createQuery("from Customer").getResultList();

    }

    @Override
    @Transactional
    public void save(Customer customer) {

        if (customer.getId() != null && customer.getId() > 0) {
            em.merge(customer);
        } else {
            em.persist(customer);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Customer findOne(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = findOne(id);
        em.remove(customer);
    }

}
