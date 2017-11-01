package controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import service.SolicitudService;
import vo.PagoVO;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class RegistrarPagoController {

	private List<SolicitudResponseVO> solicitudResponseVOList;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	private PagoVO pagoVO;
	
	@EJB
	private SolicitudService solicitudService;
	
	@PostConstruct
	public void inicializar(Long idSolicitud) throws FileNotFoundException {
		findSolicitudById(idSolicitud);
	}
	
	public void findSolicitudById(Long idSolicitud){
		if(Objects.nonNull(idSolicitud)){
			solicitudResponseVOList = solicitudService.findById(idSolicitud);
		}
	}

	public List<SolicitudResponseVO> getSolicitudResponseVOList() {
		return solicitudResponseVOList;
	}

	public void setSolicitudResponseVOList(List<SolicitudResponseVO> solicitudResponseVOList) {
		this.solicitudResponseVOList = solicitudResponseVOList;
	}

	public SolicitudResponseVO getSolicitudResponseVO() {
		return solicitudResponseVO;
	}

	public void setSolicitudResponseVO(SolicitudResponseVO solicitudResponseVO) {
		this.solicitudResponseVO = solicitudResponseVO;
	}

	public PagoVO getPagoVO() {
		return pagoVO;
	}

	public void setPagoVO(PagoVO pagoVO) {
		this.pagoVO = pagoVO;
	}
	
}
