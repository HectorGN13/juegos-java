import javax.swing.*;

public class Boton extends JButton {

	private boolean oculto;

	//Constructor

	// Getter
	public boolean isOculto() {
		return oculto;
	}

	//setter
	public Boton(String text) {
		super(text);
	}
}