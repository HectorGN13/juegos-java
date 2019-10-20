import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VentanaJuego extends JFrame {

	private VentanaJuego that;
	private JPanel contenedorBotones;
	private int filas = 4;
	private int columnas = 4;
	private ArrayList<Boton> botones;
	private Font fuente;

	public VentanaJuego() throws HeadlessException {
		cofigurarVentana();
		inicializarComponentes();
		that = this;
	}

	// Getter

	public JPanel getContenedorBotones() {
		return contenedorBotones;
	}

	//Setter

	public void setContenedorBotones(JPanel contenedorBotones) {
		this.contenedorBotones = contenedorBotones;
	}

	public void inicializarComponentes() {
		//Crear Componentes

		contenedorBotones = new JPanel();
		botones = new ArrayList<Boton>();
		fuente = new Font("Dialog", Font.BOLD, 34);
		int i = 1;
		while (botones.size() < (filas*columnas)) {
			Boton boton = new Boton(i+"");
			boton.setFont(fuente);
			boton.setBackground(Color.PINK);
			botones.add(boton);

			boton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Boton lanzador = (Boton)e.getSource();
					int p_lanzador = -1;
					int p_oculto = -1;
					for (int j = 0; j < botones.size(); j++) {
						if(botones.get(j).getText().equals("16")){
							p_oculto = j;
						}else if(botones.get(j).getText().equals(lanzador.getText())){
							p_lanzador = j;
						}
					}

					if(p_lanzador+columnas == p_oculto || p_lanzador-columnas == p_oculto || p_lanzador-1 == p_oculto || p_lanzador+1 == p_oculto){
						String texto = botones.get(p_lanzador).getText();
						botones.get(p_lanzador).setText(botones.get(p_oculto).getText());
						botones.get(p_lanzador).setVisible(false);

						botones.get(p_oculto).setText(texto);
						botones.get(p_oculto).setVisible(true);
					}

					if(hasGanado(botones)){
						JOptionPane.showMessageDialog(that, "¡Has ganado!");
					}
				}

			});
			if(i == 16){
				boton.setVisible(false);
			}
			i++;
		}



		//Configurar Componentes

		Collections.shuffle(botones);

		this.setLayout(new BorderLayout());
		contenedorBotones.setLayout(new GridLayout(filas,columnas));
		agregarBotones(contenedorBotones, botones);

		//Añadir Componentes

		this.add(contenedorBotones, BorderLayout.CENTER);



	}

	public boolean hasGanado(List<Boton> lista){
		boolean todo_OK = true;
		for(int i = 1; todo_OK && i < lista.size(); i++){
			if(!lista.get(i-1).getText().equals(i+"")){
				todo_OK = false;
			}
		}
		return todo_OK;
	}

	public void cofigurarVentana() {
		this.setTitle("15 PUZZLE");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void agregarBotones (JPanel contenedor, ArrayList<Boton> botones) {
		contenedor.removeAll();
		for (int i = 0; i < botones.size(); i++) {
			contenedor.add(botones.get(i));
		}
	}
}
