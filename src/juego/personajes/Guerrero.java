package juego.personajes;

// Importación de clases
import juego.enums.Raza;
import juego.enums.Rol;

// HERENCIA
// Implementa los atributos y mét.odos comunes de la clase Personaje

public class Guerrero  extends Personaje {
    public Guerrero(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad, int carisma, int vida) {

        // Llama al Constructor de la clase padre
        super(nombre, raza, Rol.GUERRERO, fuerza, agilidad, inteligencia, voluntad, carisma, vida);
    }

    // POLIMORFISMO
    // Redefinición del mét.odo abstracto de la clase padre para la acción especial.

    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la fuerza del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getFuerza();
        System.out.println(getNombre() + " trata de bloquear con su escudo. Tira el dado y saca " + tirada + " + " + getFuerza() + ") = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " bloquea el ataque y lo reduce en " + getFuerza() + " de daño.");
            sumarVida(total);
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue bloquear el ataque.");
        }
    }

    @Override
    public String getAccionEspecial() {
        return "bloquear";
    }
}
