package vo;

import java.io.Serializable;
import java.util.List;

public class SolicitudResponseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SolicitudVO solicitudVO;
	private List<AnexoVO> anexoVOList;
	private List<DesignacionVO> designacionVOList;
	private List<ParteVO> parteVOList;
	
	public SolicitudVO getSolicitudVO() {
		return solicitudVO;
	}
	public void setSolicitudVO(SolicitudVO solicitudVO) {
		this.solicitudVO = solicitudVO;
	}
	public List<AnexoVO> getAnexoVOList() {
		return anexoVOList;
	}
	public void setAnexoVOList(List<AnexoVO> anexoVOList) {
		this.anexoVOList = anexoVOList;
	}
	public List<DesignacionVO> getDesignacionVOList() {
		return designacionVOList;
	}
	public void setDesignacionVOList(List<DesignacionVO> designacionVOList) {
		this.designacionVOList = designacionVOList;
	}
	public List<ParteVO> getParteVOList() {
		return parteVOList;
	}
	public void setParteVOList(List<ParteVO> parteVOList) {
		this.parteVOList = parteVOList;
	}
}
