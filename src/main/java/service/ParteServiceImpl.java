package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.ParteDAOImpl;
import entidades.Parte;
import vo.ParteVO;
import org.modelmapper.ModelMapper;

@Stateless
public class ParteServiceImpl implements ParteService {
	
	@EJB
	private ParteDAOImpl parteDAO;
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ParteVO> findAll() {
		List<Parte> parteList;
		List<ParteVO> parteVOList;
		
		parteList = parteDAO.findAll();
		if (parteList != null) {

			parteVOList = new ArrayList<>();
			for (Parte item : parteList) {
				parteVOList.add(modelMapper.map(item, ParteVO.class));
			}

			return parteVOList;
		} else
			return new ArrayList<>();
	}

	@Override
	public void insert(ParteVO parteVO) {
		Parte parte = modelMapper.map(parteVO, Parte.class);
		
		if(parte != null){
			parteDAO.insert(parte);
		}
	}

	@Override
	public void update(ParteVO parteVO) {
		Parte parte = modelMapper.map(parteVO, Parte.class);
		
		if(parte != null){
			parteDAO.update(parte);
		}
	}

	@Override
	public void delete(ParteVO parteVO) {
		Parte Parte = modelMapper.map(parteVO, Parte.class);
		
		if(Parte != null){
			parteDAO.delete(Parte);
		}
	}

	@Override
	public ParteVO findById(Integer id) {
		Parte Parte = parteDAO.findById(id);
		
		return modelMapper.map(Parte, ParteVO.class);
	}

	@Override
	public Long findMaxId() {
		return parteDAO.findMaxId();
	}

}
