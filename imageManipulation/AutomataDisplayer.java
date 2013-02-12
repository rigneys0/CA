package imageManipulation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class AutomataDisplayer {
	private static Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
	private AutomataDisplayer(){
		
	}
	public static void toImage(byte[][] lattice, String imageFormat, String outputFile, States num) throws IOException{
		int width=lattice[0].length;
		int height = lattice.length;
		BufferedImage output = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		for(int vertical=0;vertical < height;vertical++){
			for(int horizontal=0;horizontal < width;horizontal++){
				output.setRGB(horizontal, vertical, colors[lattice[vertical][horizontal]].getRGB());
			}
		}
		File automataImage = new File(outputFile+"."+imageFormat);
		ImageIO.write(output, imageFormat, automataImage);
	}
}
