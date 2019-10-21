import javax.swing.*;

/**
 * Clase propia boton heredada de Jbutton.
 * @author Hector E. Garcia Nuñez
 * @version 0.1
 */
public class Boton extends JButton {

    private int coordenadaFila;
    private int coordenadaColumna;
    private Boolean activado;

    /**
     * Constructor por defecto para incializar el componente Boton. Necesarios dos parametros
     * la fila y la columna, ambos integers.
     * @param fila Determina la fila de la cuadricula en la que será colocado el botón.
     * @param columna Determina la columna de la cuadricula en la que será colocado el botón.
     */
    Boton(int fila, int columna) {
        super("");
        activado = false;
        coordenadaFila = fila;
        coordenadaColumna = columna;
    }

    //Getters

    public int getCoordenadaFila() {
        return coordenadaFila;
    }

    public int getCoordenadaColumna() {
        return coordenadaColumna;
    }

    Boolean getActivado() {
        return activado;
    }

    //Setters

    void setActivado(Boolean activado) {
        this.activado = activado;
    }

}