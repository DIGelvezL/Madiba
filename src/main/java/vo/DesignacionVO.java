package vo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@SessionScoped
public class DesignacionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idDesignacion;
	private Boolean aceptada;
	private String observacion;
	private String tipoDesignacion;
	private ConciliadorVO conciliadorVO;
	private SolicitudVO solicitudVO;
	
	public Long getIdDesignacion() {
		return idDesignacion;
	}
	public void setIdDesignacion(Long idDesignacion) {
		this.idDesignacion = idDesignacion;
	}
	public Boolean getAceptada() {
		return aceptada;
	}
	public void setAceptada(Boolean aceptada) {
		this.aceptada = aceptada;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getTipoDesignacion() {
		return tipoDesignacion;
	}
	public void setTipoDesignacion(String tipoDesignacion) {
		this.tipoDesignacion = tipoDesignacion;
	}
	public ConciliadorVO getConciliadorVO() {
		return conciliadorVO;
	}
	public void setConciliadorVO(ConciliadorVO conciliadorVO) {
		this.conciliadorVO = conciliadorVO;
	}
	public SolicitudVO getSolicitudVO() {
		return solicitudVO;
	}
	public void setSolicitudVO(SolicitudVO solicitudVO) {
		this.solicitudVO = solicitudVO;
	}
}
