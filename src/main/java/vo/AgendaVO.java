package vo;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AgendaVO implements Serializable {

	private static final long serialVersionUID = -2899713756769600472L;

	private Long idAgenda;
	private Date fecha;
	private Integer horaFinal;
	private Integer horaInicial;
	private AudienciaVO audienciaVO;
	private SalaVO salaVO;
	
	public Long getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(Integer horaFinal) {
		this.horaFinal = horaFinal;
	}
	public Integer getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(Integer horaInicial) {
		this.horaInicial = horaInicial;
	}
	public AudienciaVO getAudienciaVO() {
		return audienciaVO;
	}
	public void setAudienciaVO(AudienciaVO audienciaVO) {
		this.audienciaVO = audienciaVO;
	}
	public SalaVO getSalaVO() {
		return salaVO;
	}
	public void setSalaVO(SalaVO salaVO) {
		this.salaVO = salaVO;
	}
	
}
