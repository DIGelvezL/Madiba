package service;

import java.util.List;

import javax.ejb.Local;

import vo.SolicitudResponseVO;
import vo.SolicitudVO;

@Local
public interface SolicitudService {

	public List<SolicitudVO> findAll();

	public void insert(SolicitudVO solicitudVO);

	public void update(SolicitudVO solicitudVO);

	public void delete(SolicitudVO solicitudVO);
	
	public SolicitudVO getById(Integer id);
	
	public SolicitudVO findByEstado(String estado);
	
	public Long findMaxId();
	
	public List<SolicitudResponseVO> findByEstadoGuardada();
}
