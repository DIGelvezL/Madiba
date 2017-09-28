package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.modelmapper.ModelMapper;

import dao.SolicitudDAOImpl;
import entidades.Anexo;
import entidades.Designacion;
import entidades.Parte;
import entidades.Solicitud;
import vo.AnexoVO;
import vo.ConciliadorVO;
import vo.DesignacionVO;
import vo.ParteVO;
import vo.SolicitudResponseVO;
import vo.SolicitudVO;

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
	
	@Override
	public List<SolicitudResponseVO> findByEstadoGuardada() {
		try {
			List<Solicitud> solicitudList = solicitudDAO.findByEstadoGuardada();
			List<SolicitudResponseVO> solicitudResponseVOList = new ArrayList<>();
			
			if(Objects.nonNull(solicitudList)){
				for(Solicitud solicitud: solicitudList){
					SolicitudResponseVO solicitudResponseVO = new SolicitudResponseVO();
					
					List<AnexoVO> anexoVOList = new ArrayList<>();
					List<DesignacionVO> designacionVOList = new ArrayList<>();
					List<ParteVO> parteVOList = new ArrayList<>();
					
					SolicitudVO solicitudVO;
					solicitudVO = modelMapper.map(solicitud, SolicitudVO.class);
					
					for(Anexo anexo: solicitud.getAnexos()){
						AnexoVO anexoVO;
						anexoVO = modelMapper.map(anexo, AnexoVO.class);
						anexoVO.setSolicitudVO(solicitudVO);
						anexoVOList.add(anexoVO);
					}
					
					solicitudResponseVO.setAnexoVOList(anexoVOList);
					
					for(Designacion designacion: solicitud.getDesignacions()){
						DesignacionVO designacionVO;
						ConciliadorVO conciliadorVO;
						designacionVO = modelMapper.map(designacion, DesignacionVO.class);
						conciliadorVO = modelMapper.map(designacion.getConciliador(), ConciliadorVO.class);
						designacionVO.setConciliadorVO(conciliadorVO);
						designacionVO.setSolicitudVO(solicitudVO);
						designacionVOList.add(designacionVO);
					}
					
					solicitudResponseVO.setDesignacionVOList(designacionVOList);
					
					for(Parte parte: solicitud.getPartes()){
						ParteVO parteVO;
						parteVO = modelMapper.map(parte, ParteVO.class);
						parteVO.setSolicitudVO(solicitudVO);
						parteVOList.add(parteVO);
					}
					
					solicitudResponseVO.setParteVOList(parteVOList);
					solicitudResponseVO.setSolicitudVO(solicitudVO);
					
					solicitudResponseVOList.add(solicitudResponseVO);
				}
			}
			
			return solicitudResponseVOList;
			
		} catch (Throwable t) {
			return null;
		}
	}

}
