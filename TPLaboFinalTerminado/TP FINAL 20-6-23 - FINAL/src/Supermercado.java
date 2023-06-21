import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Supermercado  {

    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Factura> facturas;
    private Encargado encargado;

    public Supermercado(Encargado encargado) {
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.encargado = encargado;
    }
    public Supermercado(){
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
        facturas = new ArrayList<>();

    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }


    @Override
    public String toString() {
        return "Supermercado{" +
                "clientes=" + clientes +
                ", productos=" + productos +
                ", facturas=" + facturas +
                ", encargado=" + encargado +
                '}';
    }

    public void agregarCliente (){
       clientes.add(crearUsuario());
    }

    public Boolean existeUsuario (String usuario){
        for(Cliente c : clientes){
            if(c.getUsuario().equals(usuario)){
                return true;
            }
        }
        return false;
    }

    public Cliente ingresarCliente(){

        String username= "";
        String contrasena= "";
        Cliente cliente = new Cliente();

        do{
            System.out.println("Ingrese su nombre de usuario: ");
            username = Leer.leerNext();
            if(existeUsuario(username)){
                for(Cliente c : clientes){
                    if(c.getUsuario().equals(username) && c.getActivo()){
                        cliente = c;
                    }else if(c.getUsuario().equals(username) && !c.getActivo()){
                        System.out.println("Su usuario se encuentra dado de baja, enviar un email a soporte@mercado.com.ar");
                        return null;
                    }
                }
            }else if(username.equals("1")){
                return null;
            }else{
                System.out.println("Usuario incorrecto, ingreselo nuevamete o aprete 1 para salir.");
            }
        }while(!existeUsuario(username) && !username.equals("1"));
        do{
            System.out.println("ingrese su contraseña para ingresar: ");
            contrasena = Leer.leerNext();


            if(cliente.getContrasena().equals(contrasena)){
                return cliente;
            } else if(contrasena.equals("1")){
                return null;
            }
            else{
                System.out.println("contraseña invalida, ingrese nuevamente o aprete 1 para salir");
            }

        }while(!cliente.getContrasena().equals(contrasena));
      return null;
    }

    public Cliente crearUsuario (){
        String usuario;
            do{
                System.out.println("Ingrese el nombre de  usuario");
                usuario = Leer.leerNext();
                Leer.leerNextLine();
                if (existeUsuario(usuario)){
                    System.out.println("El nombre de usuario ya existe");
                }
            }while (existeUsuario(usuario));

        return crearCliente(usuario);
        }

    public Cliente crearCliente(String usuario){
        Cliente cliente = new Cliente();
        Scanner scan = new Scanner(System.in);
        cliente.setUsuario(usuario);

        System.out.println("Ingrese su contraseña");
        cliente.setContrasena(Leer.leerNextLine());
        System.out.println("Ingrese su nombre");
        cliente.setNombre(Leer.leerNextLine());
        System.out.println("Ingrese su apellido");
        cliente.setApellido(Leer.leerNextLine());
        System.out.println("Ingrese su genero");
        cliente.setGenero(elegirGenero());
        System.out.println("Ingrese su dni");
        cliente.setDni(Leer.leerNextLine());
        System.out.println("Ingrese su domicilio");
        cliente.setDomicilio(Leer.leerNextLine());
       // Leer.leerNext();
        return cliente;
    }

    public Genero elegirGenero() {
        Genero genero1 = null;

        int opcion = 0;

        do {
            System.out.println("1. Femenino");
            System.out.println("2. Masculino");
            System.out.print("Selecciona una opción: ");

            if (Leer.leerHasNextInt()) {
                opcion = Leer.leerInt();

                if (opcion == 1) {
                    System.out.println("Has elegido: Femenino");
                    genero1 = Genero.F;
                } else if (opcion == 2) {
                    System.out.println("Has elegido: Masculino");
                    genero1 = Genero.M;
                } else {
                    System.out.println("Opción inválida. Por favor, selecciona 1 o 2.");
                }
            }else{
                System.out.println("Opción inválida. Por favor, selecciona 1 o 2.");
                Leer.leerNext();
            }
        }while (opcion != 1 && opcion != 2);
        Leer.leerString();
        return genero1;
    }

    public void modificarNombreUsuario(Cliente c){

        String nUsuario = "";
        String nuevoNombre= "";
        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de usuario o 0 para salir");

            nUsuario = Leer.leerNextLine();
            if(c.getUsuario().equals(nUsuario)){
                System.out.println("Escriba su nuevo nombre de usuario: ");
                nuevoNombre = Leer.leerNextLine();
                do{
                    System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                    cContraseña = Leer.leerNextLine();
                    if(c.getContrasena().equals(cContraseña)){
                        c.setUsuario(nuevoNombre);
                        System.out.println("Su nombre de usuario ha sido modificado correctamente.\n");
                        return;
                    }else {
                        if(!cContraseña.equals("0")){
                            System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                        }
                    }
                }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
            }else {
                System.out.println("El usuario no coincide. Intentelo nuevamente o ingrese 0 para salir.");
            }
        } while (!c.getUsuario().equals(nUsuario) && !nUsuario.equals("0") );
    }

    public void modificarNombre(Cliente c){
        String nUsuario = "";
        String nuevoNombre= "";
        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de usuario o 0 para salir");

            nUsuario = Leer.leerNextLine();
        if(c.getUsuario().equals(nUsuario)){
            System.out.println("Escriba su nuevo nombre: ");
            nuevoNombre = Leer.leerNextLine();
            do{
                System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                cContraseña = Leer.leerNextLine();
                if(c.getContrasena().equals(cContraseña)){
                    c.setNombre(nuevoNombre);
                    System.out.println("Su nombre ha sido modificado correctamente.\n");
                    return;
                }else {
                    if(!cContraseña.equals("0")){
                        System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                    }
                }
            }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
        }else {
            System.out.println("El usuario no coincide. Intentelo nuevamente o ingrese 0 para salir.");
        }
    } while (!c.getUsuario().equals(nUsuario) && !nUsuario.equals("0") );
}

    public void modificarApellido(Cliente c){
        String nUsuario = "";
        String nuevoNombre= "";
        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de usuario o 0 para salir");

            nUsuario = Leer.leerNextLine();
            if(c.getUsuario().equals(nUsuario)){
                System.out.println("Escriba su nuevo apellido: ");
                nuevoNombre = Leer.leerNextLine();
                do{
                    System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                    cContraseña = Leer.leerNextLine();
                    if(c.getContrasena().equals(cContraseña)){
                        c.setApellido(nuevoNombre);
                        System.out.println("Su apellido ha sido modificado correctamente.\n");
                        return;
                    }else {
                        if(!cContraseña.equals("0")){
                            System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                        }
                    }
                }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
            }else {
                System.out.println("El usuario no coincide. Intentelo nuevamente o ingrese 0 para salir.");
            }
        } while (!c.getUsuario().equals(nUsuario) && !nUsuario.equals("0") );
}

    public void modificarDomicilio(Cliente c){
        String nUsuario = "";
        String nuevoNombre= "";
        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de usuario o 0 para salir");

            nUsuario = Leer.leerNextLine();
            if(c.getUsuario().equals(nUsuario)){
                System.out.println("Escriba su nuevo domicilio: ");
                nuevoNombre = Leer.leerNextLine();
                do{
                    System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                    cContraseña = Leer.leerNextLine();
                    if(c.getContrasena().equals(cContraseña)){
                        c.setDomicilio(nuevoNombre);
                        System.out.println("Su domicilio ha sido modificado correctamente.\n");
                        return;
                    }else {
                        if(!cContraseña.equals("0")){
                            System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                        }
                    }
                }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
            }else {
                System.out.println("El usuario no coincide. Intentelo nuevamente o ingrese 0 para salir.");
            }
        } while (!c.getUsuario().equals(nUsuario) && !nUsuario.equals("0") );
    }

    public void modificarContrasena(Cliente c){
        String nUsuario = "";
        String nuevoNombre= "";
        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de usuario o 0 para salir");

            nUsuario = Leer.leerNextLine();
            if(c.getUsuario().equals(nUsuario)){
                System.out.println("Escriba su nueva contraseña: ");
                nuevoNombre = Leer.leerNextLine();
                do{
                    System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                    cContraseña = Leer.leerNextLine();
                    if(c.getContrasena().equals(cContraseña)){
                        c.setContrasena(nuevoNombre);
                        System.out.println("Su contraseña ha sido modificado correctamente.\n");
                        return;
                    }else {
                        if(!cContraseña.equals("0")){
                            System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                        }
                    }
                }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
            }else {
                System.out.println("El usuario no coincide. Intentelo nuevamente o ingrese 0 para salir.");
            }
        } while (!c.getUsuario().equals(nUsuario) && !nUsuario.equals("0") );
    }

    public void modificarDni(Cliente c){
        String nUsuario = "";
        String nuevoNombre= "";
        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de usuario o 0 para salir");

            nUsuario = Leer.leerNextLine();
            if(c.getUsuario().equals(nUsuario)){
                System.out.println("Escriba su nuevo Dni: ");
                nuevoNombre = Leer.leerNextLine();
                do{
                    System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                    cContraseña = Leer.leerNextLine();
                    if(c.getContrasena().equals(cContraseña)){
                        c.setDni(nuevoNombre);
                        System.out.println("Su dni ha sido modificado correctamente.\n");
                        return;
                    }else {
                        if(!cContraseña.equals("0")){
                            System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                        }
                    }
                }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
            }else {
                System.out.println("El usuario no coincide. Intentelo nuevamente o ingrese 0 para salir.");
            }
        } while (!c.getUsuario().equals(nUsuario) && !nUsuario.equals("0") );
    }

    public void bajaCliente(){
        String nUsuario = "";

        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de  usuario");
             nUsuario = Leer.leerNext();
        if(existeUsuario(nUsuario)){
            for (Cliente c : clientes){
                if(c.getUsuario().equals(nUsuario)){
                    do{
                        System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                        cContraseña = Leer.leerNext();
                        if(c.getContrasena().equals(cContraseña)){
                            c.setActivo(false);
                            System.out.println("El usuario ha sido dado de baja.\n");
                            return;
                        }else{
                            if(!cContraseña.equals("0")){
                                System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                            }
                        }
                    }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
                }
            }
        }else {
            System.out.println("El usuario no coincide. Ingrese su nombre de usuario para continuar o 0 para salir");
        }
        } while (!existeUsuario(nUsuario) && !nUsuario.equals("0") );
    }

    public void altaCliente(){
        String nUsuario = "";

        String cContraseña = "";
        do {
            System.out.println("Ingrese el nombre de  usuario");
            nUsuario = Leer.leerNext();
            if(existeUsuario(nUsuario)){
                for (Cliente c : clientes){
                    if(c.getUsuario().equals(nUsuario)){
                        do{
                            System.out.println("Para confirmar ingrese su contraseña o 0 para salir:");
                            cContraseña = Leer.leerNext();
                            if(c.getContrasena().equals(cContraseña)){
                                c.setActivo(true);
                                System.out.println("El usuario ha sido dado de alta.\n");
                                return;
                            }else{
                                if(!cContraseña.equals("0")){
                                    System.out.println("su contraseña es incorrecta, ingrese nuevamente");
                                }
                            }
                        }while(!cContraseña.equals(c.getContrasena()) && !cContraseña.equals("0"));
                    }
                }
            }else {
                System.out.println("El usuario no coincide. Ingrese su nombre de usuario para continuar o 0 para salir");
            }
        } while (!existeUsuario(nUsuario) && !nUsuario.equals("0") );
    }

    public void agregarProducto(Producto producto){
        productos.add(producto);
    }

    public void verListaDeProductos(){
        for(Producto p : productos){
            System.out.println(p);
        }

    }

    public void agregarFactura(Factura factura){
        this.facturas.add(factura);
    }

    public void compraCliente(Cliente cliente){
        Factura f = cliente.compra(productos,cliente);
        if (f!=null) {
            this.agregarFactura(f);
        }
            }

    public void verFacturasCliente(Usuario usuario){
        for(Factura f: facturas){
            if(f.getUsuario().getUsuario().equals(usuario.getUsuario())){
                f.mostrarFactura();
            }
        }

    }

    /**FUNCIONES DE ENCARGADO */
    public Encargado ingresarEncargado(){

        String username= "";
        String contrasena = "";
        Encargado encargado = new Encargado();
        do{
            System.out.println("Ingrese su nombre de usuario: ");
            username = Leer.leerNext();
            if(getEncargado().getUsuario().equals(username)){

                        encargado = getEncargado();

                }
            else if(username.equals("1")){
                return null;
            }else{
                System.out.println("Usuario incorrecto, ingreselo nuevamete o aprete 1 para salir.");
            }
        }while( !getEncargado().getUsuario().equals(encargado.getUsuario()) && !username.equals("1"));

        do{
            System.out.println("ingrese su contraseña para ingresar: ");
            contrasena = Leer.leerNext();


            if(encargado.getContrasena().equals(contrasena)){
                return encargado;
            } else if(contrasena.equals("1")){
                return null;
            }
            else{
                System.out.println("contraseña invalida, ingrese nuevamente o aprete 1 para salir");
            }

        }while(!encargado.getContrasena().equals(contrasena));

        return null;
    }

    public void verClientes(){
        for(Cliente c : clientes){
            c.mostrarUnCliente();
        }
    }

    public void verFacturas(){
        for(Factura f : facturas){
            f.mostrarFactura();
        }
    }

    public void verProductos(){
        for(Producto p: productos){
            if(p.getStock()<=5){
                System.out.println(p.getNombre() + " su stock es: "+ p.getStock() + " El stock es bajo, se recomienda comprar!");
            }else{
                System.out.println(p.getNombre() + " su stock es: "+ p.getStock());
            }

        }
        System.out.println();
    }

    public void compraEncargado(){
        encargado.compra(productos,encargado);
    }

    public Boolean existeProducto (String producto){
        for(Producto p : productos){
            if(p.getNombre().equalsIgnoreCase(producto)){
                return true;
            }
        }
        return false;
    }

    public void comprarUnProductoNuevo(){
        String nombreProducto = "";
        Double precioProducto = 0d;
        Integer stockProducto = 0;

        System.out.println("Que producto deseas comprar?");
        nombreProducto = Leer.leerNext();

        if (!existeProducto(nombreProducto)) {
            boolean bandera = false;
            do {
                System.out.println("¿Cuál es el precio del producto?");
                String precio = Leer.leerNext();
                try {
                    precioProducto = Double.parseDouble(precio);
                    bandera = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingresa un precio válido.");
                }
            } while (!bandera);

            do{
                System.out.println("Cuantas unidades desea comprar?: ");
                String unidad = Leer.leerNext();
                try {
                    stockProducto = Integer.parseInt(unidad);
                    bandera = false;
                }catch (NumberFormatException e){
                    System.out.println("Error: Ingresa un numero valido.");
                }
            }while (bandera);


            Producto p = new Producto(nombreProducto, precioProducto,stockProducto);

            productos.add(p);
            System.out.println("El producto ha sido comprado con exito! \n");
        }else{
            System.out.println("El producto ya existe a la venta. \n");
        }
    }

    public Boolean productoExiste(String product){
        for(Producto p: productos){
            if(p.getNombre().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public void eliminarProducto (Usuario usuario){

        System.out.println("¿Que producto quiere eliminar?");
        String eliminado= Leer.leerNext();
        if(productoExiste(eliminado)){
            for(Producto p: productos){
                if(p.getNombre().equalsIgnoreCase(eliminado)){
                    System.out.println("Ingrese contraseña: ");
                    if (Leer.leerNext().equals(usuario.getContrasena())){
                        productos.remove(p);
                        System.out.println("El producto ha sido eliminado con exito.\n");
                        break;
                    }else {
                        System.out.println("Contraseña incorrecta, intente mas tarde");
                    }
                }
            }
        }else{
        System.out.println("El producto a eliminar no existe\n");
    }
}

    public void buscarCliente (){
        System.out.println("¿Ingrese el usuario del cliente que quiere buscar?");
        String buscado= Leer.leerNext();
        if(existeUsuario(buscado)) {
            for (Cliente c : clientes) {
                if (c.getUsuario().equals(buscado)) {
                    c.mostrarUnCliente();
                    System.out.println("Facturas del cliente: \n");
                    verFacturasCliente(c);
                }
            }
        }else {
            System.out.println("El usuario no existe");
        }
    }

    public void aumentoDePrecio(){
        for(Producto p: productos){
            p.setPrecio(p.getPrecio()+(p.getPrecio() * 0.15));
        }
        System.out.println("Los productos fueron aumentados un 15% \n");
    }

}