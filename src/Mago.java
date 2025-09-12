public class Mago  extends Personaje {
    public Mago(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        super(nombre, raza, Rol.MAGO, fuerza, agilidad, inteligencia, voluntad);
    }
    public void lanzarConjuro() {
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getInteligencia();
        System.out.println(getNombre() + " trata de lanzar un conjuro. " + tirada + " + " + getInteligencia() + " = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " lanza una bola de fuego y hace " + getInteligencia() + " de daño.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue lanzar el conjuro.");
        }
    }
}