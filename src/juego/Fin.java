package juego;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fin extends JDialog {
	
	private JLabel label, logo;
	
	public Fin() {
		
		setLayout(null);
		setBounds(200,150,500,300);
		
		logo = new JLabel();
		logo.setIcon(new ImageIcon("src/juego/imagenes/gameover.png"));
		logo.setBounds(50, 10, 400, 200);
		add(logo);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Fin del Juego");
	
		
	}
	
}