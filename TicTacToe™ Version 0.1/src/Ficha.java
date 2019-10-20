/**
 * Clase ficha, el unico atributo que tiene es el tipo que puede ser X o O.
 * @author Hector E. Garcia Nuñez
 * @version 0.1
 */
public class Ficha {

    private TipoFicha tipo;

    /**
     * Constructor para la clase ficha, solo necesita como parametro el tipo de ficha a crear.
     * @param tipo TipoFicha.
     */
    public Ficha(TipoFicha tipo) {
        setTipo(tipo);
    }

    /**
     * Mutador para cambiar el tipo de una ficha.
     * @param tipo TipoFicha
     */
    private void setTipo(TipoFicha tipo) {
        assert tipo!=null : "Error: el tipo de ficha no puede ser nulo";
        this.tipo = tipo;
    }

    /**
     * Metodo equals de la clase ficha, consifera de que dos fichas son iguales si son del mismo tipo.
     * @param o Object
     * @return boolean. true en el caso de que la ficha sea identica a la pasada por parametros, y false en el caso
     * contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ficha ficha = (Ficha) o;
        return tipo.equals(ficha.tipo);
    }


    @Override
    public int hashCode() {
        return tipo.hashCode();
    }


    /**
     * Representacion grafica en modo consola de la clase ficha, contienen un color y un formato.
     * @return String
     */
    @Override
    public String toString() {
        String fichaO = "\033[34m"+"\u26d4"+"\033[0m";
        String fichaX = "\033[31m"+"\u26cc"+"\033[0m";
        return tipo.equals(TipoFicha.O) ? fichaO : fichaX;
    }


}


