package edu.eci.cvds.sampleprj.dao.mybatis;

import java.sql.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISClienteDAO implements ClienteDAO {
	
    @Inject
    private ClienteMapper clienteMapper;

	@Override
	public void insertarCliente(Cliente c) throws PersistenceException {
		// TODO Auto-generated method stub
        try{
            clienteMapper.insertarCliente(c);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar al cliente" ,e);
        }
	}

	@Override
	public Cliente consultarCliente(long id) throws PersistenceException {
		// TODO Auto-generated method stub
        try{
            return clienteMapper.consultarCliente(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar cliente con documento"+id,e);
        }
	}

	@Override
	public List<Cliente> consultarClientes() throws PersistenceException {
        try{
            return clienteMapper.consultarClientes();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar clientes", e);
        }
	}

	@Override
	public List<ItemRentado> consultarItemsCliente(long idcliente) throws PersistenceException {
        try{
            return clienteMapper.consultarItemsCliente(idcliente);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items del client" ,e);
        }
	}

	@Override
	public void agregarItemRentadoACliente(int idCliente, int idItem, Date fechaInicio, Date fechaFin) throws PersistenceException {
	    try{
	        clienteMapper.agregarItemRentadoACliente(idCliente,idItem,fechaInicio,fechaFin);
	    }
	    catch(org.apache.ibatis.exceptions.PersistenceException e){
	        throw new PersistenceException("No se pudo aggregar el item al cliente con id: "+idCliente+" y id de tipo item:"+idItem,e);
	    }
	}

	@Override
	public void vetarCliente(long idCliente, Boolean estado) throws PersistenceException {
        try{
            clienteMapper.vetarCliente(idCliente, estado);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("No se pudo vetar al cliente con id: "+ idCliente ,e);
        }
		
	}
	
}
