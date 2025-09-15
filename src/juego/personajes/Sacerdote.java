package juego.personajes;

// Importación de clases
import juego.enums.Raza;
import juego.enums.Rol;

// HERENCIA
// Implementa los atributos y mét.odos comunes de la clase Personaje

public class Sacerdote  extends Personaje {
    public Sacerdote(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {

        // Llama al Constructor de la clase padre
        super(nombre, raza, Rol.SACERDOTE, fuerza, agilidad, inteligencia, voluntad);
    }

    // POLIMORFISMO
    // Redefinición del mét.odo abstracto de la clase padre para la acción especial.

    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la voluntad del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getVoluntad();
        System.out.println(getNombre() + " intenta rezar. Tira el dado y saca " + tirada + " + " + getVoluntad() + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " reza con convicción y se cura " + getVoluntad() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra obtener ayuda divina.");
        }
    }

    @Override
    public String getAccionEspecial() {
        return "rezar";
    }
}