import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario implements iOferta {
    private String nombre;
    private String apellido;
    private Genero genero;
    private String dni;
    private String domicilio;
    private Integer cantDeCompras;
    private Boolean activo;
    private String tipo;

    public Cliente(String usuario, String contraseña, String nombre, String apellido, Genero genero, String dni, String domicilio) {
        super(usuario, contraseña);
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.dni = dni;
        this.domicilio = domicilio;
        this.cantDeCompras = 0;
        this.activo = true;
        tipo="cliente";

    }

    public Cliente(){
        this.cantDeCompras = 0;
        this.activo = true;
        tipo="cliente";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCantDeCompras() {
        return cantDeCompras;
    }

    public void setCantDeCompras(Integer cantDeCompras) {
        this.cantDeCompras = cantDeCompras;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero=" + genero +
                ", dni='" + dni + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", cantDeCompras=" + cantDeCompras +
                ", activo=" + activo +
                ", nombreDeUsuario= " + super.getUsuario()+
                '}';
    }

    @Override
    public Double descuento(Double precioFinal) {

        if(cantDeCompras>5){
            precioFinal = precioFinal*0.85;
        }
        return precioFinal;
    }

    public Boolean hayCant (List<Producto> productos, int num, int cant){
        return productos.get(num).getStock()>= cant;
    }

    @Override
    public Factura compra(List<Producto> productos, Usuario usuario) {
        Factura factura = new Factura();
        String compararContraseña ="";
        int opcion;
        int cant;
        String salida = "";

        List<Producto>changui = new ArrayList<>();

        do {
            for (int i = 0; i < productos.size(); i++) {
                System.out.println(i + 1 + ".............. " + productos.get(i).getNombre() + "   " + productos.get(i).getPrecio());
            }
            System.out.println("Ingrese el producto que quiere comprar");
            if (Leer.leerHasNextInt()) {

                opcion = Leer.leerInt();
                if (opcion > 0 && opcion < productos.size()+1) {

                    System.out.println("Ingrese la cantidad a comprar: ");
                    if (Leer.leerHasNextInt()) {


                        cant = Leer.leerInt();
                        Producto pElegido = productos.get(opcion - 1);
                        if (hayCant(productos, opcion - 1, cant)) {

                            Producto p = new Producto(productos.get(opcion - 1).getNombre(), productos.get(opcion - 1).getPrecio(), cant, productos.get(opcion - 1).getId());
                            factura.agregarProductoFactura(p);
                            changui.add(p);
                            pElegido.setStock(pElegido.getStock() - cant);

                            System.out.println("El producto fue agregado al carrito");

                            System.out.println("Presione 1 para pasar al siguiente paso o cualquier letra para seguir comprando.");
                            salida = Leer.leerNext();
                        } else {
                            System.out.println("La cantidad solicitada no esta disponible, la cantidad disponible es: " + pElegido);
                        }
                    } else {

                        System.out.println("Error- Apretar numero para elegir la cantidad");

                    }
                }else{
                    System.out.println("Error- Opcion no valida.");

                }

            }else{
                System.out.println("Error- Opción no valida");
                Leer.leerNext();
            }


        } while (!salida.equals("1"));
        opcion= 0;
        System.out.println("Ingrese su contraseña para confirmar la compra o ingrese 1 para cancelarla.");

        do{
            compararContraseña=Leer.leerNext();

            if(compararContraseña.equals("1")){
                System.out.println("Se cancelo la compra.");
                    for (Producto p : productos){
                        for(Producto c: changui){
                            if(p.getNombre().equals(c.getNombre())){
                                p.setStock(p.getStock()+c.getStock());
                        }


                    }
                return null;
            }
            }else if (!usuario.getContrasena().equals(compararContraseña)){
                System.out.println("Error- Contraseña incorrecta ingresela nuevamente");
            }
        }while(!usuario.getContrasena().equals(compararContraseña));

        do{
            try{
                System.out.println("De que manera va a pagar?: \n 1.Efectivo \n 2.Debito \n 3.Credito ");

                opcion = Leer.leerInt();

                if(opcion >=1 && opcion<=3){
                    factura.setMetodoDePago(factura.elegirMetodoDePego(opcion));
                    factura.setPrecioFinal(descuento(factura.obtenerPrecioFinal()));
                    factura.setUsuario(usuario);
                    factura.setFecha(LocalDate.now());
                }else{
                    System.out.println("Error- Opcion no valida, ingrese nuevamente");
                }

            }catch(Exception e){
                Leer.leerNext();
                System.out.println("Error Opcion no valida. Solo numeros");
            }
        }while(opcion !=1 && opcion !=2 && opcion!=3 );
        System.out.println("Muchas gracias por comprar en Supermercado Market. Aqui esta su factura! \n");
        factura.mostrarFactura();
        this.cantDeCompras+=1;
        return factura;
    }

    public void mostrarUnCliente(){
        System.out.println("Usuario: " + super.getUsuario());
        System.out.println(String.format("%-37s", "Nombre: ")+ getNombre());
        System.out.println(String.format("%-37s", "Apellido: ")+ getApellido());
        System.out.println(String.format("%-37s", "Genero ")+ getGenero());
        System.out.println(String.format("%-37s", "Dni: ")+ getDni());
        System.out.println(String.format("%-37s", "Direccion: ")+ getDomicilio());
        System.out.println(String.format("%-37s", "Cantidad de compras realizadas: ") + getCantDeCompras());
        System.out.println(String.format("%-37s", "Esta activo?: ")+ getActivo());
        System.out.println("-----------------------------------------------------------------\n");
    }


}
