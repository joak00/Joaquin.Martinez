package juego;

import javax.swing.JPanel;

public class Armas extends Cosas {
		

	Armas(JPanel MiJ) {
		super(MiJ);
			// TODO Auto-generated constructor stub
	}

	Armas(JPanel MiJ, int x, int y) {
		super(MiJ); 
		this.x = x;
		this.y = y;
		
	}

	public void posArmas() {
	
	this.setPosicion("src/juego/imagenes/espada.png");
	
	
	}

}
