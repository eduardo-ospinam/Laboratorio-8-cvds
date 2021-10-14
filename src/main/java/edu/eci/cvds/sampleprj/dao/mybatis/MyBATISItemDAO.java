package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;

import java.util.List;

public class MyBATISItemDAO implements ItemDAO{

  @Inject
  private ItemMapper itemMapper;    

  @Override
  public void insertarItem(Item it) throws PersistenceException{
  try{
      itemMapper.insertarItem(it);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
  }        

  }

  @Override
  public Item consultarItem(int id) throws PersistenceException {
	  try{
	      return itemMapper.consultarItem(id);
	  }
	  catch(org.apache.ibatis.exceptions.PersistenceException e){
	      throw new PersistenceException("Error al consultar el item con el id: " + id,e);
	  }
  }

	@Override
	public List<Item> consultarItems() throws PersistenceException {
        try{
            return itemMapper.consultarItems();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("No se pudo consultar los items",e);
        }
	}
	
	@Override
	public List<Item> consultarItemsDisponibles() throws PersistenceException {
        try{
            return itemMapper.consultarItemsDisponibles();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar items disponibles",e);
        }
	}
	
	@Override
	public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
		// TODO Auto-generated method stub
        try {
            itemMapper.actualizarTarifaItem(id,tarifa);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al actualizar tarifa del item", e);
        }
	}

 }
