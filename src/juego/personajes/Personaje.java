package juego.personajes;

// Importación de clases
import juego.enums.Raza;
import juego.enums.Rol;

// Importación de funciones
import java.util.random.RandomGenerator;
/*
ABSTRACCIÓN
Clase abstracta que sirve como modelo para todos los tipos de personajes.
Define un concepto generalizado de "Personaje" sin necesidad de ser instanciado.

HERENCIA
Se definen los atributos y métodos comunes, que luego se heredan a las subclases de tipo 'rol'.
*/
public abstract class Personaje {

    // ENCAPSULAMIENTO
    // El modificador 'private' se utiliza para encapsular atributos del personaje.
    private String nombre;
    private Raza raza; // tipo "raza"
    private Rol rol; // tipo "rol"
    private int fuerza;
    private int agilidad;
    private int inteligencia;
    private int voluntad;
    private int carisma;

    // Atributo estático para definir un n° aleatorio del dado
    private static final RandomGenerator dado = RandomGenerator.getDefault();

    public Personaje(
            String nombre,
            Raza raza,
            Rol rol,
            int fuerza,
            int agilidad,
            int inteligencia,
            int voluntad,
            int carisma)
    {
        this.nombre = nombre;
        this.raza = raza;
        this.rol = rol;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.inteligencia = inteligencia;
        this.voluntad = voluntad;
        this.carisma = carisma;
    }

    // Metodos públicos GETTER para el acceso controlado a atributos privados.
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
    public int getCarisma() {
        return carisma;
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

    public void desafiar() {
        int tirada = tirarDado();
        int total = tirada + voluntad;
        System.out.println(getNombre() + " intenta desafiar a su enemigo. Tira el dado y saca: " + tirada + " + " + voluntad + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " retó al enemigo a un duelo individual.");
        } else {
            System.out.println("Fallo. " + getNombre() + " es ignorado por el enemigo.");
        }
    }

    public void convencer() {
        int tirada = tirarDado();
        int total = tirada + carisma;
        System.out.println(getNombre() + " trata de convencer a un aliado para que lo ayude. " + tirada + " + " + carisma + " = " + total);
        if (total >= 15) {
            System.out.println("¡Éxito! " + getNombre() + " logra convencer a un aliado para que lo ayude.");
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra convencer al aliado.");
        }
    }

    // POLIMORFISMO
    // Uso de clases abstractas
    public abstract void hacerAccionEspecial();

    // Este metodo abstracto devuelve el nombre de la acción especial, lo que permite in generar un menú dinámico.
    public abstract String getAccionEspecial();
}