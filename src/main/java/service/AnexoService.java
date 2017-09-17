package service;

import java.util.List;

import javax.ejb.Local;

import org.primefaces.model.UploadedFile;

import vo.AnexoVO;



@Local
public interface AnexoService {
	
	public List<AnexoVO> findAll();

	public void insert(AnexoVO anexoVO);

	public void update(AnexoVO anexoVO);

	public void delete(AnexoVO anexoVO);
	
	public AnexoVO findById(Integer id);
	
	public Long findMaxId();
	
	public String guardarAnexos(UploadedFile file, String nombreArchivo, String idSolicitud);

}
