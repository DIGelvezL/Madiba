package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Audiencia;

@Stateless
public class AudienciaDAOImpl {

	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Audiencia> findAll() {
		return em.createNamedQuery("Audiencia.findAll").getResultList();
	}

	public void insert(Audiencia audiencia) {
		em.persist(audiencia);
	}

	public void update(Audiencia audiencia) {
		em.merge(audiencia);
	}

	public void delete(Audiencia audiencia) {
		em.remove(em.find(Audiencia.class, audiencia.getIdAudiencia()));
	}

	public Audiencia findById(Integer id) {
		Query q = em.createNamedQuery("Audiencia.findById");
        q.setParameter("idAudiencia", id);
		return (Audiencia) q.getSingleResult();
	}
	
	public Long findMaxId() {
		Query q = em.createQuery("SELECT MAX(a.idAudiencia) FROM Audiencia a");
		return (Long) q.getSingleResult();
	}
}
