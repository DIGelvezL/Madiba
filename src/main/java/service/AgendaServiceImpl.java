package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.modelmapper.ModelMapper;

import dao.AgendaDAOImpl;
import entidades.Agenda;
import vo.AgendaVO;

@Stateless
public class AgendaServiceImpl implements AgendaService {

	@EJB private AgendaDAOImpl agendaDAO;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<AgendaVO> findAll() {
		List<Agenda> agendaList;
		List<AgendaVO> agendaVOList;
		
		agendaList = agendaDAO.findAll();
		if (agendaList != null) {

			agendaVOList = new ArrayList<>();
			for (Agenda item : agendaList) {
				agendaVOList.add(modelMapper.map(item, AgendaVO.class));
			}

			return agendaVOList;
		} else
			return new ArrayList<>();
	}

	@Override
	public void insert(AgendaVO agendaVO) {
		Agenda agenda = modelMapper.map(agendaVO, Agenda.class);
		
		if(agenda != null){
			agendaDAO.insert(agenda);
		}
	}

	@Override
	public void update(AgendaVO agendaVO) {
		Agenda agenda = modelMapper.map(agendaVO, Agenda.class);
		
		if(agenda != null){
			agendaDAO.update(agenda);
		}
	}

	@Override
	public void delete(AgendaVO agendaVO) {
		Agenda agenda = modelMapper.map(agendaVO, Agenda.class);
		
		if(agenda != null){
			agendaDAO.delete(agenda);
		}
	}

	@Override
	public AgendaVO findById(Integer id) {
		Agenda agenda = agendaDAO.findById(id);
		
		return modelMapper.map(agenda, AgendaVO.class);
	}

	@Override
	public Long findMaxId() {
		return agendaDAO.findMaxId();
	}

}
