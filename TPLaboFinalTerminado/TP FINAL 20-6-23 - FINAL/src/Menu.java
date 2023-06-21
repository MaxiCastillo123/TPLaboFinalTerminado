import java.util.Scanner;

public class Menu {

    private  Supermercado market = new Supermercado();
    private Cliente cliente = new Cliente();
    private Encargado encargado = new Encargado();

    public Menu(Supermercado market) {
        this.market = market;
    }

    public Supermercado getMarket() {
        return market;
    }

    public void setMarket(Supermercado market) {
        this.market = market;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void mostrarMenuPrincipal(Scanner scanner) {
        int opcionPrincipal;

        do {
            System.out.println("\nMenú Principal");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            if (scanner.hasNextInt()) {
                opcionPrincipal = scanner.nextInt();
                switch (opcionPrincipal) {
                    case 1:
                        mostrarMenuCliente(scanner);
                        break;
                    case 2:
                        System.out.println();
                        encargado = market.ingresarEncargado();
                        if(encargado==market.getEncargado()){
                            mostrarMenuEmpleado(scanner);
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Error - Opcion no valida.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el valor no válido del buffer de entrada
                opcionPrincipal = -1; // Establecer una opción inválida para que se repita el bucle
            }
        } while (opcionPrincipal != 0);
    }

    public void mostrarMenuEmpleado(Scanner scanner) {
        int opcionEmpleado;

        do {
            System.out.println("\nMenú Empleado");
            System.out.println("1. Ver clientes");
            System.out.println("2. Ver facturas");
            System.out.println("3. Ver productos");
            System.out.println("4. Comprar productos");
            System.out.println("5. Comprar un producto nuevo");
            System.out.println("6. Eliminar un producto");
            System.out.println("7. Buscar un cliente");
            System.out.println("8. Dar de baja cliente");
            System.out.println("9. Dar de alta cliente");
            System.out.println("10. Aumentar precio de productos");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            if (scanner.hasNextInt()) {
                opcionEmpleado = scanner.nextInt();

                switch (opcionEmpleado) {
                    case 1:
                        System.out.println("\nVer clientes");
                        market.verClientes();

                        break;
                    case 2:
                        System.out.println("\nVer facturas");
                        market.verFacturas();

                        break;
                    case 3:
                        System.out.println("\nVer productos");
                        market.verProductos();

                        break;
                    case 4:
                        System.out.println("\nComprar productos");
                        market.compraEncargado();

                        break;
                    case 5:
                        System.out.println("\nComprar productos");
                        market.comprarUnProductoNuevo();

                        break;
                    case 6:
                        System.out.println("\nEliminar un producto");
                        market.eliminarProducto(encargado);

                        break;
                    case 7:
                        System.out.println("\nBuscar un cliente");
                        market.buscarCliente();

                        break;
                    case 8:
                        System.out.println("\nDar de baja un cliente");
                        market.bajaCliente();

                        break;
                    case 9:
                        System.out.println("\nDar de alta un cliente");
                        market.altaCliente();

                        break;
                    case 10:
                        System.out.println("\nAumentar precio de productos");
                        market.aumentoDePrecio();

                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Error - Opcion no valida.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next();
                opcionEmpleado = -1;
            }
        } while (opcionEmpleado != 0);
    }

    public void mostrarMenuCliente(Scanner scanner) {
        int opcionCliente;

        do {
            System.out.println("\nMenú Cliente");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            if (scanner.hasNextInt()) {
                opcionCliente = scanner.nextInt();

                switch (opcionCliente) {
                    case 1:
                        System.out.println("\nRegistrarse");
                        market.agregarCliente();
                        break;
                    case 2:
                        System.out.println("\nIngresar");

                        cliente = market.ingresarCliente();
                        if(cliente != null){
                            mostrarMenuIngresarCliente(scanner);
                        }

                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Error - Opcion no valida.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next();
                opcionCliente = -1;
            }
        } while (opcionCliente != 0);
    }

    public void mostrarMenuIngresarCliente(Scanner scanner) {
        int opcionIngresar;

        do {
            System.out.println("\nMenú Ingresar Cliente");
            System.out.println("1. Comprar");
            System.out.println("2. Modificar información personal");
            System.out.println("3. Ver compras");
            System.out.println("4. Dar de baja");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");

            if (scanner.hasNextInt()) {
                opcionIngresar = scanner.nextInt();

                switch (opcionIngresar) {
                    case 1:
                        System.out.println("\nComprar");
                        market.compraCliente(cliente);

                        break;
                    case 2:
                        System.out.println("\nModificar información personal");
                        mostrarMenuModificarInformacionPersonal(scanner);
                        break;
                    case 3:
                        System.out.println("\nVer compras");
                        market.verFacturasCliente(cliente);

                        break;
                    case 4:
                        System.out.println("\nDar de baja");
                        System.out.println("Para dar de baja su usuario mandar un mail a soporte@mercado.com.ar");

                        break;
                    case 0:
                        System.out.println("Volviendo al menú anterior...\n");
                        break;
                    default:
                        System.out.println("Error - Opcion no valida.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next();
                opcionIngresar = -1;
            }
        } while (opcionIngresar != 0);
    }

    public void mostrarMenuModificarInformacionPersonal(Scanner scanner) {
        int opcionModificar;

        do {
            System.out.println("\nMenú Modificar Información Personal");
            System.out.println("1. Modificar nombre de usuario");
            System.out.println("2. Modificar contraseña");
            System.out.println("3. Modificar nombre");
            System.out.println("4. Modificar apellido");
            System.out.println("5. Modificar domicilio");
            System.out.println("6. Modificar DNI");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");
            if (scanner.hasNextInt()) {
                opcionModificar = scanner.nextInt();
                switch (opcionModificar) {
                    case 1:
                        System.out.println("\nModificar nombre de usuario");
                        market.modificarNombreUsuario(cliente);

                        break;
                    case 2:
                        System.out.println("\nModificar contraseña");
                        market.modificarContrasena(cliente);

                        break;
                    case 3:
                        System.out.println("\nModificar nombre");
                        market.modificarNombre(cliente);

                        break;
                    case 4:
                        System.out.println("\nModificar apellido");
                        market.modificarApellido(cliente);

                        break;
                    case 5:
                        System.out.println("\nModificar domicilio");
                        market.modificarDomicilio(cliente);

                        break;
                    case 6:
                        System.out.println("\nModificar DNI");
                        market.modificarDni(cliente);

                        break;
                    case 0:
                        System.out.println("Volviendo al menú anterior...");
                        break;
                    default:
                        System.out.println("Error - Opcion no valida.");
                        break;
                }
            }else{
                System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                scanner.next();
                opcionModificar = -1;
            }
        } while (opcionModificar != 0);
    }
}