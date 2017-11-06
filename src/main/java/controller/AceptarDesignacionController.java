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

import service.DesignacionService;
import service.SolicitudService;
import vo.AnexoVO;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class AceptarDesignacionController {
	
	private List<SolicitudResponseVO> solicitudResponseVOList;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	
	private StreamedContent file;
	private Boolean aceptar;
	private Boolean aceptada;
	
	@ManagedProperty(value = "#{fileList}")
	private List<StreamedContent> fileList;
	
	@EJB private SolicitudService solicitudService;
	@EJB private DesignacionService designacionService;
	
	@Resource(mappedName="java:jboss/mail/Gmail")
    private Session sessionMail;

	@PostConstruct
	public void inicializar() throws FileNotFoundException {
		findSolicitudById();
	}
	
	public void aceptarSolicitud(){
		for (SolicitudResponseVO solicitudResponseVO: solicitudResponseVOList) {
			if(Objects.nonNull(aceptar)){
				if(aceptar){
					solicitudResponseVO.getDesignacionVOList().get(0).setAceptada(aceptar);
					aceptada = true;
					designacionService.update(solicitudResponseVO.getDesignacionVOList().get(0));
					messageSuccess("Se envió la respuesta correctamente.");
				}else{
					aceptada = true;
					solicitudResponseVO.getDesignacionVOList().get(0).setConciliadorVO(null);
					designacionService.update(solicitudResponseVO.getDesignacionVOList().get(0));
					
					solicitudResponseVO.getSolicitudVO().setEstado("RADICADA");
					solicitudService.update(solicitudResponseVO.getSolicitudVO());
					
					messageSuccess("Se envió la respuesta correctamente.");
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
				if(Objects.nonNull(solicitudResponseVO.getDesignacionVOList().get(0).getAceptada())){
					if(solicitudResponseVO.getDesignacionVOList().get(0).getAceptada()){
						aceptar = solicitudResponseVO.getDesignacionVOList().get(0).getAceptada();
						aceptada = aceptar; 
					}else{
						aceptar = solicitudResponseVO.getDesignacionVOList().get(0).getAceptada();
						aceptada = true;
					}
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

	public Boolean getAceptar() {
		return aceptar;
	}

	public void setAceptar(Boolean aceptar) {
		this.aceptar = aceptar;
	}

	public Boolean getAceptada() {
		return aceptada;
	}

	public void setAceptada(Boolean aceptada) {
		this.aceptada = aceptada;
	}
	
}
