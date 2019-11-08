package adaptador;

import controlador.Controlador;
import vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Adaptador implements ActionListener {

    private Controlador controlador;
    private VentanaPrincipal vista;

    /**
     * Consructor por defecto.
     * @param controlador Controlador
     * @param vista Vista del usuario Ventana principal
     */
    public Adaptador(Controlador controlador, VentanaPrincipal vista) {
        this.controlador = controlador;
        this.vista = vista;
    }

    public Controlador getControlador() {
        return controlador;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ArrayList<Integer> position = vista.obtenerCoordenadas(actionEvent);
        controlador.peticion(position);
        if (!controlador.getM().isColocar()){
            vista.actualizar(controlador.getM().getTurno());
            vista.dejarTodoigual();
        }
        vista.finJuego(controlador.getM().esFinJuego());
    }
}
