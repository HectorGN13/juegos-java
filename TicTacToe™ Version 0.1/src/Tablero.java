import java.util.Scanner;

/**
 * Clase tablero encargada de generar el tablero y comprobar la lógica del juego.
 * Contiene un array bidimensional de casillas.
 * @author Hector E. García Nuñez.
 * @version 0.1
 */
public class Tablero {

    private Casilla[][] casillas;

    // Dimension del tablero
    private int dimension;

    // Número de casillas ocupadas con una ficha en el tablero
    private int ocupadas;

    /**
     * Constructor de la clase tablero, ya que el tablero es un cuadrado perfecto tiene que tener la misma
     * dimension de filas y de columnas. No pueden ser inferior a 3.
     * @param dimension int se multiplica por si mismo para establecer la dimension del tablero.
     */
    public Tablero(int dimension) {
        setDimension(dimension);
        ocupadas= 0;
        casillas = new Casilla[dimension][dimension];
        inicializarTablero();
    }

    private void setDimension(int dimension) {
        assert dimension > 2 : String.format("Error la dimension del tablero tiene que ser mayor de 2x2. " +
                "Dimension introducida = %d", dimension);
        assert dimension < 11 : String.format("Error la dimension del tablero tiene que ser menor de 10x10. " +
                "Dimension introducida = %d", dimension);
        this.dimension = dimension;
    }


    /**
     * Metodo encargado de inicializar el array de casillas.
     */
    private void inicializarTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    /**
     * Metodo encargado de leer una coordenada por teclado y validarla. en el caso de que la corrdenada sea correcta
     * devolverá dicha coordenada.
     * @return Coordena coordenada leida y validada correctamente.
     */
    public Coordenada leerCoordenada() {
        Scanner sc = new Scanner(System.in);
        boolean correcto = false;
        int fila;
        int columna;

        // Leer fila
        do {
            System.out.printf("Fila (debe estar comprendida entre 0 y %d):", casillas.length-1);
            fila = sc.nextInt();
            correcto=esFilaValida(fila);
            if (!correcto)
                System.out.printf("Error: fila incorrecta (%d)\n",fila);
        } while (!correcto);

        // Leer columna
        do {
            System.out.printf("Columna (Debe estar comprendidad entre 0 y %d):", casillas[0].length-1);
            columna = sc.nextInt();
            correcto=esColumnaValida(columna);
            if (!correcto)
                System.out.printf("Error: columna incorrecta (%d)\n",columna);
        } while (!correcto);

        return new Coordenada(fila,columna);
    }

    /**
     * Metodo que comprueba si la fila introducida es correcta.
     * @param fila int numero de la fila.
     * @return true si la fila cumple con los requisitos
     */
    private boolean esFilaValida(int fila) {
        return (fila>=0 && fila<=casillas.length);
    }

    /**
     * Metodo que comprueba si la columna introducida es correcta.
     * @param columna int numero de la columna.
     * @return true si la fila cumple con los requisitos
     */
    private boolean esColumnaValida(int columna) {
        return (columna>=0 && columna<=casillas[0].length);
    }

    /**
     * Metodo encargado de validar una corrdenada pasada por paremetro dentro del tablero.
     * @param c Coordenada introducida
     * @return boolean true en el caso de que sea una coordenada valida y false en el caso contrario.
     */
    private boolean esCoordenadaValida(Coordenada c) {
        assert c != null : "Error: la coordenada introducida no puede ser null";
        int fila = c.getFila();
        int columna = c.getColumna();

        return  (fila>=0 && fila<=casillas.length-1) &&
                (columna>=0 && columna<=casillas[0].length-1);
    }

    /**
     * metodo encargado de colocar las fichas en el tablero.
     * Devuelve true si el método ha realizado la acción correctamente y false en el caso de que no se haya podido
     * colocar la ficha.
     * @param ficha Ficha la ficha que se desea colocar.
     * @param c Coordenada en la coordenada del tablero donde se desea colocar la ficha.
     * @return boolean true en el caso de que la accion se haya realizado satisfactoriamente y false en el caso contrario.
     */
    public boolean colocar(Ficha ficha, Coordenada c) {
        assert ficha != null : "Error: la ficha introducida no puede ser nula";
        assert c != null : "Error: la coordenada introducida no puede ser nula";

        if (!esCoordenadaValida(c))
            return false;

        int fila = c.getFila();
        int columna = c.getColumna();
        Casilla casilla = casillas[fila][columna];
        if (!casilla.estaVacia())
            return false;

        // Coloco la ficha en el tablero
        casillas[fila][columna].colocarFicha(ficha);
        ocupadas++;

        return true;
    }

