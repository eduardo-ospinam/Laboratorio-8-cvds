package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO {

   public void insertarItem(Item it) throws PersistenceException;

   public Item consultarItem(int id) throws PersistenceException;
   
   public List<Item> consultarItems() throws PersistenceException;

   public List<Item> consultarItemsDisponibles() throws PersistenceException ;

   public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException ;

}