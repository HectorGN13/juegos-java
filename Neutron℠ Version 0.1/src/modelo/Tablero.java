package modelo;

import java.awt.*;

/**
 * Clase Tablero que representa al tablero de juego dentro del modelo, esta compuesto por casillas.
 * @author Héctor E. García Núñez
 * @version 0.1
 */
public class Tablero {
    private Casilla[][] casillas;

    /**
     * Constructor por defecto del Tablero.
     * @param tamanio el tamaño por defecto que tendrá la cuadricula este parametro se elevara a 2 (tamanio^2)
     */
    public Tablero(int tamanio) {
        casillas = new Casilla[tamanio][tamanio];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    /**
     * Coloca una pieza en una casilla mediante el método de setFicha(ficha).
     * @param ficha La ficha que queremos colocar.
     * @param casilla la casilla en la que la colocaremos.
     */
    void colocar(Ficha ficha, Casilla casilla) {
        casilla.setFicha(ficha);
    }

    /**
     * Método encargado de mover las fichas dentro del tablero.
     * @param origen Casilla de origen.
     * @param destino Casilla de destino.
     */
    public void mover(Casilla origen, Casilla destino) throws IllegalMoveException {
        if (destino.estaVacia()){
            colocar(origen.getFicha(), destino);
            origen.vaciar();
        } else {
            throw new IllegalMoveException("Error: movimiento ilegal.");
        }

    }

    /**
     * Método que se usa para devolver un array con las casillas que
     * contienen electrones del color introducido por parametros.
     * @param color el color de los electrones a buscar.
     * @return array con las casillas de los electrones introducidos.
     */
    public Casilla[] obtenerCasillasElectron(Color color) {
        if (color.equals(Color.red) || color.equals(Color.ORANGE) || color.equals(Color.CYAN)){
            Casilla[] electrones = new Casilla[5];
            int k = 0;
            for (Casilla[] casilla : casillas) {
                for (int j = 0; j < casillas[0].length; j++) {
                    if (!casilla[j].estaVacia()) {
                        if (casilla[j].getFicha().getColor().equals(color)) {
                            electrones[k] = casilla[j];
                            if (k < casillas.length - 1)
                                k++;
                        }
                    }
                }
            }
            return electrones;
        } else {
            throw new IllegalArgumentException("Error: el color introducido es invalido. Colores validos (RED, ORANGE, CYAN");
        }
    }

    /**
     * Devuelve la casilla donde se encuentra el neutron.
     * @return Casilla
     */
    public Casilla obtenerCasillaNeutron() {
        Casilla celda = null;
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                if(!casillas[i][j].estaVacia()){
                    if (casillas[i][j].getFicha().esNeutron())
                        celda = casillas[i][j];
                }
            }
        }
        return celda;
    }
}
