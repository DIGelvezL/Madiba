package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import presentacion.ModelBusqueda;
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
	public List<SolicitudResponseVO> getById(Long id) {
		try {
			List<Solicitud> solicitudList = solicitudDAO.getById(id);
			
			return mapearSolicitudes(solicitudList);
			
		} catch (Throwable t) {
			return null;
		}
	}
	
	@Override
	public List<SolicitudResponseVO> findById(Long id) {
		try {
			List<Solicitud> solicitudList = solicitudDAO.findById(id);
			
			return mapearSolicitudes(solicitudList);
			
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
			
			return mapearSolicitudes(solicitudList);
			
		} catch (Throwable t) {
			return null;
		}
	}
	
	@Override
	public List<SolicitudResponseVO> findByParte(ModelBusqueda modelBusqueda) {
		try {
			String identificacion = modelBusqueda.getNumero();
			String tipoParte = modelBusqueda.getTipoFiltro();
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicial = formatoDelTexto.parse(modelBusqueda.getFechaInicio());
			Date fechaFinal = formatoDelTexto.parse(modelBusqueda.getFechaFinal());
			
			List<Solicitud> solicitudList = solicitudDAO.findByParte(identificacion, fechaInicial, fechaFinal, tipoParte);
			
			return mapearSolicitudes(solicitudList);
			
		} catch (Throwable t) {
			return null;
		}
	}
	
	@Override
	public List<SolicitudResponseVO> findByConciliador(ModelBusqueda modelBusqueda) {
		try {
			String identificacion = modelBusqueda.getNumero();
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicial = formatoDelTexto.parse(modelBusqueda.getFechaInicio());
			Date fechaFinal = formatoDelTexto.parse(modelBusqueda.getFechaFinal());
			
			List<Solicitud> solicitudList = solicitudDAO.findByConciliador(identificacion, fechaInicial, fechaFinal);
			
			return mapearSolicitudes(solicitudList);
			
		} catch (Throwable t) {
			return null;
		}
	}
	
	@Override
	public List<SolicitudResponseVO> findByFechas(ModelBusqueda modelBusqueda) {
		try {
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicial = formatoDelTexto.parse(modelBusqueda.getFechaInicio());
			Date fechaFinal = formatoDelTexto.parse(modelBusqueda.getFechaFinal());
			
			List<Solicitud> solicitudList = solicitudDAO.findByFechas(fechaInicial, fechaFinal);
			
			return mapearSolicitudes(solicitudList);
			
		} catch (Throwable t) {
			return null;
		}
	}
	
	private List<SolicitudResponseVO> mapearSolicitudes(List<Solicitud> solicitudList){
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
					if(designacion.getConciliador() != null){
						conciliadorVO = modelMapper.map(designacion.getConciliador(), ConciliadorVO.class);
					}else{
						conciliadorVO = null;
					}
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
	}

}
