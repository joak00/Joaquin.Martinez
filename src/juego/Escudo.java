package juego;

import javax.swing.JPanel;

public class Escudo extends Cosas {
	

	Escudo(JPanel MiJ) {
		super(MiJ);
		// TODO Auto-generated constructor stub
	}

	Escudo(JPanel MiJ, int x, int y) {
		super(MiJ); 
		this.x = x;
		this.y = y;
	
	}

	public void posEscudo() {

		this.setPosicion("src/juego/imagenes/escudo.png");


	}
}