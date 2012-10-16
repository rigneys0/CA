package concept_proving_area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ImageCreator {
	public static void main(String[] args) throws IOException{
		BufferedImage imageTest = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		for(int i=20;i<50; i++){
			for(int j=20; j<50;j++){
				imageTest.setRGB(i, j, 0xB9D3EE);
			}
		}
		File newImage = new File("image.png");
		ImageIO.write(imageTest, "png", newImage);
	}
}
