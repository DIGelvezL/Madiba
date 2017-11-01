package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.modelmapper.ModelMapper;

import dao.PagoDAOImpl;
import entidades.Pago;
import vo.AnexoVO;
import vo.PagoVO;

@Stateless
public class PagoServiceImpl implements PagoService {

	@EJB
	private PagoDAOImpl pagoDAO;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<PagoVO> findAll() {
		List<Pago> pagoList;
		List<PagoVO> pagoVOList;
		
		pagoList = pagoDAO.findAll();
		if (pagoList != null) {

			pagoVOList = new ArrayList<>();
			for (Pago item : pagoList) {
				pagoVOList.add(modelMapper.map(item, PagoVO.class));
			}

			return pagoVOList;
		} else
			return new ArrayList<>();
	}

	@Override
	public void insert(PagoVO pagoVO) {
		Pago pago = modelMapper.map(pagoVO, Pago.class);
		
		if(pago != null){
			pagoDAO.insert(pago);
		}
	}

	@Override
	public void update(PagoVO pagoVO) {
		Pago pago = modelMapper.map(pagoVO, Pago.class);
		
		if(pago != null){
			pagoDAO.update(pago);
		}
	}

	@Override
	public void delete(PagoVO pagoVO) {
		Pago pago = modelMapper.map(pagoVO, Pago.class);
		
		if(pago != null){
			pagoDAO.delete(pago);
		}
	}

	@Override
	public AnexoVO findById(Integer id) {
		Pago pago = pagoDAO.findById(id);
		
		return modelMapper.map(pago, AnexoVO.class);
	}

	@Override
	public Long findMaxId() {
		return pagoDAO.findMaxId();
	}

}
