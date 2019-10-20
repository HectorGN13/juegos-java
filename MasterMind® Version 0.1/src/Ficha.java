/**
 * Esta clase representa las fichas que van a estar colocadas en el tablero.
 * Cada ficha consta de un color que la representa, y un marcador booleano para indicar si esa ficha esta colocada o no.
 * La clase ficha nos servirá tanto para representar la clave secreta como para indicar los errores y aciertos.
 * @version 0.1
 * @author Hector E. Garcia Nuñez.
 */
public class Ficha {

    private TipoColor color;
    private boolean colocada;

    /**
     * Constructor para la clase ficha, para crear una ficha se debe itroducir un color.
     * Este metodo cazara una excepcion del tipo NullPointerException en el caso de que el color introducido sea nulo.
     * @param color TipoColor el color de la ficha.
     */
    Ficha(TipoColor color) {
        try {
            setColor(color);
            colocada = false;
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * Mutador privado para el atributo color de la ficha.
     * Este metodo lanzara una excepcion del tipo NullPointerException en el caso de que el parametro introducido sea
     * null.
     * @param color TipoColor el color de la ficha.
     * @throws NullPointerException Lanzara esta excepcion si el parametro TipoColor es de tipo null.
     */
    private void setColor(TipoColor color) throws NullPointerException {
        if (color == null) {
            throw new NullPointerException("El color de la ficha no puede ser nulo.");
        }
        this.color = color;
    }

    /**
     * Mutador para el atributo colocada.
     * Este método nos permite modificar el atributo colocada de una ficha para indicar si dicha ficha esta colocada en
     * el tablero.
     * @param colocada boolean
     */
    public void setColocada(boolean colocada) {
        this.colocada = colocada;
    }

    //////////////
    //ACCESORES//
    /////////////

    /**
     * Metodo accesor que nos devuelve el color que tiene la ficha.
     * @return TipoColor color que tiene la ficha.
     */
    private TipoColor getColor() {
        return color;
    }

    /**
     * Metodo accesor que nos devuelve si una ficha esta colocada en el tablero o no.
     * @return boolean. true en el caso en el que la ficha se encuentre colocada en el tablero y false en el caso con-
     * trario.
     */
    private boolean isColocada() {
        return colocada;
    }

    /**
     * Este metodo nos sirve para comparar si dos fichas son la misma. Se considera que dos fichas son iguales cuando
     * ambas estan colocadas y tienen el mismo color.
     * @param obj El objeto con el que se desea comparar.
     * @return boolean. true en el caso de que ambas fichas introducidas tengan el mismo color y ambas esten colocadas,
     * y false en el caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj instanceof Ficha){
            Ficha fich2 = (Ficha) obj;
            return this.getColor().equals(fich2.getColor()) && this.isColocada() == fich2.isColocada();
        }
        return false;
    }

    /**
     * Metodo toString para la representacion grafica de una ficha.
     * Utiliza el tipoColor para representar el color en consola.
     * La variable local ficha es el simbolo ● en ASCII.
     * @return String con la representacion gráfica de la ficha y su color.
     */
    @Override
    public String toString() {
        String ficha = "\u25CF";
        String resultado = "\033[30m"+ficha+"\033[0m";
        for (int i = 0; i < TipoColor.values().length; i++) {
            if (TipoColor.values()[i].equals(getColor())){
                resultado = "\033[3"+i+"m"+ficha+"\033[0m";
            }
        }
        return resultado;
    }
}
