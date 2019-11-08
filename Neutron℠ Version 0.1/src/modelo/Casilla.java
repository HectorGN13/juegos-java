package modelo;

import java.util.Objects;

/**
 * Clase parte del modelo que representa a una casilla.
 * @author Héctor E. García Núñez
 * @version 0.1
 */
public class Casilla {

    private int fila;
    private int columna;
    private Ficha ficha;

    /**
     * Constructor por defecto.
     * @param fila fila de la casilla.
     * @param columna columna de la casilla.
     */
    Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    //GETTERS

    int getFila() {
        return fila;
    }

    int getColumna() {
        return columna;
    }

    public Ficha getFicha() {
        return ficha;
    }

    //SETTERS

    void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * Método que devuelve si una casilla está vacía o no.
     * @return boolean (true en el caso de estar vacía y false en el caso contrario).
     */
    boolean estaVacia(){
        boolean result = true;
        if (getFicha() != null) {
            result = false;
        }
        return result;
    }

    /**
     * Método encargado de vaciar la casilla. Es decir eliminar ficha de esta.
     */
    void vaciar(){ setFicha(null);
    }

    /**
     * Método equals de la clase casilla comprueba si dos casillas son iguales en el caso
     * de que su columna y su fila sean lo mismo.
     * @param o Object objeto a comparar.
     * @return boolean en el que si indica si los objetos a comparar son iguales o no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casilla casilla = (Casilla) o;
        return fila == casilla.fila &&
                columna == casilla.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}
