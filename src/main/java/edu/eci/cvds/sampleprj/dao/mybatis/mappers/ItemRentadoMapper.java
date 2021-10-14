package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import edu.eci.cvds.samples.entities.*;

public interface ItemRentadoMapper {
	
	public List<TipoItem> consultarItemRentados();
	
	public TipoItem consultarItemRentado(int id);
	
}
