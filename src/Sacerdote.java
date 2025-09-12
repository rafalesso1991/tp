public class Sacerdote  extends Personaje {
    public Sacerdote(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        super(nombre, raza, Rol.SACERDOTE, fuerza, agilidad, inteligencia, voluntad);
    }
    public void rezarConVoluntad() {
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getVoluntad();
        System.out.println(getNombre() + " intenta rezar. " + tirada + " + " + getVoluntad() + " = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " reza con convicción y se cura " + getVoluntad() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra obtener ayuda divina.");
        }
    }
}