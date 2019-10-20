/**
 * Clase Jugador que representa a los usuarios de la aplicacion tienen varios atributos. turno, tipoFicha y alias
 * @author Hector E. García Nuñez.
 * @version 0.1
 */
public class Jugador {
    private String alias;           // NO NULO, NO VACIO
    private TipoFicha tipoFicha;
    private int turno;              // 0 o 1

    /**
     * Constructor para la clase Jugador.
     * @param alias String Alias o nickname introducido para el jugador
     * @param tipoFicha TipoFicha el tipo de ficha con la que va jugar.
     * @param turno int debe estar comprendido entre 0 y 1.
     */
    public Jugador(String alias, TipoFicha tipoFicha, int turno) {
        setAlias(alias);
        setTipoFicha(tipoFicha);
        setTurno(turno);
    }

    /**
     * Mutador para el atributo alias.
     * @param alias String.
     */
    private void setAlias(String alias) {
        assert alias!=null : "Error: el alias no puede ser nulo";
        assert !alias.isEmpty(): "Error: el alias no puede estar vacio";
        this.alias = alias;
    }

    /**
     * Mutador para el atributo TipoFicha.
     * @param tipoFicha TipoFicha.
     */
    private void setTipoFicha(TipoFicha tipoFicha) {
        assert tipoFicha!=null : "Error: el tipo de ficha no puede ser nula";
        this.tipoFicha = tipoFicha;
    }

    /**
     * Mutador para el atributo de clase turno.
     * @param turno int debe estar entre 0 y 1.
     */
    private void setTurno(int turno) {
        assert turno==0 || turno==1: "Error: el turno debe ser 0 o 1";
        this.turno = turno;
    }

    /**
     * Accesor para el tipo de ficha que colocará el jugador
     * @return Tipo de ficha.
     */
    public TipoFicha getTipoFicha() {
        return tipoFicha;
    }

    /**
     * Representacion grafica de un jugador.
     * @return String.
     */
    @Override
    public String toString() {
         return alias+" ("+tipoFicha+")";
    }

}
