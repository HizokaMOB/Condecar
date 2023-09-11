package crm.automotive.Models.Dao;

import java.util.List;

import crm.automotive.Models.Entity.Usuario;

public interface IUsuarioDao 
{
    public List<Usuario> findAll();
    public void save(Usuario usuario);
    public Usuario findOne(Long id);
    public void delete(Long id);  
}
