package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Conciliador;

@Stateless
public class ConciliadorDAOImpl {
	
	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Conciliador> findAll() {
		return em.createNamedQuery("Conciliador.findAll").getResultList();
	}

	public void insert(Conciliador conciliador) {
		em.persist(conciliador);
	}

	public void update(Conciliador conciliador) {
		em.merge(conciliador);
	}

	public void delete(Conciliador conciliador) {
		em.remove(em.find(Conciliador.class, conciliador.getIdConciliador()));
	}

	public Conciliador findById(Integer id) {
		Query q = em.createNamedQuery("Conciliador.findById");
        q.setParameter("idConciliador", id);
		return (Conciliador) q.getSingleResult();
	}

}
