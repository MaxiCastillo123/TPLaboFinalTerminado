import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    //  Supermercado market = new Supermercado(new Encargado("user", "123"));
        Supermercado market = new Supermercado();
    /*  Producto producto1 = new Producto("Gaseosa", 400d, 100);
        Producto producto2 = new Producto("Galletitas", 230d, 50);
        Producto producto3 = new Producto("Cafe", 1550d, 100);
        Producto producto4 = new Producto("Azucar", 450d, 60);
        Producto producto5 = new Producto("Yerba", 1200d, 80);
        Cliente c1 = new Cliente("andrea","123","Andrea","Barcala",Genero.F,"34851240","Friuli 123");
        Cliente c2 = new Cliente("maxi","123","Maximiliano","Castillo",Genero.M,"37623895","El cano 3852");
        Cliente c3 = new Cliente("vicky","123","Victoria","Cabo",Genero.F,"38591237","Piedra buena 523");

        market.getClientes().add(c1);
        market.getClientes().add(c2);
        market.getClientes().add(c3);
        market.agregarProducto(producto1);
        market.agregarProducto(producto2);
        market.agregarProducto(producto3);
        market.agregarProducto(producto4);
        market.agregarProducto(producto5);
     /*  /// System.out.println(market.getProductos());

        /*System.out.println(market.getProductos());
        System.out.println("VAmos A comprar con el cliente que acaba de ingresar.");
        market.compraCliente(c1);
        market.verFacturas();
        */


        ///Leer
        ObjectMapper mapper = new ObjectMapper();
        File archivo = new File("archivo_super.json");

        try {
            market = mapper.readValue(archivo, Supermercado.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        Menu menu = new Menu(market);
        menu.mostrarMenuPrincipal(new Scanner(System.in));


        //Escribir archivo

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, market);

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}