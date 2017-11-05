package controller;

import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import service.SolicitudService;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class RadicarController {

	private List<SolicitudResponseVO> solicitudResponseVOList;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	
	@EJB private SolicitudService solicitudService;
	
	public void radicar(){
		findSolicitudById();
		
		solicitudResponseVOList.get(0).getSolicitudVO().setEstado("RADICADA");
		
		generarNumeroRadicado();
    	
    	solicitudService.update(solicitudResponseVOList.get(0).getSolicitudVO());
    	
    	messageSuccess("Se radicó la solicitud correctamente.");
	}
	
	public void findSolicitudById(){
		if(Objects.nonNull(solicitudResponseVO.getIdSolicitud())){
			solicitudResponseVOList = solicitudService.findById(solicitudResponseVO.getIdSolicitud());
		}
	}
	
	public void generarNumeroRadicado(){
		String numeroRadicado = solicitudResponseVOList.get(0).getSolicitudVO().getFecha().toString();
		String[] parts = numeroRadicado.split("-");
		
		numeroRadicado = parts[0] + parts[1] + parts[2];
		
		switch (solicitudResponseVOList.get(0).getSolicitudVO().getIdSolicitud().toString().length()) {
		case 1:
			numeroRadicado += "0000" + solicitudResponseVOList.get(0).getSolicitudVO().getIdSolicitud().toString(); 
			break;
		case 2:
			numeroRadicado += "000" + solicitudResponseVOList.get(0).getSolicitudVO().getIdSolicitud().toString(); 
			break;
		case 3:
			numeroRadicado += "00" + solicitudResponseVOList.get(0).getSolicitudVO().getIdSolicitud().toString(); 
			break;
		case 4:
			numeroRadicado += "0" + solicitudResponseVOList.get(0).getSolicitudVO().getIdSolicitud().toString(); 
			break;
		default:
			numeroRadicado += solicitudResponseVOList.get(0).getSolicitudVO().getIdSolicitud().toString();
			break;
		}
		
		solicitudResponseVOList.get(0).getSolicitudVO().setNroRadicado(numeroRadicado);
	}
	
	/*----------------------- Mensajes ---------------------------*/
	public void messageSuccess(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!!", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void messageError(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!!", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void messageWarning(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING!!", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	/*----------------------- getters y setters ---------------------------*/

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
