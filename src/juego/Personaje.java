package juego;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Personaje {
	protected int x;
	protected int y;
	private JPanel MiJP;
	private String Direccion;
	
	
	Personaje (JPanel MiJ) {
		this.x = 0;
		this.y = 0;
		this.MiJP=MiJ;
	}
	
	public int CoordX() {
		return this.x;
	}
	
	public int CoordY() {
		return this.y;
	}
	
	public String getDireccion() {return this.Direccion; }
	
	public void setDireccion(String Direccion) {this.Direccion = Direccion; }
	
	public void setCoordX(int x) { this.x = x; }
	
	public void setCoordY(int y) { this.y = y; }
	
	public JPanel getPanel() {return this.MiJP; }
	
}
