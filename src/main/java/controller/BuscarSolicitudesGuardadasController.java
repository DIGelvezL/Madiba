package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.Solicitud;
import presentacion.ModelBusqueda;
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
	private SolicitudResponseVO solicitudResponseVO;
	private String statusSelect = "";
	
	@ManagedProperty(value = "#{modelBusqueda}")
	private ModelBusqueda modelBusqueda;
	
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
		
		solicitudResponseVOList = solicitudService.findByEstadoGuardada();
	}
	
	/*----------------------- Medtodos ---------------------------*/	
	public void addSelectSolicitud(SolicitudResponseVO auxSolicitud, String estado){
		if(!statusSelect.equals(estado)){
			statusSelect = estado;
			solicitudResponseVO = new SolicitudResponseVO();
		}
		
		if(Objects.nonNull(solicitudResponseVO.getSolicitudVO()) && solicitudResponseVO.getSolicitudVO().getIdSolicitud().equals(auxSolicitud.getSolicitudVO().getIdSolicitud())){
			solicitudResponseVO = new SolicitudResponseVO();
		}else{
			solicitudResponseVO = auxSolicitud;
			statusSelect = estado;
		}
	}
	
	public String colorSolicitudEstado(String estado){
		return coloresEstado.get(estado);
	}
	
	public String seleccionarSolicitud(SolicitudResponseVO auxSolicitud, String estado){
		if(Objects.nonNull(solicitudResponseVO) && Objects.nonNull(solicitudResponseVO.getSolicitudVO()) 
			&& solicitudResponseVO.getSolicitudVO().getIdSolicitud().equals(auxSolicitud.getSolicitudVO().getIdSolicitud())){
			return "seleccionar-solicitud-"+coloresEstado.get(estado);
		}
		return "";
	}
	
	public void buscarSolicitud(){
		switch (modelBusqueda.getTipoFiltro()) {
			case "Radicado":
				solicitudResponseVOList = new ArrayList<>();
				solicitudResponseVOList = solicitudService.getById(Long.parseLong(modelBusqueda.getNumero()));
				break;
			case "Convocante":
				solicitudResponseVOList = new ArrayList<>();
				solicitudResponseVOList = solicitudService.findByParte(modelBusqueda);
				break;
			case "Convocado":
				solicitudResponseVOList = new ArrayList<>();
				solicitudResponseVOList = solicitudService.findByParte(modelBusqueda);
				break;
			case "Conciliador":
				solicitudResponseVOList = new ArrayList<>();
				solicitudResponseVOList = solicitudService.findByConciliador(modelBusqueda);
				break;
			case "Fecha":
				solicitudResponseVOList = new ArrayList<>();
				solicitudResponseVOList = solicitudService.findByFechas(modelBusqueda);
				break;
			default:
				solicitudResponseVOList = solicitudService.findByEstadoGuardada();
				break;
		}
		
	}
	
	/*----------------------- Mensajes ---------------------------*/

	/*----------------------- getters y setters ---------------------------*/
	public List<SolicitudResponseVO> getSolicitudResponseVOList() {
		return solicitudResponseVOList;
	}

	public void setSolicitudResponseVOList(List<SolicitudResponseVO> solicitudResponseVOList) {
		this.solicitudResponseVOList = solicitudResponseVOList;
	}

	public ModelBusqueda getModelBusqueda() {
		return modelBusqueda;
	}

	public void setModelBusqueda(ModelBusqueda modelBusqueda) {
		this.modelBusqueda = modelBusqueda;
	}
	
}
