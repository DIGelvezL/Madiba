package vo;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class PagoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idPago;
	private Integer banco;
	private Date fecha;
	private Integer formaPago;
	private Integer referencia;
	private double valor;
	private String estado;
	private SolicitudVO solicitudVO;
	
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}
	public Integer getReferencia() {
		return referencia;
	}
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
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
