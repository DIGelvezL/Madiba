package service;

import java.util.List;

import vo.ParteVO;

public interface ParteService {
	
	public List<ParteVO> findAll();

	public void insert(ParteVO parteVO);

	public void update(ParteVO parteVO);

	public void delete(ParteVO parteVO);
	
	public ParteVO findById(Integer id);
	
	public Long findMaxId();
}
