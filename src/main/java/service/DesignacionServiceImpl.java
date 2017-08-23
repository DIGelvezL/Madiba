package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.modelmapper.ModelMapper;

import dao.DesignacionDAOImpl;
import entidades.Designacion;
import vo.DesignacionVO;

public class DesignacionServiceImpl implements DesignacionService {

	@EJB
	private DesignacionDAOImpl designacionDAOImpl;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<DesignacionVO> findAll() {
		List<Designacion> designacionList;
		List<DesignacionVO> designacionVOList;
		
		designacionList = designacionDAOImpl.findAll();
		if (designacionList != null) {

			designacionVOList = new ArrayList<>();
			for (Designacion item : designacionList) {
				designacionVOList.add(modelMapper.map(item, DesignacionVO.class));
			}

			return designacionVOList;
		} else
			return new ArrayList<>();
	}

	@Override
	public void insert(DesignacionVO designacionVO) {
		Designacion designacion = modelMapper.map(designacionVO, Designacion.class);
		
		if(designacion != null){
			designacionDAOImpl.insert(designacion);
		}
	}

	@Override
	public void update(DesignacionVO designacionVO) {
		Designacion designacion = modelMapper.map(designacionVO, Designacion.class);
		
		if(designacion != null){
			designacionDAOImpl.update(designacion);
		}
	}

	@Override
	public void delete(DesignacionVO designacionVO) {
		Designacion designacion = modelMapper.map(designacionVO, Designacion.class);
		
		if(designacion != null){
			designacionDAOImpl.delete(designacion);
		}
	}

	@Override
	public DesignacionVO findById(Integer id) {
		Designacion designacion = designacionDAOImpl.findById(id);
		
		return modelMapper.map(designacion, DesignacionVO.class);
	}

	@Override
	public Long findMaxId() {
		return designacionDAOImpl.findMaxId();
	}

}
