package juego.personajes;

import juego.enums.Raza;
import juego.enums.Rol;

import java.util.random.RandomGenerator;
// CLASE "PADRE"
public abstract class Personaje {
    // ATRIBUTOS
    private String nombre;
    private Raza raza; // tipo "raza"
    private Rol rol; // tipo "rol"
    private int fuerza;
    private int agilidad;
    private int inteligencia;
    private int voluntad;
    // ATRIBUTO ESTÁTICO para DEFINIR un N° ALEATORIO del DADO
    private static final RandomGenerator dado = RandomGenerator.getDefault();

    public Personaje(
            String nombre,
            Raza raza,
            Rol rol,
            int fuerza,
            int agilidad,
            int inteligencia,
            int voluntad)
    {
        this.nombre = nombre;
        this.raza = raza;
        this.rol = rol;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.inteligencia = inteligencia;
        this.voluntad = voluntad;
    }

    // GETTERS
    public String getNombre() {
        return nombre;
    }
    public Raza getRaza() {
        return raza;
    }
    public Rol getRol() {
        return rol;
    }
    public int getFuerza() {
        return fuerza;
    }
    public int getAgilidad() {
        return agilidad;
    }
    public int getInteligencia() {
        return inteligencia;
    }
    public int getVoluntad() {
        return voluntad;
    }

    // METODO TIRAR DADO
    private int tirarDado() {
        return dado.nextInt(20) + 1;
    }

    // METODOS COMUNES
    public void golpear() {
        int tirada = tirarDado();
        int total = tirada + fuerza;
        System.out.println(getNombre() + " intenta golpear al enemigo. " + tirada + " + " + fuerza + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " golpea a su enemigo y le hace " + getFuerza() + "de daño");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra golpear al enemigo.");
        }
    }
    public void disparar() {
        int tirada = tirarDado();
        int total = tirada + agilidad;
        System.out.println(getNombre() + " dispara al enemigo. " + tirada + " + " + agilidad + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " acierta a su enemigo y le hace " + getAgilidad() + "de daño");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra acertar al enemigo.");
        }
    }
    public void pensar() {
        int tirada = tirarDado();
        int total = tirada + inteligencia;
        System.out.println(getNombre() + " trata de pensar en una idea para ganar. " + tirada + " + " + inteligencia + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " ha pensado en una excelente idea.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no se le ocurre ninguna idea.");
        }
    }
    public void convencer() {
        int tirada = tirarDado();
        int total = tirada + voluntad;
        System.out.println(getNombre() + " trata de convencer a un aliado para que lo ayude. " + tirada + " + " + voluntad + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " logra convencer a un aliado para que lo ayude.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra convencer al aliado.");
        }
    }
    public abstract void hacerAccionEspecial();

    public abstract String getAccionEspecial();
}