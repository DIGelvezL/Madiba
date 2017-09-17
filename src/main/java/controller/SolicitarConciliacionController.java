package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import service.AnexoService;
import service.ConciliadorService;
import service.DesignacionService;
import service.ParteService;
import service.SolicitudService;
import vo.AnexoVO;
import vo.ConciliadorVO;
import vo.DesignacionVO;
import vo.ParteVO;
import vo.SolicitudVO;



@ManagedBean
@ViewScoped
public class SolicitarConciliacionController {
	
	@ManagedProperty(value = "#{convocanteVO}")
	private ParteVO convocanteVO;
	
	@ManagedProperty(value = "#{convocanteAgragadoVO}")
	private ParteVO convocanteAgragadoVO;
	
	@ManagedProperty(value = "#{convocadoAgragadoVO}")
	private ParteVO convocadoAgragadoVO;
	
	@ManagedProperty(value = "#{solicitudVO}")
	private SolicitudVO solicitudVO;
	
	@ManagedProperty(value = "#{conciliadorVO}")
	private ConciliadorVO conciliadorVO;
	
	@ManagedProperty(value = "#{designacionVO}")
	private DesignacionVO designacionVO;
	
	@ManagedProperty(value = "#{anexoVO}")
	private AnexoVO anexoVO;
	
	private boolean tipoDesignacion;
	private boolean onOff;
	private boolean agregarConvocante;
	private boolean agregarConvocado;
	
	private List<ConciliadorVO> conciliadorVOList;
	private List<ParteVO> convocanteVOList;
	private List<ParteVO> convocanteAgragadoVOList;
	private List<ParteVO> convocadoVOList;
	private List<ParteVO> convocadoAgragadoVOList;
	private Long id;
	
	private UploadedFile file;
	private String archivo;
	private List<String> anexosLista;
	
	@EJB
	private ConciliadorService conciliadorService;
	
	@EJB
	private SolicitudService solicitudService;
	
	@EJB
	private ParteService parteService;
	
	@EJB
	private DesignacionService designacionService;
	
	@EJB
	private AnexoService anexoService;

	@PostConstruct
	public void inicializar() {
		try {
			onOff = false;
			tipoDesignacion = true;
			convocanteVO = new ParteVO();
			convocanteAgragadoVO = new ParteVO();
			convocadoAgragadoVO = new ParteVO();
			conciliadorVO = new ConciliadorVO();
			solicitudVO = new SolicitudVO();
			designacionVO = new DesignacionVO();
			
			conciliadorVOList = new ArrayList<>();
			conciliadorVOList =  conciliadorService.findAll();
			
			convocanteVOList = new ArrayList<>();
			convocanteAgragadoVOList = new ArrayList<>();
			convocadoVOList = new ArrayList<>();
			convocadoAgragadoVOList = new ArrayList<>();
			anexosLista = new ArrayList<>();
			
			id = null;
			agregarConvocante = true;
			agregarConvocado = true;
					
		} catch (Exception ex) {
			ex.printStackTrace();
        }
	}
	
	/*----------------------- Medtodos ---------------------------*/
	public void guardar() {
        try {
        	
        	solicitudVO.setEstado("GUARDADA");
        	Calendar calendar = Calendar.getInstance();
        	Date now = calendar.getTime();
        	solicitudVO.setFecha(now);
        	solicitudVO.setConciliable(false);
        	
        	if(onOff && conciliadorVO == null){
        		messageError("Debe seleccionar un conciliador");
        		return;
        	}
    		
        	if(id == null){
        		long idSolicitud;
        		if(solicitudService.findMaxId() != null)
        			idSolicitud = solicitudService.findMaxId();
    			else
    				idSolicitud = 0;
        		
        		solicitudVO.setIdSolicitud(idSolicitud + 1);
        		id = solicitudVO.getIdSolicitud();
        		solicitudService.insert(solicitudVO);
        	}else{
        		solicitudService.update(solicitudVO);
        	}
        	
        	guardarPartes(convocanteVOList);
        	guardarPartes(convocadoVOList);
        	guardarAnexos();
        	
        	if(onOff){
        		guardarDesignacion();
        	}
        	
    		messageSuccess("Se guardo la solicitud");

      	
		} catch (Exception ex) {
			ex.printStackTrace();
			messageError("Error en la transacci�n!!");
        }
    }
	
