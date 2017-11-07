package vo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class AnexoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idAnexo;
	private Integer anexoNum;
	private String contenido;
	private SolicitudVO solicitudVO;
	
	public Long getIdAnexo() {
		return idAnexo;
	}
	public void setIdAnexo(Long idAnexo) {
		this.idAnexo = idAnexo;
	}
	public Integer getAnexoNum() {
		return anexoNum;
	}
	public void setAnexoNum(Integer anexoNum) {
		this.anexoNum = anexoNum;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public SolicitudVO getSolicitudVO() {
		return solicitudVO;
	}
	public void setSolicitudVO(SolicitudVO solicitudVO) {
		this.solicitudVO = solicitudVO;
	}
	
}
