package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Tracking;

public interface ITrackingDao {
    
    public List<Tracking> findAll();

    public void save(Tracking tracking);

    public Tracking findOne(Long id);

    public void delete(Long id);
}
