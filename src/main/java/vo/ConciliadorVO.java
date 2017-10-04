package vo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@SessionScoped
public class ConciliadorVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idConciliador;
	private String apellidos;
	private String correo;
	private String egresado;
	private Integer experiencia;
	private String foto;
	private String identificacion;
	private String nombres;
	private String telefono;
	private String tipoId;
	private List<String> nombreEspecialidad;
	
	public Long getIdConciliador() {
		return idConciliador;
	}
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
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
	public String getEgresado() {
		return egresado;
	}
	public void setEgresado(String egresado) {
		this.egresado = egresado;
	}
	public Integer getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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
	public List<String> getNombreEspecialidad() {
		return nombreEspecialidad;
	}
	public void setNombreEspecialidad(List<String> nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}
	
}
