import java.util.Scanner;

/**
 * Clase partida es la encargada de inicializar todos los componentes del juego.
 * @author Hector E. Garcia Nuñez.
 * @version 0.1
 */
public class Partida {

    private Jugador[] jugadores;
    private Tablero tablero;
    private int turno;
    private Jugador ganador;
    private static final int MINDIMENSION = 3;
    private int dimension;

    /**
     * Constructor para la clase partida necesitará como parametro las dimensiones del tablero.
     * @param n int Las dimensiones del tablero
     */
    public Partida(int n) {
        setDimension(n);
        setTurno(0);
        tablero=new Tablero(n);
        inicializarJugadores(crearJugadores());
        jugar();
    }

    /**
     * Mutador para el atributo dimension. La dimension debe ser 3 como minimo.
     * @param dimension int
     */
    private void setDimension(int dimension) {
        assert dimension>=MINDIMENSION : String.format("Error: cómo mínimo tres en raya (%d)\n", dimension);
        this.dimension = dimension;
    }


    /**
     * Metodo que se encarga de inicializar el array de jugadores a traves de los datos itroducidos por parametro.
     * El jugador1 tendrá el primer turno y se le asignará la ficha O.
     * El jugador2 tendrá el segundo turno y se le asignará la ficha X.
     * @param nombres String[] array que contiene los nombres de los jugadores no puede ser nulo y su longitud debe ser 2
     */
    private void inicializarJugadores(String[] nombres) {
        assert nombres!=null : "Error: el array de nombres no puede ser nulo";
        assert nombres.length==2 : "Error: deben haber 2 nombres en el array";

        jugadores = new Jugador[2];

        TipoFicha[] tipos = TipoFicha.values();
        for (int i = 0; i < nombres.length; i++)
            jugadores[i] = new Jugador(nombres[i], tipos[i], i);

    }

    /**
     * Metodo para usado para crear los jugadores a partir de los datos introducidos por el usuario.
     * Primero pedira los nombres de los jugadores y luego creara un array con dichos nombres.
     * Devuelve un array de nombres.
     * @return String[]
     */
    private String[] crearJugadores(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Introduzca el nombre del jugador1 recuerda no podrá estar vacio.");
        String j1 = " ";
        do {
             j1 = scn.nextLine();
        } while (j1.length() <= 0);
        System.out.println("Introduzca el nombre del jugador2");
        String j2 = " ";
        do {
            j2 = scn.nextLine();
        } while (j2.length() <= 0);

        return new String[]{j1,j2};
    }

    /**
     * Mutador para el atributo turno. Debe estar comprendido entre 0 y 1.
     * @param turno entero.
     */
    private void setTurno(int turno) {
        assert turno==0 || turno==1: "Error: el turno debe ser 0 o 1";
        this.turno = turno;
    }


    /**
     * Metodo encargado de dar inicio a la partida, lee las coordenadas y las valida.
     * Cambia el turno cuando un jugador ha colocado su ficha en una posicion correcta.
     */
    private void jugar() {
        String titulo = String.format("JUEGO DEL %d EN RAYA", dimension);
        System.out.println(titulo);
        System.out.println();
        System.out.println("Jugadores:");
        for (Jugador j:jugadores) {
            System.out.println(j.toString());
        }
        System.out.println();

        // Mostrar tablero inicial (vacio)
        System.out.println(tablero.toString());

        boolean fin;
        do {
            System.out.println(jugadores[turno].toString());

            boolean colocada;
            do {
                Coordenada c = tablero.leerCoordenada();
                Ficha ficha = new Ficha(jugadores[turno].getTipoFicha());

                colocada = tablero.colocar(ficha, c);
                if (!colocada)
                    System.out.printf("Error: no se pudo colocar la ficha %s en la coordenada %s\n", ficha, c);
            } while (!colocada);

            System.out.println(tablero.toString());
            fin = esFinJuego();
            if (!fin)
                cambiarTurno();
        } while (!fin);

    }

    /**
     * Metodo usado para cambiar el turno.
     */
    private void cambiarTurno() {
        setTurno(1-turno);
    }


    /**
     * Comprueba si hay 3 en raya o tablas o sigue el juego
     * @return  true (fin de juego), false (el juego continua)
     */
    private boolean esFinJuego() {
        if (tablero.nEnRaya()) {
            ganador = jugadores[turno];
            System.out.println("Ganador ==>"+ganador);
            return true;
        } else if (tablero.estaCompleto()) {
            System.out.println("Empate.");
            return true;
        } else
            return false;
    }

}