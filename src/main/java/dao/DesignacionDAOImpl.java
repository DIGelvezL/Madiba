package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Designacion;

@Stateless
public class DesignacionDAOImpl {
	
	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Designacion> findAll() {
		return em.createNamedQuery("Designacion.findAll").getResultList();
	}

	public void insert(Designacion designacion) {
		em.persist(designacion);
	}

	public void update(Designacion designacion) {
		em.merge(designacion);
	}

	public void delete(Designacion designacion) {
		em.remove(em.find(Designacion.class, designacion.getIdDesignacion()));
	}

	public Designacion findById(Integer id) {
		Query q = em.createNamedQuery("Designacion.findById");
        q.setParameter("idDesignacion", id);
		return (Designacion) q.getSingleResult();
	}

}
