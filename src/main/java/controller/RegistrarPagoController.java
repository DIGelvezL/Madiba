package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import service.PagoService;
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
	
	@EJB private SolicitudService solicitudService;
	@EJB private PagoService pagoService;
	
	@PostConstruct
	public void inicializar() {
		pagoVO = new PagoVO();
	}
	
	public void registrarPago(){
		if(Objects.isNull(pagoVO.getBanco()) || Objects.isNull(pagoVO.getFormaPago())){
			messageError("Debe seleccionar el banco y la forma de pago.");
		}else{
			findSolicitudById();
			
			pagoVO.setEstado("PAGADO");
        	Calendar calendar = Calendar.getInstance();
        	Date now = calendar.getTime();
        	pagoVO.setFecha(now);
        	pagoVO.setReferencia(pagoService.findMaxReferencia() + 1);
        	pagoVO.setValor(solicitudResponseVOList.get(0).getSolicitudVO().getValorPagar());
        	
        	solicitudResponseVOList.get(0).getSolicitudVO().setEstado("PAGADA");
        	
        	solicitudService.update(solicitudResponseVOList.get(0).getSolicitudVO());
        	
        	pagoVO.setSolicitudVO(solicitudResponseVOList.get(0).getSolicitudVO());
        	
        	pagoService.insert(pagoVO);
        	messageSuccess("Se registró el pago correctamente.");
		}
	}
	
	public void findSolicitudById(){
		if(Objects.nonNull(solicitudResponseVO.getIdSolicitud())){
			solicitudResponseVOList = solicitudService.findById(solicitudResponseVO.getIdSolicitud());
		}
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

	public PagoVO getPagoVO() {
		return pagoVO;
	}

	public void setPagoVO(PagoVO pagoVO) {
		this.pagoVO = pagoVO;
	}
	
}
