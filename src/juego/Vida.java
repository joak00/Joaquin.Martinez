package juego;

import javax.swing.JPanel;

public class Vida extends Cosas {
	

	Vida(JPanel MiJ) {
		super(MiJ);
		// TODO Auto-generated constructor stub
	}

	Vida(JPanel MiJ, int x, int y) {
		super(MiJ); 
		this.x = x;
		this.y = y;
	
	}

	public void posVida() {

		this.setPosicion("src/juego/imagenes/vida2.png");


	}
}
