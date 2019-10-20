import java.util.Objects;

/**
 * La siguiente clase representa a los usuarios que desean jugar a la aplicación.
 * Contiene dos valores el alias, y la puntuacion de dicho jugador que va obteniendo durante la partida.
 * @version 0.1
 * @author Hector E. Garcia Nuñez.
 */
public class Jugador {

    private String alias;
    private int score;

    /**
     * Constructor de la clase Jugador.
     * Crea el objeto jugador a partir del alias introducido por parámetro, este método cazará una excepcion en el caso
     * de que se introduzca un alias no válido, o se introduzca un valor nulo como parámetro.
     * @param alias String Nombre elegido por el usuario.
     */
    public Jugador(String alias) {
        try {
            setAlias(alias);
        }catch (NullPointerException | IllegalArgumentException e ){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        score = 0;
    }


    /**
     * Mutador para el atributo alias.
     * Existe una validacion de parametros, este metodo lanzará excepciones.
     * @param alias String del alias introducido por el usuario.
     * @throws NullPointerException Lanzará esta excepcion en el caso de que el parametro introducido sea null
     * @throws IllegalArgumentException Lanzará esta excecion en el caso de que la longuitud del alias sea inferior o
     * igual a 0 o mayor de 10
     */
    private void setAlias(String alias) throws NullPointerException, IllegalArgumentException {

            if (alias == null){
                throw new NullPointerException("El alias introducido no puedo ser nulo");
            } else if (alias.length() <= 0){
                throw new IllegalArgumentException("El alias introducido no puede estar vacio.");
            } else if (alias.length() > 10){
                throw new IllegalArgumentException(String.format("El alias introducido no puede exceder 10 " +
                        "caracteres. Longitud = %d", alias.length()));
            }

            this.alias = alias;
    }


    /**
     * Mutador para el atributo score.(Puntuacion del jugador).
     * Este metodo modifica el valor de la puntuacion obtenida por el jugador.
     * @param score int de la puntuación.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /////////////
    //ACCESORES//
    /////////////

    /**
     * Metodo accesor para el atributo alias.
     * @return String que contiene el alias del jugador.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Metodo accesor para el atributo score.
     * @return int con los valores de la puntuacion del jugador.
     */
    public int getScore() {
        return score;
    }

    /**
     * Metodo que compara si dos jugadores son el mismo.
     * La comparacion de los jugadores se hace a traves del nombre, es decir dos jugadores son iguales si su nombre es
     * igual, ignora las mayusculas.
     * @param obj Objecto con el que se busca comparar.
     * @return boolean. true en el caso de que los alias sean el mismo.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Jugador) {
            Jugador jug2 = (Jugador) obj;
            return this.getAlias().equalsIgnoreCase(jug2.getAlias());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, score);
    }


}

