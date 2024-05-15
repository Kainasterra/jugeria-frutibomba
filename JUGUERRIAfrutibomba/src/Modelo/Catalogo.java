package Modelo;

/**
 *
 * @author Adolf
 */
public class Catalogo {

    private int idProducto;
    private String nProducto;
    private String Cantidad;
    private String Precio;
    private String Categoria;
    private String Codigo1;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getnProducto() {
        return nProducto;
    }

    public void setnProducto(String nProducto) {
        this.nProducto = nProducto;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
     public String getCodigo1() {
        return Codigo1;
    }

    public void setCodigo1(String Codigo1) {
        this.Codigo1 = Codigo1;
    }
}

