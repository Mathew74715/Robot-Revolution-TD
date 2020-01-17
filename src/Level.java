import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Level {
	

	
	static String[][] gameBoard = new String[20][20];
	
	public void startLevel() {
		
		String[] map = Main.readDocToString("assets/Map.txt", 20);
		for (int i = 0; i < gameBoard.length; i++) {
			gameBoard[i] = map[i].split(" "); 
		}
		
		JFrame level = new JFrame("Robot Revolution TD");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		level.setSize((int)(d.getWidth()), (int)(d.getHeight()));
		JPanel b = (JPanel) level.getContentPane();
		b.add(new board(level));
		
		//Fullscreen
		level.setExtendedState(JFrame.MAXIMIZED_BOTH);
		level.setUndecorated(true);
		level.setVisible(true);

		level.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

				switch(e.getKeyCode()) {
				
				case KeyEvent.VK_1:
					
					break;
				case KeyEvent.VK_2:
					
					break;
				case KeyEvent.VK_3:
					
					break;
				case KeyEvent.VK_4:
					
					break;
				case KeyEvent.VK_5:
					
					break;
				default:
					System.out.println("Stop being dumb mathew");
				
				}
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

}
class board extends JComponent {
	
	public Clip menuMusic1 = Main.getAudioClip(getClass(), "MenuMusic1.wav");
	public Clip menuMusic2 = Main.getAudioClip(getClass(), "MenuMusic2.wav");
	public Clip gameMusic1 = Main.getAudioClip(getClass(), "DragonCastle.wav");
	public Clip gameMusic2 = Main.getAudioClip(getClass(), "Guardians.wav");
	public Clip gameMusic3 = Main.getAudioClip(getClass(), "Raid.wav");
	public int lastPlayed;
	BufferedImage grass = Main.loadImageFromFile(getClass(), "GrassBlock.jpeg");
	BufferedImage path = Main.loadImageFromFile(getClass(), "PathBlock.jpg");
	BufferedImage base = Main.loadImageFromFile(getClass(), "BaseBlock.jpg");
	BufferedImage gameBar = Main.loadImageFromFile(getClass(), "GameBar.png");
		
	Timer t = new Timer(1000/60, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Graphics g;
			update();
		}
	});
	
	private JFrame level;
	
	public board(JFrame level) {
		this.level = level;
		createImages();
		t.start();
	}
	
	public void update() {
		if (!(gameMusic1.isActive() || gameMusic2.isActive() || gameMusic3.isActive())) {
			switch (lastPlayed) {
				case 1:
					gameMusic1.stop();
					gameMusic2.start();
					lastPlayed = 2;
					break;
				case 2:
					gameMusic2.stop();
					gameMusic3.start();
					lastPlayed = 3;
					break;
				case 3:
					gameMusic3.stop();
				default:
					gameMusic1.start();
					lastPlayed = 1;
			}
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		for (int i = 0; i < Level.gameBoard.length; i++) {
			for (int j = 0; j < Level.gameBoard[0].length; j++) {
				switch (Level.gameBoard[i][j]) {
					case "g":
						g.drawImage(grass, (int) (j * (level.getWidth()/ 20)), (int) (i * (level.getHeight() / 20)), null);
						break;
					case "p":
						g.drawImage(path, (int) (j * (level.getWidth()/ 20)), (int) (i * (level.getHeight() / 20)), null);
						break;
					case "b":
						g.drawImage(base, (int) (j * (level.getWidth()/ 20)), (int) (i * (level.getHeight() / 20)), null);
						break;
				}
			}
		}
		g.drawImage(gameBar, 0, (int)(level.getHeight() * (9.0 / 10)), null);
		
	}
	
	public void createImages() {
		grass = Main.scaleImage(grass, level.getWidth() / 20, level.getHeight() / 20);
		path = Main.scaleImage(path, level.getWidth() / 20, level.getHeight() / 20);
		base = Main.scaleImage(base, level.getWidth() / 20, level.getHeight() / 20);
		gameBar = Main.scaleImage(gameBar, level.getWidth(), level.getHeight() / 10);
	}
	
}
class Enemy extends JComponent {
	
	int enemyHealth;
	Timer enemySpeed = new Timer(1000/60, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			update();
		}
	});
	
	private JFrame level;
	
	public Enemy(JFrame level, int health) {
		this.level = level;
		enemySpeed.start();
		enemyHealth = health;
	}
	
	public void update() {
		repaint();
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		
	}
	
	
	
}