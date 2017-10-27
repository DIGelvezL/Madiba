package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import service.SolicitudService;
import vo.AnexoVO;
import vo.SolicitudResponseVO;

@ManagedBean
@ViewScoped
public class LiquidarSolicitudController {
	
	private List<SolicitudResponseVO> solicitudResponseVOList;
	private Map<String, String> coloresEstado;
	
	@ManagedProperty(value = "#{solicitudResponseVO}")
	private SolicitudResponseVO solicitudResponseVO;
	
	private StreamedContent file;
	private Boolean conciliable;
	
	@ManagedProperty(value = "#{fileList}")
	private List<StreamedContent> fileList;
	
	@EJB
	private SolicitudService solicitudService;

	@PostConstruct
	public void inicializar() throws FileNotFoundException {
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
		
		findSolicitudById();
	}
	
	public void findSolicitudById(){
		if(Objects.nonNull(solicitudResponseVO.getIdSolicitud())){
			solicitudResponseVOList = solicitudService.findById(solicitudResponseVO.getIdSolicitud());
			
			for (SolicitudResponseVO solicitudResponseVO: solicitudResponseVOList) {
				if(solicitudResponseVO.getSolicitudVO().getConciliable()){
					conciliable = solicitudResponseVO.getSolicitudVO().getConciliable();
				}
				
				fileList = new ArrayList<>();
				for(AnexoVO anexoVO: solicitudResponseVO.getAnexoVOList()){
					InputStream stream;
					try {
						stream = new FileInputStream(anexoVO.getContenido());
						file = new DefaultStreamedContent(stream);
				        
				        fileList.add(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
			        
				}
			}
			
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
	
}
