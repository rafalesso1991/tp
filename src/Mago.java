// La clase Mago es una clase hija de Personaje.
// Implementa los métodos abstractos de la clase padre.
public class Mago  extends Personaje {
    public Mago(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        // Llama al constructor padre para setear los atributos.
        super(nombre, raza, Rol.MAGO, fuerza, agilidad, inteligencia, voluntad);
    }

    // --- APLICACIÓN DE POO: REDEFINICIÓN (SOBRESCRITURA) DE MÉTODOS Y POLIMORFISMO ---
    // Este méthodo proporciona la implementación específica para la acción especial del Mago.
    // Sobrescribe el méthodo abstracto en la clase Personaje.
    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la inteligencia del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getInteligencia();
        System.out.println(getNombre() + " intenta lanzar un conjuro. Tira el dado y saca " + tirada + " + " + getInteligencia() + ") = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " lanza una bola de fuego y hace " + getInteligencia() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue lanzar el conjuro.");
        }
    }

    // Este met.odo devuelve el nombre de la acción especial del Mago para el menú.
    @Override
    public String getAccionEspecial() {
        return "conjurar";
    }
}