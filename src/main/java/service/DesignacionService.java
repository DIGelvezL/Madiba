package service;

import java.util.List;

import vo.DesignacionVO;;

public interface DesignacionService {

	public List<DesignacionVO> findAll();

	public void insert(DesignacionVO designacionVO);

	public void update(DesignacionVO designacionVO);

	public void delete(DesignacionVO designacionVO);
	
	public DesignacionVO findById(Integer id);
	
	public Long findMaxId();
}
