package juego;

import javax.swing.JPanel;


	public class Cosas {
		protected int x;
		protected int y;
		private JPanel MiJP;
		private String posicion;
		
		
		Cosas (JPanel MiJ) {
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
		
		public String getPosicion() {return this.posicion; }
		
		public void setPosicion(String posicion) {this.posicion = posicion; }
		
		public void setCoordX(int x) { this.x = x; }
		
		public void setCoordY(int y) { this.y = y; }
		
		public JPanel getPanel() {return this.MiJP; }
		
}


