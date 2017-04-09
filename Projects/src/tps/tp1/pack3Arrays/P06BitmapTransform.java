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
 * Esta classe visa aplicar v�rias transformadas ao pixel a uma imagem, gerando
 * uma nova imagem
 * 
 * @author ateofilo
 * 
 */
public class P06BitmapTransform {
//	public static String IMAGEPATH = "images/image1.jpg";
	 public static String IMAGEPATH = "images/image2.jpg";
//	 public static String IMAGEPATH = "images/image3.jpg";

	/**
	 * Method that copies the image
	 * 
	 * @param image
	 *            the image to be copied
	 * @return the new image
	 */
	public static BufferedImage copyImage(BufferedImage image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		int height = image.getHeight();
		int width = image.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixelRGB = image.getRGB(x, y);
				newImage.setRGB(x, y, pixelRGB);
			}
		}
		return newImage;
	}

	/**
	 * Method that do some color transformation on the image
	 * 
	 * @param image
	 *            the image to be transformed
	 * @return the new image
	 */
	public static BufferedImage changeImageColors(BufferedImage image) {
		BufferedImage newImage = null;
		newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

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
	 * Method that mirrors the image
	 * 
	 * @param image
	 *            the image to be mirrored
	 * @return the new image
	 */
	public static BufferedImage mirrorImage(BufferedImage image) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		int height = image.getHeight();
		int width = image.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x <= width / 2; x++) {
				int pixelRGB = image.getRGB(x, y);
				newImage.setRGB(x, y, pixelRGB);
				newImage.setRGB(width - x - 1, y, pixelRGB);
			}
		}
		return newImage;
	}

	/**
	 * Method that should do the quadrant transformation
	 * 
	 * @param image
	 *            the image to be transform
	 * @return the new image
	 */
	public static BufferedImage quadrantTransform(BufferedImage image) {

		int quadrant_height = image.getHeight() / 2;
		int quadrant_width = image.getWidth() / 2;
		BufferedImage new_image = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

		for (int line = 0; line < quadrant_height; line++) {
			for (int column = 0; column < quadrant_width; column++) {
				int pixel = image.getRGB(column, line);

				new_image.setRGB(quadrant_width - column, quadrant_height - line, pixel);
				new_image.setRGB(quadrant_width - column, quadrant_height + line, pixel);
				new_image.setRGB(quadrant_width + column, quadrant_height - line, pixel);
				new_image.setRGB(quadrant_width + column, quadrant_height + line, pixel);
			}
		}

		return new_image;
	}

	/**
	 * Method that shows one image in a new frame
	 * 
	 * @param title
	 */
	public static void buildFrameForImage(String title, BufferedImage image) throws IOException {
		// the frame
		JFrame frame = new JFrame();

		frame.setTitle(title);

		// frame should be disposed when we press close button
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// set frame size
		frame.setSize(image.getWidth() + 50, image.getHeight() + 80);

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

	/**
	 * method that gets the blue value from a RGB pixel value
	 */
	static int getRGBBlue(int rgb) {
		return (rgb) & 0xFF;
	}

	/**
	 * method that gets the green value from a RGB pixel value
	 */
	static int getRGBGreen(int rgb) {
		return (rgb >> 8) & 0xFF;
	}

	/**
	 * method that gets the red value from a RGB pixel value
	 */
	static int getRGBRed(int rgb) {
		return (rgb >> 16) & 0xFF;
	}

	/**
	 * method that set the blue value to a RGB pixel value
	 * 
	 * @return the new pixel RGB value
	 */
	static int setRGBBlue(int rgb, int blue) {
		return (rgb & 0xFFFFFF00) | (blue & 0xFF);
	}

	/**
	 * method that sets the green value to a RGB pixel value
	 * 
	 * @return the new pixel RGB value
	 */
	static int setRGBGreen(int rgb, int green) {
		return (rgb & 0xFFFF00FF) | ((green & 0xFF) << 8);
	}

	/**
	 * method that sets the red value to a RGB pixel value
	 * 
	 * @return the new pixel RGB value
	 */
	static int setRGBRed(int rgb, int red) {
		return ((red & 0xFF) << 16) | rgb & 0xFF00FFFF;
	}

	/**
	 * Main method - execution entry point
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {

					// read the image to memory
					BufferedImage image = ImageIO.read(P06BitmapTransform.class.getResource(IMAGEPATH));

					// show original image
					buildFrameForImage("Copy image", copyImage(image));

					// show image with some color transformation
					buildFrameForImage("Color changed image", changeImageColors(image));

					// show mirrored image
					buildFrameForImage("Mirror image", mirrorImage(image));

					// quadrant transform image
					buildFrameForImage("Quadrant transform", quadrantTransform(image));

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
