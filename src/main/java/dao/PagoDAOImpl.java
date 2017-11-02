package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Pago;

@Stateless
public class PagoDAOImpl {
	
	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Pago> findAll() {
		return em.createNamedQuery("Pago.findAll").getResultList();
	}

	public void insert(Pago pago) {
		em.persist(pago);
	}

	public void update(Pago pago) {
		em.merge(pago);
	}

	public void delete(Pago pago) {
		em.remove(em.find(Pago.class, pago.getIdPago()));
	}

	public Pago findById(Integer id) {
		Query q = em.createNamedQuery("Pago.findById");
        q.setParameter("idPago", id);
		return (Pago) q.getSingleResult();
	}
	
	public Long findMaxId() {
		Query q = em.createQuery("SELECT MAX(p.idPago) FROM Pago p");
		return (Long) q.getSingleResult();
	}
	
	public Integer findMaxReferencia() {
		Query q = em.createQuery("SELECT MAX(p.referencia) FROM Pago p");
		return (Integer) q.getSingleResult();
	}

}
