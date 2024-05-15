/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class PuntoVentasDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    
    public boolean Guardar(PuntoVentas PV) {
     
        
        String SentenciaSQL = "INSERT INTO Ventas (Codigo, nombre_calzado, Categoria, Cantidad, Precio, Total)"
                + " VALUES (?,?,?,?,?,?)";

        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(SentenciaSQL);
           
             ps.setInt(1,PV.getCodigo());
             ps.setString(2,PV.getNombreProducto());
             ps.setString(3,PV.getCategoria());
             ps.setInt(4,PV.getCantidad());
             ps.setInt(5,PV.getPrecio());
             ps.setInt(6,PV.getTotal());
             
             ps.executeUpdate();
             
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } 
    }
    
     public boolean Buscar(PuntoVentas PV) {
        
        
         String SentenciaSQL = "SELECT Codigo, nombre_calzado, Categoria, Precio FROM Ventas WHERE nombre_calzado =?";
        try{
            
            con = conectar.Conectar();
            ps = con.prepareStatement(SentenciaSQL);
            //ps.setString(0, nom);
            ps.setString(1, PV.getNombreProducto());
            rs = ps.executeQuery();
            
           if(rs.next()){
               PV.setCodigo(Integer.parseInt(rs.getString("Codigo")));
               PV.setNombreProducto(rs.getString("NombreProducto"));
               PV.setCategoria(rs.getString("Categoria"));
               PV.setPrecio(Integer.parseInt(rs.getString("Precio")));
               return true;
               
           } else{
               JOptionPane.showMessageDialog(null,"Producto No Existente");
               return false;
           }
           
       } catch (Exception e){
           System.err.println(e);
                   
                   }return false;
     }
}
    
    
    

