package crm.automotive.Models.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crm.automotive.Models.Entity.Suppliers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SuppliersDaoImp implements ISuppliersDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Suppliers> findAll() {

        return em.createQuery("from Suppliers").getResultList();

    }

    @Override
    @Transactional
    public void save(Suppliers suppliers) {

        if (suppliers.getId() != null && suppliers.getId() > 0) {
            em.merge(suppliers);
        } else {
            em.persist(suppliers);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Suppliers findOne(Long id) {
        return em.find(Suppliers.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Suppliers suppliers = findOne(id);
        em.remove(suppliers);
    }

}
