package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Adolf
 */
public class CatalogoDao {

    Conexion con = new Conexion();
    PreparedStatement psql;
    ResultSet rs;

    //Añadimos funcion al boton Buscar
    public boolean BuscarProducto(Catalogo cat) {
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM registro_zapatos WHERE idProductos=?";
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1, cat.getIdProducto());
            rs = psql.executeQuery();
            
            if (rs.next()) {
                cat.setIdProducto(Integer.parseInt(rs.getString("idProductos")));
                cat.setCodigo1(rs.getString("Codigo"));
                cat.setnProducto(rs.getString("nombre_calzado"));
                cat.setCantidad(rs.getString("Cantidad"));
                cat.setPrecio(rs.getString("Precio"));
                cat.setCategoria(rs.getString("Categoria"));
                
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

    /*Este metodo selecciona toda la informacion de la tabla Catalogo,
    la pasa a un vector de arreglos, para despues mandarlo a mostrar en una tabla*/
    public ArrayList<Catalogo> listCatalogo() {
        ArrayList listaCatalogo = new ArrayList();
        Catalogo ctlg;

        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM registro_zapatos";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs = psql.executeQuery();

            while (rs.next()) {
                ctlg = new Catalogo();
                ctlg.setIdProducto(rs.getInt(1));
                ctlg.setCodigo1(rs.getString(2));
                ctlg.setnProducto(rs.getString(3));
                ctlg.setCantidad(rs.getString(4));
                ctlg.setPrecio(rs.getString(5));
                ctlg.setCategoria(rs.getString(6));
                
                listaCatalogo.add(ctlg);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            con.cerrar();
        }
        return listaCatalogo;
    }
}
