public class Ladron  extends Personaje {
    public Ladron(String nombre, Raza raza, int fuerza, int agilidad, int inteligencia, int voluntad) {
        super(nombre, raza, Rol.LADRON, fuerza, agilidad, inteligencia, voluntad);
    }
    public void esconderseConAgilidad() {
        int tirada = new java.util.Random().nextInt(20) + 1;
        int total = tirada + getAgilidad();
        System.out.println(getNombre() + " intenta esconderse. " + tirada + " + " + getAgilidad() + " = " + total);

        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " logra esconderse y no puede ser atacado el próximo turno");
        } else {
            System.out.println("Fallo. " + getNombre() + " no consigue un buen escondite.");
        }
    }
}