package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Solicitud;

@Stateless
public class SolicitudDAOImpl {
	
	static String estadoGuardada = "GUARDADA";
	
	@PersistenceContext(unitName = "MadibaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Solicitud> findAll() {
		return em.createNamedQuery("Solicitud.findAll").getResultList();
	}

	public void insert(Solicitud solicitud) {
		em.persist(solicitud);
	}

	public void update(Solicitud solicitud) {
		em.merge(solicitud);
	}

	public void delete(Solicitud solicitud) {
		Solicitud solicit = em.find(Solicitud.class, solicitud.getIdSolicitud());
		em.remove(solicit);
	}

	public Solicitud findById(Integer id) {
		Query q = em.createNamedQuery("Solicitud.findById");
        q.setParameter("idSolicitud", id);
		return (Solicitud) q.getSingleResult();
	}
	
	public Solicitud findByEstado(String estado) {
		Query q = em.createQuery("SELECT s FROM Solicitud s WHERE s.estado = :estado");
        q.setParameter("estado", estado);
		return (Solicitud) q.getSingleResult();
	}
	
	public Long findMaxId() {
		Query q = em.createQuery("SELECT MAX(s.idSolicitud) FROM Solicitud s");
		return (Long) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findByEstadoGuardada() {
		Query q = em.createQuery("SELECT s FROM Solicitud s WHERE s.estado = '" + estadoGuardada + "'");
		return q.getResultList();
	}

}
