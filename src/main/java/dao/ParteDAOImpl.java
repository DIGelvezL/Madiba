package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Parte;

@Stateless
public class ParteDAOImpl {
	
	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Parte> findAll() {
		return em.createNamedQuery("Parte.findAll").getResultList();
	}

	public void insert(Parte Parte) {
		em.persist(Parte);
	}

	public void update(Parte Parte) {
		em.merge(Parte);
	}

	public void delete(Parte Parte) {
		em.remove(em.find(Parte.class, Parte.getIdParte()));
	}

	public Parte findById(Integer id) {
		Query q = em.createNamedQuery("Parte.findById");
        q.setParameter("idParte", id);
		return (Parte) q.getSingleResult();
	}
	
	public Long findMaxId() {
		Query q = em.createQuery("SELECT MAX(p.idParte) FROM Parte p");
		return (Long) q.getSingleResult();
	}

}
