package modelo;

import vista.*;
import java.awt.*;

/**
 * La clase modelo es donde se encuentra el estado actual del juego asi como donde reside la logica de este.
 * La clase modelo llama a la vista para actualizar la interfaz gráfica del usuario de acuerdo con el estado actual del juego.
 */
public class Modelo {
    private VentanaPrincipal v;
    private Tablero tablero;
    private boolean turno;
    private boolean primerMovimiento;
    private boolean colocar;

    private Casilla casillaInicio;
    private Ficha mano;


    /**
     * Constructor por defecto de la clase Modelo.
     */
    public Modelo(VentanaPrincipal v) {
        this.v = v;
        turno = true;
        primerMovimiento = true;
        colocar = false;
        tablero = new Tablero(5);
        rellenarTablero(tablero);
    }

    //SETTERS

    /**
     * Método que cambia el turno automaticamente.
     */
    private void cambiarTurno(){
        turno = !turno;
    }

    /**
     * Mutador que coloca una ficha en la mano (imaginaria).
     * @param mano Variable auxial para colocar la ficha
     */
    private void setMano(Ficha mano) {
        this.mano = mano;
    }

    /**
     * Mutador para la Casilla de inicio.
     * @param casillaInicio Casilla que empieza el movimiento
     */
    private void setCasillaInicio(Casilla casillaInicio) {
        this.casillaInicio = casillaInicio;
    }

    //GETTERS

    public boolean isColocar() {
        return colocar;
    }

    public boolean getTurno() {
        return turno;
    }

    public Tablero getTablero() {
        return tablero;
    }


