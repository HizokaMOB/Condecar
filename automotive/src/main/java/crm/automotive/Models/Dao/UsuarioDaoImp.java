package crm.automotive.Models.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crm.automotive.Models.Entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDaoImp implements IUsuarioDao
{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Usuario> findAll()
    { 
        return em.createQuery("from Usuario").getResultList();
    }

    @Override
    @Transactional
    public void save(Usuario usuario) 
    {
        if(usuario.getId() != null && usuario.getId()>0)
        {
            em.merge(usuario);
        }
        else
        {
            em.persist(usuario);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findOne(Long id) 
    {
        return em.find(Usuario.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) 
    {
        Usuario usuario = findOne(id);
        em.remove(usuario);
    }
}
