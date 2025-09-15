package juego.personajes;

// Importación de clases
import juego.enums.Raza;
import juego.enums.Rol;

// HERENCIA
// Implementa los atributos y mét.odos comunes de la clase Personaje

public class Mago  extends Personaje {
    public Mago(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad, int carisma) {

        // Llama al Constructor de la clase padre
        super(nombre, raza, Rol.MAGO, fuerza, agilidad, inteligencia, voluntad, carisma);
    }

    // POLIMORFISMO
    // Redefinición del mét.odo abstracto de la clase padre para la acción especial.

    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la inteligencia del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getInteligencia();
        System.out.println(getNombre() + " intenta lanzar un conjuro. Tira el dado y saca " + tirada + " + " + getInteligencia() + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " lanza una bola de fuego y hace " + getInteligencia() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue lanzar el conjuro.");
        }
    }

    @Override
    public String getAccionEspecial() {
        return "conjurar";
    }
}