package service;

import java.util.List;

import javax.ejb.Local;

import vo.AgendaVO;

@Local
public interface AgendaService {

	public List<AgendaVO> findAll();

	public void insert(AgendaVO agendaVO);

	public void update(AgendaVO agendaVO);

	public void delete(AgendaVO agendaVO);
	
	public AgendaVO findById(Integer id);
	
	public Long findMaxId();
}
