/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2162877;
            registrarNuevoProducto(con, suCodigoECI, "Ospina", 20000);            
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        //Asignar parámetros
        //usar 'execute' 

        PreparedStatement productoRegistrar = null;
        String insertar = "INSERT INTO ORD_PRODUCTOS VALUES (?,?,?);"; //Se realiza un insert sin valores especificos para poder asginarlos despues
        productoRegistrar = con.prepareStatement(insertar);
        productoRegistrar.setInt(1, codigo);
        productoRegistrar.setString(2, nombre);
        productoRegistrar.setInt(3, precio);
        productoRegistrar.executeUpdate();
        con.commit();
        
    }
    
    
    
    /** 
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultados del ResultSet
        //Llenar la lista y retornarla
        
        PreparedStatement nameProducto = null; // El PreparedStatement nos permite definir una sentencia SQL base, que nos sirve para modificar/insertar/buscar 
        String select = "SELECT nombre , pedido_fk " +
                        "FROM ORD_PRODUCTOS ordProduct ,ORD_DETALLE_PEDIDO ordPedido " +
                        "WHERE ordProduct.codigo = ordPedido.producto_fk " +
                        "ORDER BY pedido_fk;";
        nameProducto = con.prepareStatement(select);
        ResultSet informacionSelect = nameProducto.executeQuery(); //Puede utilizar un objeto ResultSet para ejecutar una consulta y examinar los resultados de la consulta.
        while(informacionSelect.next()){
            String nombreProducto= informacionSelect.getString("nombre");
            String pedidoProducto = informacionSelect.getString("pedido_fk");
            np.add(nombreProducto);
            np.add(pedidoProducto);
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException {
        
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultado del ResultSet
        
        PreparedStatement valorPedido = null;
        String select = "SELECT sum(cantidad) as valor " +
                        "FROM ORD_DETALLE_PEDIDO " +
                        "WHERE pedido_fk ="+codigoPedido+" "+
                        "GROUP BY (pedido_fk);";
        valorPedido = con.prepareStatement(select);
        ResultSet valor = valorPedido.executeQuery();
        int costoDelPedido = 0;
        while(valor.next()){
        	costoDelPedido = valor.getInt("valor");
        }
        return costoDelPedido;
    }
   
}