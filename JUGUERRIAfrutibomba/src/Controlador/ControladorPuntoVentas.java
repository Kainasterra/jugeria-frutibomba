/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.PuntoVentas;
import Modelo.PuntoVentasDAO;
import Vista.JRPuntoVentas;
import Vista.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class ControladorPuntoVentas implements ActionListener, KeyListener, MouseListener{

    private frmMenu menu;
    private JRPuntoVentas VistaVen;
    private PuntoVentasDAO VenDao;
    private PuntoVentas Ven;
    DefaultTableModel modelo;
    int filas;
    
    
    public ControladorPuntoVentas(frmMenu menu, JRPuntoVentas VistaVen, PuntoVentasDAO VenDao, PuntoVentas Ven) {
        
        this.menu = menu;
        this.VistaVen = VistaVen;
        this.VenDao = VenDao;
        this.Ven = Ven;
        
         this.menu.opcVenta.addActionListener(this);
         this.VistaVen.btnCobrar.addActionListener(this);
         this.VistaVen.btnGuardar.addActionListener(this);
         this.VistaVen.btnBuscar.addActionListener(this);
         this.VistaVen.btnEliminar.addActionListener(this);
         this.VistaVen.btnModificar.addActionListener(this);
         this.VistaVen.btnNuevo.addActionListener(this);
         this.VistaVen.txtCantidad.addKeyListener(this);
         this.VistaVen.tblProductos.addMouseListener(this);
         
         modelo = new DefaultTableModel(){
             public boolean isCellEditable(int rowIndex, int mColIndex) 
                {
                return false;
                }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Producto");
        modelo.addColumn("Categoria");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
        this.VistaVen.tblProductos.setModel(modelo);
    }
    
    public void iniciar() {
        VistaVen.setTitle("Punto Venta");
        VistaVen.setResizable(true);//se podrá modificar su tamaño      
        VistaVen.txtId.setVisible(false);
        VistaVen.setVisible(true);
        VistaVen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        VistaVen.setClosable(true);
        VistaVen.btnCobrar.setEnabled(false);
        VistaVen.btnEliminar.setEnabled(false);
        VistaVen.btnModificar.setEnabled(false);
         }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== menu.opcVenta) {
            menu.Escritorio.add(VistaVen);
            VistaVen.setVisible(true);
        }
        
        
        if (e.getSource() == VistaVen.btnCobrar) {
          /* String columna1= null;
           String columna2= null;
           String columna3= null;
           String columna4= null;
           String columna5= null;
           String columna6= null;
            
           for (int i = 0; i<VistaVen.tblProductos.getRowCount(); i++){
                
               columna1 = VistaVen.tblProductos.getValueAt(i, 1).toString();
               columna2 = VistaVen.tblProductos.getValueAt(i, 2).toString();
               columna3 = VistaVen.tblProductos.getValueAt(i, 3).toString();
               columna4 = VistaVen.tblProductos.getValueAt(i, 4).toString();
               columna5 = VistaVen.tblProductos.getValueAt(i, 5).toString();
               columna6 = VistaVen.tblProductos.getValueAt(i, 6).toString();
               
             
            Ven.setCodigo(1, VistaVen.tblProductos.getValueAt(i, 1));
            Ven.setNombreProducto(2);
            Ven.setCategoria(3);
            Ven.setCantidad(4);
            Ven.setPrecio(5);
            Ven.setTotal(6);
            
             
            }*/
          
          }
        
        if (e.getSource() == VistaVen.btnBuscar) {
            
           
            Ven.setNombreProducto(VistaVen.txtNombreProducto.getText());
            
            if (VenDao.Buscar(Ven)) {
                VistaVen.txtCodigo.setText(String.valueOf(Ven.getCodigo()));
                VistaVen.txtNombreProducto.setText(Ven.getNombreProducto());
                VistaVen.txtCategoria.setText(Ven.getCategoria());
                VistaVen.txtPrecio.setText(String.valueOf(Ven.getPrecio()));
                
               
            }else {
                JOptionPane.showMessageDialog(null, "No se encontro el registro");
            }
                
                

          }
        
        
         if (e.getSource() == VistaVen.btnGuardar) {
            
          if(VistaVen.txtCodigo.getText().equals("")||
             VistaVen.txtNombreProducto.getText().equals("")||
             VistaVen.txtCategoria.getText().equals("")||
             VistaVen.txtCantidad.getText().equals("")||
             VistaVen.txtPrecio.getText().equals("") ) {
              
             JOptionPane.showMessageDialog(null, "Complete Todos Los Campos");
           
          }else{
        String []info = new String[6];
        
        info[0]=VistaVen.txtCodigo.getText();
        info[1]=VistaVen.txtNombreProducto.getText();
        info[2]=VistaVen.txtCategoria.getText();
        info[3]=VistaVen.txtCantidad.getText();
        info[4]=VistaVen.txtPrecio.getText();
        info[5]=VistaVen.txtFinal.getText();
        modelo.addRow(info);
        
        
        VistaVen.txtCodigo.setText("");
        VistaVen.txtNombreProducto.setText("");
        VistaVen.txtCategoria.setText("");
        VistaVen.txtCantidad.setText("");
        VistaVen.txtPrecio.setText("");
        VistaVen.txtFinal.setText("");
       
        VistaVen.btnCobrar.setEnabled(true);
        Final();
         }
         }
         
         if (e.getSource() == VistaVen.btnEliminar) {
             
             int seleccion=VistaVen.tblProductos.getSelectedRow();
             if(seleccion >= 0){
                 VistaVen.tblProductos.remove(seleccion);
             }
         }
         
         
         if (e.getSource() == VistaVen.btnModificar) {
            
             String []info = new String[6];
        info[0]=VistaVen.txtCodigo.getText();
        info[1]=VistaVen.txtNombreProducto.getText();
        info[2]=VistaVen.txtCategoria.getText();
        info[3]=VistaVen.txtCantidad.getText();
        info[4]=VistaVen.txtPrecio.getText();
        info[5]=VistaVen.txtFinal.getText();
        
        for (int i=0; i < VistaVen.tblProductos.getColumnCount(); i++){
            modelo.setValueAt(info[i],filas, i);
        }
        
        Final();
         }
         if (e.getSource() == VistaVen.btnNuevo) {
            LimpiarCajas();
         }
    }
    
   
    public void LimpiarTabla(){
       for(int i = 0; i<= VistaVen.tblProductos.getRowCount(); i++){
         modelo.removeRow(i);
          i = i-1;
        }
    }
    public void LimpiarCajas (){
        
        VistaVen.txtCodigo.setText(null);
        VistaVen.txtNombreProducto.setText(null);
        VistaVen.txtCategoria.setText(null);
        VistaVen.txtCantidad.setText(null);
        VistaVen.txtPrecio.setText(null);
        VistaVen.txtFinal.setText(null);
        
    }
    private void Final(){
       
       float suma =0;
       for (int i = 0; i < VistaVen.tblProductos.getRowCount(); i++)
       {
       float renglon;
       renglon = Float.parseFloat(VistaVen.tblProductos.getValueAt(i, 4).toString() );
       
       suma = suma + renglon;
       }
       VistaVen.txtTotal2.setText(String.valueOf(suma));
       VistaVen.txtTotal1.setText(String.valueOf(suma*0.20));
       VistaVen.txtTotal.setText(String.valueOf(suma + suma * 0.20));
   
    }
    @Override
    public void keyTyped(KeyEvent e) {
       
    }
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
       
        float a,b,c;
        if (e.getSource() == VistaVen.txtCantidad) {
        
            
            a = Float.parseFloat(VistaVen.txtCantidad.getText());
            b = Float.parseFloat(VistaVen.txtPrecio.getText());
            c = a*b;
            VistaVen.txtFinal.setText(String.valueOf(c));
            
            if(VistaVen.txtFinal.getText().equals("")){
       
             VistaVen.txtFinal.setText("0");
            }
         
        }
        
           
        
      
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == VistaVen.tblProductos) {
            
        int seleccion=VistaVen.tblProductos.getSelectedRow();
        
        VistaVen.txtCodigo.setText(VistaVen.tblProductos.getValueAt(seleccion , 0).toString());
        VistaVen.txtNombreProducto.setText(VistaVen.tblProductos.getValueAt(seleccion , 1).toString());
        VistaVen.txtCategoria.setText(VistaVen.tblProductos.getValueAt(seleccion , 2).toString());
        VistaVen.txtCantidad.setText(VistaVen.tblProductos.getValueAt(seleccion , 3).toString());
        VistaVen.txtPrecio.setText(VistaVen.tblProductos.getValueAt(seleccion , 4).toString());
        VistaVen.txtFinal.setText(VistaVen.tblProductos.getValueAt(seleccion , 5).toString());
        filas=seleccion;
        
        VistaVen.btnEliminar.setEnabled(true);
        VistaVen.btnModificar.setEnabled(true);
        VistaVen.btnGuardar.setEnabled(false);
        VistaVen.btnCobrar.setEnabled(false);
        VistaVen.btnBuscar.setEnabled(false);
        
            
       }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
