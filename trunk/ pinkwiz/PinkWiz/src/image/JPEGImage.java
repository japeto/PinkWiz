package image;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.awt.*;
import java.awt.color.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * The JPEGImage class represents a extends of PWImage by RGB and GRAYSCALE image from JPEG files. 
 * @author  Jefferson Amado Pe\~na Torres <jeffersonamado@gmail.com>
 * @version 1.0, jun 2013
 * @see PWImage
 * @see PWImageType
 *
 */
public class JPEGImage extends PWImage{
	/**
	* Constructor 
	* Read file from path an load a PWImage
	* @param path path file to read
	* @throws   java.io.FileNotFoundException   if the directory/image specified is wrong
	* @throws   java.io.IOException  if there are problems reading the file.	
	*/
	public JPEGImage(String path)throws FileNotFoundException,IOException{
		readImage(path);
	}
	//---
	private void readImage(String pathImage)throws FileNotFoundException,IOException{
		
		File imgPath = new File(pathImage);
		BufferedImage image = ImageIO.read(imgPath);
						String name=imgPath.getName().replaceAll(".jpg", "");
			String extension=pathImage.substring(pathImage.lastIndexOf(".") + 1, pathImage.length());
		int width = image.getWidth();
		int height = image.getHeight();
		int[][]  pixels = new int[height][width];		//array pixels
		int[][] Rpixels = new int[height][width];
		int[][] Gpixels = new int[height][width];
		int[][] Bpixels = new int[height][width];
		boolean imgTypeState= true;
		
		System.out.println("JPEGImage:: Name: "+name+"."+extension);	
		System.out.println("JPEGImage:: Width: "+width);
		System.out.println("JPEGImage:: Height: "+height);
		System.out.println("JPEGImage:: start Read array... \n");
			
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					int value = image.getRGB(col, row);
				
					Rpixels[row][col]=(value >> 16) & 0xff; //red
					Gpixels[row][col]=(value >>  8) & 0xff; //green
					Bpixels[row][col]=(value      ) & 0xff; //blue
					// if always Red is equal to Blue and too green this image is GRAYSCALE
					imgTypeState = (imgTypeState) ?   (Rpixels[row][col] == Gpixels[row][col] && Rpixels[row][col] == Bpixels[row][col]): false;
				}
			}
			if(imgTypeState){ // is grayscale
				super.setTypePWImage(PWImageType.TYPE_GRAYSCALE);
				for (int row = 0; row < height; row++) {
					for (int col = 0; col < width; col++) {
						pixels[row][col]= (int)(Rpixels[row][col] * 0.299 + Gpixels[row][col] * 0.587 + Bpixels[row][col] * 0.114);
					}
				}
				super.setGrayPixels(pixels);	                						
			}else{ // is color RGB
				super.setTypePWImage(PWImageType.TYPE_RGB);					
				super.setRPixels(Rpixels);
				super.setGPixels(Gpixels);
				super.setBPixels(Bpixels);				
			}
		super.setName(name);
		super.setFileExtension(extension);
        super.setRasterImage((Image)image);
		System.out.println("JPEGImage:: End Read image ");		
	}
//	/**
//	* Write to <code&gt;fn</code&gt; file the <code&gt;data</code&gt; using the
//	* <code&gt;width, height</code&gt; variables. Data is assumed to be 8bit RGB.
//	* @param 	path 	destination by file
//	* @param	Rpixels	array the pixels Red channel (x,y) 
//	* @param	Rpixels	array the pixels green channel (x,y) 
//	* @param	Rpixels	array the pixels blue channel (x,y) 		
//	* @throws   java.io.FileNotFoundException   if the directory/image specified is wrong
//	* @throws   java.io.IOException  if there are problems writer or reading in the file.
//	*/
     public static void writeImage(String path,int [][] Rpixels,int [][] Gpixels,int [][] Bpixels)throws FileNotFoundException,IOException{
            System.out.println("JPEGImage:: start Write image... \n");
			int height = Rpixels.length;            
			int width = Rpixels[0].length;
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
			for(int indexRow=0;indexRow <height;indexRow++){
				for(int indexCol=0;indexCol <width;indexCol++){
					bufferedImage.setRGB(indexCol,indexRow,((Rpixels[indexRow][indexCol] &0x0ff)<<16)|(( Gpixels[indexRow][indexCol] &0x0ff)<<8)|( Bpixels[indexRow][indexCol] &0x0ff));
				}
			}
			ImageIO.write(bufferedImage, "jpg", new File(path+".jpg"));
            System.out.println("JPEGImage:: end write image "+path+".jpg");
    }
//	/**
//	* Write to <code&gt;fn</code&gt; file the <code&gt;data</code&gt; using the
//	* <code&gt;width, height</code&gt; variables. Data is assumed to be 8bit RGB.
//	* @param 	path 	destination by file
//	* @param	type	indecate image type @ see PWImageType { RGB, GRAYSCALE}
//	* @param	pixels	array the pixels (x,y) 
//	* @throws   java.io.FileNotFoundException   if the directory/image specified is wrong
//	* @throws   java.io.IOException  if there are problems writer or reading in the file.
//	*/
     public static void writeImage(String path,PWImageType type,int [][] pixels)throws FileNotFoundException,IOException{
            System.out.println("JPEGImage:: start Write image... \n");
			int height = pixels.length;            
			int width = pixels[0].length;		
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );            
			switch(type){
				case TYPE_BIN:
					for(int indexRow=0;indexRow <height;indexRow++){
						for(int indexCol=0;indexCol <width;indexCol++){
							if(pixels[indexRow][indexCol]==0){
								bufferedImage.setRGB(indexCol,indexRow,new Color(0,0,0).getRGB());
							}else{
								bufferedImage.setRGB(indexCol,indexRow,new Color(255,255,255).getRGB());						
							}
						}
					}
				    ImageIO.write(bufferedImage, "jpg", new File(path+".jpg"));     					        		        
		        break;			
				case TYPE_GRAYSCALE:
					int[] grayLevels= new int[height*width];
					for (int i = 0; i < 256; i++) {
						grayLevels[i] = new Color(i, i, i).getRGB();
					}
					for(int indexRow=0;indexRow <height;indexRow++){
						for(int indexCol=0;indexCol <width;indexCol++){
							bufferedImage.setRGB(indexCol,indexRow,grayLevels[(int)pixels[indexRow][indexCol] & 0xff]);
						}
					}
				    ImageIO.write(bufferedImage, "jpg", new File(path+".jpg"));
		        break;
		    }
            System.out.println("JPEGImage:: end write image "+path+".jpg");
    }
}
