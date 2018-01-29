/*==========================================================================
 * RGBConversion
 * Create methods that will read in a file to change the image to green,
 * red, blue, and grayscale
 * CS 108-2
 * March 25, 2016
 * @author Shawn Nehemiah Chua
 * =========================================================================
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RGBConversion {
	// Private variables
	protected BufferedImage img = null;
	protected String out = "";
	protected String color = "";
	/**
	 * Read in image file into img variable
	 */
	public void readImage(String input){
		File f = null;
		out = input;//so the string can be copied to the outfile
		
		//read image
		try{
			f = new File(input);
			
			if(f.canRead())
				img = ImageIO.read(f);
			else{
				throw new IOException("Cannot read file: " + f.getPath());
			}
		}catch (IOException fx){
			System.out.println(fx);
			System.exit(1);
		}
	}
	/**
	 * Write img to file
	 */
	public void writeImage (){
		File f = null;
		
		//write image
		try{
			f = new File(appendColorToOutput());
			ImageIO.write(img, "jpg", f);
		}catch(IOException fx){
			System.out.println(fx);
		}
	}
	/**
	 * Display RGB value (omit transparency) in decimal
	 */
		
	public void test(int x, int y){
		int p = img.getRGB(x, y);
		int r = (p>>16) & 0xFF;
		int g = (p>>8) & 0xFF;
		int b = p & 0xFF;
		//prints out the value of the r g b
		System.out.println("RGB: " + r + "," + g + "," + b);
	}
	/**
	 * Convert each pixel in img to grayscale getting R,G,B to average
	 */
	public void toGrayscale(){
		for(int y = 0; y < img.getHeight(); y++){ //get image height
			for(int x = 0; x < img.getWidth(); x++){ //get image width
				int p = img.getRGB(x,y);
				
				int a = (p>>24) & 0xFF;
				int r = (p>>16) & 0xFF;
				int g = (p>>8) & 0xFF;
				int b = p &  0xFF;
				
				//calculate the average
				int avg = (r+g+b)/3;
				
				//set new RGB
				p = (a<<24) | (avg<<16) | (avg<<8) | avg;
				
				img.setRGB(x,  y,  p);
			}
		}
		color = "Grayscale";
	}
	/**
	 * Convert each pixel in img to red, masking out green and blue
	 */
	public void toRed(){
		for(int y = 0; y < img.getHeight(); y++){  //get image height
			for(int x = 0; x < img.getWidth(); x++){ //get image width
				int p = img.getRGB(x,y);
				
				int a = (p>>24) & 0xFF;
				int r = (p>>16) & 0xFF;
				
				//set new RGB
				p = (a<<24) | (r<<16) | (0<<8) | 0;
				
				img.setRGB(x,  y,  p);
			}
		}
		color = "Red";
	}
	/**
	 * Convert each pixel in img to green, masking out red and blue
	 */
	public void toGreen(){
		for(int y = 0; y < img.getHeight(); y++){ //get image height
			for(int x = 0; x < img.getWidth(); x++){ //get image width
				int p = img.getRGB(x,y);
				
				int a = (p>>24) & 0xFF;
				int g = (p>>8) & 0xFF;
				
				//set new RGB
				p = (a<<24) | (0<<16) | (g<<8) | 0;
				
				img.setRGB(x,  y,  p);
			}
		}
		color = "Green";
	}
	/**
	 * Convert each pixel in img to blue, masking out red and green
	 */
		
	public void toBlue(){
		for(int y = 0; y < img.getHeight(); y++){ //get image height
			for(int x = 0; x < img.getWidth(); x++){ //get image width
				int p = img.getRGB(x,y);
				
				int a = (p>>24) & 0xFF;
				int b = p & 0xFF;
				
				//set new RGB
				p = (a<<24) | (0<<16) | (0<<8) | b;
				
				img.setRGB(x,  y,  p);
			}
		}
		color = "Blue";
	}
	// Helper methods here
	/**
	 * Return output file name with color appended before ".jpg"
	 */
	private	String appendColorToOutput(){
		String f = out.substring(0, out.length() - 4) + color + ".jpg";
		return f;
	}
}
