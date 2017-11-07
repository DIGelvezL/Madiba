package service;

import java.util.List;

import javax.ejb.Local;

import vo.AudienciaVO;

@Local
public interface AudienciaService {

	public List<AudienciaVO> findAll();

	public void insert(AudienciaVO audienciaVO);

	public void update(AudienciaVO audienciaVO);

	public void delete(AudienciaVO audienciaVO);
	
	public AudienciaVO findById(Integer id);
	
	public Long findMaxId();

}
