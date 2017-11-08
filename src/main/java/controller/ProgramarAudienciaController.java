package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import service.AgendaService;
import service.AudienciaService;
import service.SolicitudService;
import vo.AgendaVO;
import vo.AudienciaVO;
import vo.SalaVO;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class ProgramarAudienciaController {

	private List<SolicitudResponseVO> solicitudResponseVOList;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	private AudienciaVO audienciaVO;
	private AgendaVO agendaVO;
	private SalaVO salaVO;
	private String fecha;
	
	@EJB private SolicitudService solicitudService;
	@EJB private AudienciaService audienciaService;
	@EJB private AgendaService agendaService;
	
	@PostConstruct
	public void inicializar() {
		audienciaVO = new AudienciaVO();
		agendaVO = new AgendaVO();
		salaVO = new SalaVO();
		
		Date fechaActual = new Date();
		fecha = new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);
	}
	
	public void programarAudiencia(){
		if(Objects.isNull(agendaVO.getHoraInicial()) || Objects.isNull(agendaVO.getHoraFinal()) || Objects.isNull(salaVO.getIdSala())){
			messageError("Debe completar todos los campos.");
		}else{
			solicitudResponseVOList.get(0).getSolicitudVO().setEstado("AUDIENCIA-CITACION");
			solicitudService.update(solicitudResponseVOList.get(0).getSolicitudVO());
			
			if(audienciaService.findMaxId() != null)
				audienciaVO.setIdAudiencia(audienciaService.findMaxId() + 1);
			else{
				audienciaVO.setIdAudiencia(1L);
			}
    		
			audienciaVO.setAudienciaNum(1);
			audienciaVO.setEstado("CITACION");
			audienciaVO.setSolicitudVO(solicitudResponseVOList.get(0).getSolicitudVO());
			
			audienciaService.insert(audienciaVO);
			
			if(agendaService.findMaxId() != null)
				agendaVO.setIdAgenda(agendaService.findMaxId() + 1);
			else{
				agendaVO.setIdAgenda(1L);
			}
			
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);
						
				agendaVO.setFecha(date);
				agendaVO.setAudienciaVO(audienciaVO);
				agendaVO.setSalaVO(salaVO);
				
				agendaService.insert(agendaVO);
				
	        	messageSuccess("Se programó la Audiencia correctamente.");
			} catch (ParseException e) {

			}
		}
	}
	
	public void findSolicitudById(){
		if(Objects.nonNull(solicitudResponseVO.getIdSolicitud())){
			solicitudResponseVOList = solicitudService.findById(solicitudResponseVO.getIdSolicitud());
		}
	}
	
	public void valFech(){
		Date fechaActual = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fechaActual);
    	calendar.add(Calendar.MONTH, -3);   
    	
		if( this.fecha==null || this.fecha.isEmpty()){
			this.fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
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

	public AudienciaVO getAudienciaVO() {
		return audienciaVO;
	}

	public void setAudienciaVO(AudienciaVO audienciaVO) {
		this.audienciaVO = audienciaVO;
	}

	public AgendaVO getAgendaVO() {
		return agendaVO;
	}

	public void setAgendaVO(AgendaVO agendaVO) {
		this.agendaVO = agendaVO;
	}

	public SalaVO getSalaVO() {
		return salaVO;
	}

	public void setSalaVO(SalaVO salaVO) {
		this.salaVO = salaVO;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
