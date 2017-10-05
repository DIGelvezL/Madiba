package vo;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SolicitudVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idSolicitud;
	private String asunto;
	private Boolean conciliable;
	private double cuantia;
	private String estado;
	private Date fecha;
	private String nroRadicado;
	private double valorPagar;
	
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Boolean getConciliable() {
		return conciliable;
	}
	public void setConciliable(Boolean conciliable) {
		this.conciliable = conciliable;
	}
	public double getCuantia() {
		return cuantia;
	}
	public void setCuantia(double cuantia) {
		this.cuantia = cuantia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNroRadicado() {
		return nroRadicado;
	}
	public void setNroRadicado(String nroRadicado) {
		this.nroRadicado = nroRadicado;
	}
	public double getValorPagar() {
		return valorPagar;
	}
	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}
	
}
