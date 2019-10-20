/**
 * Clase general para generar coordenadas, enta basado en las coordenadas cartesianas (y,x) para un plano en dos
 * dimensiones
 * @author Hector E. Garcia Nuñez
 * @version 0.1
 */
public class Coordenada {

    private int coordenadaX;
    private int coordenadaY;

    /**
     * Constructor de la clase Coordenada necesita dos enteros para crear la coodenada.
     * @param x int coordenada este<=>oeste.
     * @param y int coordenada norte<=>sur.
     */
    public Coordenada(int y, int x) {
        coordenadaY = y;
        coordenadaX = x;
    }

    /**
     * Accesor para las filas o la corrdenada Y.
     * @return int coordenadaY
     */
    public int getFila() {
        return coordenadaY;
    }

    /**
     * Accesor para las columnas o coordenada X.
     * @return int coordenadaX
     */
    public int getColumna() {
        return coordenadaX;
    }

    /**
     * Metodo que representa gradicamente en consola el valor de una corrdenada.
     * @return String representacion grafica de la coordenada (y,x).
     */
    @Override
    public String toString() {
        return "("+coordenadaY+","+coordenadaX+")";
    }
}
