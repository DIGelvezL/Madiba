package service;

import java.util.List;

import javax.ejb.Local;

import vo.ConciliadorVO;

@Local
public interface ConciliadorService {
	
	public List<ConciliadorVO> findAll();

	public void insert(ConciliadorVO conciliadorVO);

	public void update(ConciliadorVO conciliadorVO);

	public void delete(ConciliadorVO conciliadorVO);
	
	public ConciliadorVO findById(Integer id);

}
