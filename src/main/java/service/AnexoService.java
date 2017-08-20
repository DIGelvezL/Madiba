package service;

import java.util.List;

import javax.ejb.Local;

import vo.AnexoVO;



@Local
public interface AnexoService {
	
	public List<AnexoVO> findAll();

	public String insert(AnexoVO anexoVO);

	public void update(AnexoVO anexoVO);

	public void delete(AnexoVO anexoVO);
	
	public AnexoVO getById(AnexoVO anexoVO);

}
