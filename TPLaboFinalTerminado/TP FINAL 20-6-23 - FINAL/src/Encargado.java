import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encargado extends Usuario  {
    private String tipo;

    public Encargado(String usuario, String contraseña) {
        super(usuario, contraseña);
        tipo="encargado";
    }

    public Encargado() {
        tipo="encargado";
    }

    @Override
    public Factura compra(List<Producto> productos, Usuario usuario) {
        String compararContraseña ="";
        int opcion;
        int cant;
        int salida = 0;
        List<Producto> changui = new ArrayList<>();

        do {
            for (int i = 0; i < productos.size(); i++) {
                System.out.println(i + 1 + ".............. " + productos.get(i).getNombre());
            }
            System.out.println("Seleccione el producto que quiere comprar");
            if (Leer.leerHasNextInt()) {
                opcion = Leer.leerInt();
                if (opcion > 0 && opcion < productos.size()+1) {
                    System.out.println("Ingrese la cantidad a comprar: ");
                    if (Leer.leerHasNextInt()) {
                        cant = Leer.leerInt();
                        Producto pElegido = productos.get(opcion - 1);
                        Producto p = new Producto(productos.get(opcion - 1).getNombre(), productos.get(opcion - 1).getPrecio(), cant, productos.get(opcion - 1).getId());
                        changui.add(p);
                        pElegido.setStock(pElegido.getStock() + cant);
                        }
                }else{
                    System.out.println("Error - Opcion no valida.");
                }
            }else{
                System.out.println("Error - Opcion no valida.");
                Leer.leerNext();
            }
            System.out.println("Presione 1 para pasar al siguiente paso o cualquier letra para seguir comprando.");
            if (!Leer.leerHasNextInt()){
                salida =2;
                Leer.leerNext();
            }else{
                salida = Leer.leerInt();
            }
        } while (salida != 1);
        opcion= 0;
        System.out.println("Ingrese su contraseña para confirmar la compra o ingrese 1 para cancelarla.");
        do{
            compararContraseña=Leer.leerNext();
            if(compararContraseña.equals("1")){
                System.out.println("Su compra ha sido cancelada.");
                for (Producto p : productos){
                    for(Producto c: changui){
                        if(p.getNombre().equals(c.getNombre())){
                            p.setStock(p.getStock()-c.getStock());
                        }
                    }
                return null;
                }
            }else if (!usuario.getContrasena().equals(compararContraseña)){
                System.out.println("Error- Contraseña incorrecta ingresela nuevamente");
            }
        }while(!usuario.getContrasena().equals(compararContraseña));
        System.out.println("Su compra ha sido realizada con exito. \n");
        return null;
    }
}
