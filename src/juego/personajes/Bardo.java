package juego.personajes;

// Importación de clases
import juego.enums.Raza;
import juego.enums.Rol;

// HERENCIA
// Implementa los atributos y mét.odos comunes de la clase Personaje

public class Bardo  extends Personaje {
    public Bardo(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad, int carisma) {

        // Llama al Constructor de la clase padre
        super(nombre, raza, Rol.BARDO, fuerza, agilidad, inteligencia, voluntad, carisma);
    }

    // POLIMORFISMO
    // Redefinición del mét.odo abstracto de la clase padre para la acción especial.

    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la carisma del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getFuerza();
        System.out.println(getNombre() + " canta para inspirar a su grupo. Tira el dado y saca " + tirada + " + " + getFuerza() + ") = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " inspira un gran valor en sus compañeros.");
        } else {
            System.out.println("Fallo. " + getNombre() + " desafina y no logra inspirar a su grupo.");
        }
    }

    @Override
    public String getAccionEspecial() {
        return "inspirar";
    }
}