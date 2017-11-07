package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Agenda;

@Stateless
public class AgendaDAOImpl {

	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Agenda> findAll() {
		return em.createNamedQuery("Agenda.findAll").getResultList();
	}

	public void insert(Agenda agenda) {
		em.persist(agenda);
	}

	public void update(Agenda agenda) {
		em.merge(agenda);
	}

	public void delete(Agenda agenda) {
		em.remove(em.find(Agenda.class, agenda.getIdAgenda()));
	}

	public Agenda findById(Integer id) {
		Query q = em.createNamedQuery("Audiencia.findById");
        q.setParameter("idAgenda", id);
		return (Agenda) q.getSingleResult();
	}
	
	public Long findMaxId() {
		Query q = em.createQuery("SELECT MAX(a.idAgenda) FROM Agenda a");
		return (Long) q.getSingleResult();
	}
}
