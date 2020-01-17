import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu {
	
	public Clip menuMusic1 = Main.getAudioClip(getClass(), "MenuMusic1.wav");
	public Clip menuMusic2 = Main.getAudioClip(getClass(), "MenuMusic2.wav");
	public Clip gameMusic1 = Main.getAudioClip(getClass(), "DragonCastle.wav");
	public Clip gameMusic2 = Main.getAudioClip(getClass(), "Guardians.wav");
	public Clip gameMusic3 = Main.getAudioClip(getClass(), "Raid.wav");
	public static int[] mouseCoords = new int[2];
	
	JFrame menu = new JFrame("Robot Revolution TD");
	
	public void launchMenu() {
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		menu.setSize((int)(d.getWidth()), (int)(d.getHeight()));
		JPanel b = (JPanel) menu.getContentPane();
		b.add(new menuScreen(menu));
		menuMusic2.start();
		menu.addKeyListener(keyListener);
		
		//Fullscreen
		menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		menu.setUndecorated(true);
		menu.setVisible(true);
	}
	
		KeyListener keyListener = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {

			switch(e.getKeyCode()) {
			
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				case KeyEvent.VK_ENTER:
					(new Level()).startLevel();
					menuMusic2.stop();
					menu.dispose();
					break; 
				case KeyEvent.VK_R:
					
					break;
				default:
					
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
	static MouseListener mouseListener = new MouseListener() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			mouseCoords = Main.getMouseCoords(e);
			System.out.println("X = " + mouseCoords[0] + " Y = " + mouseCoords[1]);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	
	
	
}

class menuScreen extends JComponent {
	
	private JFrame stage;
	
	public menuScreen(JFrame stage) {
		this.stage = stage;
		repaint();
	}
	
	public void update() {
//		if () {
//			
//		}
	}
	
	public void paint(Graphics g) {
		update();
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, stage.getWidth(), stage.getHeight());
		BufferedImage menuBackdrop = Main.loadImageFromFile(getClass(), "MenuScreenBackdrop.jpg");
		menuBackdrop = Main.scaleImage(menuBackdrop, (int)(stage.getHeight() * 1.3333333), stage.getHeight());
		g.drawImage(menuBackdrop, (int)((stage.getWidth() - (int)(stage.getHeight() * 1.3333333)) / 2), 0, null);
	}
}