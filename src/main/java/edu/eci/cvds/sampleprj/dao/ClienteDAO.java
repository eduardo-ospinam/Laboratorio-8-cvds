package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ClienteDAO {
	
    public void insertarCliente(Cliente c) throws PersistenceException;

	public Cliente consultarCliente(long id) throws PersistenceException;
	
	public List<Cliente> consultarClientes() throws PersistenceException;
	
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws PersistenceException;
	
	public void agregarItemRentadoACliente(int idCliente,int idItem,Date fechaInicio,Date fechaFin)throws PersistenceException;
	
	public void vetarCliente (long idCliente, Boolean estado)throws PersistenceException;
	

}