    /**
     * Método que sirve para averiguar si el movimiento es legal.
     * @param casillaInicio casilla desde la que parte el movimiento.
     * @param casillaFinal casilla en la que será colocada. es decir la casilla destino del movimiento.
     * @return boolean (true si es legal y false en el caso contrario)
     */
    private boolean movimientoLegal(Casilla casillaInicio, Casilla casillaFinal){
        boolean result = false;
        if (casillaFinal.estaVacia()){
            if (movimientoCorrecto(casillaInicio, casillaFinal)){
                if (noHayFichaEnElCamino(casillaInicio, casillaFinal)){
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Método que sirve para averiguar si entre la casilla de inicio y la casilla destino
     * se encuentra alguna ficha.
     * @param casillaInicio casilla desde donde empezaremos el movimiento.
     * @param casillaFinal casilla destino del movimiento
     * @return boolean (true si no nos encontramos fichas por el camino y false en el caso contrario)
     */
    private boolean noHayFichaEnElCamino(Casilla casillaInicio, Casilla casillaFinal) {

        boolean result = true;

        //Direccion arriba
        if (casillaInicio.getFila() > casillaFinal.getFila()
                && casillaInicio.getColumna() == casillaFinal.getColumna()){
            for (int i = casillaFinal.getFila(); i < casillaInicio.getFila(); i++) {
                if (!tablero.getCasillas()[i][casillaInicio.getColumna()].estaVacia()){
                    result = false;
                }
            }

            //Direccion abajo
        } else if (casillaInicio.getFila() < casillaFinal.getFila()
                && casillaInicio.getColumna() == casillaFinal.getColumna()){
            for (int i = casillaFinal.getFila(); i > casillaInicio.getFila(); i--) {
                if (!tablero.getCasillas()[i][casillaInicio.getColumna()].estaVacia()){
                    result = false;
                }
            }

            //Direccion izquierda
        } else if (casillaInicio.getColumna() < casillaFinal.getColumna()
                && casillaInicio.getFila() == casillaFinal.getFila()){
            for (int i = casillaFinal.getColumna(); i < casillaInicio.getColumna(); i++) {
                if (!tablero.getCasillas()[casillaInicio.getFila()][i].estaVacia()){
                    result = false;
                }
            }

            //Direccion derecha
        } else if (casillaInicio.getColumna() > casillaFinal.getColumna()
                && casillaInicio.getFila() == casillaFinal.getFila()){
            for (int i = casillaFinal.getColumna(); i > casillaInicio.getColumna(); i--) {
                if (!tablero.getCasillas()[casillaInicio.getFila()][i].estaVacia()){
                    result = false;
                }
            }

            //Direccion NO
        } else if (casillaInicio.getFila() > casillaFinal.getFila()
                && casillaInicio.getColumna() > casillaFinal.getColumna()){
            for (int i = casillaFinal.getFila(), j = casillaFinal.getColumna();
                 i < casillaInicio.getFila() && j < casillaInicio.getColumna(); i++,j++){
                if (!tablero.getCasillas()[i][j].estaVacia()){
                    result = false;
                }
            }

            //Direccion NE
        } else if (casillaInicio.getFila() > casillaFinal.getFila()
                && casillaInicio.getColumna() < casillaFinal.getColumna()){
            for (int i = casillaFinal.getFila(), j = casillaFinal.getColumna();
                 i < casillaInicio.getFila() && j > casillaInicio.getColumna(); i++,j--){
                if (!tablero.getCasillas()[i][j].estaVacia()){
                    result = false;
                }
            }

            //Direccion SO
        } else if (casillaInicio.getFila() < casillaFinal.getFila()
                && casillaInicio.getColumna() > casillaFinal.getColumna()){
            for (int i = casillaFinal.getFila(), j = casillaFinal.getColumna();
                 i > casillaInicio.getFila() && j < casillaInicio.getColumna(); i--,j++){
                if (!tablero.getCasillas()[i][j].estaVacia()){
                    result = false;
                }
            }

            //Direccion SE
        } else if (casillaInicio.getFila() < casillaFinal.getFila()
                && casillaInicio.getColumna() < casillaFinal.getColumna()){
            for (int i = casillaFinal.getFila(), j = casillaFinal.getColumna();
                 i > casillaInicio.getFila() && j > casillaInicio.getColumna(); i--,j--){
                if (!tablero.getCasillas()[i][j].estaVacia()){
                    result = false;
                }
            }

        }
        return result;
    }

    /**
     * Método que determina si el movimento realizado por la ficha es un movimiento correcto.
     * @param casillaInicio Donde comenzará el movimiento.
     * @param casillaFinal Donde finalizará el movimiento.
     * @return true en el caso de que el movimiento sea correcto y false en el caso contrario.
     */
    private boolean movimientoCorrecto(Casilla casillaInicio, Casilla casillaFinal) {
        boolean result = false;
        if ((casillaInicio.getFila() == casillaFinal.getFila())
                || (casillaInicio.getColumna() == casillaFinal.getColumna())
                || (Math.abs(casillaInicio.getFila() - casillaFinal.getFila())
                == Math.abs(casillaInicio.getColumna() - casillaFinal.getColumna()))) {
            result = true;
        }
        return result;
    }

    /**
     * Metodo encargado de rellenar el tablero colocando las fichas.
     * @param t Tablero.
     */
    private void rellenarTablero(Tablero t){
        colocarNeutron(t);
        colocarElectrones(t);
    }



    /**
     * Coloca el neutron en el centro del tablero.
     * @param tablero tablero de la partida
     */
    private void colocarNeutron(Tablero tablero) {
        tablero.colocar(new Ficha(Color.RED), tablero.getCasillas()[2][2]);
    }

    /**
     * Coloca electrones en la posicion de salida del tablero.
     * @param tablero tablero de la partida
     */
    private void colocarElectrones(Tablero tablero) {
        for (int i = 0; i < tablero.getCasillas().length; i++) {
            tablero.colocar(new Ficha(Color.ORANGE), tablero.getCasillas()[0][i]);
        }
        for (int i = 0; i < tablero.getCasillas().length; i++) {
            tablero.colocar(new Ficha(Color.CYAN), tablero.getCasillas()[4][i]);
        }
    }

    /**
     * Metodo encargado de recibir el neutron en la jugada para luego colocarlo en la casilla destino.
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return Ficha neutron.
     */
    private Ficha jugadaRecibirNeutron(int x, int y){
        Ficha neutron = null;
        if (getTablero().getCasillas()[x][y].getFicha().esNeutron()){
            neutron = getTablero().getCasillas()[x][y].getFicha();
            getTablero().getCasillas()[x][y].vaciar();
        }
        return neutron;
    }

    /**
     * Metodo encargado de recibir los electrones del jugador 1.
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return Ficha electron Naranja
     */
    private Ficha jugadaRecibirElectronJugador1(int x, int y){
        Ficha ficha1 = null;
        if (getTablero().getCasillas()[x][y].getFicha().equals(new Ficha(Color.ORANGE))){
            ficha1 = getTablero().getCasillas()[x][y].getFicha();
            getTablero().getCasillas()[x][y].vaciar();
        }
        return ficha1;
    }

    /**
     * Metodod encargado de recibir los electrones del jugador 2.
     * @param x Coordenanada X
     * @param y Coordenada Y
     * @return Ficha electron Azul
     */
    private Ficha jugadaRecibirElectronJugador2(int x, int y){
        Ficha ficha2 = null;
        if (getTablero().getCasillas()[x][y].getFicha().equals(new Ficha(Color.CYAN))){
            ficha2 = getTablero().getCasillas()[x][y].getFicha();
            getTablero().getCasillas()[x][y].vaciar();
        }
        return ficha2;
    }

    /**
     * Metodo encargado de colocar la ficha recibida por parametros en las coordenadas del tablero recibidas por parametros.
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param ficha Ficha a colocar
     * @return Boolean que indica si el movimiento se ha realizado con éxito.
     */
    private boolean jugadaColocarFicha(int x, int y, Ficha ficha){
        boolean correcto = false;
        if (getTablero().getCasillas()[x][y].getFicha() == null && ficha != null){
            getTablero().getCasillas()[x][y].setFicha(ficha);
            correcto = true;
        }
        return correcto;
    }

    /**
     * Metodo principal que se encarga de hacer las llamadas a los demas metodos para relaizar los movimientos
     * dependiendo de varios factores como el turno o si es el primer movimiento o el segundo.
     * @param x Coordenada X
     * @param y Coordenada Y
     */
    public void jugar(int x, int y){
        if (primerMovimiento){
            jugarPrimerMovimiento(x,y);
        } else {
            jugarSegundoMovimiento(x,y);
        }
    }

    /**
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     */
    private void jugarPrimerMovimiento(int x, int y){
        if (!colocar) {
            if (!getTablero().getCasillas()[x][y].estaVacia()){
                if (getTablero().getCasillas()[x][y].getFicha().esNeutron()) {
                    setMano(jugadaRecibirNeutron(x,y));
                    mostrarOpciones(x, y);
                }
            }
        } else {
            while (movimientoLegal(casillaInicio, getTablero().getCasillas()[x][y])){
                jugadaColocarFicha(x,y, mano);
                setCasillaInicio(null);
                setMano(null);
                colocar = false;
                primerMovimiento = false;
            }
        }
    }

    /**
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     */
    private void mostrarOpciones(int x, int y) {
        setCasillaInicio(getTablero().getCasillas()[x][y]);
        for (int i = 0; i < getTablero().getCasillas().length; i++) {
            for (int j = 0; j < getTablero().getCasillas()[i].length; j++) {
                if (movimientoLegal(getTablero().getCasillas()[x][y], getTablero().getCasillas()[i][j])) {
                    v.cambiarColor(i,j);
                }
            }
        }
        colocar = true;
    }

    /**
     *
     * @param x Coordenada X
     * @param y Coordenada Y
     */
    private void jugarSegundoMovimiento(int x, int y){
        if (!colocar){
            if (turno){
                if (!getTablero().getCasillas()[x][y].estaVacia()) {
                    if (getTablero().getCasillas()[x][y].getFicha().esElectron()){
                        if (getTablero().getCasillas()[x][y].getFicha().equals(new Ficha(Color.ORANGE))){
                            setMano(jugadaRecibirElectronJugador1(x,y));
                            mostrarOpciones(x, y);
                        }
                    }
                }
            } else {
                if (!getTablero().getCasillas()[x][y].estaVacia()) {
                    if (getTablero().getCasillas()[x][y].getFicha().esElectron()){
                        if (getTablero().getCasillas()[x][y].getFicha().equals(new Ficha(Color.CYAN))){
                            setMano(jugadaRecibirElectronJugador2(x,y));
                            mostrarOpciones(x, y);
                        }
                    }
                }
            }
        } else {
            while (movimientoLegal(casillaInicio, getTablero().getCasillas()[x][y])){
                jugadaColocarFicha(x,y, mano);
                setCasillaInicio(null);
                setMano(null);
                colocar = false;
                primerMovimiento = true;
                cambiarTurno();
            }
        }
    }


    /**
     * Método que indica si la partida está acabada o no.
     * La partida se acaba cuando uno de los jugadores obtiene la victoria.
     * Para obtener la victoria la ficha neutron debe estar colocada en alguna
     * de las casillas de la primera fila o de la ultima fila.
     * @return boolean True en el caso positivo y false en el caso negativo.
     */
    public boolean esFinJuego(){
        boolean result = false;
        if (turno) {
            for (int i = 0; i < getTablero().getCasillas().length; i++) {
                if (!getTablero().getCasillas()[4][i].estaVacia() && getTablero().getCasillas()[4][i].getFicha().esNeutron()){
                    result = true;
                }
            }
        } else {
            for (int i = 0; i < getTablero().getCasillas().length; i++) {
                if (!getTablero().getCasillas()[0][i].estaVacia() && getTablero().getCasillas()[0][i].getFicha().esNeutron()){
                    result = true;
                }
            }
        }
        return result;
    }
}
