import java.util.Scanner;

public class Main {

    // ===== MÉTODO MAIN =====
    public static void main(String[] args) {
        menu();
    }



    // ===== ARREGLO =====
    public static String[][] inventario = new String[10][2];
    public static int largoInventario   = inventario.length;



    // ===== VALIDAR CANTIDAD =====
    public static boolean validarCantidad(String cantidadProducto) {
        try {
            int cantidad = Integer.parseInt(cantidadProducto);
            if (cantidad <= 0) {
                System.out.println("La cantidad del producto debe ser un número positivo");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("El stock del producto debe ser un número válido");
            return false;
        }
    }



    // ===== AGREGAR PRODUCTO =====
    public static void agregarProducto(String nombreProducto, String cantidadProducto) {
        if (!validarCantidad(cantidadProducto)) {
            return;
        }
        for (int i = 0; i < largoInventario; i++) {
            if (inventario[i][0] == null) {
                inventario[i][0] = nombreProducto;
                inventario[i][1] = cantidadProducto;
                System.out.println("El producto " + nombreProducto + " fue agregado correctamente");
                return;
            }
        }
        System.out.println("Error, inventario lleno");
    }



    // ===== SOLICITAR DATOS PRODUCTO =====
    public static String[] solicitarDatosProducto() {
        Scanner lector          = new Scanner(System.in);
        String[] datosProducto  = new String[2];

        System.out.println("\n ----- AGREGAR PRODUCTO ----- ");
        System.out.print  ("    Nombre  : ");
        datosProducto[0] = lector.nextLine();

        System.out.print  ("    Cantidad: ");
        datosProducto[1] = lector.nextLine();

        return datosProducto;
    }



    // ===== MENÚ AGREGAR PRODUCTO =====
    public static void agregarProductoMenu() {
        String[] datosProducto  = solicitarDatosProducto();
        String nombre           = datosProducto[0];
        String cantidad         = datosProducto[1];

        try {
            Integer.parseInt(cantidad);
            agregarProducto(nombre, cantidad);
        } catch (NumberFormatException e) {
            System.out.println("El stock del producto debe ser un número");
        }
    }



    // ===== SOLICITAR DATOS VENTA =====
    public static String[] solicitarDatosVenta() {
        Scanner lector      = new Scanner(System.in);
        String[] datosVenta = new String[2];

        System.out.println("\n ----- VENDER PRODUCTO ----- ");
        System.out.print  ("    Nombre  : ");
        datosVenta[0] = lector.nextLine();

        System.out.print  ("    Cantidad: ");
        datosVenta[1] = lector.nextLine();

        return datosVenta;
    }



    // ===== MENÚ VENDER PRODUCTO =====
    public static void menuVenderProducto() {
        String[] datosVenta     = solicitarDatosVenta();
        String nombreProducto   = datosVenta[0];
        String cantidadProducto = datosVenta[1];

        try {
            int cantidad = Integer.parseInt(cantidadProducto);
            if (validarCantidad(cantidadProducto) == false) {
                return;
            }

            int posicion = buscarProducto(nombreProducto);

            if (posicion != -1) {
                postVenta(posicion, cantidad);
                System.out.println("Venta realizada correctamente");
            } else {
                System.out.println("El producto no existe");
            }
        } catch (NumberFormatException e) {
            System.out.println("La cantidad debe ser un número");
        }
    }



    // ===== POST VENTA =====
    public static void postVenta(int posicion, int cantidad) {
        String stock        = inventario[posicion][1];
        int cantidadActual  = Integer.parseInt(stock);

        if (cantidadActual >= cantidad) {
            inventario[posicion][1] = String.valueOf(cantidadActual - cantidad);
        } else {
            System.out.println("No hay suficiente cantidad de producto");
        }
    }



    // ===== MOSTRAR INVENTARIO =====
    public static void mostrarInventario() {
        System.out.println("\n    ----- INVENTARIO ------ ");
        for (int i = 0; i < largoInventario; i++) {
            if (inventario[i][0] != null) {
                System.out.println("Producto: " + inventario[i][0] + " - Cantidad: " + inventario[i][1]);
            }
        }
    }



    // ===== BUSCAR PRODUCTO =====
    public static int buscarProducto(String nombreProducto) {
        for (int i = 0; i < largoInventario; i++) {
            if (inventario[i][0] != null && inventario[i][0].equals(nombreProducto)) {
                return i;
            }
        }
        return -1;
    }



    // ===== MENÚ PRINCIPAL =====
    public static void menu () {
        Scanner lector = new Scanner(System.in);
        String opcion;

        do {
            mostrarMenuPrincipal();
            opcion = lector.nextLine();
            switch (opcion){
                case "1" -> agregarProductoMenu();
                case "2" -> {
                    mostrarInventario();
                    menuVenderProducto();
                }
                case "3" -> mostrarInventario();
                case "4" -> System.out.println(" Hasta luego...");
                default  -> System.out.println(" Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (!opcion.equals("4"));
    }



    // ===== MÉTODO PARA MOSTRAR MENU PRINCIPAL =====
    public static void mostrarMenuPrincipal() {
        System.out.println("\n======================================");
        System.out.println("            Compra y Venta            ");
        System.out.println("               La Granja              ");
        System.out.println("======================================");
        System.out.println("        [1] Agregar producto          ");
        System.out.println("        [2] Vender producto           ");
        System.out.println("        [3] Mostrar inventario        ");
        System.out.println("        [4] Salir                     ");
        System.out.println("======================================");
        System.out.print  ("            Ingrese su opción  : ");
    }
}