	public void solicitar() {
        try {
        	
        	solicitudVO.setEstado("GRABADA");
        	Calendar calendar = Calendar.getInstance();
        	Date now = calendar.getTime();
        	solicitudVO.setFecha(now);
        	solicitudVO.setConciliable(false);
        	
        	if(onOff && conciliadorVO == null){
        		messageError("Debe seleccionar un conciliador");
        		return;
        	}
    		
        	if(id == null){
        		long idSolicitud;
        		if(solicitudService.findMaxId() != null)
        			idSolicitud = solicitudService.findMaxId();
    			else
    				idSolicitud = 0;
        		
        		solicitudVO.setIdSolicitud(idSolicitud + 1);
        		id = solicitudVO.getIdSolicitud();
        		solicitudService.insert(solicitudVO);
        	}else{
        		solicitudService.update(solicitudVO);
        	}
        	
        	guardarPartes(convocanteVOList);
        	guardarPartes(convocadoVOList);
        	guardarAnexos();
        	
        	if(onOff){
        		guardarDesignacion();
        	}
        	
    		messageSuccess("Se guardo la solicitud");

      	
		} catch (Exception ex) {
			ex.printStackTrace();
			messageError("Error en la transacci�n!!");
        }
    }
	
	private void guardarPartes(List<ParteVO> parteList){
		for (ParteVO item : parteList) {
    		item.setSolicitudVO(solicitudVO);
    		if(item.getIdParte() == null){
    			long idconcovante;
    			if(parteService.findMaxId() != null)
    				idconcovante = parteService.findMaxId();
    			else
    				idconcovante = 0;
				
    			item.setIdParte(idconcovante + 1);
    			
    			parteService.insert(item);
    		}else{
    			parteService.update(item);
    		}
		}
	}
	
	private void guardarDesignacion(){
		designacionVO.setTipoDesignacion("Solicitud");
		designacionVO.setConciliadorVO(conciliadorVO);
		designacionVO.setSolicitudVO(solicitudVO);
		
		if(designacionVO.getIdDesignacion() == null){
			long idDesignacion;
			if(designacionService.findMaxId() != null)
				idDesignacion = designacionService.findMaxId();
			else
				idDesignacion = 0;
			
			designacionVO.setIdDesignacion(idDesignacion + 1);
			
			designacionService.insert(designacionVO);
		}else{
			designacionService.update(designacionVO);
		}
	}
	
	private void guardarAnexos(){
		int cont = 0;
		
		for (String ubicacion : anexosLista) {
			cont++;
			
			anexoVO = new AnexoVO();
			
			anexoVO.setSolicitudVO(solicitudVO);
			
			anexoVO.setContenido(ubicacion);
			
			if(anexoVO.getIdAnexo() == null){
				long idAnexo;
				if(anexoService.findMaxId() != null)
					idAnexo = anexoService.findMaxId();
				else
					idAnexo = 0;
				
				anexoVO.setIdAnexo(idAnexo + 1);
				int num = (int) (idAnexo + 1);
				anexoVO.setAnexoNum(cont);
				
				anexoService.insert(anexoVO);
			}else{
				anexoService.update(anexoVO);
			}
		}
	}
	
	public void selectTipoDesignacion() {
		
		tipoDesignacion = onOff ? false : true;
		if(!onOff)
			conciliadorVO = null;
    }
	
	public void agregarConvocante(){
		boolean failure = false;
		for (ParteVO item : convocanteVOList) {
			if(item.getIdentificacion().equals(convocanteVO.getIdentificacion())){
				messageError("ya está agregado el convocante!!");
				failure = true;
				break;
			}
		}
		
		if(!failure){
			if(!convocanteVO.getNombres().equals("")
				&& !convocanteVO.getApellidos().equals("")
				&& convocanteVO.getTipoId() != null
				&& !convocanteVO.getIdentificacion().equals("")
				&& !convocanteVO.getDireccion().equals("")
				&& !convocanteVO.getTelefono().equals("")
				&& !convocanteVO.getCorreo().equals("")){
				ParteVO coVO = convocanteVO;
				convocanteVO = new ParteVO();
				coVO.setTipoParte("Convocante");
				convocanteVOList.add(coVO);
			}else
				messageError("Debe completar todos los campos del convocante!!");
		}
		
		agregarConvocante = false;
	}
	
	public void eliminarConvocante() {
		for(ParteVO item: convocanteAgragadoVOList){
			convocanteVOList.remove(item);
		}
	}
	
	public void agregarConvocado(){
		boolean failure = false;
		for (ParteVO item : convocadoVOList) {
			if(item.getIdentificacion().equals(convocadoAgragadoVO.getIdentificacion())){
				messageError("ya está agregado el convocado!!");
				failure = true;
				break;
			}
		}
		
		if(!failure){
			if(!convocadoAgragadoVO.getNombres().equals("")
				&& !convocadoAgragadoVO.getApellidos().equals("")
				&& convocadoAgragadoVO.getTipoId() != null
				&& !convocadoAgragadoVO.getIdentificacion().equals("")
				&& !convocadoAgragadoVO.getDireccion().equals("")
				&& !convocadoAgragadoVO.getTelefono().equals("")
				&& !convocadoAgragadoVO.getCorreo().equals("")){
				ParteVO convocadoVO = convocadoAgragadoVO;
				convocadoAgragadoVO = new ParteVO();
				convocadoVO.setTipoParte("Convocado");
				convocadoVOList.add(convocadoVO);
			}else
				messageError("Debe completar todos los campos del convocado!!");
		}
		
		agregarConvocado = false;
	}
	
