package service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.modelmapper.ModelMapper;
import org.primefaces.model.UploadedFile;

import dao.AnexoDAOImpl;
import entidades.Anexo;
import vo.AnexoVO;


@Stateless
public class AnexoServiceImpl implements AnexoService {
	
	@EJB
	private AnexoDAOImpl anexoDAO;
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<AnexoVO> findAll() {
		List<Anexo> anexoList;
		List<AnexoVO> anexoVOList;
		
		anexoList = anexoDAO.findAll();
		if (anexoList != null) {

			anexoVOList = new ArrayList<>();
			for (Anexo item : anexoList) {
				anexoVOList.add(modelMapper.map(item, AnexoVO.class));
			}

			return anexoVOList;
		} else
			return new ArrayList<>();
	}

	@Override
	public void insert(AnexoVO anexoVO) {
		Anexo anexo = modelMapper.map(anexoVO, Anexo.class);
		
		if(anexo != null){
			anexoDAO.insert(anexo);
		}
	}

	@Override
	public void update(AnexoVO anexoVO) {
		Anexo anexo = modelMapper.map(anexoVO, Anexo.class);
		
		if(anexo != null){
			anexoDAO.update(anexo);
		}
	}

	@Override
	public void delete(AnexoVO anexoVO) {
		Anexo anexo = modelMapper.map(anexoVO, Anexo.class);
		
		if(anexo != null){
			anexoDAO.delete(anexo);
		}
	}

	@Override
	public AnexoVO findById(Integer id) {
		Anexo anexo = anexoDAO.findById(id);
		
		return modelMapper.map(anexo, AnexoVO.class);
	}
	
	@Override
	public Long findMaxId() {
		return anexoDAO.findMaxId();
	}
	
	@Override
	public String guardarAnexos(UploadedFile file, String nombreArchivo, String idSolicitud) {
		String ubicacionArchivo = "";
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String path = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "images"
				+ File.separatorChar + "logos" + File.separatorChar + nombreArchivo;
		
		String archivoLocal = "D:\\anexos" + File.separatorChar + idSolicitud;
		
		File borrarLogo;
		File crearCarpeta;
		
		try{
			borrarLogo = new File(path);
			if(borrarLogo.exists())
				borrarLogo.delete();
			
			crearCarpeta = new File("D:\\anexos");
			if(!crearCarpeta.exists())
				crearCarpeta.mkdirs();
			
			crearCarpeta = new File(archivoLocal);
			if(!crearCarpeta.exists())
				crearCarpeta.mkdirs();
			
			BufferedInputStream in = (BufferedInputStream) file.getInputstream(); 
			FileOutputStream out = new FileOutputStream(archivoLocal + File.separatorChar + nombreArchivo);
			byte[] buffer = new byte [(int) file.getSize()];
			int c = 0;
			while((c = in.read(buffer)) != -1 ){
				out.write(buffer, 0, c);
			}
			in.close();
			out.flush();
			out.close();
			
			ubicacionArchivo = archivoLocal + File.separatorChar + nombreArchivo;
			
			return ubicacionArchivo;

		}catch(Throwable t){
			return ubicacionArchivo;
		}	
	}

}
