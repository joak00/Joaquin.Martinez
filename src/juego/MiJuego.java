package juego;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

//Librerias para el tiempo
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

public class MiJuego extends JFrame {

	private JPanel contentPane;
	
	Link player; // Creamos el objeto

	/**
	 * Create the frame.
	 */
	public MiJuego() {
		setTitle("Mini Juego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		
		// Creación de personajes y objetos
		player = new Link(panel, 100, 0, 0);
		//Creación de nuestro primer enemigo en la posición x=100 y=300
		Broncas broncas = new Broncas(panel, 100, 300);
		
		Topo topo = new Topo(panel, 100,100);
		Armas arma1 = new Armas(panel, 400, 150);
		Vida vida = new Vida(panel, 350, 300);
		Escudo escudo = new Escudo(panel, 100,500);
		
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon(MiJuego.class.getResource("/juego/imagenes/mapa2.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("Salud: "+player.getSalud());
		
		JLabel lblNewLabel_1 = new JLabel("Arma: "+player.getArma());
		
		JLabel lblNewLabel_2 = new JLabel("Escudo: "+player.getEscudo());
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(27)
					.addComponent(lblNewLabel_2)
					.addContainerGap(145, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				System.out.println("x-->"+player.CoordX()+",y-->"+player.CoordY());
								
				
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(player.CoordX()>0) {
						player.setCoordX(player.CoordX()-10);
						player.setDireccion("src/juego/imagenes/izquierda.png");
					}
					break;
				case KeyEvent.VK_RIGHT:
					// Le restamos 200 a la condición de movimiento para evitar
					// que el personaje desaparezca debido a la anchura del escenario
					if(player.CoordX()<(panel.getWidth()-100)) {
						player.setCoordX(player.CoordX()+10);
						player.setDireccion("src/juego/imagenes/derecha.png");
					}
					break;
				case KeyEvent.VK_UP:
					if(player.CoordY()>0) {
						player.setCoordY(player.CoordY()-10);
						player.setDireccion("src/juego/imagenes/Arriba.png");;
					}
					break;
				case KeyEvent.VK_DOWN:
					// Le restamos 200 a la condición de movimiento para evitar
					// que el personaje desaparezca debido a la anchura del escenario
					if(player.CoordY()<(panel.getWidth()-200)) {
						player.setCoordY(player.CoordY()+10);
						player.setDireccion("src/juego/imagenes/Abajo.png");
					}
					break;
				}
				
			}
		});
		
		
		//Parte del código que cada 100 milisegundos moverá a los enemigos
		Timer reloj = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Actualizamos las posiciones de los personajes
				broncas.getPanel().update(panel.getGraphics());
				player.getPanel().update(panel.getGraphics());
				topo.getPanel().update(panel.getGraphics());
				arma1.getPanel().update(panel.getGraphics());
				vida.getPanel().update(panel.getGraphics());
				escudo.getPanel().update(panel.getGraphics());
				
				//Actualizamos al personaje player
				ImageIcon MiImagen = new ImageIcon(player.getDireccion());
				panel.getGraphics().drawImage(MiImagen.getImage(), player.CoordX(), player.CoordY(), panel);
				
				//Actualizamos al personaje Broncas
				broncas.movimientoBroncas();
				ImageIcon ImagenBroncas = new ImageIcon(broncas.getDireccion());
				panel.getGraphics().drawImage(ImagenBroncas.getImage(), broncas.CoordX(), broncas.CoordY(), panel);
				
				//Actualizamos al personaje Topo
				topo.movimientoTopo();
				ImageIcon ImagenTopo = new ImageIcon(topo.getDireccion());
				panel.getGraphics().drawImage(ImagenTopo.getImage(), topo.CoordX(), topo.CoordY(), panel);
				
				//Actualizamos armas
				arma1.posArmas();
				ImageIcon ImagenArma1 = new ImageIcon(arma1.getPosicion());
				panel.getGraphics().drawImage(ImagenArma1.getImage(), arma1.CoordX(), arma1.CoordY(), panel);
				
				//Actualizamos vida
				vida.posVida();
				ImageIcon ImagenVida = new ImageIcon(vida.getPosicion());
				panel.getGraphics().drawImage(ImagenVida.getImage(), vida.CoordX(), vida.CoordY(), panel);
				
				//Actualizamos escudo
				escudo.posEscudo();
				ImageIcon ImagenEscudo = new ImageIcon(escudo.getPosicion());
				panel.getGraphics().drawImage(ImagenEscudo.getImage(), escudo.CoordX(), escudo.CoordY(), panel);
				
				//Linea para evitar parpadeos
				panel.getGraphics().drawImage(null, 10, 80, null);
				
				if(player.x == arma1.x && player.y == arma1.y) {
					player.setArma(player.getArma()+50);
					lblNewLabel_1.setText("Arma: "+String.valueOf(player.getArma()));
					arma1.setCoordX(-100);
					arma1.setCoordY(-100);
				}
				
				if(player.x == broncas.x && player.y == broncas.y) {
					player.setSalud(player.getSalud()-20);
					lblNewLabel.setText("Salud: "+String.valueOf(player.getSalud()));
				}
				
				if(player.x == topo.x && player.y == topo.y) {
					player.setSalud(player.getSalud()-10);
					lblNewLabel.setText("Salud: "+String.valueOf(player.getSalud()));
				}
				
				if(player.x == vida.x && player.y == vida.y) {
					player.setSalud(player.getSalud()+30);
					lblNewLabel.setText("Salud: "+String.valueOf(player.getSalud()));
					vida.setCoordX(-100);
					vida.setCoordY(-100);
				}
				
				if(player.x == escudo.x && player.y == escudo.y) {
					player.setEscudo(player.getEscudo()+50);
					lblNewLabel_2.setText("Escudo: "+String.valueOf(player.getEscudo()));
					escudo.setCoordX(-100);
					escudo.setCoordY(-100);
				}
				
			}
		});
		reloj.start();
		
	}
	
}
