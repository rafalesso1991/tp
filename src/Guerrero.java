// La clase Guerrero es una clase hija de Personaje.
// Implementa los métodos abstractos de la clase padre.
public class Guerrero  extends Personaje {
    public Guerrero(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        // Llama al constructor padre para setear los atributos.
        super(nombre, raza, Rol.GUERRERO, fuerza, agilidad, inteligencia, voluntad);
    }

    // --- APLICACIÓN DE POO: REDEFINICIÓN (SOBRESCRITURA) DE MÉTODOS Y POLIMORFISMO ---
    // Este méthodo proporciona la implementación específica para la acción especial del Guerrero.
    // Sobrescribe el méthodo abstracto en la clase Personaje.
    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la fuerza del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getFuerza();
        System.out.println(getNombre() + " trata de bloquear con su escudo. Tira el dado y saca " + tirada + " + " + getFuerza() + ") = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " bloquea el ataque y lo reduce en " + getFuerza() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue bloquear el ataque.");
        }
    }

    // Este met.odo devuelve el nombre de la acción especial del Guerrero para el menú.
    @Override
    public String getAccionEspecial() {
        return "bloquear";
    }
}