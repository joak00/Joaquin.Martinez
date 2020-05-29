package juego;

import javax.swing.JPanel;

public class Broncas extends Personaje {

	private String Sentido;
	
	
	Broncas(JPanel MiJ, int x, int y) {
		super(MiJ); 
		this.x = x;
		this.y = y;
		this.Sentido="ESTE";
	}
	
	public void movimientoBroncas() {
		if(this.x<0) {
			this.Sentido = "ESTE";
			this.x = this.x+10;
			this.setDireccion("src/juego/imagenes/Broncas_Derecha.png");
		}
		
		//Le restamos 200 a la condición de movimiento para evitar que el 
		//personaje desaparezca debido a la anchura del escenario
		else if(this.x>this.getPanel().getWidth()-100) {
			this.Sentido="OESTE";
			this.x = this.x-10;
			this.setDireccion("src/juego/imagenes/Broncas_Izquierda.png");
		}
		
		else if (this.Sentido.equals("ESTE")) {
			this.x=this.x+10;
			this.setDireccion("src/juego/imagenes/Broncas_Derecha.png");
		}
		
		else if(this.Sentido.equals("OESTE")) {
			this.x=this.x-10;
			this.setDireccion("src/juego/imagenes/Broncas_Izquierda.png");
		}
	}

}
