package crm.automotive.Models.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crm.automotive.Models.Entity.Sales;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SalesDaoImp implements ISalesDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Sales> findAll() {

        return em.createQuery("from Sales").getResultList();

    }

    @Override
    @Transactional
    public void save(Sales sales) {

        if (sales.getId() != null && sales.getId() > 0) {
            em.merge(sales);
        } else {
            em.persist(sales);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Sales findOne(Long id) {
        return em.find(Sales.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Sales sales = findOne(id);
        em.remove(sales);
    }
}
