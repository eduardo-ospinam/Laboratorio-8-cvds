package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemDAO {
	
	
	public void addTipoItem(TipoItem tipoItem) throws PersistenceException;

	public TipoItem getTipoItem(int id) throws PersistenceException;

	public List<TipoItem> getTiposItems() throws PersistenceException;

}