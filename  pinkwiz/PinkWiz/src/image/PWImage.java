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
 * The PWImage class implements Cloneable and represents 
 * a image file, A instace of this contains array pixels in Grayscale and binary images 
 * or three array whith Red, Green and Blue channel by Color iamges
 * @see Cloneable
 */

public class PWImage implements Cloneable{

	private PWImageType type;
	private int[][] pixels,Rpixels,Gpixels,Bpixels;
	private int width,height,intensity=255;
	private String strFilextension,name;
	private Image image;

	/**
	* With this methhod obtain the  name file
	* @return name this file
	*/
	public String getName(){return this.name;}
			/**
			* Set the name file
			* @param name new name by file
			*/
			public void setName(String name){this.name=name;}		
	/**
	* With this methhod obtain the  extension file
	* @return extension (.ppm) by P3 image, (.pgm) by P2 image and (.jpg) by JPEG images
	*/
	public String getFileExtension(){return this.strFilextension;}
			/**
			* Set extension (.ppm) by P3 image, (.pgm) by P2 image and (.jpg) by JPEG images
			* @param extension new extension by file
			*/
			public void setFileExtension(String extension){this.strFilextension=extension;}	
	/**
	* With this method obtain Color depth by P*M images
	* @return Width of the image
	*/
	public int getIntensity(){ return this.intensity; }
			/**
			*set Color depth of image
			*@param intensity intensity value
			*/
			public void setIntensity(int intensity){this.intensity=intensity;}	
	/**
	* With this method obtain the type image
	* @return type 
	* @see PWImageType
	*/
	public PWImageType getTypePWImage(){ return this.type; }
			/**
			* Set The type image
			* @param type type of image
			* @see PWImageType			
			*/
			public void setTypePWImage(PWImageType type){this.type=type;}
	/**
	* With this method obtain width image
	* @return width Width of the image
	*/
	public int getWidth(){ return this.width; }
			/**
			* Set Width of image
			* @param width width value
			*/
			public void setWidth(int width){this.width=width;}
	/**
	* Width this method obtain height image
	* @return Height
	*/
	public int getHeight(){ return this.height; }
			/**
			* Set Height of image
			* @param height height value
			*/
			public void setHeight(int height){this.height=height;}
	/**
	* With this method obtain the array the grayscale image
	* @return array pixels
	*/
	public int[][] getGrayPixels(){ return this.pixels; }
			/**
			* Set array pixels by a array the grayscale image
			* @param pixels arrayPixels one channel
			* if set a new array change width and height values 
			*/
			public void setGrayPixels(int[][] pixels){
				this.width=pixels[0].length;
				this.height=pixels.length;
				this.pixels=pixels;
			}			
	/**
	* With this method obtain the arrayPixels Red channel 
	* @return array with the red channel
	*/
	public int[][] getRPixels (){return this.Rpixels;}
			/**
			* With this method set arrayPixels Red channel 
			* @param arrayRpixels the red channel
			* if set a new array change width and height values 			
			*/
			public void setRPixels (int[][] arrayRpixels){
				this.width=arrayRpixels[0].length;
				this.height=arrayRpixels.length;
				this.Rpixels=arrayRpixels;
			}
	/**
	* With this method obtain the arrayPixels green channel 
	* @return array with the green channel
	*/
	public int[][] getGPixels (){return this.Gpixels;}
			/**
			* With this method set arrayPixels green channel 
			* @param arrayGpixels the green channel
			*/
			public void setGPixels (int[][] arrayGpixels){
				this.width=arrayGpixels[0].length;
				this.height=arrayGpixels.length;
				this.Gpixels=arrayGpixels;
			}
	/**
	* With this method obtain the arrayPixels blue channel 
	* @return array with the blue channel
	*/
	public int[][] getBPixels (){return this.Bpixels;}
			/**
			* With this method set arrayPixels blue channel 
			* @param arrayBpixels the blue channel
			*/
			public void setBPixels (int[][] arrayBpixels){
				this.width=arrayBpixels[0].length;
				this.height=arrayBpixels.length;
				this.Bpixels=arrayBpixels;
			}
		/**
		 * This method return a Image by draw int the gui
		 * @see java.awt.Image
		 * @return Image
		 */                
		public Image getRasterImage(){ 
			if(type==PWImageType.TYPE_BIN){
				int height = this.pixels.length;            
				int width = this.pixels[0].length;		

				BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
				for(int indexRow=0;indexRow <height;indexRow++){
					for(int indexCol=0;indexCol <width;indexCol++){
						if(this.pixels[indexRow][indexCol]==0){
							bufferedImage.setRGB(indexCol,indexRow,new Color(0,0,0).getRGB());
						}else{
							bufferedImage.setRGB(indexCol,indexRow,new Color(255,255,255).getRGB());						
						}
					}
				}
		        this.image=(Image)bufferedImage;     			
			}else if(type==PWImageType.TYPE_GRAYSCALE){
				int height = this.pixels.length;            
				int width = this.pixels[0].length;		
				int[] grayLevels= new int[256];
				for (int i = 0; i < 256; i++) {
					grayLevels[i] = new Color(i, i, i).getRGB();
				}
				BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
				for(int indexRow=0;indexRow <height;indexRow++){
					for(int indexCol=0;indexCol <width;indexCol++){
						bufferedImage.setRGB(indexCol,indexRow,grayLevels[this.pixels[indexRow][indexCol] & 0xff]);
					}
				}
		        this.image=(Image)bufferedImage;       
			}else if(type==PWImageType.TYPE_RGB){
				int height = this.Rpixels.length;            
				int width = this.Rpixels[0].length;		
				BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
				for(int indexRow=0;indexRow <height;indexRow++){
					for(int indexCol=0;indexCol <width;indexCol++){
						bufferedImage.setRGB(indexCol,indexRow,((this.Rpixels[indexRow][indexCol] &0x0ff)<<16)|(( this.Gpixels[indexRow][indexCol] &0x0ff)<<8)|( this.Bpixels[indexRow][indexCol] &0x0ff));
					}
				}
		        this.image=(Image)bufferedImage;       
			}
			return this.image; 
		}
				/**
				*
				*/
				public void setRasterImage(Image img){this.image=img;}							
	/**
	* this method return a clone this object
	* @return instance of object
	*/
	public PWImage clone(){
		PWImage obj=this;
		try{
			obj=(PWImage) super.clone();
		}catch(CloneNotSupportedException ex){
			System.out.println("PixMapImage::clone() No se puede clonar");
		}
		return obj;
	}
	/**
	* Whith this metodth write a image 
	* @param name nombre output file
	*/
	public void writeImage(String name)throws FileNotFoundException,IOException{
			System.err.println(type+" <<<>>> "+this.getFileExtension());
		switch(this.type){
			case TYPE_BIN:
				if(this.getFileExtension().equals("jpg")){ JPEGImage.writeImage(name,PWImageType.TYPE_BIN,this.getGrayPixels() );			
				}else if(this.getFileExtension().equals("pgm")){PNMImage.writeImage(name,this.getIntensity(),this.getGrayPixels());}
			break;
			case TYPE_GRAYSCALE:
				if(this.getFileExtension().equals("jpg")){ JPEGImage.writeImage(name,PWImageType.TYPE_GRAYSCALE,this.getGrayPixels());	
				}else if(this.getFileExtension().equals("pgm")){  PNMImage.writeImage(name,this.getIntensity(),this.getGrayPixels());}
			break;
			case TYPE_RGB:
				if(this.getFileExtension().equals("jpg")){
					JPEGImage.writeImage(name,this.getRPixels(),this.getGPixels(),this.getBPixels());	
				}else if(this.getFileExtension().equals("ppm")){  PNMImage.writeImage(name,this.getIntensity(),this.getRPixels(),this.getGPixels(),this.getBPixels());}
			break;
			default:
				System.err.println("<<<>>>");
			break;
		}
		
	}
	/**
	* String with image representing
	* @return string
	* @deprecated
	*/
	public String toString(){
		String stringImage="PNMImage::NOT SUPPORT BY THIS IMAGE";
		if(type==PWImageType.TYPE_GRAYSCALE){
			stringImage = " W: "+width+" H: "+height+"\n";
			for(int indexRow=0;indexRow <height;indexRow++){
				for(int indexCol=0;indexCol <width;indexCol++){
					stringImage+=pixels[indexRow][indexCol]+"";
				}
				stringImage+="\n";
			}
		}else if(type==PWImageType.TYPE_BIN){
			stringImage = " W: "+width+" H: "+height+"\n";
			for(int indexRow=0;indexRow <height;indexRow++){
				for(int indexCol=0;indexCol <width;indexCol++){
					stringImage+=pixels[indexRow][indexCol]+"";
				}
				stringImage+="\n";
			}
		}else if(type==PWImageType.TYPE_RGB){
			stringImage = " W: "+width+" H: "+height+" \n";
			for(int indexRow=0;indexRow <height;indexRow++){
				for(int indexCol=0;indexCol <width;indexCol++){
					stringImage+=(Rpixels[indexRow][indexCol]+" "+Gpixels[indexRow][indexCol]+" "+Bpixels[indexRow][indexCol]+" ");
				}
				stringImage+="\n";
			}
		}
		return stringImage;
	}
}
