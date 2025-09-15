package juego.personajes;

import juego.enums.Raza;
import juego.enums.Rol;

// La clase Ladron es una clase hija de Personaje.
// Implementa los métodos abstractos de la clase padre.
public class Ladron  extends Personaje {
    public Ladron(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        // Llama al constructor padre para setear los atributos.
        super(nombre, raza, Rol.LADRON, fuerza, agilidad, inteligencia, voluntad);
    }

    // --- APLICACIÓN DE POO: REDEFINICIÓN (SOBRESCRITURA) DE MÉTODOS Y POLIMORFISMO ---
    // Este mét.odo proporciona la implementación específica para la acción especial del Ladrón.
    // Sobrescribe el mét.odo abstracto en la clase Personaje.
    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la agilidad del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getAgilidad();
        System.out.println(getNombre() + " intenta esconderse. Tira el dado y saca " + tirada + " + " + getAgilidad() + ") = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " logra esconderse y no puede ser atacado el próximo turno.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue un buen escondite.");
        }
    }

    // Este met.odo devuelve el nombre de la acción especial del Ladrón para el menú.
    @Override
    public String getAccionEspecial() {
        return "esconderse";
    }
}