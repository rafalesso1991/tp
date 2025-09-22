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
    private int vida;

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
            int carisma,
            int vida)
    {
        this.nombre = nombre;
        this.raza = raza;
        this.rol = rol;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.inteligencia = inteligencia;
        this.voluntad = voluntad;
        this.carisma = carisma;
        this.vida = vida;
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
    public int getAgilidad() {return agilidad;}
    public int getInteligencia() {
        return inteligencia;
    }
    public int getVoluntad() {
        return voluntad;
    }
    public int getCarisma() {return carisma; }
    public int getVida() { return vida; }

    // METODO TIRAR DADO
    private int tirarDado() {
        return dado.nextInt(20) + 1;
    }

    public void restarVida(int cantidad) {
      vida-= cantidad;

        if (vida < 0) {
            vida = 0;
            System.out.println("Victoria!" + getNombre() + " no tiene mas vidas. Personaje eliminado del juego");
        }
        else
        {
            System.out.println("Lo hiciste con éxito! " + getNombre() + " Ahora tiene " + vida + " vidas.");
        }

    }

    public void sumarVida(int cantidad) {
        vida+= cantidad;

            System.out.println("Lo hiciste con éxito! Sumaste " + cantidad + " vidas a " + getNombre() + ". Ahora tienes " + vida + "  vidas.");

    }

    // METODOS COMUNES
    public boolean golpear(Personaje enemigo) {
        int tirada = tirarDado();
        int total = tirada + fuerza;

        System.out.println(getNombre() + " intenta golpear al enemigo. Con " + total + " de daño");
        if (total >= 15) {
           enemigo.restarVida(total);
           return true;
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra golpear al enemigo.");
        return false;
        }

    }

    public boolean disparar(Personaje enemigo) {
        int tirada = tirarDado();
        int total = tirada + agilidad;
        System.out.println(getNombre() + " dispara al enemigo. " + tirada + " + " + agilidad + " = " + total);
        if (total >= 15) {
           enemigo.restarVida(total);
            return true;
        } else {
            System.out.println("Fallo. " + getNombre() + " no logra acertar al enemigo.");
            return false;
        }
    }

    public void pensar() {
        int tirada = tirarDado();
        int total = tirada + inteligencia;
        System.out.println(getNombre() + " trata de pensar en una idea para ganar. " + tirada + " + " + inteligencia + " = " + total);

        if (total >= 15) {
            sumarVida(total);
        } else {
            System.out.println("Fallo. " + getNombre() + " no se le ocurre ninguna idea.");
        }
    }

    public boolean desafiar( Personaje enemigo) {
        int tirada = tirarDado();
        int total = tirada + voluntad;
        System.out.println(getNombre() + " intenta desafiar a su enemigo. Tira el dado y saca: " + tirada + " + " + voluntad + " = " + total);
        if (total >= 15) {
            enemigo.restarVida(total);
            sumarVida(total);
            return true;
        } else {
            System.out.println("Fallo. " + getNombre() + " es ignorado por el enemigo. Su enemigo suma vidas");
            enemigo.sumarVida(total);
            return false;
        }
    }

    public void convencer() {
        int tirada = tirarDado();
        int total = tirada + carisma;
        System.out.println(getNombre() + " trata de convencer a un aliado para que lo ayude. " + tirada + " + " + carisma + " = " + total);
        if (total >= 15) {
            sumarVida(total);
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