	public void upLoad(FileUploadEvent event){

		setFile(event.getFile());
    	setArchivo(event.getFile().getFileName());

		long idSolicitud;
		if(solicitudService.findMaxId() != null)
			idSolicitud = solicitudService.findMaxId();
		else
			idSolicitud = 0;
		
		solicitudVO.setIdSolicitud(idSolicitud + 1);

		anexosLista.add(anexoService.guardarAnexos(getFile(), getArchivo(), solicitudVO.getIdSolicitud().toString()));
	}
	
	public void eliminarConvocado() {
		for(ParteVO item: convocadoAgragadoVOList){
			convocadoVOList.remove(item);
		}
	}
	
	public void mostrarAgregarConvocante() {
		agregarConvocante = true;
	}
	
	public void mostrarAgregarConvocado() {
		agregarConvocado = true;
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
	public ParteVO getConvocanteVO() {
		return convocanteVO;
	}

	public void setConvocanteVO(ParteVO convocanteVO) {
		this.convocanteVO = convocanteVO;
	}
	
	public ParteVO getConvocadoAgragadoVO() {
		return convocadoAgragadoVO;
	}

	public void setConvocadoAgragadoVO(ParteVO convocadoAgragadoVO) {
		this.convocadoAgragadoVO = convocadoAgragadoVO;
	}

	public SolicitudVO getSolicitudVO() {
		return solicitudVO;
	}

	public void setSolicitudVO(SolicitudVO solicitudVO) {
		this.solicitudVO = solicitudVO;
	}
	
	public ConciliadorVO getConciliadorVO() {
		return conciliadorVO;
	}

	public void setConciliadorVO(ConciliadorVO conciliadorVO) {
		this.conciliadorVO = conciliadorVO;
	}

	public boolean isTipoDesignacion() {
		return tipoDesignacion;
	}

	public void setTipoDesignacion(boolean tipoDesignacion) {
		this.tipoDesignacion = tipoDesignacion;
	}

	public boolean isOnOff() {
		return onOff;
	}

	public void setOnOff(boolean onOff) {
		this.onOff = onOff;
	}

	public List<ConciliadorVO> getConciliadorVOList() {
		return conciliadorVOList;
	}

	public void setConciliadorVOList(List<ConciliadorVO> conciliadorVOList) {
		this.conciliadorVOList = conciliadorVOList;
	}

	public List<ParteVO> getConvocadoVOList() {
		return convocadoVOList;
	}

	public void setConvocadoVOList(List<ParteVO> convocadoVOList) {
		this.convocadoVOList = convocadoVOList;
	}

	public List<ParteVO> getConvocadoAgragadoVOList() {
		return convocadoAgragadoVOList;
	}

	public void setConvocadoAgragadoVOList(List<ParteVO> convocadoAgragadoVOList) {
		this.convocadoAgragadoVOList = convocadoAgragadoVOList;
	}

	public ParteVO getConvocanteAgragadoVO() {
		return convocanteAgragadoVO;
	}

	public void setConvocanteAgragadoVO(ParteVO convocanteAgragadoVO) {
		this.convocanteAgragadoVO = convocanteAgragadoVO;
	}

	public List<ParteVO> getConvocanteVOList() {
		return convocanteVOList;
	}

	public void setConvocanteVOList(List<ParteVO> convocanteVOList) {
		this.convocanteVOList = convocanteVOList;
	}

	public List<ParteVO> getConvocanteAgragadoVOList() {
		return convocanteAgragadoVOList;
	}

	public void setConvocanteAgragadoVOList(List<ParteVO> convocanteAgragadoVOList) {
		this.convocanteAgragadoVOList = convocanteAgragadoVOList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAgregarConvocante() {
		return agregarConvocante;
	}

	public void setAgregarConvocante(boolean agregarConvocante) {
		this.agregarConvocante = agregarConvocante;
	}

	public boolean isAgregarConvocado() {
		return agregarConvocado;
	}

	public void setAgregarConvocado(boolean agregarConvocado) {
		this.agregarConvocado = agregarConvocado;
	}

	public DesignacionVO getDesignacionVO() {
		return designacionVO;
	}

	public void setDesignacionVO(DesignacionVO designacionVO) {
		this.designacionVO = designacionVO;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public AnexoVO getAnexoVO() {
		return anexoVO;
	}

	public void setAnexoVO(AnexoVO anexoVO) {
		this.anexoVO = anexoVO;
	}

	public List<String> getAnexosLista() {
		return anexosLista;
	}

	public void setAnexosLista(List<String> anexosLista) {
		this.anexosLista = anexosLista;
	}
	
}
