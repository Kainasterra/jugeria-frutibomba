package Controlador;

import Modelo.Catalogo;
import Modelo.CatalogoDao;
import Vista.JRCatalogo;
import Vista.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adolf
 */
public class ControladorCatalogo implements ActionListener {

    private frmMenu menu;
    private JRCatalogo vistaCat;
    private CatalogoDao modeloCat;
    private Catalogo cat;

    public ControladorCatalogo(frmMenu menu, JRCatalogo vistaCat, CatalogoDao modeloCat, Catalogo cat) {
        this.menu = menu;
        this.vistaCat = vistaCat;
        this.modeloCat = modeloCat;
        this.cat = cat;
        //Botones de La interfaz
        this.menu.opcCatalogo.addActionListener(this);
        this.vistaCat.btnBuscarP.addActionListener(this);
        this.vistaCat.btnListarP.addActionListener(this);
        this.vistaCat.btnLimpiarP.addActionListener(this);
    }
    public void iniciar() {
        vistaCat.setTitle("Catalogo de Calzado");
        vistaCat.setResizable(true);
        vistaCat.btnBuscarP.setEnabled(true);
        vistaCat.btnLimpiarP.setEnabled(true);
        vistaCat.btnListarP.setEnabled(true);
        LlenarTabla(vistaCat.tblCatalogo);
        vistaCat.setVisible(true);
        vistaCat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vistaCat.setClosable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.opcCatalogo) {
            menu.Escritorio.add(vistaCat);
            vistaCat.setVisible(true);
        }
        //Boton Buscar
        if (e.getSource() == vistaCat.btnBuscarP) {
            cat.setIdProducto(Integer.parseInt(vistaCat.txtIdProducto.getText()));//busca los datos a travez del id

            if (modeloCat.BuscarProducto(cat)) {
                vistaCat.txtIdProducto.setText(String.valueOf(cat.getIdProducto()));
                vistaCat.txtCodigo1.setText(cat.getCodigo1());
                vistaCat.txtnProducto.setText(cat.getnProducto());
                vistaCat.txtCantidad.setText(cat.getCantidad());
                vistaCat.txtPrecio.setText(cat.getPrecio());
                vistaCat.txtCodigo1.setText(cat.getCategoria());
                //Inhabiltamos o habilitamos los botones Limpiar y Listar al buscar
                vistaCat.btnBuscarP.setEnabled(true);
                vistaCat.btnLimpiarP.setEnabled(true);
                vistaCat.btnListarP.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron los datos");
                limpiar();
            }
        }
        if (e.getSource() == vistaCat.btnLimpiarP) {
            limpiar();
        }
        if (e.getSource() == vistaCat.btnListarP) {
            LlenarTabla(vistaCat.tblCatalogo);
        }
    }
// Metodo con el cual vamos a limpiar las tablas

    public void limpiar() {
        vistaCat.txtCodigo1.setText(null);
        DefaultTableModel temp = (DefaultTableModel) vistaCat.tblCatalogo.getModel();
        int filas = vistaCat.tblCatalogo.getRowCount();

        for (int a = 0; filas > a; a++) {
            temp.removeRow(0);
        }
    }
//Metodo con el cual llenaremos las tablas

    public void LlenarTabla(JTable tablaP) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaP.setModel(modeloTabla);
        modeloTabla.addColumn("id Producto");
        modeloTabla.addColumn("Codigo");
        modeloTabla.addColumn("Nombre del producto");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Categoria");
        

        Object[] columna = new Object[6];
        int numReg = modeloCat.listCatalogo().size();

        for (int i = 0; i < numReg; i++) {
            columna[0] = modeloCat.listCatalogo().get(i).getIdProducto();
            columna[1] = modeloCat.listCatalogo().get(i).getCodigo1();
            columna[2] = modeloCat.listCatalogo().get(i).getnProducto();
            columna[3] = modeloCat.listCatalogo().get(i).getCantidad();
            columna[4] = modeloCat.listCatalogo().get(i).getPrecio();
            columna[5] = modeloCat.listCatalogo().get(i).getCategoria();
            
            modeloTabla.addRow(columna);
        }
    }
}
