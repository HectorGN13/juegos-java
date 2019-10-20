import java.util.Arrays;

/**
 * Clase que representa las diferentes combinacionaciones de codigoSecreto que trataremos de descubrir en el juego.
 * Consta de dos atributos, uno llamado combinacion, que es una array de fichas donde se almacenaran las fichas del
 * que formaran la clave, hay una modalidad de juego en el que la clave puede que no tenga alguna ninguna ficha colocada
 * por lo que se permite que exista null dentro del array de Fichas, ademas no hay ninguna restriccion en el caso de que
 * se coloquen fichas iguales( cuando hablamos de fichas iguales nos referimos a que tienen el mismo color)
 * El atributo MAXLONGITUD nos indica la longuitud maxima de la combinación en nuestro caso es de 8.
 * @version 0.1
 * @author Héctor E. García Núñez
 */
public class CodigoSecreto implements Comparable<CodigoSecreto> {

    private Ficha[] combinacion;
    private static final int MAXLONGITUD = 8;
    private static int posicion = 0;


    /**
     * Constructor para la clase CodigoSecreto
     * Si el codigo secreto introducido por parametro supera la longuitud maxima o es null recibiremos una excepcion.
     * @param combinacion Ficha[] Un array de ficha
     */
    CodigoSecreto(Ficha[] combinacion) {
        try {
       setCombinacion(combinacion);
        } catch (NullPointerException | IllegalArgumentException e){
            e.printStackTrace();
        }
    }



    ////////////
    //MUTADOR//
    ///////////

    /**
     * Mutador para el atributo de clase combinacion, en el que se le aplican las restricciones. Este método lanzara
     * varias excepciones. Recibe un array de fichas.
     * @param combinacion array de fichas
     * @throws NullPointerException Esta excepcion sera lanzada en el caso de que el parametro introducido sea nulo.
     * @throws IllegalArgumentException Esta excepcion sera lanzada en el caso de que la longitud sea superior a MAXLONGITUD
     */
    private void setCombinacion(Ficha[] combinacion) throws NullPointerException, IllegalArgumentException {
        if (combinacion == null){
            throw new NullPointerException("La combinacion no puede ser null");
        } else if (combinacion.length > MAXLONGITUD){
            throw new IllegalArgumentException("La combinacion no puede exceder el Maximo de Longitud: "+MAXLONGITUD+
                    " Actual: "+combinacion.length);
        }
        this.combinacion = combinacion;
    }

    ////////////
    //ACCESOR//
    ///////////


    private static int getPosicion() {
        return posicion;
    }

    Ficha[] getCombinacion() {
        return combinacion;
    }


    /**
     * Metodo que compara objetos y devuelve una variable booleana que indica si el objeto llamante y el objeto recibido
     * por parametros son iguales o no. Un codigo secreto sera igual a otro si todas las fichas que componen su
     * combinacion son iguales.
     * @param obj recibe por parametros un objeto Objects
     * @return devuelve una variable booleana, true en el caso de que sean iguales y false si son distintos.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CodigoSecreto)) return false;
        CodigoSecreto otro = (CodigoSecreto) obj;
        if (this.getCombinacion().length != otro.getCombinacion().length) return false;
        int contador = 0;
        for (int i = 0; i < getCombinacion().length; i++) {
           if(this.getCombinacion()[i] == null) {
                if (otro.getCombinacion()[i] == null)
                    contador++;
           } else if (this.getCombinacion()[i].equals(otro.getCombinacion()[i]))
               contador++;
        }
        return (contador == getCombinacion().length);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getCombinacion());
    }

    /**
     * Metodo toString de la clase CodigoSecreto.
     * @return String que representa graficamente en consola del codigo secreto.
     */
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();

        for (Ficha aCombinacion : combinacion) {
            if (aCombinacion == null) {
                resultado.append("\u25cb");
            } else resultado.append(aCombinacion.toString());
        }

        return resultado.toString();
    }

    @Override
    public int compareTo(CodigoSecreto o) {
        return getPosicion() - posicion;
    }
}
