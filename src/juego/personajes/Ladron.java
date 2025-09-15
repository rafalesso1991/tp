package juego.personajes;

// Importación de clases
import juego.enums.Raza;
import juego.enums.Rol;

// HERENCIA
// Implementa los atributos y mét.odos comunes de la clase Personaje

public class Ladron  extends Personaje {
    public Ladron(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {

        // Llama al Constructor de la clase padre
        super(nombre, raza, Rol.LADRON, fuerza, agilidad, inteligencia, voluntad);
    }

    // POLIMORFISMO
    // Redefinición del mét.odo abstracto de la clase padre para la acción especial.

    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la agilidad del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getAgilidad();
        System.out.println(getNombre() + " intenta esconderse. Tira el dado y saca " + tirada + " + " + getAgilidad() + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " logra esconderse y no puede ser atacado el próximo turno.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue un buen escondite.");
        }
    }

    @Override
    public String getAccionEspecial() {
        return "esconderse";
    }
}