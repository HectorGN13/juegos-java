package vista;

import adaptador.Adaptador;
import controlador.Controlador;
import modelo.Ficha;
import modelo.Modelo;
import modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase encargada de la vista de la intefaz del juego con la que el usuario interactuará.
 * @author Héctor E. García Núñez
 * @version 0.1
 */
public class VentanaPrincipal extends JFrame{

    private Adaptador adaptador;
    private Font fuente;
    private JTextField jugador1;
    private JTextField jugador2;
    private Boton[][] tablero;
    private int dimensiones;
    private boolean turno;

    /**
     * Constructor por defecto que incializa la interfaz grafica como un JFrame.
     * @param medidas Medidas que tendrá la cuadrícula.
     * @throws HeadlessException heredado de JFrame.
     */
    public VentanaPrincipal(int medidas) throws HeadlessException {
        super();
        dimensiones = medidas;
        adaptador = new Adaptador(new Controlador(new Modelo(this)),this);
        configurarVentana();
        inicializarComponentes();
        actualizar(getTurno());
    }

    /**
     * Método accesor que devuelve el turno. si es true es turno del jugador 1 si es false es turno del jugador 2.
     * @return turno(true o false).
     */
    public boolean getTurno() {
        return turno;
    }

    /**
     * Método accesor que devuelve la dimensiones de cada lado del tablero.
     * @return dimensiones.
     */
    public int getDimensiones() {
        return dimensiones;
    }

