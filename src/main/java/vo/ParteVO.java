package vo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ParteVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idParte;
	private String apellidos;
	private String correo;
	private String direccion;
	private String identificacion;
	private String nombres;
	private String telefono;
	private String tipoId;
	private String tipoParte;
	private SolicitudVO solicitudVO;
	
	public Long getIdParte() {
		return idParte;
	}
	public void setIdParte(Long idParte) {
		this.idParte = idParte;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipoId() {
		return tipoId;
	}
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	public String getTipoParte() {
		return tipoParte;
	}
	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}
	public SolicitudVO getSolicitudVO() {
		return solicitudVO;
	}
	public void setSolicitudVO(SolicitudVO solicitudVO) {
		this.solicitudVO = solicitudVO;
	}

}
