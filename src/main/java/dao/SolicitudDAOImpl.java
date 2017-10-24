package dao;

import java.util.Date;
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
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> getById(Long id) {
		Query q = em.createQuery("SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud AND s.estado = 'GUARDADA'");
        q.setParameter("idSolicitud", id);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findById(Long id) {
		Query q = em.createQuery("SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud AND s.estado != 'GUARDADA'");
        q.setParameter("idSolicitud", id);
		return q.getResultList();
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
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findByParte(String identificacion, Date fechaInicial, Date fechaFinal, String tipoParte) {
		Query q = em.createQuery("SELECT s FROM Solicitud s, Parte p WHERE s.fecha between :fechaInicial AND :fechaFinal AND s.estado = 'GUARDADA' AND s.idSolicitud = p.solicitud.idSolicitud AND p.identificacion = :identificacion AND p.tipoParte=:tipoParte ORDER BY s.fecha");
        q.setParameter("identificacion", identificacion);
        q.setParameter("fechaInicial", fechaInicial);
        q.setParameter("fechaFinal", fechaFinal);
        q.setParameter("tipoParte", tipoParte);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findByConciliador(String identificacion, Date fechaInicial, Date fechaFinal) {
		Query q = em.createQuery("SELECT DISTINCT s FROM Solicitud s, Designacion d, Conciliador c WHERE s.estado = 'GUARDADA' AND s.fecha BETWEEN :fechaInicial AND :fechaFinal AND s.idSolicitud = d.solicitud.idSolicitud AND d.conciliador.idConciliador = c.idConciliador AND c.identificacion = :identificacion ORDER BY s.fecha");
        q.setParameter("identificacion", identificacion);
        q.setParameter("fechaInicial", fechaInicial);
        q.setParameter("fechaFinal", fechaFinal);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> findByFechas(Date fechaInicial, Date fechaFinal) {
		Query q = em.createQuery("SELECT s FROM Solicitud s WHERE s.estado = 'GUARDADA' AND s.fecha BETWEEN :fechaInicial AND :fechaFinal ORDER BY s.fecha");
        q.setParameter("fechaInicial", fechaInicial);
        q.setParameter("fechaFinal", fechaFinal);
		return q.getResultList();
	}

}
