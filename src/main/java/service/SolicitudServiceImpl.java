package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.SolicitudDAOImpl;
import entidades.Solicitud;
import vo.SolicitudVO;
import org.modelmapper.ModelMapper;

@Stateless
public class SolicitudServiceImpl implements SolicitudService {

	@EJB
	private SolicitudDAOImpl solicitudDAO;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<SolicitudVO> findAll() {
		List<Solicitud> solicitudList;
		List<SolicitudVO> SolicitudVOList;

		try {
			solicitudList = solicitudDAO.findAll();
			if (solicitudList != null) {

				SolicitudVOList = new ArrayList<>();
				for (Solicitud item : solicitudList) {
					SolicitudVOList.add(modelMapper.map(item, SolicitudVO.class));
				}

				return SolicitudVOList;
			} else
				return SolicitudVOList = null;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public void insert(SolicitudVO solicitudVO) {
		try {
			Solicitud solicitud = modelMapper.map(solicitudVO, Solicitud.class);
			
			if(solicitud != null){
				solicitudDAO.insert(solicitud);
			}
			
		} catch (Throwable t) {
		}
	}

	@Override
	public void update(SolicitudVO solicitudVO) {
		try {
			Solicitud solicitud = modelMapper.map(solicitudVO, Solicitud.class);
			
			if(solicitud != null){
				solicitudDAO.update(solicitud);
			}

		} catch (Throwable t) {
		}
	}

	@Override
	public void delete(SolicitudVO solicitudVO) {
		try {
			Solicitud solicitud = modelMapper.map(solicitudVO, Solicitud.class);
			
			if(solicitud != null){
				solicitudDAO.delete(solicitud);
			}

		} catch (Throwable t) {
		}
	}

	@Override
	public SolicitudVO getById(Integer id) {
		try {
			Solicitud solicitud = solicitudDAO.findById(id);
			
			return modelMapper.map(solicitud, SolicitudVO.class);
			
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public SolicitudVO findByEstado(String estado) {
		try {
			Solicitud solicitud = solicitudDAO.findByEstado(estado);
			
			return modelMapper.map(solicitud, SolicitudVO.class);
			
		} catch (Throwable t) {
			return null;
		}
	}
	
	@Override
	public Long findMaxId() {
		return solicitudDAO.findMaxId();
	}

}
