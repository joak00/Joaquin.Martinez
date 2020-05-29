package juego;

import javax.swing.JPanel;

public class Topo extends Personaje {

	private String Sentido;
	
	Topo(JPanel MiJ) {
		super(MiJ);
		// TODO Auto-generated constructor stub
	}
	
	Topo(JPanel MiJ, int x, int y) {
		super(MiJ); 
		this.x = x;
		this.y = y;
		this.Sentido="SUR";
	}
	
	public void movimientoTopo() {
		if(this.y<0) {
			this.Sentido = "SUR";
			this.y = this.y+10;
			this.setDireccion("src/juego/imagenes/Topo_abajo.png");
		}
		
		//Le restamos 200 a la condición de movimiento para evitar que el 
		//personaje desaparezca debido a la anchura del escenario
		else if(this.y>this.getPanel().getHeight()-100) {
			this.Sentido="NORTE";
			this.y = this.y-10;
			this.setDireccion("src/juego/imagenes/Topo_arriba.png");
		}
		
		else if (this.Sentido.equals("SUR")) {
			this.y=this.y+10;
			this.setDireccion("src/juego/imagenes/Topo_abajo.png");
		}
		
		else if(this.Sentido.equals("NORTE")) {
			this.y=this.y-10;
			this.setDireccion("src/juego/imagenes/Topo_arriba.png");
		}
	}

}



