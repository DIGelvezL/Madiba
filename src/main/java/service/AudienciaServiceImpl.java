package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.modelmapper.ModelMapper;

import dao.AudienciaDAOImpl;
import entidades.Audiencia;
import vo.AudienciaVO;

@Stateless
public class AudienciaServiceImpl implements AudienciaService {

	@EJB private AudienciaDAOImpl audienciaDAO;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<AudienciaVO> findAll() {
		List<Audiencia> audienciaList;
		List<AudienciaVO> audienciaVOList;
		
		audienciaList = audienciaDAO.findAll();
		if (audienciaList != null) {

			audienciaVOList = new ArrayList<>();
			for (Audiencia item : audienciaList) {
				audienciaVOList.add(modelMapper.map(item, AudienciaVO.class));
			}

			return audienciaVOList;
		} else
			return new ArrayList<>();
	}

	@Override
	public void insert(AudienciaVO audienciaVO) {
		Audiencia audiencia = modelMapper.map(audienciaVO, Audiencia.class);
		
		if(audiencia != null){
			audienciaDAO.insert(audiencia);
		}
	}

	@Override
	public void update(AudienciaVO audienciaVO) {
		Audiencia audiencia = modelMapper.map(audienciaVO, Audiencia.class);
		
		if(audiencia != null){
			audienciaDAO.update(audiencia);
		}
	}

	@Override
	public void delete(AudienciaVO audienciaVO) {
		Audiencia audiencia = modelMapper.map(audienciaVO, Audiencia.class);
		
		if(audiencia != null){
			audienciaDAO.delete(audiencia);
		}
	}

	@Override
	public AudienciaVO findById(Integer id) {
		Audiencia audiencia = audienciaDAO.findById(id);
		
		return modelMapper.map(audiencia, AudienciaVO.class);
	}

	@Override
	public Long findMaxId() {
		return audienciaDAO.findMaxId();
	}

}
