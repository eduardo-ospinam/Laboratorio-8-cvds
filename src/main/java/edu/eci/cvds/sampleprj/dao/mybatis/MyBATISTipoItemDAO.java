package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{
	
    @Inject
    private TipoItemMapper tipoItemMapper;

	@Override
	public void addTipoItem(TipoItem tipoItem) throws PersistenceException {
        try{
            tipoItemMapper.addTipoItem(tipoItem);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar tipo item." ,e);
        }
	}

	@Override
	public TipoItem getTipoItem(int id) throws PersistenceException {
        try{
            return tipoItemMapper.getTipoItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("No se pudo consultar el tipo item con id: "+ id,e);
        }
	}

	@Override
	public List<TipoItem> getTiposItems() throws PersistenceException {
        try{
            return tipoItemMapper.getTiposItems();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los tipo items.",e);
        }
	}



}
