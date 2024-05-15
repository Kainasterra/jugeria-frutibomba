package Principal;

import Controlador.ControladorCatalogo;
import Controlador.ControladorPuntoVentas;
import Controlador.ControladorProducto;
import Modelo.Catalogo;
import Modelo.CatalogoDao;
import Modelo.Productos;
import Modelo.ProductosDao;
import Modelo.PuntoVentas;
import Modelo.PuntoVentasDAO;
import Vista.JRCatalogo;
import Vista.JRRegistro;
import Vista.JRPuntoVentas;
import Vista.frmMenu;

/**
 *
 * @author Adolf
 */
public class Principal {

    public static void main(String[] args) {
        
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        menu.setExtendedState(menu.MAXIMIZED_BOTH);
        
        JRCatalogo vistaCal = new JRCatalogo();
        CatalogoDao modeloCal = new CatalogoDao();
        Catalogo cat = new Catalogo();
        ControladorCatalogo controladorC = new ControladorCatalogo(menu, vistaCal, modeloCal, cat);
        controladorC.iniciar();
        
        JRRegistro vistaP = new JRRegistro();
        ProductosDao modeloP = new ProductosDao();
        Productos pro = new Productos();
        ControladorProducto controladorP = new ControladorProducto(menu,vistaP,modeloP,pro);
        controladorP.iniciar();
        
        JRPuntoVentas vistaV = new JRPuntoVentas();
        PuntoVentasDAO modeloV = new PuntoVentasDAO();
        PuntoVentas VE = new PuntoVentas();
        Controlador.ControladorPuntoVentas controladorV = new ControladorPuntoVentas(menu, vistaV, modeloV, VE);
        controladorV.iniciar();

    }
}
