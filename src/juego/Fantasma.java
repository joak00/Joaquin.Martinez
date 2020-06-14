package juego;

import javax.swing.JPanel;

public class Fantasma extends Personaje {
	private String sentido;
	private int visible;	//0 será invisible y 1 visible.

	Fantasma(JPanel MiJ, int x, int y){
		super(MiJ);	//Primero llamamos al constructor padre
		this.x=x;
		this.y=y;
		this.sentido="ESTE";
		visible=0;
		this.setDireccion("src/juego/imagenes/Fantasma.png");
	}

	public void movimientoFantasma() {
		this.setDireccion("src/juego/imagenes/Fantasma.png");
	}
	public String getSentido() {
		return sentido;
	}


	public void setSentido(String sentido) {
		this.sentido = sentido;
	} 

	public int getVisible() {
		return visible;
	}


	public void setVisible() {
		if (visible==0) {
			visible=1;
		}
		else {
			visible=0;
		}
	} 


}

