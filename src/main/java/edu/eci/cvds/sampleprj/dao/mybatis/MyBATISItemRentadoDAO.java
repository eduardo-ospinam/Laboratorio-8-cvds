package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {

    @Inject
    private ItemRentadoMapper itemRentadoMapper;

	@Override
	public ItemRentado consultarItemRentado(int id) throws PersistenceException {
        itemRentadoMapper.consultarItemRentado(id);
        return null;
	}

	@Override
	public List<ItemRentado> consultarItemRentados() throws PersistenceException {
		itemRentadoMapper.consultarItemRentados();
		return null;
	}
	


}
