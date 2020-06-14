package juego;

import java.awt.BorderLayout;
import java.awt.Color;
//Librerias para el tiempo
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MiJuego extends JFrame {

	private JPanel contentPane;
	private Timer reloj;

	Link player; // Creamos el objeto

	/**
	 * Create the frame.
	 */
	public MiJuego() {
		Clip sonido = null;
		try {
			sonido = AudioSystem.getClip();
		//Se carga con un fichero wav
			sonido.open(AudioSystem.getAudioInputStream(new File("src/juego/ringtones-tema-zelda.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Comienza la reproducción
		sonido.start();
		sonido.loop(100);
		
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

		// Creación de nuestro primer enemigo en la posición x=100 y=300
		Broncas broncas = new Broncas(panel, 100, 300);

		Topo topo = new Topo(panel, 100, 100);
		Armas arma1 = new Armas(panel, 400, 150);
		Vida vida = new Vida(panel, 350, 300);
		Escudo escudo = new Escudo(panel, 100, 500);
		Fantasma ghost = new Fantasma(panel, 200, 200);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon(MiJuego.class.getResource("/juego/imagenes/mapa2.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3,
				GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);

		JLabel lblNewLabel = new JLabel("Salud: " + player.getSalud());

		JLabel lblNewLabel_1 = new JLabel("Arma: " + player.getArma());

		JLabel lblNewLabel_2 = new JLabel("Escudo: " + player.getEscudo());

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1).addComponent(lblNewLabel_2))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(19).addComponent(lblNewLabel).addGap(18)
						.addComponent(lblNewLabel_1).addGap(27).addComponent(lblNewLabel_2)
						.addContainerGap(145, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				System.out.println("x-->" + player.CoordX() + ",y-->" + player.CoordY());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (player.CoordX() > 0) {
						player.setCoordX(player.CoordX() - 10);
						player.setDireccion("src/juego/imagenes/izquierda.png");
					}
					break;
				case KeyEvent.VK_RIGHT:
					// Le restamos 200 a la condición de movimiento para evitar
					// que el personaje desaparezca debido a la anchura del escenario
					if (player.CoordX() < (panel.getWidth() - 100)) {
						player.setCoordX(player.CoordX() + 10);
						player.setDireccion("src/juego/imagenes/derecha.png");
					}
					break;
				case KeyEvent.VK_UP:
					if (player.CoordY() > 0) {
						player.setCoordY(player.CoordY() - 10);
						player.setDireccion("src/juego/imagenes/Arriba.png");
						;
					}
					break;
				case KeyEvent.VK_DOWN:
					// Le restamos 200 a la condición de movimiento para evitar
					// que el personaje desaparezca debido a la anchura del escenario
					if (player.CoordY() < (panel.getWidth() - 200)) {
						player.setCoordY(player.CoordY() + 10);
						player.setDireccion("src/juego/imagenes/Abajo.png");
					}
					break;
				}

			}
		});

		// Parte del código que cada 100 milisegundos moverá a los enemigos
		reloj = new Timer(100, new ActionListener() {

			int contTiempo = 0;
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				contTiempo = (contTiempo + reloj.getDelay()) % 5000;
			
				//Condicional de muerte
				if(player.getSalud() <= 0) {
					Fin f = new Fin();
					f.setVisible(true);
				
				}

				// Actualizamos las posiciones de los personajes
				broncas.getPanel().update(panel.getGraphics());
				player.getPanel().update(panel.getGraphics());
				topo.getPanel().update(panel.getGraphics());
				ghost.getPanel().update(panel.getGraphics());
				// Y las posiciones de los objetos
				arma1.getPanel().update(panel.getGraphics());
				vida.getPanel().update(panel.getGraphics());
				escudo.getPanel().update(panel.getGraphics());

				// Actualizamos al personaje player
				ImageIcon MiImagen = new ImageIcon(player.getDireccion());
				panel.getGraphics().drawImage(MiImagen.getImage(), player.CoordX(), player.CoordY(), panel);

				// Actualizamos al personaje Broncas
				broncas.movimientoBroncas();
				ImageIcon ImagenBroncas = new ImageIcon(broncas.getDireccion());
				panel.getGraphics().drawImage(ImagenBroncas.getImage(), broncas.CoordX(), broncas.CoordY(), panel);

				// Actualizamos al personaje Topo
				topo.movimientoTopo();
				ImageIcon ImagenTopo = new ImageIcon(topo.getDireccion());
				panel.getGraphics().drawImage(ImagenTopo.getImage(), topo.CoordX(), topo.CoordY(), panel);

				// Actualizamos al personaje Fantasma
				if (contTiempo == 0) {
					ghost.setVisible();
					Random r = new Random();
					int valorX = r.nextInt(650);
					int valorY = r.nextInt(460);
					ghost.setCoordX(valorX);
					ghost.setCoordY(valorY);
				
				}
				if (ghost.getVisible() == 1) {
					ghost.movimientoFantasma();
					ImageIcon ImagenFantasma = new ImageIcon(ghost.getDireccion());
					panel.getGraphics().drawImage(ImagenFantasma.getImage(), ghost.CoordX(), ghost.CoordY(), panel);
					
				}

				// Actualizamos armas
				arma1.posArmas();
				ImageIcon ImagenArma1 = new ImageIcon(arma1.getPosicion());
				panel.getGraphics().drawImage(ImagenArma1.getImage(), arma1.CoordX(), arma1.CoordY(), panel);

				// Actualizamos vida
				vida.posVida();
				ImageIcon ImagenVida = new ImageIcon(vida.getPosicion());
				panel.getGraphics().drawImage(ImagenVida.getImage(), vida.CoordX(), vida.CoordY(), panel);

				// Actualizamos escudo
				escudo.posEscudo();
				ImageIcon ImagenEscudo = new ImageIcon(escudo.getPosicion());
				panel.getGraphics().drawImage(ImagenEscudo.getImage(), escudo.CoordX(), escudo.CoordY(), panel);

				// Linea para evitar parpadeos
				panel.getGraphics().drawImage(null, 10, 80, null);

				
				
				/** COLISIONES **/
				//Broncas
				int hiddenBox1 = 100; // Variable creada para regular el tamaño de la caja oculta
				if ((player.CoordX() >= broncas.CoordX() - hiddenBox1
						&& player.CoordX() <= broncas.CoordX() + hiddenBox1)
						&& ((player.CoordY() >= broncas.CoordY() - hiddenBox1
								&& player.CoordY() <= broncas.CoordY() + hiddenBox1))) {

					System.out.println("¡Player está tocando a broncas!");

					if (player.getArma() == 50) {
						broncas.setCoordX(-200);
						broncas.setCoordY(-200);
						
					} else if (player.getEscudo() > 0) {
						player.setEscudo(player.getEscudo() - 3);
						lblNewLabel_2.setText("Escudo: " + player.getEscudo());
					}

					else if (player.getEscudo() <= 0 && player.getSalud() > 0) {
						player.setSalud(player.getSalud() - 2);
						lblNewLabel.setText("Salud: " + player.getSalud());
					}

					else if (player.getSalud() <= 0) {
						reloj.stop();
						dispose();
					}
				}

				//Topo
				int hiddenBox2 = 80; // Variable creada para regular el tamaño de la caja oculta
				if ((player.CoordX() >= topo.CoordX() - hiddenBox2 && player.CoordX() <= topo.CoordX() + hiddenBox2)
						&& ((player.CoordY() >= topo.CoordY() - hiddenBox2
								&& player.CoordY() <= topo.CoordY() + hiddenBox2))) {

					System.out.println("¡Player está tocando a topo!");

					if (player.getArma() == 50) {
						topo.setCoordX(-200);
						topo.setCoordY(-200);
					}

					else if (player.getEscudo() > 0) {
						player.setEscudo(player.getEscudo() - 3);
						lblNewLabel_2.setText("Escudo: " + player.getEscudo());
					}

					else if (player.getEscudo() <= 0 && player.getSalud() > 0) {
						player.setSalud(player.getSalud() - 2);
						lblNewLabel.setText("Salud: " + player.getSalud());
					}

					else if (player.getSalud() <= 0) {
						reloj.stop();
						dispose();
					}
				}

				
				//Ghost
				hiddenBox1 = 100; // Variable creada para regular el tamaño de la caja oculta
				if ((player.CoordX() >= ghost.CoordX() - hiddenBox1 && player.CoordX() <= ghost.CoordX() + hiddenBox1)
						&& ((player.CoordY() >= ghost.CoordY() - hiddenBox1
								&& player.CoordY() <= ghost.CoordY() + hiddenBox1))) {

					System.out.println("¡Player está tocando a ghost!");

					if (player.getArma() == 50) {
						ghost.setCoordX(-200);
						ghost.setCoordY(-200);
						contTiempo = 9999999;
						
						
					} else if (player.getEscudo() > 0) {
						player.setEscudo(player.getEscudo() - 3);
						lblNewLabel_2.setText("Escudo: " + player.getEscudo());
					}

					else if (player.getEscudo() <= 0 && player.getSalud() > 0) {
						player.setSalud(player.getSalud() - 2);
						lblNewLabel.setText("Salud: " + player.getSalud());
					}

					else if (player.getSalud() <= 0) {
						reloj.stop();
						dispose();
					}
				}

				// Arma
				int hiddenBox3 = 50; // Variable creada para regular el tamaño de la caja oculta
				if ((player.CoordX() >= arma1.CoordX() - hiddenBox1 && player.CoordX() <= arma1.CoordX() + hiddenBox3)
						&& ((player.CoordY() >= arma1.CoordY() - hiddenBox3
								&& player.CoordY() <= arma1.CoordY() + hiddenBox3))) {

					System.out.println("¡Player está tocando a arma1!");
					player.setArma(player.getArma() + 50);
					lblNewLabel_1.setText("Arma: " + String.valueOf(player.getArma()));
					arma1.setCoordX(-200);
					arma1.setCoordY(-200);
				}

				// Vida
				hiddenBox3 = 50; // Variable creada para regular el tamaño de la caja oculta
				if ((player.CoordX() >= vida.CoordX() - hiddenBox1 && player.CoordX() <= vida.CoordX() + hiddenBox3)
						&& ((player.CoordY() >= vida.CoordY() - hiddenBox3
								&& player.CoordY() <= vida.CoordY() + hiddenBox3))) {

					System.out.println("¡Player está tocando a vida!");
					player.setSalud(player.getSalud() + 50);
					lblNewLabel.setText("Salud: " + String.valueOf(player.getSalud()));
					vida.setCoordX(-200);
					vida.setCoordY(-200);
				}

				// Escudo
				hiddenBox3 = 50; // Variable creada para regular el tamaño de la caja oculta
				if ((player.CoordX() >= escudo.CoordX() - hiddenBox1 && player.CoordX() <= escudo.CoordX() + hiddenBox3)
						&& ((player.CoordY() >= escudo.CoordY() - hiddenBox3
								&& player.CoordY() <= escudo.CoordY() + hiddenBox3))) {

					System.out.println("¡Player está tocando a escudo!");
					player.setEscudo(player.getEscudo() + 50);
					lblNewLabel_2.setText("Escudo: " + String.valueOf(player.getEscudo()));
					escudo.setCoordX(-200);
					escudo.setCoordY(-200);
				}
				
			}
			
		});
		reloj.start();

		
		
	}

}
