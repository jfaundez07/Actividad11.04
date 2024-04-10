import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static String[][] inventario = new String[10][2];

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        int continuar = 0;

        do {
            mostrarOpciones();
            String opcion = scanner.nextLine();

            switch(opcion){
                case "1":
                    menuAgregarProducto();
                    break;
                case "2":
                    menuVenderProducto();
                    break;
                case "3":
                    mostrarInventario(inventario);
                    break;
                case "4":
                    System.out.println("Saliendo del programa");
                    continuar = 1;
                default:
                    System.out.println("Opción no válida");
            }
        } while(continuar == 0);


    }

    public static void mostrarOpciones(){
        System.out.println("[1] Agregar Producto");
        System.out.println("[2] Vender Producto");
        System.out.println("[3] Mostrar Inventario");
        System.out.println("[4] Salir");
        System.out.println("Ingrese una opción: ");
    }

    public static void agregarPoducto(String [][] matrizInventario, String nombreProducto, String cantidadProducto){
        for(int i = 0; i < matrizInventario.length; i++){
            if(matrizInventario[i][0] == null){
                matrizInventario[i][0] = nombreProducto;
                matrizInventario[i][1] = cantidadProducto;
                break;
            }
        }
    }

    public static void venderProducto(String[][] matrizInventario, int posicion, int cantidad) {
        if (matrizInventario[posicion][1] != null) {
            String stock = matrizInventario[posicion][1];
            int cantidadActual = Integer.parseInt(stock);
            if (cantidadActual >= cantidad) {
                matrizInventario[posicion][1] = String.valueOf(cantidadActual - cantidad);
            } else {
                System.out.println("No hay suficiente cantidad de producto");
            }
        } else {
            System.out.println("No hay producto en la posición indicada");
        }
    }

    public static void mostrarInventario(String[][] matrizInventario) {
        for (int i = 0; i < matrizInventario.length; i++) {
            if (matrizInventario[i][0] != null) {
                System.out.println("Producto: " + matrizInventario[i][0] + " - Cantidad: " + matrizInventario[i][1]);
            }
        }
    }

    public static int buscarProducto(String[][] matrizInventario, String nombreProducto) {
        for (int i = 0; i < matrizInventario.length; i++) {
            if (matrizInventario[i][0] != null && matrizInventario[i][0].equals(nombreProducto)) {
                return i;
            }
        }
        return -1;
    }

    public static void menuAgregarProducto(){
        System.out.println("Ingrese el nombre del producto");
        String nombreProducto = scanner.nextLine();
        System.out.println("Ingrese la cantidad del producto");
        String cantidadProducto = scanner.nextLine();
        try {
            Integer.parseInt(cantidadProducto);
            agregarPoducto(inventario, nombreProducto, cantidadProducto);
        } catch (NumberFormatException e) {
            System.out.println("El stock debe ser un numero");
        }

    }

    public static void menuVenderProducto(){

        System.out.println("Ingrese el nombre del producto");
        String nombreProducto = scanner.nextLine();

        int posicion = buscarProducto(inventario, nombreProducto);

        if (posicion != -1) {

            System.out.println("Ingrese la cantidad del producto");
            String cantidadProducto = scanner.nextLine();

            try {
                int cantidad = Integer.parseInt(cantidadProducto);
                venderProducto(inventario, posicion, cantidad);
            } catch (NumberFormatException e) {
                System.out.println("La cantidad debe ser un numero");
            }
        } else {
            System.out.println("El producto no existe");
        }
    }
}
