import java.util.Random;
import java.util.Scanner;

/**
 * La siguiente clase representa a la partida. Para comenzar una partida necesitamos 1 jugador y un tablero.
 * Se pediran los datos al usuario al comienzo de cada partida.
 * @version 0.1
 * @author Hector E. Garcia Nuñez.
 */
public class Partida {

    private Jugador jugador;
    private Tablero tablero;

    /**
     * Constructor de la clase Partida no hace falta pasarle ningun parametro solo invocarlo y el ya genera el codigo
     * para que la partida de comienzo.
     */
    public Partida() {
        pedirDatosJugador();
        try {
            setTablero(new Tablero(generarCodigoSecreto()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        jugar();
    }

    ///////////////
    // MUTADORES //
    ///////////////

    /**
     * Mutador para el atributo de clase jugador.
     * @param jugador Clase jugador
     * @throws NullPointerException Este método lanzara una excepcion cuando el jugador introducido sea nulo.
     */
    private void setJugador(Jugador jugador) throws NullPointerException{
        if (jugador == null)
            throw new NullPointerException("Debe existir un jugador para comenzar la partida.");
        this.jugador = jugador;
    }

    /**
     * Mutador para el atributo de clase Tablero.
     * @param tablero Clase tablero.
     * @throws NullPointerException Este método lanzara una excepcion cuando el tablero introducido sea nulo.
     */
    private void setTablero(Tablero tablero) throws NullPointerException{
        if (tablero == null)
            throw new NullPointerException("Debe existir un tablero para comenzar la partida.");
        this.tablero = tablero;
    }

    /**
     * Metodo encargado de pedir los datos al jugador.
     */
    private void pedirDatosJugador(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Hello! Please insert your name:");
        try {
            setJugador(new Jugador(scn.nextLine()));
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que genera el CodigoSecreto que el jugador tendrá que descifrar.
     * En futuras versiones según la dificultad de la IA habrá menos restricciones.
     * @return CodigoSecreto
     */
    private CodigoSecreto generarCodigoSecreto(){
        Ficha[] fichas = new Ficha[5];
        Random rnd = new Random();
        TipoColor[] colores = TipoColor.values();
        for (int i = 0; i < fichas.length; i++) {
            fichas[i] = new Ficha(colores[rnd.nextInt(colores.length)]);
        }
        return new CodigoSecreto(fichas);
    }

    /**
     * Metodo que da comienzo a la partida. Es el encargado de llamar a los demás metodos además da respuesta a los dos
     * posibles resultados de la partida, (victoria y derrota).
     */
    private void jugar(){
        do {
            System.out.println(tablero);
            System.out.printf("Intenta averiguar el codigo Secreto."+" Intentos: %d\n\n", 9 - tablero.getTablero().size());

            tablero.agregarValores(codigoGeneradoHumano());
        } while (!tablero.esVictoria() && !tablero.esDerrota());
        if (tablero.esVictoria()){
            System.out.println(tablero);
            System.out.println("Congrats you win!!");
        } else if (tablero.esDerrota()){
            System.out.println(tablero);
            System.out.println("Lo siento, otra vez será...");
        }

    }

    /**
     * Metodo que sirve para generar el codigo que el jugador intuye que pueda ser el correcto.
     * Se piden los datos al usuario a traves de la clase Scaner.
     * @return CodigoSecreto devuelve un codigo generado por el humano.
     */
    private CodigoSecreto codigoGeneradoHumano(){
        int longitudDeLaCOmbinacion = tablero.getCombinacionSecreta().getCombinacion().length;
        int numVeces = 0;
        Ficha[] fichas = new Ficha[tablero.getCombinacionSecreta().getCombinacion().length];

        System.out.println("Introduzca un numero.");
        System.out.println("1.-NEGRO      5.-AZUL\n"
                          +"2.-ROJO       6.-PURPURA\n"
                          +"3.-VERDE      7.-CELESTE\n"
                          +"4.-AMARILLO   8.-BLANCO\n");
        Scanner scn = new Scanner(System.in);

        do {
            System.out.printf("Valor para el %dº pin", numVeces+1);
            int numero = scn.nextInt();
            if (numero <= TipoColor.values().length) {
                fichas[numVeces] = new Ficha(TipoColor.values()[numero-1]);
                numVeces++;
            } else {
                System.out.printf("Error: el numero debe de estar entre 0 y %d. ", TipoColor.values().length);
                System.out.println("Vuelva a intentarlo.");
            }
        }while (numVeces < longitudDeLaCOmbinacion);
        return new CodigoSecreto(fichas);
    }
}
