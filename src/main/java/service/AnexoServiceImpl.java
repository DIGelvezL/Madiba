package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.AnexoDAOImpl;
import entidades.Anexo;
import vo.AnexoVO;


@Stateless
public class AnexoServiceImpl implements AnexoService {
	
	@EJB
	private AnexoDAOImpl anexoDAO;

	@Override
	public List<AnexoVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(AnexoVO anexoVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AnexoVO anexoVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(AnexoVO anexoVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public AnexoVO getById(AnexoVO anexoVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	private AnexoVO entityToVO(Anexo anexo) {
//		if(anexo != null){
//			AnexoVO anexoVO = new AnexoVO();
//	
//			anexoVO.setIdAnexo(anexo.getIdAnexo());
//			anexoVO.setDescripcion(anexo.getDescripcion());
//			anexoVO.setUbicacion(anexo.getUbicacion());
//			//anexoVO.setSolicitud(anexo.getSolicitud());
//	
//			return anexoVO;
//		}else
//			return new AnexoVO();
//	}
	
//	private Cliente voToEntity(ClienteVO clienteVO) {
//		if(clienteVO != null){
//			Cliente cliente = new Cliente();
//	
//			cliente.setNombres(clienteVO.getNombres());
//			cliente.setApellidos(clienteVO.getApellidos());
//			cliente.setIdentificacion(clienteVO.getIdentificacion());
//			cliente.setDireccion(clienteVO.getDireccion());
//			cliente.setTelefono(clienteVO.getTelefono());
//			cliente.setEmail(clienteVO.getEmail());
//	
//			return cliente;
//		}else
//			return new Cliente();
//	}

}
