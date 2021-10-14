package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {
	
    public ItemRentado consultarItemRentado(int id) throws PersistenceException;

    public List<ItemRentado> consultarItemRentados() throws PersistenceException;

}
