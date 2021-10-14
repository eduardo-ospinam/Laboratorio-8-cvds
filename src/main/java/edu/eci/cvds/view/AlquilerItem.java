package edu.eci.cvds.view;



import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.sql.Date;
import java.util.List;

@ManagedBean(name = "AlquilerItem")
@ApplicationScoped
public class AlquilerItem extends BasePageBean {
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente selectedCliente;
    private long costo;

    public void registrarelCliente(String nombre,long doc,String telefono, String direccion,String email) throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.registrarCliente(new Cliente(nombre,doc,telefono,direccion,email));
        } catch (ExcepcionServiciosAlquiler ex) {
            throw new ExcepcionServiciosAlquiler("Error registrando cliente");
        }
    }

    public Cliente getCliente() {
        return selectedCliente;
    }

    public void setCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<Cliente> consultarlosClientes() throws ExcepcionServiciosAlquiler {
        try{
            return serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("Error con consulta de clientes");
        }
    }

    public List<ItemRentado> consultarlosItemsdelCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarItemsCliente(idcliente);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("no se pudo consultar los items del cliente");
        }
    }

    public void registrarunAlquiler(int idItem , int numdias) throws ExcepcionServiciosAlquiler {
        Item item = serviciosAlquiler.consultarItem(idItem);
        serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),selectedCliente.getDocumento(),item,numdias);
    }

    public long consultarUnaMulta(int iditem) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarMultaAlquiler(iditem, new Date(System.currentTimeMillis()));
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("no se pudo consultar la multa");
        }
    }

    public void consultarUnCosto(int iditem , int numdias) throws ExcepcionServiciosAlquiler {
        try {
            this.costo = serviciosAlquiler.consultarCostoAlquiler(iditem, numdias);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            throw new ExcepcionServiciosAlquiler("no se pudo consultar costo alquiler");
        }
    }
    public long getCosto(){
        return costo;
    }
}