    /**
     * Método encargado de la inicialización de los componentes del JFrame.
     */
    private void configurarVentana() {
        this.setTitle("Neutron℠ V0.1");
        this.setSize(900, 900);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Método encargado de inicializar los paneles, los contenedores y los botones, asi como de su configuración.
     */
    private void inicializarComponentes() {

        // creamos los componentes
        JPanel contenedorBotones = new JPanel();
        JPanel interfazLectura = new JPanel();

        // Personalizamos los componentes
        turno = true;
        fuente = new Font("Dialog", Font.BOLD, 190);


        jugador1 = new JTextField("JUGADOR 1\n NARANJA");
        jugador2 = new JTextField("JUGADOR 2\n AZUL");
        jugador1.setFont(new Font("Dialog", Font.BOLD, 30));
        jugador2.setFont(new Font("Dialog", Font.BOLD, 30));
        jugador1.setBackground(Color.WHITE);
        jugador2.setBackground(Color.WHITE);
        jugador1.setHorizontalAlignment(JTextField.LEFT);
        jugador2.setHorizontalAlignment(JTextField.RIGHT);
        jugador1.setFocusable(false);
        jugador2.setFocusable(false);
        interfazLectura.setLayout(new GridLayout(1, 2));
        interfazLectura.add(jugador1);
        interfazLectura.add(jugador2);

        contenedorBotones.setLayout(new GridLayout(getDimensiones(), getDimensiones()));

        tablero = new Boton[getDimensiones()][getDimensiones()];
        for (int i = 0; i < getDimensiones(); i++) {
            for (int j = 0; j < getDimensiones(); j++) {
                crearBotones(i, j, contenedorBotones);
            }
        }

        // agregamos los componentes a la ventana
        this.add(contenedorBotones, BorderLayout.CENTER);
        this.add(interfazLectura, BorderLayout.NORTH);
    }

    /**
     * Método encargado de crear boton y agregarlo al panel.
     * @param fila Fila de la cuadrícula donde irá colocado el botón.
     * @param columna Columna de la cuadrícula donde irá colocado el botón.
     * @param contenedorDeLosBotones Panel contenedor donde agregar el boton.
     */
    private void crearBotones(int fila, int columna, JPanel contenedorDeLosBotones) {
        Boton btn = new Boton(fila, columna);
        btn.setFont(fuente);
        btn.setForeground(Color.BLACK);
        btn.setBackground(new Color(0xA9B6A3));
        btn.addActionListener(adaptador);
        tablero[fila][columna] = btn;
        contenedorDeLosBotones.add(btn);
    }

    /**
     * Método encargado de cambiar la interfaz dependiendo del turno de los jugadores.
     */
    private void cambioTurno(Boolean turno) {
        this.turno = turno;
        if (getTurno()) {
            jugador1.setBackground(Color.ORANGE);
            jugador1.setForeground(Color.WHITE);
            jugador2.setBackground(Color.WHITE);
            jugador2.setForeground(Color.BLACK);
        } else {
            jugador2.setBackground(Color.CYAN);
            jugador2.setForeground(Color.WHITE);
            jugador1.setBackground(Color.WHITE);
            jugador1.setForeground(Color.BLACK);
        }
    }

    /**
     * Método que sirve para encontrar las coordenadas (x,y) de la casilla
     * a la que se le ha pulsado el botón.
     * @param e ActionEvent
     * @return arrayList de Coordenadas
     */
    public ArrayList<Integer> obtenerCoordenadas(ActionEvent e) {
        ArrayList<Integer> coordenadas = new ArrayList<>();
        for(int i = 0; i < dimensiones ; i++) {
            for(int j = 0; j < dimensiones ; j++) {
                if(e.getSource() == tablero[i][j]) {
                    coordenadas.add(i);
                    coordenadas.add(j);
                    System.out.println(coordenadas);
                }
            }
        }
        return coordenadas;
    }

    /**
     * Método encargado de actualizar la vista con el modelo actual.
     */
    public void actualizar(boolean turno){
        Tablero t = adaptador.getControlador().getM().getTablero();
        for (int i = 0; i < t.getCasillas().length; i++) {
            for (int j = 0; j < t.getCasillas().length; j++) {
                if (t.getCasillas()[i][j].getFicha() == null){
                    tablero[i][j].setText("");
                    tablero[i][j].setForeground(Color.WHITE);
                } else if (t.getCasillas()[i][j].getFicha().equals(new Ficha(Color.ORANGE))){
                    tablero[i][j].setText("•");
                    tablero[i][j].setForeground(Color.ORANGE);
                } else if (t.getCasillas()[i][j].getFicha().equals(new Ficha(Color.CYAN))){
                    tablero[i][j].setText("•");
                    tablero[i][j].setForeground(Color.CYAN);
                } else if (t.getCasillas()[i][j].getFicha().equals(new Ficha(Color.RED))){
                    tablero[i][j].setText("•");
                    tablero[i][j].setForeground(Color.RED);
                }
            }
        }
        cambioTurno(turno);
    }

    /**
     * Método utilizado para cambiar los colores de las casillas.
     * @param x coordenada x
     * @param y coordenada y
     */
    public void cambiarColor(int x, int y){
        tablero[x][y].setBackground(new Color(0x8ACB00));
    }

    /**
     * Metodo que deja por defecto el color de los botones.
     */
    public void dejarTodoigual(){
        for (Boton[] btn : tablero) {
            for (Boton botones : btn) {
                botones.setBackground(new Color(0xA9B6A3));
                botones.setEnabled(true);
            }
        }
    }


    /**
     * Metodo que muestra por pantalla un dialogo al usuario/s para indicar cual es el jugador vencedor.
     * Ademas muestra un segundo cuadro de dialogo en el que se pregunta al usuario si desea volver a Jugar,
     * y en su defecto cierra el programa.
     * @param finJuego Parametro boleano que indica si el juego está terminado.
     */
    public void finJuego(boolean finJuego){
        if (finJuego){
            JOptionPane.showMessageDialog(this, "El jugador con "+ (turno ? "numero 1" : "numero 2") +" ha ganado la partida");
            if(JOptionPane.showConfirmDialog(this, "¿Desea volver a jugar?") == 0){
                //todo hacer un iniciar partida.
            } else {
                this.dispose();
            }
        }
    }


}
