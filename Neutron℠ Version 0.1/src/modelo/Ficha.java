package modelo;

import java.awt.*;
import java.util.Objects;

/**
 * Clase que representa a una ficha en el modelo.
 * @author Héctor E. García Núñez
 * @version 0.1
 */
public class Ficha {

    private Color color;

    /**
     * Constructor por defecto. Necesita el color por parametro.
     * @param color colores válidos azul naranja y rojo.
     */
    public Ficha(Color color) {
        try {
            setColor(color);
        } catch (IllegalArgumentException e){
            e.getMessage();
        }
    }

    private void setColor(Color color) throws IllegalArgumentException {
        if (color.equals(Color.red) || color.equals(Color.ORANGE) || color.equals(Color.CYAN)) {
            this.color = color;
        } else {
            throw new IllegalArgumentException("Error: el color introducido es invalido. Colores validos (RED, ORANGE, CYAN");
        }
    }

    Color getColor() {
        return color;
    }

    /**
     * Método que devuelve si una Ficha es un neutrón o no.
     * @return boolean
     */
    boolean esNeutron(){
        boolean result = false;
        if (getColor().equals(Color.RED))
            result = true;
        return result;
    }

    /**
     * Método que devuelve si una Ficha es un electron o no.
     * @return boolean
     */
    boolean esElectron(){
        boolean result = false;
        if (getColor().equals(Color.CYAN) || getColor().equals(Color.ORANGE))
            result = true;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ficha ficha = (Ficha) o;
        return color.equals(ficha.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
