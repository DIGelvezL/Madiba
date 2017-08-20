package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.ConciliadorDAOImpl;
import entidades.Conciliador;
import entidades.Conciliador_Especialidad;
import vo.ConciliadorVO;
import org.modelmapper.ModelMapper;

@Stateless
public class ConciliadorServiceImpl implements ConciliadorService {
	
	@EJB
	private ConciliadorDAOImpl conciliadorDAO;
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ConciliadorVO> findAll() {
		List<Conciliador> conciliadorList;
		List<ConciliadorVO> conciliadorVOList;

		try {
			conciliadorList = conciliadorDAO.findAll();

			if (conciliadorList != null) {

				conciliadorVOList = new ArrayList<>();
				for (Conciliador item : conciliadorList) {
					List<String> nombreEspecialidadList = new ArrayList<>();
					for(Conciliador_Especialidad l: item.getConciliadorEspecialidads()){
						nombreEspecialidadList.add(l.getEspecialidad().getNombre());
					}
					ConciliadorVO coVO = modelMapper.map(item, ConciliadorVO.class);
					coVO.setNombreEspecialidad(nombreEspecialidadList);
					conciliadorVOList.add(coVO);
				}

				return conciliadorVOList;
			} else
				return conciliadorVOList = null;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public void insert(ConciliadorVO conciliadorVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ConciliadorVO conciliadorVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ConciliadorVO conciliadorVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public ConciliadorVO findById(Integer id) {
			Conciliador conciliador;
			conciliador = conciliadorDAO.findById(id);
			
			return modelMapper.map(conciliador, ConciliadorVO.class);
	}

}
