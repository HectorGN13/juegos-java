/**
 * Clase casilla que contendran una ficha o null en el caso de que este vacia.
 * @author Hector E. Garcia Nuñez.
 * @version 0.1
 */
public class Casilla {

    private Ficha ficha;

    /**
     * Constructor para la clase Casilla, con este constructor las casillas empezarán vacias.
     */
    public Casilla() {
        ficha = null;
    }

    /**
     * Constructor sobrecargado para la clase casilla, con este constructor podremos parasarle como parametro una ficha.
     * @param ficha Ficha.
     */
    public Casilla(Ficha ficha) {
        this.ficha = ficha;
    }


    public boolean estaVacia() {
        return ficha==null;
    }

    /**
     * modificador para la clase casilla, con este método se puede colocar las fichas en las casillas.
     * @param ficha
     */
    public void colocarFicha(Ficha ficha) {
        this.ficha = ficha;
    }



    /**
     * Comprueba si 2 casillas son iguales
     * @param o Object es parametro es la casilla introducida con la que se quiere comparar
     * @return true si las dos casillas comparadas contienen la misma ficha o si las dos están vacias
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Casilla casilla = (Casilla) o;

        if (this.estaVacia() && casilla.estaVacia()) return true;
        return ficha.equals(casilla.ficha);
    }

    @Override
    public int hashCode() {
        return ficha.hashCode();
    }


    /**
     * Metodo para representar graficamente una casilla.
     * @return String
     */
    @Override
    public String toString() {
        if (this.estaVacia()){
            return " " + "\033[33m"+"  | "+"\033[0m";
        }
        return ficha.toString() + "\033[33m"+" | "+"\033[0m";
    }


}