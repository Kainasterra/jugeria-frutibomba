package Controlador;

import Modelo.Productos;
import Modelo.ProductosDao;
import Vista.JRRegistro;
import Vista.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Adolf
 */
public class ControladorProducto implements ActionListener {

    private frmMenu menu;
    private JRRegistro VistaProducto;
    private ProductosDao modeloProducto;
    private Productos productos;

    public ControladorProducto(frmMenu menu, JRRegistro VistaP, ProductosDao modeloP, Productos productos) {
        this.menu = menu;
        this.VistaProducto = VistaP;
        this.modeloProducto = modeloP;
        this.productos = productos;

        this.menu.opcRegistro.addActionListener(this);
        this.VistaProducto.btnGuardar1.addActionListener(this);
        this.VistaProducto.btnModificar1.addActionListener(this);
        this.VistaProducto.btnEliminar1.addActionListener(this);
        this.VistaProducto.btnBuscar1.addActionListener(this);
        this.VistaProducto.btnNuevo1.addActionListener(this);

    }

    public void iniciar() {

        VistaProducto.setTitle("Registro de Calzado");
        VistaProducto.setResizable(true);
        VistaProducto.txtIdProducto1.setVisible(true);
        VistaProducto.setVisible(true);
        VistaProducto.setClosable(true);
        VistaProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.opcRegistro) {
            menu.Escritorio.add(VistaProducto);
            VistaProducto.setVisible(true);
        }//Boton Guardar
        if (e.getSource() == VistaProducto.btnGuardar1) {
            productos.setCodigo(VistaProducto.txtCodigo.getText());
            productos.setnProductos1(VistaProducto.txtnProducto1.getText());
            productos.setCantidad1(VistaProducto.txtCantidad1.getText());
            productos.setPrecio1(VistaProducto.txtPrecio1.getText());
            productos.setCategoria1(VistaProducto.txtCategoria1.getText());
            
            
            if (modeloProducto.GuardarProducto(productos)) {
                JOptionPane.showMessageDialog(null, "Producto guardado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error producto no guardado");
            }
            limpiar();

        }
        //Boton Modificar
        if (e.getSource() == VistaProducto.btnModificar1) {
            productos.setidProducto1(Integer.parseInt(VistaProducto.txtIdProducto1.getText()));
            productos.setnProductos1(VistaProducto.txtnProducto1.getText());
            productos.setCantidad1(VistaProducto.txtCantidad1.getText());
            productos.setPrecio1(VistaProducto.txtPrecio1.getText());
            productos.setCategoria1(VistaProducto.txtCategoria1.getText());
            if (JOptionPane.showConfirmDialog(null, "Esta seguro que quiere actualizar el registro del producto?") == 0) {
                if (modeloProducto.ModificarProducto(productos)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar el registro");
                }
            }
            //inhabilitamos botones
            VistaProducto.btnModificar1.setEnabled(true);
            VistaProducto.btnEliminar1.setEnabled(true);
            VistaProducto.btnGuardar1.setEnabled(true);
            VistaProducto.btnNuevo1.setEnabled(true);
            limpiar();

        }
        //Boton Eliminar
        if (e.getSource() == VistaProducto.btnEliminar1) {
            productos.setidProducto1(Integer.parseInt(VistaProducto.txtIdProducto1.getText()));
            if (JOptionPane.showConfirmDialog(null, "esta seguro de eliminar") == 0) {
                if (modeloProducto.EliminarProducto(productos)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
                }
            }
            //inhabilitimos botones
            VistaProducto.btnModificar1.setEnabled(true);
            VistaProducto.btnEliminar1.setEnabled(true);
            VistaProducto.btnGuardar1.setEnabled(true);
            VistaProducto.btnNuevo1.setEnabled(true);
            limpiar();
        }
        //Boton Buscar
        if (e.getSource() == VistaProducto.btnBuscar1) {
            productos.setidProducto1(Integer.parseInt(VistaProducto.txtIdProducto1.getText()));

            if (modeloProducto.BuscarProducto(productos)) {
                VistaProducto.txtIdProducto1.setText(String.valueOf(productos.getidProductos1()));
                VistaProducto.txtCodigo.setText(productos.getCodigo());
                VistaProducto.txtnProducto1.setText(productos.getnProductos1());
                VistaProducto.txtCantidad1.setText(productos.getCantidad1());
                VistaProducto.txtPrecio1.setText(productos.getPrecio1());
                VistaProducto.txtCategoria1.setText(productos.getCategoria1());
                
                
                
                //inhabilitamos Botones 
                VistaProducto.btnModificar1.setEnabled(true);
                VistaProducto.btnEliminar1.setEnabled(true);
                //desabilito el boton guardar y nuevo 
                VistaProducto.btnGuardar1.setEnabled(false);
                VistaProducto.btnNuevo1.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron los datos");
                limpiar();
            }
        }

        if (e.getSource() == VistaProducto.btnNuevo1) {
            limpiar();

        }
    }

    public void limpiar() {
        VistaProducto.txtIdProducto1.setText(null);
        VistaProducto.txtCodigo.setText(null);
        VistaProducto.txtnProducto1.setText(null);
        VistaProducto.txtCantidad1.setText(null);
        VistaProducto.txtPrecio1.setText(null);
        VistaProducto.txtCategoria1.setText(null);
        

    }
}
