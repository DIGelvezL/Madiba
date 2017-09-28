package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.SolicitudService;
import vo.ConciliadorVO;
import vo.DesignacionVO;
import vo.ParteVO;
import vo.SolicitudResponseVO;
import vo.SolicitudVO;

@ManagedBean
@ViewScoped
public class BuscarSolicitudesGuardadasController {
	
	private List<SolicitudResponseVO> solicitudResponseVOList;
	private Map<String, String> coloresEstado;
	
	@EJB
	private SolicitudService solicitudService;
	
	@PostConstruct
	public void inicializar() {
		coloresEstado = new HashMap<String, String>();
		coloresEstado.put("GRABADA", "info");
		coloresEstado.put("PAGADA", "primary");
		coloresEstado.put("RADICADA", "warning");
		coloresEstado.put("DESIGNACION", "success");
		coloresEstado.put("AUDIENCIA-CITACION", "info");
		coloresEstado.put("AUDIENCIA-PENDIENTE", "primary");
		coloresEstado.put("AUDIENCIA-ENCURSO", "warning");
		coloresEstado.put("AUDIENCIA-FINALIZADA", "success");
		coloresEstado.put("FINALIZADA-SOBRECOSTO", "black");
		coloresEstado.put("DESIGNACION-SOBRECOSTO", "black");
		coloresEstado.put("REGISTRADA", "black");
		
		solicitudResponseVOList = solicitudService.findByEstadoGuardada();
	}

	public List<SolicitudResponseVO> getSolicitudResponseVOList() {
		return solicitudResponseVOList;
	}

	public void setSolicitudResponseVOList(List<SolicitudResponseVO> solicitudResponseVOList) {
		this.solicitudResponseVOList = solicitudResponseVOList;
	}
}
