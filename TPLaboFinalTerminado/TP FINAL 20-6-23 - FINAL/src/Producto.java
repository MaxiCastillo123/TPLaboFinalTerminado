import java.io.Serializable;

public class Producto  {

    private String nombre;
    private Double precio;
    private Integer stock;
    private int id;
    private static int ide=0;

    public Producto(String nombre, Double precio, Integer stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        ide++;
        id= ide;
    }

    public Producto(String nombre, Double precio, Integer stock, int id) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id=id;
    }

    public Producto(){
        ide++;
        id= ide;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }



}
