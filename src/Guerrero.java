public class Guerrero  extends Personaje {
    public Guerrero(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        super(nombre, raza, Rol.GUERRERO, fuerza, agilidad, inteligencia, voluntad);
    }
    public void bloquearConEscudo() {
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getFuerza();
        System.out.println(getNombre() + " trata de bloquear con su escudo. " + tirada + " + " + getFuerza() + " = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " bloquea el ataque y lo reduce en " + getFuerza() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue bloquear el ataque.");
        }
    }
}