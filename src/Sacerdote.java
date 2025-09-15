// La clase Sacerdote es una clase hija de Personaje.
// Implementa los métodos abstractos de la clase padre.
public class Sacerdote  extends Personaje {
    public Sacerdote(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        // Llama al constructor padre para setear los atributos.
        super(nombre, raza, Rol.SACERDOTE, fuerza, agilidad, inteligencia, voluntad);
    }

    // --- APLICACIÓN DE POO: REDEFINICIÓN (SOBRESCRITURA) DE MÉTODOS Y POLIMORFISMO ---
    // Este méthodo proporciona la implementación específica para la acción especial del Mago.
    // Sobrescribe el méthodo abstracto en la clase Personaje.
    @Override
    public void hacerAccionEspecial() {
        // Tira un dado de 20 caras y suma la voluntad del Personaje.
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getVoluntad();
        System.out.println(getNombre() + " intenta rezar. Tira el dado y saca " + tirada + " + " + getVoluntad() + ") = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " reza con convicción y se cura " + getVoluntad() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra obtener ayuda divina.");
        }
    }

    // Este met.odo devuelve el nombre de la acción especial del Sacerdote para el menú.
    @Override
    public String getAccionEspecial() {
        return "rezar";
    }
}