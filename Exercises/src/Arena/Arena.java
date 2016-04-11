package Arena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arena extends JPanel {
	private static final int MAX_X = 400;
	private static final int MAX_Y = 400;
	private static final int POS_INIT_X = 200;
	private static final int POS_INIT_Y = 200;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D draw = (Graphics2D) g;
		
		Color color = new Color(150, 150, 150);
		draw.setColor(color);
		
		draw.fillRect(10, 10, 100, 100);
		
		color = new Color(255, 0, 0);
		draw.setColor(color);
		
		draw.fillRect(100, 100, 50, 50);
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
