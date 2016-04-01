package tps.tp1.pack2Ciclos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Classe que � a frame onde tud ocorre 
 *
 */
public class P03ColorFrame extends JFrame {
	private static final long serialVersionUID = -330888082383077655L;

	private MyLabel label1;
	private JButton bnColorStart;
	private JButton bnColorEnd;

	/**
	 * Method that creates the frame
	 */
	protected void init() {
		setTitle("...: ColorFrame :...");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// panel central
		JPanel jpCentral = new JPanel(new ProportionalLayout(0.1f));
		jpCentral.setBackground(Color.ORANGE);
		jpCentral.setOpaque(true);
		add(jpCentral, BorderLayout.CENTER);

		// label central
		label1 = new MyLabel();
		jpCentral.add(label1, ProportionalLayout.CENTER);

		// buttons panel
		JPanel jpButtons = new JPanel();
		add(jpButtons, BorderLayout.SOUTH);

		// button start color
		bnColorStart = new JButton("Start color");
		bnColorStart.setBackground(Color.GREEN);
		bnColorStart.setOpaque(true);
		bnColorStart.setHorizontalAlignment(SwingConstants.CENTER);
		jpButtons.add(bnColorStart);

		// button end color
		bnColorEnd = new JButton("End color");
		bnColorEnd.setBackground(Color.MAGENTA);
		bnColorEnd.setOpaque(true);
		bnColorEnd.setHorizontalAlignment(SwingConstants.CENTER);
		jpButtons.add(bnColorEnd);

		// listener start color button
		bnColorStart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(P03ColorFrame.this,
						"Choose Start Color", bnColorStart.getBackground());
				if (newColor != null) {
					bnColorStart.setBackground(newColor);
					label1.repaint();
				}
			}
		});

		// listener end color button
		bnColorEnd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(P03ColorFrame.this,
						"Choose End Color", bnColorEnd.getBackground());
				if (newColor != null) {
					bnColorEnd.setBackground(newColor);
					label1.repaint();
				}
			}
		});

		// set frame visible
		setVisible(true);
	}

	/**
	 * Main method - the execution starts here
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				P03ColorFrame frame = new P03ColorFrame();
				frame.init();
			}
		});
	}

	/**
	 * Auxiliary class that extends JLabel
	 */
	class MyLabel extends JLabel {
		private static final long serialVersionUID = -4402584053051810107L;

		/**
		 * Method that paints the label
		 */
		public void paint(Graphics g) {
			super.paint(g);
			System.out.println("Paint...");

			drawColors(g, getWidth(), getHeight(),
					bnColorStart.getBackground(), bnColorEnd.getBackground());
		}

		/**
		 * Should draw all the drawing area with lines with color varying from
		 * StartColor to EndColor. To change the drawing color use:
		 * Graphics.setColor(Color newColor). To draw a line use:
		 * Graphics.drawLine(int x1, int y1, int x2, int y2)
		 * 
		 * @param g
		 *            the graphics where we should draw the lines
		 * @param dimX
		 *            x dimension of drawing area
		 * @param dimY
		 *            y dimension of drawing area
		 * @param startColor
		 *            start color, should be at left
		 * @param endColor
		 *            end color, should be at right
		 */
		private void drawColors(Graphics g, int dimX, int dimY, Color startColor, Color endColor) {
			int[] delta = new int[] {
					endColor.getRed() - startColor.getRed(),
					endColor.getGreen() - startColor.getGreen(),
					endColor.getBlue() - startColor.getBlue()
			};
			
			double[] step = new double[] {
					(double) delta[0] / dimY,
					(double) delta[1] / dimY,
					(double) delta[2] / dimY
			};
			
			double[] currentPixel = new double[] {
					startColor.getRed(),
					startColor.getGreen(),
					startColor.getBlue()
			};
			
			for(int line = 1; line <= dimY; line++) {
				currentPixel[0] += step[0];
				currentPixel[1] += step[1];				
				currentPixel[2] += step[2];
				
				g.setColor(new Color((int)currentPixel[0], (int)currentPixel[1], (int)currentPixel[2]));
				g.drawLine(0, line, 255, line);
				
				System.out.printf("{%d} %d %d %d\n", line, (int)currentPixel[0], (int)currentPixel[1], (int)currentPixel[2]);
			}
		}
	}

}
