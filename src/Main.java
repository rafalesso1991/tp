import java.io.*;
import java.util.Scanner;

// MAIN CLASS --> Maneja las entradas y la creación del personaje.
public class Main {
    public static void main(String[] args) {
        // Crea un objeto Scanner para leer la entrada del usuario desde la consola.
        Scanner scanner = new Scanner(System.in);

        // Prompt NOMBRE
        System.out.print("Ingresa el nombre de tu personaje: ");
        String nombre = scanner.nextLine();

        // Prompt RAZA
        Raza raza = null;
        while (raza == null) {
            System.out.println("Elije una raza (Humano, Elfo, Enano, Orco): ");
            String razaIngresada = scanner.nextLine().toUpperCase();
            try {
                // Convierte el String en una constante "enum"
                raza = Raza.valueOf(razaIngresada);
            } catch (IllegalArgumentException e) {
                System.out.println("Raza inválida. Por favor, seleccione una de la lista.");
            }
        }

        // Prompt ROL
        Rol rol = null;
        while (rol == null) {
            System.out.println("Elije un rol (Guerrero, Mago, Ladrón, Sacerdote): ");
            String rolIngresado = scanner.nextLine().toUpperCase();
            try {
                rol = Rol.valueOf(rolIngresado);
            } catch (IllegalArgumentException e) {
                System.out.println("Rol inválido. Por favor, seleccione uno de la lista.");
            }
        }

        // Inicializamos los atributos básicos para el personaje
        int fuerza = 5;
        int agilidad = 5;
        int inteligencia = 5;
        int voluntad = 5;

        // Aplicamos los bonificadores raciales
        switch (raza) {
            case HUMANO:
                inteligencia += 2;
                break;
            case ELFO:
                agilidad += 2;
                break;
            case ENANO:
                voluntad += 2;
                break;
            case ORCO:
                fuerza += 2;
                break;
        }
        // Crea una instancia de la clase de personaje específica según la profesión elegida.
        Personaje nuevoPersonaje = null;
        switch (rol) {
            case GUERRERO:
                nuevoPersonaje = new Guerrero(nombre, raza, fuerza, agilidad, inteligencia, voluntad);
                break;
            case MAGO:
                nuevoPersonaje = new Mago(nombre, raza, fuerza, agilidad, inteligencia, voluntad);
                break;
            case LADRON:
                nuevoPersonaje = new Ladron(nombre, raza, fuerza, agilidad, inteligencia, voluntad);
                break;
            case SACERDOTE:
                nuevoPersonaje = new Sacerdote(nombre, raza, fuerza, agilidad, inteligencia, voluntad);
                break;
        }
        if (nuevoPersonaje != null) {
            System.out.println("Nombre: " + nuevoPersonaje.getNombre());
            System.out.println("Raza: " + nuevoPersonaje.getRaza());
            System.out.println("Rol: " + nuevoPersonaje.getRol());
            System.out.println("Fuerza: " + nuevoPersonaje.getFuerza());
            System.out.println("Agilidad: " + nuevoPersonaje.getAgilidad());
            System.out.println("Inteligencia: " + nuevoPersonaje.getInteligencia());
            System.out.println("Voluntad: " + nuevoPersonaje.getVoluntad());

            while (true) {
                System.out.println("\n--- ¿Qué quieres hacer en tu turno? ---");
                System.out.println("Acciones: golpear, disparar, pensar, convencer, especial, guardar, salir");
                System.out.print("Escribe tu elección: ");
                String accion = scanner.nextLine().toLowerCase();

                // Use a switch statement to perform an action based on the user's choice.
                switch (accion) {
                    case "golpear":
                        nuevoPersonaje.golpear();
                        break;
                    case "disparar":
                        nuevoPersonaje.disparar();
                        break;
                    case "pensar":
                        nuevoPersonaje.pensar();
                        break;
                    case "convencer":
                        nuevoPersonaje.convencer();
                        break;
                    case "especial":
                        if (nuevoPersonaje instanceof Guerrero) {
                            ((Guerrero) nuevoPersonaje).bloquearConEscudo();
                        } else if (nuevoPersonaje instanceof Mago) {
                            ((Mago) nuevoPersonaje).lanzarConjuro();
                        } else if (nuevoPersonaje instanceof Ladron) {
                            ((Ladron) nuevoPersonaje).esconderseConAgilidad();
                        } else if (nuevoPersonaje instanceof Sacerdote) {
                            ((Sacerdote) nuevoPersonaje).rezarConVoluntad();
                        }
                        break;
                    case "guardar":
                        DataHandler.guardarPersonaje(nuevoPersonaje, "personajes.txt");
                        break;
                    case "salir":
                        System.out.println("Programa terminado ¡Adiós!");
                        scanner.close();
                        return; // Sale del method Main
                    default:
                        System.out.println("Acción inválida, por favor seleccione una de la lista.");
                        break;
                }
            }
        }
    }
}
class DataHandler {
    public static void guardarPersonaje(Personaje personaje, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(personaje.getNombre() + "," + personaje.getRaza().toString() + "," + personaje.getRol().toString() + "," +
                    personaje.getFuerza() + "," + personaje.getAgilidad() + "," + personaje.getInteligencia() + "," + personaje.getVoluntad());
            System.out.println("Personaje guardado: " + personaje.getNombre());
        } catch (IOException e) {
            System.out.println("No se puedo guardar el personaje.");
        }
    }

    public static void loadCharacters(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                // Check if the line has all the expected data parts.
                if (parts.length == 7) {
                    String nombre = parts[0];
                    Raza raza = Raza.valueOf(parts[1]);
                    Rol rol = Rol.valueOf(parts[2]);
                    int fuerza = Integer.parseInt(parts[3]);
                    int agilidad = Integer.parseInt(parts[4]);
                    int inteligencia = Integer.parseInt(parts[5]);
                    int voluntad = Integer.parseInt(parts[6]);

                    System.out.println("Loaded character: " + nombre + " - " + raza + " - " + rol +
                            " (Str: " + fuerza + ", Agi: " + agilidad + ", Int: " + inteligencia + ", Will: " + voluntad + ")");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }
}