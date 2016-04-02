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
 * Apply reduce by half on an image
 * 
 * @author ateofilo
 * 
 */
public class P06BitmapTransform {

	/**
	 * Method that copies the image, could apply some pixel transformation
	 * 
	 * @param image
	 *            the image to be copied and / or transformed
	 * @return a new image
	 */
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
	 * 
	 * TODO Method to be done. It contains some code that have to be changed
	 * 
	 * @return a new reduced by half image
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

	/**
	 * show original image
	 * 
	 */
	public static void showOriginalImageTask() throws IOException {
		// read the image to memory
		BufferedImage image = ImageIO.read(
				P06BitmapTransform.class.getResource("images/image1.jpg"));

		// show original image
		buildFrameForImage(image);
	}

	/**
	 * show image with some transformation
	 * 
	 */
	public static void transformTask() throws IOException {
		// read the image to memory
		BufferedImage image = ImageIO.read(
				P06BitmapTransform.class.getResource("images/image1.jpg"));

		// get a new image that is the transformation of original image
		BufferedImage newImage = copyImage(image);

		// show new image
		buildFrameForImage(newImage);
	}

	/**
	 * reduce image to a new image with size reduce by half
	 * 
	 */
	public static void reduceTask() throws IOException {
		// read the image to memory
		BufferedImage image = ImageIO.read(
				P06BitmapTransform.class.getResource("images/image1.jpg"));

		// apply reduction
		BufferedImage newImage = reduceImage(image);

		// show reduced image
		buildFrameForImage(newImage);
	}

	/**
	 * Method that shows one image in a new frame
	 */
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
