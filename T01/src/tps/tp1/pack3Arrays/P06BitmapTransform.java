package tps.tp1.pack3Arrays;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * A classe P06BitmapTransform corresponde ao 6o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe foi fornecida previamente de forma a realizar o exercicio.
 * <p>
 * Pretende-se que uma determinada imagem seja redimensionada para metade do seu tamanho
 * atraves da media RGB entre pixeis.
 * 
 * @author Andre Fonseca
 */
public class P06BitmapTransform {

	public static BufferedImage copyImage(BufferedImage image) {
		BufferedImage newImage = null;
		newImage = new BufferedImage(image.getWidth(), image.getHeight(),
				image.getType());

		int height = image.getHeight();
		int width = image.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixelRGB = image.getRGB(x, y);
				int newPixelColor = pixelRGB;

				// TODO testar com uma s� linha activada, entre as seguintes
				newPixelColor = pixelRGB & 0xFF; // azul no m�ximo
				// newPixelColor = pixelRGB & 0xFF00; // verde no m�ximo
				// newPixelColor = pixelRGB & 0xFF0000; // vermelho no m�ximo

				newImage.setRGB(x, y, newPixelColor);
			}
		}
		return newImage;
	}

	/**
	 * Reduz o tamanho de uma imagem para metade atraves da media RGB entre pixeis.
	 * A reducao da imagem e' realizada percorrendo os pixeis da nova imagem a ser gerada,
	 * em que cada pixel ira conter a media da respectiva componente RGB de um conjunto
	 * de pixeis da imagem original.
	 * 
	 * @param image
	 * 				Imagem a ser reduzida
	 * @return
	 * 				Imagem reduzida
	 */
	public static BufferedImage reduceImage(BufferedImage image) {
		int factor = 2;
		
		BufferedImage newImage = new BufferedImage(image.getWidth() / factor, image.getHeight() / factor,
				image.getType());
		
		for (int y = 0; y < image.getHeight() / factor; y++) {
			for (int x = 0; x < image.getWidth() / factor; x++) {
				int[] newRGB = new int[] { 0, 0, 0 };
				int pixel = 0;
				
				for (int i = 0; i < factor; i++) {
					for (int j = 0; j < factor; j++) {
						int pixelRGB = image.getRGB(x * j + x, y * i + y);
						pixel += pixelRGB;
						newRGB[0] += getRGBRed(pixelRGB);
						newRGB[1] += getRGBGreen(pixelRGB);
						newRGB[2] += getRGBBlue(pixelRGB);
					}
				}
				
				for (int i = 0; i < newRGB.length; i++) {
					newRGB[i] /= factor * factor;
				}
				
				pixel = setRGBRed(pixel / factor * factor, newRGB[0]);
				pixel = setRGBGreen(pixel, newRGB[1]);
				pixel = setRGBBlue(pixel, newRGB[2]);
				
				newImage.setRGB(x, y, pixel);
			}
		}

		return newImage;
	}

	public static void showOriginalImageTask() throws IOException {
		// read the image to memory
		BufferedImage image = ImageIO.read(
				P06BitmapTransform.class.getResource("images/image1.jpg"));

		// show original image
		buildFrameForImage(image);
	}

	public static void transformTask() throws IOException {
		// read the image to memory
		BufferedImage image = ImageIO.read(
				P06BitmapTransform.class.getResource("images/image1.jpg"));

		// get a new image that is the transformation of original image
		BufferedImage newImage = copyImage(image);

		// show new image
		buildFrameForImage(newImage);
	}

	public static void reduceTask() throws IOException {
		// read the image to memory
		BufferedImage image = ImageIO.read(
				P06BitmapTransform.class.getResource("images/image1.jpg"));

		// apply reduction
		BufferedImage newImage = reduceImage(image);

		// show reduced image
		buildFrameForImage(newImage);
	}

	public static void buildFrameForImage(BufferedImage image)
			throws IOException {
		// the frame
		JFrame frame = new JFrame();

		// frame should be disposed when we press close button
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// set frame size
		frame.setSize(400, 300);

		// center the frame
		frame.setLocationRelativeTo(null);

		// show the image inside a label
		ImageIcon img = new ImageIcon(image);
		JLabel label = new JLabel(img, JLabel.CENTER);
		// add the label to frame
		frame.add(label);

		// turn frame visible
		frame.setVisible(true);
	}

	static int getRGBBlue(int rgb) {
		return (rgb) & 0xFF;
	}

	static int getRGBGreen(int rgb) {
		return (rgb >> 8) & 0xFF;
	}

	static int getRGBRed(int rgb) {
		return (rgb >> 16) & 0xFF;
	}

	static int setRGBBlue(int rgb, int blue) {
		return (rgb & 0xFFFFFF00) | (blue & 0xFF);
	}

	static int setRGBGreen(int rgb, int green) {
		return (rgb & 0xFFFF00FF) | ((green & 0xFF) << 8);
	}

	static int setRGBRed(int rgb, int red) {
		return ((red & 0xFF) << 16) | rgb & 0xFF00FFFF;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {

					// show original image
					showOriginalImageTask();

					// show new image with some transformation
					transformTask();

					// reduce new image by half
					reduceTask();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
