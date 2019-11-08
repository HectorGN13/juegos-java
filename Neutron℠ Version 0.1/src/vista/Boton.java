package vista;
import javax.swing.*;

/**
 * Clase propia boton heredada de Jbutton.
 * @author Hector E. Garcia Nuñez
 * @version 0.1
 */
public class Boton extends JButton {

    private int coordenadaFila;
    private int coordenadaColumna;

    /**
     * Constructor por defecto para incializar el componente Boton. Necesarios dos parametros
     * la fila y la columna, ambos integers.
     * @param fila Determina la fila de la cuadricula en la que será colocado el botón.
     * @param columna Determina la columna de la cuadricula en la que será colocado el botón.
     */
    Boton(int fila, int columna) {
        super("");
        coordenadaFila = fila;
        coordenadaColumna = columna;
    }

}