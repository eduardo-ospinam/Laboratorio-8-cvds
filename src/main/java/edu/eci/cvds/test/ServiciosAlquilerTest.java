package edu.eci.cvds.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.ParseException;
import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }


    @Test
    public void NoPuedeConsultarCostoItemDesconocido() throws ExcepcionServiciosAlquiler {
        try{
            assertEquals(40*30,serviciosAlquiler.consultarCostoAlquiler(1900,25));
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            assertTrue(true);
        }
    }
    
    @Test
    public void NoPuedeConsultarTipoItem() throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.consultarTipoItem(40);
            assertTrue(false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            assertTrue(true);
        }
    }
    
    @Test
    public void RegristrarYConsultarItem () throws ExcepcionServiciosAlquiler{
        try{
            boolean pass = false;
            serviciosAlquiler.registrarTipoItem(new TipoItem(1,"CVDS"));
            List<TipoItem> tipoItems = serviciosAlquiler.consultarTiposItem();
            for(int i=0 ; i<tipoItems.size() ; i++){
                if(tipoItems.get(i).getDescripcion().equals("CVDS")){
                    pass = true;
                }
            }
            assertTrue(pass);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            fail();
        }
    }
    
    @Test
    public void NoPuedeActualizarItemNoRegistrado()   throws PersistenceException , ParseException {
        try{
            serviciosAlquiler.actualizarTarifaItem(10000,40);
        } catch (ExcepcionServiciosAlquiler ex){
            assertTrue(true);
        }
    }
    
    @Test
    public void ConsultarCliente() throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.registrarCliente(new Cliente("Ospina", 11, "531651651", "Calle 1","ospina@mail.escuelaing.edu.co"));
            assertEquals(serviciosAlquiler.consultarCliente(11).getDocumento(),11);
        }catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            assertTrue(false);
        }
    }
    
    @Test
    public void LanzarExcepcionSiNoExisteElCliente() throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.consultarCliente(3213213);
        }catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            assertTrue(true);
        }
    }
    
    @Test
    public void RegistrarAlCliente() throws ExcepcionServiciosAlquiler {
        try{
            serviciosAlquiler.registrarCliente(new Cliente("Deivid", 11235, "541651", "Calle 2","deivid@gmail.com"));
            long clineteRegistrado = serviciosAlquiler.consultarCliente(11235).getDocumento();
            if( clineteRegistrado == 11235 ){
                assertTrue(true);
            }
            else{
                assertTrue(false);
            }
        }catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            fail();
        }
    }
    
    @Test
    public void CambiarEstadoVetadoDelCliente() throws ExcepcionServiciosAlquiler{
        try {
            serviciosAlquiler.registrarCliente(new Cliente("Mono", 51651, "451645", "Calle 3", "mono@gmail.com"));
            serviciosAlquiler.vetarCliente(51651, true);
            if (serviciosAlquiler.consultarCliente(51651).isVetado()) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
            serviciosAlquiler.vetarCliente(51651, false);
            if (!serviciosAlquiler.consultarCliente(51651).isVetado()) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        }catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            fail();
        }
    }

}