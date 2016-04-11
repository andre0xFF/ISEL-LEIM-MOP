package Arena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arena extends JPanel {
	private static final int MAX_X = 400;
	private static final int MAX_Y = 400;
	private static final int POS_INIT_X = 200;
	private static final int POS_INIT_Y = 200;
	private static final int NUM_ACTORS = 3;
	
	private Actor[] actors = new Actor[NUM_ACTORS];
	
	public Arena() {
		Random r = new Random();
		
		for (int i = 0; i < actors.length; i++) {
			actors[i] = new Actor();
			actors[i].setSize(100);
			actors[i].setX(r.nextInt(MAX_X - actors[i].getSize()));
			actors[i].setY(r.nextInt(MAX_Y - actors[i].getSize()));
			actors[i].setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D draw = (Graphics2D) g;
		
		for (int i = 0; i < actors.length; i++) {
			draw.setColor(actors[i].getColor());
			draw.fillRect(actors[i].getX(), actors[i].getY(), actors[i].getSize(), actors[i].getSize());
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Arena arena = new Arena();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(arena);
		frame.setSize(MAX_X, MAX_Y);
		frame.setLocation(POS_INIT_X, POS_INIT_Y);
		frame.setVisible(true);
		
		while (true) {
			arena.repaint();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
