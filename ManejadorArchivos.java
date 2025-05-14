package main;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManejadorArchivos {
    private static final String ARCHIVO_LOG = "archivos/log.txt";

    public void crearArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                System.out.println("El archivo ya existe.");
                registrarLog("Intento de crear archivo existente: " + ruta);
                return;
            }
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado exitosamente.");
                registrarLog("Archivo creado: " + ruta);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            registrarLog("Error al crear archivo: " + ruta + " - " + e.getMessage());
        }
    }

    public void escribirEnArchivo(String ruta, String contenido, boolean sobrescribir) {
        try (FileWriter fw = new FileWriter(ruta, !sobrescribir);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contenido);
            System.out.println("Contenido escrito exitosamente.");
            registrarLog("Contenido " + (sobrescribir ? "sobrescrito" : "añadido") + " en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            registrarLog("Error al escribir en archivo: " + ruta + " - " + e.getMessage());
        }
    }

    public void leerArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            System.out.println("\nContenido del archivo:");
            System.out.println("**************************");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("**************************");
            registrarLog("Archivo leído: " + ruta);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
            registrarLog("Intento de leer archivo inexistente: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            registrarLog("Error al leer archivo: " + ruta + " - " + e.getMessage());
        }
    }

    private void registrarLog(String mensaje) {
        try (FileWriter fw = new FileWriter(ARCHIVO_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            bw.write(timestamp + " - " + mensaje + "\n");
        } catch (IOException e) {
            System.out.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}
