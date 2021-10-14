package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler, ParseException {
        Date fecha =new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-13");
        ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting().registrarItem(new Item(new TipoItem(1, "basededatos" ),1567,
                "item56", "item956", new SimpleDateFormat("yyyy/MM/dd").parse("2019/10/13"),
                56,"Digital","56"));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting().consultarItem(1567).getNombre());
    }
}