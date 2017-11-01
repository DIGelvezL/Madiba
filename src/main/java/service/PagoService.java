package service;

import java.util.List;

import javax.ejb.Local;

import vo.AnexoVO;
import vo.PagoVO;

@Local
public interface PagoService {

	public List<PagoVO> findAll();

	public void insert(PagoVO pagoVO);

	public void update(PagoVO pagoVO);

	public void delete(PagoVO pagoVO);
	
	public AnexoVO findById(Integer id);
	
	public Long findMaxId();

}