    /**
     * Metodo que realiza la loguica del tablero comprobando se hay n en raya.
     * @return boolean, true en el caso afirmativo y false en el caso negativo.
     */
    public boolean nEnRaya() {
        return (hayRayaHorizontal() || hayRayaVertical() || hayRayaDiagonal());
    }

    /**
     * Metodo que comprueba si todas las casillas horizontales tienen las mismas fichas.
     * @return boolean, true afirmativo y false negativo.
     */
    private boolean hayRayaHorizontal() {
        for (Casilla[] casilla : casillas) {
            if (linea(casilla))
                return true;
        }

        return false;
    }

    /**
     * Metodo que comprueba si todas las casillas verticales tienen las mismas fichas.
     * @return boolean, true afirmativo y false negativo.
     */
    private boolean hayRayaVertical() {
        for (int j = 0; j < casillas[0].length; j++) {
            Casilla[] columna = new Casilla[casillas.length];
            for (int i = 0; i < casillas.length; i++) {
                columna[i] = casillas[i][j];
            }
            if (linea(columna))
                return true;
        }

        return false;
    }

    /**
     * Metodo que comprueba si en las diagonales diagonales principales del tablero hay cassillas con las mismas fichas.
     * @return boolean, true afirmativo y false negativo.
     */
    private boolean hayRayaDiagonal() {
        // Diagonal principal (i==j)
        Casilla[] diagonalPri = new Casilla[casillas.length];
        for (int i = 0; i < casillas.length; i++) {
            diagonalPri[i] = casillas[i][i];
        }
        if (linea(diagonalPri))
            return true;

        // Diagonal secundaria (i==(j=i-1))
        Casilla[] diagonalSec = new Casilla[casillas.length];
        for (int i = 0; i < casillas.length; i++) {
            diagonalSec[i] = casillas[i][casillas.length-1-i];
        }

        return linea(diagonalSec);
    }

    /**
     * Comprueba si todas las casillas recibidas tienen la misma ficha
     * @param casillas Casilla[]
     * @return true si todas las casillas tienen las misma ficha y no están vacias (e.o.c. false)
     */
    private boolean linea(Casilla[] casillas) {
        assert casillas != null : "Error: casillas no puede ser nulo";
        assert casillas.length >= 1 : String.format("Error: casillas debe tener longitud >= 1 (longitud %d)\n",
                casillas.length);

        Casilla aux = casillas[0];
        if (aux.estaVacia()) return false;      // No puede haber 3 fichas iguales si la primera casilla está vacia

        for (int i = 1; i < casillas.length; i++)
            if (!aux.equals(casillas[i]))
                return false;

        return true;
    }

    public boolean estaCompleto() {
        return ocupadas==Math.pow(casillas.length,2);
    }

    /**
     * Metodo encargado de representar graficamente por consola el tablero.
     * @return String representacion grafica de tablero.
     */
    @Override
    public String toString() {
        int numeritosY = 0;
        int numeritosX = 0;
        StringBuilder datos = new StringBuilder(" ");

        //Imprime los numeros de arriba
        for (int x = 0; x < casillas.length; x++) {
            datos.append("    ").append(numeritosX);
            numeritosX++;
        }
        datos.append("\n  ");
        //Imprime la primera linea que corresponde con la parte superior del tablero.
        for (int x = 0; x < casillas.length; x++) {
            datos.append("\033[33m"+"+----"+"\033[0m");
        }
        datos.append("\033[33m"+"+"+"\033[0m");
        datos.append("\n");
        //Imprime el resto del tablero.
        for (int y = 0; y < casillas.length; y++) {
            datos.append(numeritosY).append("\033[33m"+" | "+"\033[0m");
            for (int x = 0; x < casillas[y].length; x++) {
                datos.append(casillas[y][x].toString());
            }
            datos.append("\n");
            datos.append("  ");
            for (int i = 0; i < casillas[y].length; i++) {
                datos.append("\033[33m"+"+----"+"\033[0m");
            }
            datos.append("\033[33m"+"+"+"\033[0m");
            datos.append("\n");
            numeritosY++;
        }
        return datos.toString();
    }
}


