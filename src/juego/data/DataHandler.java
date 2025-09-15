package juego.data;

// Importación de clases
import juego.personajes.*;
import juego.enums.Raza;
import juego.enums.Rol;

// Importación de funciones
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Persistencia de datos.
// Gestiona operaciones de I/O de archivos para guardar y cargar una lista de personajes.
public class DataHandler {

    public static void guardarTodosLosPersonajes(List<Personaje> personajes, String nombreArchivo) {

        // Manejo de excepciones.
        // Las clases FileWriter y PrintWriter se utilizan para escribir los datos de los Personajes en un archivo de texto.
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, false))) { // Using false to overwrite the file
            for (Personaje personaje : personajes) {
                writer.println(personaje.getNombre() + "," + personaje.getRaza().toString() + "," + personaje.getRol().toString() + "," +
                        personaje.getFuerza() + "," + personaje.getAgilidad() + "," + personaje.getInteligencia() + "," + personaje.getVoluntad());
            }
            System.out.println("Todos los personajes guardados en " + nombreArchivo + ".");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al guardar los personajes.");
        }
    }

    /*
     Cargar personajes de un archivo de texto y los devuelve como una lista.
     'param' => nombreArchivo: El nombre del archivo desde el que se cargará.
     'return' => Lista de objetos 'Personaje' cargados.
     */
    public static List<Personaje> cargarTodosLosPersonajes(String nombreArchivo) {

        // Usamos un ArrayList para almacenar los personajes a medida que los cargamos.
        List<Personaje> personajesCargados = new ArrayList<>();
        File file = new File(nombreArchivo);

        // Manejo de excepciones.
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String nombre = parts[0];
                    Raza raza = Raza.valueOf(parts[1]);
                    Rol rol = Rol.valueOf(parts[2]);
                    int fuerza = Integer.parseInt(parts[3]);
                    int agilidad = Integer.parseInt(parts[4]);
                    int inteligencia = Integer.parseInt(parts[5]);
                    int voluntad = Integer.parseInt(parts[6]);

                    // POLIMORFISMO
                    // Instancia la subclase de personaje según el rol elegido.
                    switch (rol) {
                        case GUERRERO:
                            personajesCargados.add(new Guerrero(nombre, raza, fuerza, agilidad, inteligencia, voluntad));
                            break;
                        case MAGO:
                            personajesCargados.add(new Mago(nombre, raza, fuerza, agilidad, inteligencia, voluntad));
                            break;
                        case LADRON:
                            personajesCargados.add(new Ladron(nombre, raza, fuerza, agilidad, inteligencia, voluntad));
                            break;
                        case SACERDOTE:
                            personajesCargados.add(new Sacerdote(nombre, raza, fuerza, agilidad, inteligencia, voluntad));
                            break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al analizar los datos del archivo. El archivo podría estar dañado.");
            return new ArrayList<>();
        }

        return personajesCargados;
    }
}