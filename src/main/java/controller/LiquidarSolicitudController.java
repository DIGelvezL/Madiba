package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import service.SolicitudService;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class LiquidarSolicitudController {
	
	private List<SolicitudResponseVO> solicitudResponseVOList;
	private Map<String, String> coloresEstado;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	
	@EJB
	private SolicitudService solicitudService;

	@PostConstruct
	public void inicializar() {
		coloresEstado = new HashMap<>();
		coloresEstado.put("GRABADA", "info");
		coloresEstado.put("PAGADA", "primary");
		coloresEstado.put("RADICADA", "warning");
		coloresEstado.put("DESIGNACION", "success");
		coloresEstado.put("AUDIENCIA-CITACION", "info");
		coloresEstado.put("AUDIENCIA-PENDIENTE", "primary");
		coloresEstado.put("AUDIENCIA-ENCURSO", "warning");
		coloresEstado.put("AUDIENCIA-FINALIZADA", "success");
		coloresEstado.put("GUARDADA", "black");
		coloresEstado.put("DESIGNACION-SOBRECOSTO", "black");
		coloresEstado.put("REGISTRADA", "black");
		
		if(Objects.nonNull(solicitudResponseVO.getIdSolicitud())){
			solicitudResponseVOList = solicitudService.findById(solicitudResponseVO.getIdSolicitud());
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
	
}
