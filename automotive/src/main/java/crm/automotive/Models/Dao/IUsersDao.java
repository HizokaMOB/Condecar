package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Users;

public interface IUsersDao 
{
    public List<Users> findAll();
    public void save(Users user);
    public Users findOne(Long id);
    public void delete(Long id);  
}
