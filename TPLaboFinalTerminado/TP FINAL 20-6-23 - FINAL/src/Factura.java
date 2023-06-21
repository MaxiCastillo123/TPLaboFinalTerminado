import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factura {
    private Usuario usuario;
    private HashMap<Integer,Producto> changuito;
    private int id;
    private static int ide = 0;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)

    private LocalDate fecha;
    private Double precioFinal;
    private MetodoDePago metodoDePago;

    public Factura(Usuario usuario, MetodoDePago metodoDePago) {
        this.usuario = usuario;
        this.changuito = new HashMap<>();
        this.precioFinal = 0d;
        this.metodoDePago = metodoDePago;
        ide++;
        this.id = ide;
        this.fecha = LocalDate.now();
    }

    public Factura() {
        this.changuito = new HashMap<>();
        ide++;
        this.id = ide;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HashMap<Integer, Producto> getChanguito() {
        return changuito;
    }

    public void setChanguito(HashMap<Integer, Producto> changuito) {
        this.changuito = changuito;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public MetodoDePago getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public void mostrarFactura(){
        System.out.println("Factura: "+ id);
        System.out.println("Usuario: " + usuario.getUsuario());
        System.out.println(String.format("%-20s", "Producto:") + String.format("%-5s", "Cantidad:")+ String.format("%-5s", " ") + "Precio unitario:");
        int i = 1;
        for(Producto p : changuito.values()){
            System.out.println(String.format("%-10s",p.getNombre()) + String.format("%-10s", " ") + p.getStock() + " " +String.format("%-15s"," ") + p.getPrecio());
            i++;
        }
        System.out.println(String.format("%-37s", "Fecha de la compra: ")+ fecha);
        System.out.println(String.format("%-37s", "Metodo de pago: ") + metodoDePago.toString());
        System.out.println(String.format("%-37s", "Precio final: ")+ precioFinal);
        System.out.println("-----------------------------------------------------------------\n");
    }

    @Override
    public String toString() {
        return "Factura{" +
                "usuario=" + usuario +
                ", changuito=" + changuito.values() +
                ", fecha=" + fecha +
                ", precioFinal=" + precioFinal +
                ", metodoDePago=" + metodoDePago +
                '}';
    }

    public Boolean existeProducto (Producto producto){
        for(Map.Entry<Integer, Producto> p : changuito.entrySet()){
            if (p.getKey().equals(producto.getId())){
                return true;
            }
        }
        return false;
    }

    public void agregarProductoFactura(Producto producto) {
        if(existeProducto(producto)){
            for (Map.Entry<Integer, Producto> p : changuito.entrySet()){
                if(p.getKey().equals(producto.getId())){
                    p.getValue().setStock(p.getValue().getStock()+producto.getStock());
                }
            }
        }else{
            changuito.put(producto.getId(),producto);
        }
    }

    public MetodoDePago elegirMetodoDePego(int opcion) {

        if (opcion == 1) {
            this.setMetodoDePago(MetodoDePago.EFECTIVO);
        } else if (opcion == 2) {
            this.setMetodoDePago(MetodoDePago.DEBITO);
        } else {
            this.setMetodoDePago(MetodoDePago.CREDITO);
        }
        return this.metodoDePago;
    }

    public Double obtenerPrecioFinal() {
        Double precioFinal = 0.00;
        for (Producto p : changuito.values()) {
            precioFinal += p.getPrecio() * p.getStock();
        }
        if (this.metodoDePago.equals(MetodoDePago.EFECTIVO)) {
            return precioFinal = precioFinal - (precioFinal * 0.10);
        } else if (this.metodoDePago.equals(MetodoDePago.CREDITO)) {
            return precioFinal = precioFinal + (precioFinal * 0.10);
        }
        return precioFinal;
    }
}


