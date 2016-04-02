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
 * A classe P02Triangulo corresponde ao 3o exercicio do capitulo de Ciclos da serie de
 * exercicios. Esta classe foi previamente fornecida para a realizacao do exercicio.<p>
 * @author Andre Fonseca
 */
public class P03ColorFrame extends JFrame {
	private static final long serialVersionUID = -330888082383077655L;

	private MyLabel label1;
	private JButton bnColorStart;
	private JButton bnColorEnd;

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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				P03ColorFrame frame = new P03ColorFrame();
				frame.init();
			}
		});
	}

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
		 * Desenha linhas em toda a area de desenho inicialmente com a cor inicial e
		 * progressivamente alterando-se para a cor final.<p>
		 * Inicialmente e' calculado a diferenca de RGB entre a cor inicial e a cor
		 * final. O incremento e' calculado com base nessa diferenca e a dimensao da
		 * area de desenho.<p>
		 * Para desenhar a area de desenho completa e' feito um ciclo que ira percorrer
		 * todas as linhas da area de desenho, iniciando-se com a cor inicial que sera
		 * incrementada linha a linha com o salto calculado ate chegar ao final da
		 * area de desenho com a cor final.
		 * 
		 * @param g
		 *            the graphics where we should draw the lines
		 * @param dimX
		 *            Dimensao x da area de desenho
		 * @param dimY
		 *            Dimensao y da area de desenho
		 * @param startColor
		 *            Cor inicial
		 * @param endColor
		 *            Cor final
		 */
		protected void drawColors(Graphics g, int dimX, int dimY, Color startColor, Color endColor) {
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
