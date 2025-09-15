import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

// MAIN CLASS --> Maneja las entradas y la creación del personaje.
public class Main {
    public static void main(String[] args) {
        // Crea un objeto Scanner para leer la entrada del usuario desde la consola.
        Scanner scanner = new Scanner(System.in);

        // --- Manejo de ArrayList ---
        // Esta ArrayList almacenará todos los personajes creados durante la sesión.
        List<Personaje> grupoDePersonajes = new ArrayList<>();


        // Bucle del programa principal
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Crear un nuevo personaje");
            System.out.println("2. Ver to.dos los personajes");
            System.out.println("3. Selecciona a un personaje");
            System.out.println("4. Guardar todos los personajes en un archivo");
            System.out.println("5. Cargar personajes del archivo");
            System.out.println("6. Salir");
            System.out.print("Elige tu opción: ");
            String opcionMenuPrincipal = scanner.nextLine();

            // --- ESTRUCTURA DEL PROGRAMA: Declaraciones condicionales ---
            switch (opcionMenuPrincipal) {
                case "1":
                    // --- CREACION DE PERSONAJE ---
                    System.out.print("Ingresa el nombre de tu personaje: ");
                    String nombre = scanner.nextLine();

                    Raza raza = null;
                    while (raza == null) {
                        System.out.println("Elije una raza (Humano, Elfo, Enano, Orco): ");
                        String razaIngresada = scanner.nextLine().toUpperCase();
                        try {
                            raza = Raza.valueOf(razaIngresada);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Raza inválida. Seleccione una de la lista.");
                        }
                    }

                    Rol rol = null;
                    while (rol == null) {
                        System.out.println("Elije un rol (Guerrero, Mago, Ladron, Sacerdote): ");
                        String rolIngresado = scanner.nextLine().toUpperCase();
                        try {
                            rol = Rol.valueOf(rolIngresado);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Rol inválido, Seleccione uno de la lista.");
                        }
                    }

                    int fuerza = 5;
                    int agilidad = 5;
                    int inteligencia = 5;
                    int voluntad = 5;

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
                        grupoDePersonajes.add(nuevoPersonaje);
                        System.out.println("\n¡Personaje creado y sumado al grupo!");
                    }
                    break;

                case "2":
                    if (grupoDePersonajes.isEmpty()) {
                        System.out.println("Tu grupo está vacío, crea un personaje primero.");
                    } else {
                        System.out.println("\n--- GRUPO ACTUAL ---");
                        Iterator<Personaje> iterador = grupoDePersonajes.iterator();
                        while (iterador.hasNext()) {
                            Personaje personaje = iterador.next();
                            System.out.println(personaje.getNombre() + " (" + personaje.getRaza() + " " + personaje.getRol() + ")");
                        }
                    }
                    break;
                case "3":
                    if (grupoDePersonajes.isEmpty()) {
                        System.out.println("Tu grupo está vacío, crea un personaje primero");
                        break;
                    }

                    System.out.println("\n--- SELECCIONAR PERSONAJE ---");
                    for (int i = 0; i < grupoDePersonajes.size(); i++) {
                        System.out.println((i + 1) + ". " + grupoDePersonajes.get(i).getNombre());
                    }
                    System.out.print("Ingresa el número del grupo de personajes que quieres seleccionar: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < grupoDePersonajes.size()) {
                            Personaje personajeSeleccionado = grupoDePersonajes.get(index);

                            while (true) {
                                System.out.println("\n--- Qué quieres que haga " + personajeSeleccionado.getNombre() + " ? ---");
                                System.out.println("Acciones: golpear, disparar, pensar, convencer, " + personajeSeleccionado.getAccionEspecial() + ", guardar, salir");
                                System.out.print("Ingresa tu elección: ");
                                String action = scanner.nextLine().toLowerCase();

                                switch (action) {
                                    case "golpear":
                                        personajeSeleccionado.golpear();
                                        break;
                                    case "disparar":
                                        personajeSeleccionado.disparar();
                                        break;
                                    case "pensar":
                                        personajeSeleccionado.pensar();
                                        break;
                                    case "convencer":
                                        personajeSeleccionado.convencer();
                                        break;
                                    case "bloquear":
                                    case "conjurar":
                                    case "esconderse":
                                    case "rezar":
                                        personajeSeleccionado.hacerAccionEspecial();
                                        break;
                                    case "guardar":
                                        DataHandler.guardarTodosLosPersonajes(grupoDePersonajes, "personajes.txt");
                                        break;
                                    case "salir":
                                        break;
                                    default:
                                        System.out.println("Entrada inválida. Por favor seleccione una de la lista.");
                                        break;
                                }
                                if (action.equals("salir")) {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Número inválido.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida, elija un número de la lista.");
                    }
                    break;

                case "4":
                    DataHandler.guardarTodosLosPersonajes(grupoDePersonajes, "personajes.txt");
                    break;

                case "5":
                    grupoDePersonajes = DataHandler.cargarTodosLosPersonajes("personajes.txt");
                    System.out.println("Personajes cargados desde el archivo.");
                    break;

                case "6":
                    System.out.println("Saliendo del programa. ¡Adiós!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Entrada inválida, por favor seleccione una de la lista.");
                    break;
            }
        }
    }
}