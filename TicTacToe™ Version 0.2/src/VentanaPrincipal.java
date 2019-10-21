import javax.swing.*;
import java.awt.*;

/**
 * La clase VentanaJuego es la responsable de ejecutar y configurar la vista (interfaz grafica) con la que el usuario
 * interactua, además es la encargada de mostrar el estado en el que se encuentra el juego en cada turno.
 * @author Hector E. Garcia Nuñez
 * @version 0.1
 */
public class VentanaPrincipal extends JFrame {

    private VentanaPrincipal that;
    private int filas;
    private int columnas;
    private Boolean turno;
    private Boolean finJuego;
    private Font fuente;
    private JTextField jugador1;
    private JTextField jugador2;
    private Boton[][] botones;
    private int total_activados;

    /**
     * Constructor por defecto que incializa la interfaz grafica como un JFrame.
     * @param medidas Medidas que tendrá la cuadrícula.
     * @throws HeadlessException heredado de JFrame.
     */
    public VentanaPrincipal(int medidas) {
        super();
        this.filas = medidas;
        this.columnas = medidas;
        configurarVentana();
        inicializarComponentes();
        finJuego = false;
        total_activados = 0;
    }


    private int getFilas() {
        return filas;
    }

    private int getColumnas() {
        return columnas;
    }

    private Boolean getTurno() {
        return turno;
    }

    /**
     * Método encargado de la inicialización de los componentes del JFrame.
     */
    private void configurarVentana() {
        this.setTitle("TicTacToe V0.2");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
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
        that = this;
        turno = true;
        fuente = new Font("Dialog", Font.BOLD, 160);


        jugador1 = new JTextField("JUGADOR 1\n X");
        jugador2 = new JTextField("JUGADOR 2\n O");
        jugador1.setFont(new Font("Dialog", Font.BOLD, 40));
        jugador2.setFont(new Font("Dialog", Font.BOLD, 40));
        jugador1.setBackground(Color.WHITE);
        jugador2.setBackground(Color.WHITE);
        jugador1.setHorizontalAlignment(JTextField.LEFT);
        jugador2.setHorizontalAlignment(JTextField.RIGHT);
        jugador1.setFocusable(false);
        jugador2.setFocusable(false);
        interfazLectura.setLayout(new GridLayout(1, 2));
        interfazLectura.add(jugador1);
        interfazLectura.add(jugador2);

        if (this.getTurno()) {
            jugador1.setBackground(Color.ORANGE);
            jugador2.setBackground(Color.WHITE);
        } else {
            jugador1.setBackground(Color.WHITE);
            jugador2.setBackground(Color.CYAN);
        }

        contenedorBotones.setLayout(new GridLayout(that.getFilas(), that.getColumnas()));

        botones = new Boton[getFilas()][getColumnas()];
        for (int i = 0; i < that.getFilas(); i++) {
            for (int j = 0; j < that.getColumnas(); j++) {
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
        btn.setBackground(Color.WHITE);
        btn.addActionListener(e -> {
            Boton b = (Boton) e.getSource();
            if (!b.getActivado()) {

                if (getTurno()) {
                    b.setText("X");
                    b.setForeground(Color.ORANGE);
                    b.setBackground(Color.WHITE);
                } else {
                    b.setText("O");
                    b.setForeground(Color.CYAN);
                    b.setBackground(Color.WHITE);
                }

                if (!b.getActivado()) {
                    total_activados++;
                    b.setActivado(true);
                }


                finJuego = comprobarLinea();
                if (!finJuego && total_activados < botones.length * botones[0].length) {
                    cambioTurno();
                } else {
                    finJuego(finJuego);
                }

            }

        });
        botones[fila][columna] = btn;
        contenedorDeLosBotones.add(btn);

    }

    /**
     * Método que ejecuta el codigo tras el final del juego. Para ello recibe un boleano que indica si el juego ha
     * terminado.
     * @param hayLinea boolean que indica el final del juego.
     */
    private void finJuego(boolean hayLinea) {
        String simbolo = "X";
        if (!turno) {
            simbolo = "O";
        }
        for (Boton[] botone : botones) {
            for (int j = 0; j < botones.length; j++) {
                botone[j].setFocusable(false);
            }
        }
        total_activados = 0;
        if (hayLinea) {
            JOptionPane.showMessageDialog(that, "El jugador con " + simbolo + " ha ganado la partida");
        } else {
            JOptionPane.showMessageDialog(that, "Empate. Nadie ha ganado");
        }
        if (JOptionPane.showConfirmDialog(that, "¿Desea volver a jugar?") == 0) {
            resetGame();
        } else {
            that.dispose();
        }
    }

    /**
     * Método encargado de resetear el juego.
     */
    private void resetGame() {
        for(int fila = 0; fila < getFilas(); fila++) {
            for(int columna = 0; columna < getColumnas(); columna++) {
                botones[fila][columna].setText("");
                botones[fila][columna].setActivado(false);
                botones[fila][columna].setForeground(Color.BLACK);
                botones[fila][columna].setBackground(Color.WHITE);
            }
        }
        if (!turno){ cambioTurno();}
    }

    /**
     * Método que comprueba el final del juego. Si existen coincidencias entre las lineas (diagonales,
     * horizontales y verticales).
     * @return boolean
     */
    private boolean comprobarLinea() {
        boolean hayLinea = false;
        String buscar;
        int filasContador;
        int columnasContador;
        int diagonalAContador = 0;
        int diagonalBContador = 0;

        //jugador =>  [ TRUE . X ] | [ FALSE . O ]

        if (turno) {
            buscar = "X";
        } else {
            buscar = "O";
        }

        //COMPROBAMOS HORIZONTALES
        for (int fila = 0; !hayLinea && fila < botones.length; fila++) {
            filasContador = 0;
            for (int columna = 0; columna < botones[fila].length && botones[fila][columna].getText().equals(buscar); columna++) {
                filasContador++;
            }
            hayLinea = filasContador == botones[fila].length;
        }

        //COMPROBAMOS VERTICALES
        for (int columna = 0; !hayLinea && columna < botones[0].length; columna++) {
            columnasContador = 0;
            for (int fila = 0; fila < botones.length && botones[fila][columna].getText().equals(buscar); fila++) {
                columnasContador++;
            }
            hayLinea = columnasContador == botones.length;
        }

        //COMPROBAMOS DIAGONALES
        for (int fila = 0, columna = 0; !hayLinea && fila < botones.length; fila++, columna++) {
            if (botones[fila][columna].getText().equals(buscar)) {
                diagonalAContador++;
                hayLinea = diagonalAContador == botones.length;
            }
            if (!hayLinea && botones[fila][botones.length - 1 - columna].getText().equals(buscar)) {
                diagonalBContador++;
                hayLinea = diagonalBContador == botones.length;
            }
        }

        return hayLinea;
    }


    private void cambioTurno() {
        turno = !turno;
        if (turno) {
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
}
