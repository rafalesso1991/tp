package juego.main;

// Importación de funciones
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

// Importación de clases
import juego.personajes.*;
import juego.data.DataHandler;
import juego.enums.Rol;
import juego.enums.Raza;

// MENÚ PRINCIPAL => Interfaz que permite al usuario realizar diversas operaciones en una misma sesión
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Manejo de ArrayList1
        // Esta ArrayList almacenará los personajes creados durante la sesión.
        List<Personaje> grupoDePersonajes = new ArrayList<>();

        // Bucle del programa principal hasta que se seleccione un número de opción
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Crear un nuevo personaje");
            System.out.println("2. Mostrar grupo de personajes");
            System.out.println("3. Seleccionar a un personaje");
            System.out.println("4. Guardar todos los personajes en un archivo");
            System.out.println("5. Cargar todos los personajes desde el archivo");
            System.out.println("6. Salir");
            System.out.print("Elige el número de tu opción: ");
            String opcionMenuPrincipal = scanner.nextLine();

            // Estructura condicional
            switch (opcionMenuPrincipal) {

                // CREAR NUEVO PERSONAJE
                case "1":
                    System.out.print("Ingresa el nombre de tu personaje: ");
                    String nombre = scanner.nextLine();

                    Raza raza = null;

                    // Bucle hasta que se seleccione una raza válida.
                    while (raza == null) {
                        System.out.println("Elije una raza (Humano, Elfo, Enano, Orco, Gnomo): ");
                        String razaIngresada = scanner.nextLine().toUpperCase();

                        // Manejo de excepciones
                        try {
                            raza = Raza.valueOf(razaIngresada);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Raza inválida. Seleccione una de la lista.");

                        }

                    }

                    Rol rol = null;

                    // Bucle hasta que se seleccione un rol válido.
                    while (rol == null) {
                        System.out.println("Elije un rol (Guerrero, Mago, Ladron, Sacerdote, Bardo): ");
                        String rolIngresado = scanner.nextLine().toUpperCase();

                        // Manejo de excepciones
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
                    int carisma = 5;
                    int vida = 40;


                    // MODIFICA LOS ATRIBUTOS BÁSICOS DEL PERSONAJE SEGÚN SU RAZA
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
                        case GNOMO:
                            carisma += 2;
                            break;
                    }

                    // CREA UNA INSTANCIA NUEVA DEL OBJETO SEGÚN EL ROL ELEGIDO
                    Personaje nuevoPersonaje = null;
                    switch (rol) {

                        case GUERRERO:
                            nuevoPersonaje = new Guerrero(nombre, raza, fuerza, agilidad, inteligencia, voluntad, carisma, vida);
                            break;
                        case MAGO:
                            nuevoPersonaje = new Mago(nombre, raza, fuerza, agilidad, inteligencia, voluntad, carisma, vida);
                            break;
                        case LADRON:
                            nuevoPersonaje = new Ladron(nombre, raza, fuerza, agilidad, inteligencia, voluntad, carisma, vida);
                            break;
                        case SACERDOTE:
                            nuevoPersonaje = new Sacerdote(nombre, raza, fuerza, agilidad, inteligencia, voluntad, carisma, vida);
                            break;
                        case BARDO:
                            nuevoPersonaje = new Bardo(nombre, raza, fuerza, agilidad, inteligencia, voluntad, carisma, vida);
                    }

                    // Estructura condicional si se ha creado correctamente el personaje
                    if (nuevoPersonaje != null) {
                        grupoDePersonajes.add(nuevoPersonaje);
                        System.out.println("\nPersonaje creado y agregado al grupo.");
                    }
                    break;

                // MOSTRAR PERSONAJES
                case "2":

                    // Estructura condicional si no hay personajes creados ni cargados
                    if (grupoDePersonajes.isEmpty()) {
                        System.out.println("Tu grupo está vacío, crea un personaje primero.");

                    } else {
                        System.out.println("\nGRUPO ACTUAL");

                        // Implementación de un Iterator para recorrer colecciones
                        Iterator<Personaje> iterador = grupoDePersonajes.iterator();

                        // Estructura de iteración usando el iterador
                        while (iterador.hasNext()) {
                            Personaje personaje = iterador.next();
                            System.out.println(personaje.getNombre() + " (" + personaje.getRaza() + " " + personaje.getRol() + ")");
                        }
                    }
                    break;

                // SELECCIONAR PERSONAJE PARA USAR SUS ACCIONES
                case "3":

                    // Estructura condicional si no hay personajes creados ni cargados
                    if (grupoDePersonajes.isEmpty()) {
                        System.out.println("Tu grupo está vacío, crea un personaje primero");
                        break;
                    }

                    System.out.println("\nSELECCIONA TU PERSONAJE");

                    // Estructura de iteración para listar personajes
                    for (int i = 0; i < grupoDePersonajes.size(); i++) {
                        System.out.println((i + 1) + ". " + grupoDePersonajes.get(i).getNombre());
                    }
                    System.out.print("Ingresa el número del personaje que quieres seleccionar: ");

                    // Manejo de exepciones
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;

                        // Estructura condicional si un personaje del grupo ha sido seleccionado
                        if (index >= 0 && index < grupoDePersonajes.size()) {
                            Personaje personajeSeleccionado = grupoDePersonajes.get(index);

                            // Estructura de iteración para listar las acciones disponibles del personaje
                            while (true) {
                                System.out.println("\n¿Qué quieres que haga " + personajeSeleccionado.getNombre() + "en su turno ?");

                                // POLIMORFISMO
                                // El nombre de la acción especial se obtiene del objeto de la subclase elegida
                                System.out.println("Acciones: golpear, disparar, pensar, desafiar ,convencer, " + personajeSeleccionado.getAccionEspecial() + ", guardar, salir");

                                System.out.print("Escribe tu elección: ");
                                String action = scanner.nextLine().toLowerCase();


                                switch (action) {

                                    // ACCIONES COMUNES
                                    case "golpear":
                                        System.out.println("Contra quien quieres realizar tu accion?");
                                        // muestra los personajes de la lista (sin el elegido para realizar la accion)

                                        for (Personaje personaje : grupoDePersonajes) {
                                            if (!personaje.getNombre().equals(personajeSeleccionado.getNombre())) {
                                                System.out.println("- " + personaje.getNombre() + " (" + personaje.getRaza() + " " + personaje.getRol() + ")");
                                            }
                                        }
                                        // seleccionar el elemento de la lista
                                        System.out.print("Escribí el nombre del enemigo: ");
                                        String nombreEnemigo = scanner.nextLine();

                                        // busca los personajes de la lista hasta encontrar el que matchea con el nombre del enemigo
                                        for (Personaje personaje : grupoDePersonajes) {
                                            if (personaje.getNombre().equalsIgnoreCase(nombreEnemigo)) {
                                                // realizo la accion contra el enemigo
                                                boolean golpeo = personajeSeleccionado.golpear(personaje);
                                                // si el enemigo se quedo sin vidas, lo elimino de la lista
                                                if (golpeo && personaje.getVida() <= 0) {
                                                    System.out.println(personaje.getNombre() + " ha sido derrotado.");
                                                    grupoDePersonajes.remove(personaje);
                                                }
                                                break;
                                            }
                                        }

                                        break;

                                    case "disparar":

                                        System.out.println("Contra quien quieres realizar tu accion?");


                                        for (Personaje personaje : grupoDePersonajes) {
                                            if (!personaje.getNombre().equals(personajeSeleccionado.getNombre())) {
                                                System.out.println("- " + personaje.getNombre() + " (" + personaje.getRaza() + " " + personaje.getRol() + ")");
                                            }
                                        }
                                        System.out.print("Escribí el nombre del enemigo: ");
                                        String nombreEnemigoDisparar = scanner.nextLine();


                                        for (Personaje personaje : grupoDePersonajes) {
                                            if (personaje.getNombre().equalsIgnoreCase(nombreEnemigoDisparar)) {
                                                boolean disparo = personajeSeleccionado.disparar(personaje);
                                                if (disparo && personaje.getVida() <= 0) {
                                                    System.out.println(personaje.getNombre() + " ha sido derrotado.");
                                                    grupoDePersonajes.remove(personaje);
                                                }
                                                break;
                                            }
                                        }
                                        break;

                                    case "pensar":
                                        personajeSeleccionado.pensar();
                                        break;

                                    case "desafiar":
                                        System.out.println("Contra quien quieres realizar tu accion?");

                                        for (Personaje personaje : grupoDePersonajes) {
                                            if (!personaje.getNombre().equals(personajeSeleccionado.getNombre())) {
                                                System.out.println("- " + personaje.getNombre() + " (" + personaje.getRaza() + " " + personaje.getRol() + ")");
                                            }
                                        }
                                        System.out.print("Escribí el nombre del enemigo: ");
                                        String nombreEnemigoDesafiar = scanner.nextLine();


                                        for (Personaje personaje : grupoDePersonajes) {
                                            if (personaje.getNombre().equalsIgnoreCase(nombreEnemigoDesafiar)) {
                                                boolean desafio = personajeSeleccionado.desafiar(personaje);
                                                if (desafio && personaje.getVida() <= 0) {
                                                    System.out.println(personaje.getNombre() + " ha sido derrotado.");
                                                    grupoDePersonajes.remove(personaje);
                                                }
                                                break;
                                            }
                                        }
                                        break;

                                    case "convencer":
                                        personajeSeleccionado.convencer();
                                        break;

                                    // ACCIONES ESPECIALES
                                    case "bloquear":
                                    case "conjurar":
                                    case "esconderse":
                                    case "rezar":
                                    case "inspirar":
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

                // GUARDAR LOS PERSONAJES CREADOS
                case "4":
                    DataHandler.guardarTodosLosPersonajes(grupoDePersonajes, "personajes.txt");
                    break;

                // CARGA LOS PERSONAJES GUARDADOS
                case "5":
                    grupoDePersonajes = DataHandler.cargarTodosLosPersonajes("personajes.txt");
                    System.out.println("Personajes cargados desde el archivo.");
                    break;

                // SALIR DEL PROGRAMA
                case "6":
                    System.out.println("Programa finalizado. ¡Adiós!");
                    scanner.close();
                    return;

                // ENTRADA INVÁLIDA
                default:
                    System.out.println("Entrada inválida, por favor seleccione una de la lista.");
                    break;
            }

        }

    }

}
