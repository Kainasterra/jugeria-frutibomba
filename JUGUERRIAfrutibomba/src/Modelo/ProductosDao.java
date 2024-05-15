package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adolf
 */
public class ProductosDao {

    Conexion con = new Conexion();
    PreparedStatement psql;
    ResultSet rs;
//Boton Guardar

    public boolean GuardarProducto(Productos pr) {
        Connection cn = null;
        String SentenciaSQL = "INSERT INTO registro_zapatos(nombre_calzado,Cantidad,Precio,Categoria,Codigo)" + "Values (?,?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1, pr.getnProductos1());
            psql.setString(2, pr.getCantidad1());
            psql.setString(3, pr.getPrecio1());
            psql.setString(4, pr.getCategoria1());
            psql.setString(5, pr.getCodigo());
            psql.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            con.cerrar();
        }
    }

    //Boton Modificar
    public boolean ModificarProducto(Productos pr) {
        Connection cn = null;
        String SentenciasSQL = "UPDATE registro_zapatos SET Cantidad=? , Precio=? , Categoria=?,Codigo=? WHERE idProductos=?";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciasSQL);
            psql.setString(1, pr.getnProductos1());
            psql.setString(2, pr.getCantidad1());
            psql.setString(3, pr.getPrecio1());
            psql.setString(4, pr.getCategoria1());
            psql.setString(5, pr.getCodigo());
            psql.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            con.cerrar();
        }

    }

    //Boton eliminar 
    public boolean EliminarProducto(Productos pr) {
        Connection cn = null;
        String SentenciasSQL = "DELETE FROM registro_zapatos WHERE idProductos=?";
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciasSQL);
            psql.setInt(1, pr.getidProductos1());
            psql.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            con.cerrar();
        }

    }
//Boton Buscar

    public boolean BuscarProducto(Productos pr) {
        Connection cn = null;
        String SentenciasSQL = "SELECT * FROM registro_zapatos WHERE Codigo=?";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciasSQL);
            psql.setInt(1, pr.getidProductos1());
            psql.executeUpdate();

            if (rs.next()) {

                pr.setidProducto1(Integer.parseInt(rs.getString("idProductos")));
                pr.setCodigo(rs.getString("Codigo"));
                pr.setnProductos1(rs.getString("nombre_calzado"));
                pr.setCantidad1(rs.getString("Cantidad"));
                pr.setPrecio1(rs.getString("Precio"));
                pr.setCategoria1(rs.getString("Categoria"));
                
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            con.cerrar();
        }

    }
}
