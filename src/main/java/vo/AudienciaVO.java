package vo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AudienciaVO implements Serializable {

	private static final long serialVersionUID = -6416089930617620427L;
	
	private Long idAudiencia;
	private Integer audienciaNum;
	private String estado;
	private SolicitudVO solicitudVO;
	
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public Integer getAudienciaNum() {
		return audienciaNum;
	}
	public void setAudienciaNum(Integer audienciaNum) {
		this.audienciaNum = audienciaNum;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public SolicitudVO getSolicitudVO() {
		return solicitudVO;
	}
	public void setSolicitudVO(SolicitudVO solicitudVO) {
		this.solicitudVO = solicitudVO;
	}
	
}
