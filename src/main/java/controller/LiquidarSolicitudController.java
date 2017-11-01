package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Session;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import presentacion.SendMail;
import service.SolicitudService;
import vo.AnexoVO;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class LiquidarSolicitudController {
	
	private List<SolicitudResponseVO> solicitudResponseVOList;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	
	private StreamedContent file;
	private Boolean conciliable;
	private double valorPagar;
	private String motivo;
	private Boolean liquidada;
	
	@ManagedProperty(value = "#{fileList}")
	private List<StreamedContent> fileList;
	
	@EJB
	private SolicitudService solicitudService;
	
	@Resource(mappedName="java:jboss/mail/Gmail")
    private Session sessionMail;

	@PostConstruct
	public void inicializar() throws FileNotFoundException {
		
		findSolicitudById();
	}
	
	public void liquidar(){
		for (SolicitudResponseVO solicitudResponseVO: solicitudResponseVOList) {
			
			if(Objects.nonNull(conciliable)){
				if(conciliable){
					solicitudResponseVO.getSolicitudVO().setConciliable(conciliable);
					
					SendMail sendMail = new SendMail();
					sendMail.emailSolicitudAceptada(solicitudResponseVO, sessionMail);
					
					liquidada = true;
					solicitudService.update(solicitudResponseVO.getSolicitudVO());
					messageSuccess("Se liquido la solicitud correctamente.");
				}else{
					if(!motivo.isEmpty()){
						solicitudResponseVO.getSolicitudVO().setConciliable(conciliable);
						solicitudResponseVO.getSolicitudVO().setValorPagar(0);
						solicitudResponseVO.getSolicitudVO().setEstado("NEGADA");
						solicitudResponseVO.getSolicitudVO().setMotivo(motivo);
						
						SendMail sendMail = new SendMail();
						sendMail.emailSolicitudNegada(solicitudResponseVO, sessionMail);
						
						liquidada = true;
						solicitudService.update(solicitudResponseVO.getSolicitudVO());
						messageSuccess("Se negó la solicitud correctamente.");
					}else{
						messageError("Debe ingresar el motivo.");
					}
				}
			}else{
				messageError("Debe seleccionar si es conciliable o no es conciliable.");
			}
		}
	}
	
	public void findSolicitudById(){
		if(Objects.nonNull(solicitudResponseVO.getIdSolicitud())){
			solicitudResponseVOList = solicitudService.findById(solicitudResponseVO.getIdSolicitud());
			
			for (SolicitudResponseVO solicitudResponseVO: solicitudResponseVOList) {
				if(solicitudResponseVO.getSolicitudVO().getConciliable()){
					conciliable = solicitudResponseVO.getSolicitudVO().getConciliable();
					liquidada = conciliable; 
				}
				
				fileList = new ArrayList<>();
				for(AnexoVO anexoVO: solicitudResponseVO.getAnexoVOList()){
					InputStream stream;
					try {
						stream = new FileInputStream(anexoVO.getContenido());
						String[] parts = anexoVO.getContenido().split("\\.");
						String tipo = parts[parts.length-1];
						file = new DefaultStreamedContent(stream, tipo);
				        
				        fileList.add(file);
					} catch (FileNotFoundException e) {
						throw new RuntimeException(e);
					}
			        
				}
			}
			
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

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<StreamedContent> getFileList() {
		return fileList;
	}

	public void setFileList(List<StreamedContent> fileList) {
		this.fileList = fileList;
	}

	public Boolean getConciliable() {
		return conciliable;
	}

	public void setConciliable(Boolean conciliable) {
		this.conciliable = conciliable;
	}

	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Boolean getLiquidada() {
		return liquidada;
	}

	public void setLiquidada(Boolean liquidada) {
		this.liquidada = liquidada;
	}
	
}
