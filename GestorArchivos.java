package main;

import java.util.Scanner;
import java.io.File;

public class GestorArchivos {
    private static final String RUTA_ARCHIVOS = "C:/Users/Public/archivos/";
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManejadorArchivos manejador = new ManejadorArchivos();

    public static void main(String[] args) {
        inicializarDirectorio();
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    crearArchivo();
                    break;
                case 2:
                    escribirEnArchivo();
                    break;
                case 3:
                    leerArchivo();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }

    private static void inicializarDirectorio() {
        File directorio = new File(RUTA_ARCHIVOS);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado exitosamente en " + RUTA_ARCHIVOS);
            } else {
                System.out.println("Error al crear el directorio.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== GESTOR DE ARCHIVOS ===");
        System.out.println("1. Crear nuevo archivo");
        System.out.println("2. Escribir en archivo existente");
        System.out.println("3. Leer archivo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static void crearArchivo() {
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = scanner.nextLine();
        manejador.crearArchivo(RUTA_ARCHIVOS + nombreArchivo);
    }

    private static void escribirEnArchivo() {
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = scanner.nextLine();
        System.out.print("¿Desea sobrescribir el archivo? (S/N): ");
        boolean sobrescribir = scanner.nextLine().equalsIgnoreCase("S");
        System.out.println("Ingrese el contenido (Línea vacía para terminar):");
        StringBuilder contenido = new StringBuilder();
        String linea;
        while (!(linea = scanner.nextLine()).isEmpty()) {
            contenido.append(linea).append("\n");
        }
        manejador.escribirEnArchivo(RUTA_ARCHIVOS + nombreArchivo, contenido.toString(), sobrescribir);
    }

    private static void leerArchivo() {
        System.out.print("Ingrese el nombre del archivo: ");
        String nombreArchivo = scanner.nextLine();
        manejador.leerArchivo(RUTA_ARCHIVOS + nombreArchivo);
    }
}
