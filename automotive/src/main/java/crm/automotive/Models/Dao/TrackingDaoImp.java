package crm.automotive.Models.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crm.automotive.Models.Entity.Tracking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TrackingDaoImp implements ITrackingDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Tracking> findAll() {

        return em.createQuery("from Tracking").getResultList();

    }

    @Override
    @Transactional
    public void save(Tracking tracking) {

        if (tracking.getId() != null && tracking.getId() > 0) {
            em.merge(tracking);
        } else {
            em.persist(tracking);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Tracking findOne(Long id) {
        return em.find(Tracking.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Tracking tracking = findOne(id);
        em.remove(tracking);
    }
}
