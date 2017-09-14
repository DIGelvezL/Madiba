package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Anexo;

@Stateless
public class AnexoDAOImpl {

	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Anexo> findAll() {
		return em.createNamedQuery("Anexo.findAll").getResultList();
	}

	public void insert(Anexo anexo) {
		em.persist(anexo);
	}

	public void update(Anexo anexo) {
		em.merge(anexo);
	}

	public void delete(Anexo anexo) {
		em.remove(em.find(Anexo.class, anexo.getIdAnexo()));
	}

	public Anexo findById(Integer id) {
		Query q = em.createNamedQuery("Anexo.findById");
        q.setParameter("idAnexo", id);
		return (Anexo) q.getSingleResult();
	}
	
	public Long findMaxId() {
		Query q = em.createQuery("SELECT MAX(a.idAnexo) FROM Anexo a");
		return (Long) q.getSingleResult();
	}

}
