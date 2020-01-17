import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {

	static Font roboFont = new Font("Copperplate Gothic", Font.BOLD, 30);
	
	public static void main(String[] args) {
		(new Menu()).launchMenu();
	}
	
	public static BufferedImage loadImageFromFile(Class resourceGrabber, String filename) {
		InputStream is = resourceGrabber.getResourceAsStream("/" + filename);
		try {
			return ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage scaleImage(BufferedImage image, int width, int height) {
		BufferedImage newImage = getEmptyImage(width, height);
		Graphics g = newImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		return newImage;
	}

	public static BufferedImage getEmptyImage(int width, int height) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsConfiguration config = env.getDefaultScreenDevice().getDefaultConfiguration();
		return config.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
	}
	
	public static String[] readDocToString(String path, int length) {
		BufferedReader in = null;
		String[] returnString = new String[length];
		int i = 0;
		try {
			in = new  BufferedReader(new FileReader(path));
			while(in.ready()) {
				returnString[i] = in.readLine();
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	public static Clip getAudioClip(Class resourceGrabber, String filename) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resourceGrabber.getResource(filename));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			return clip;
		} catch(Exception e) {
			System.out.println("Unable to load audio file: " + filename);
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static int[] getMouseCoords(MouseEvent e) {
		int[] mouseCoords = new int[2];
		mouseCoords[0] = e.getXOnScreen();
		mouseCoords[1] = e.getYOnScreen();
		return mouseCoords;
	}
	
	public static double getDirectDistance(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}
	
	
}
