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
 * The PNMImage class represents a extends of PWImage by RGB, BIN and GRAYSCALE image from P*M{pgm,ppm} files. 
 * <b>NOT<b/> read *.pbm files
 *<table>
 *<tr>
 *<th>Magic Number *</th>
 *<th>Type *</th>
 *<th>Encoding *</th>
 *</tr>
 *<tr>
 *<td>P1 *</td>
 *<td>Portable bitmap *</td>
 *<td>ASCII *</td>
 *</tr>
 *<tr>
 *<td>P2 *</td>
 *<td>Portable graymap *</td>
 *<td>ASCII *</td>
 *</tr>
 *<tr>
 *<td>P3 *</td>
 *<td>Portable pixmap *</td>
 *<td>ASCII *</td>
 *</tr> *<tr>
 *<td>P4 *</td>
 *<td>Portable pixmap *</td>
 *<td>Binary *</td>
 *</tr> *<tr>
 *<td>P5 *</td>
 *<td>Portable graymap *</td>
 *<td>Binary *</td>
 *</tr> *<tr>
 *<td>P6 *</td>
 *<td>Portable pixmap *</td>
 *<td>Binary *</td>
 *</tr>
 *</table>
 * @version 1.2,10-06-013
 *              16-06-013{*setGrayPixels change width and height}
 *              17-06-013{*add fileObject set and get methods}
 *              18-06-013{*add strFilextension set and get methods}
 *              18-06-013{*setRPixels change width and height}*
 *              03-07-013{*make superclass PWImage}* 
 * @author  Jefferson Amado Pe\~na Torres <jeffersonamado@gmail.com>
 * @see PWImage
 * @see PWImageType
 *
 */
public class PNMImage extends PWImage{
	/*
	* Constructor
	* Read file from path an load a PWImage
	* @param path path file to read
	* @throws   java.io.FileNotFoundException   if the directory/image specified is wrong
	* @throws   java.io.IOException  if there are problems reading the file.	
	*/
	public PNMImage(String path)throws Exception{
		readImage(path);
	}
	//--
	private void readImage(String pathImage)throws FileNotFoundException,IOException{
		File imgPath = new File(pathImage);
		FileInputStream f = new FileInputStream(imgPath);  
		BufferedReader d = new BufferedReader(new InputStreamReader(f));
			String name=imgPath.getName().replaceAll(".pgm", "");
					name=imgPath.getName().replaceAll(".ppm", "");
			String extension=pathImage.substring(pathImage.lastIndexOf(".") + 1, pathImage.length());
			String magicNumber = d.readLine();     // first line contains P2 or P5

		String line = d.readLine();     // second line contains comment start (#)
		while (line.startsWith("#")) {
			line=d.readLine();
		}
		Scanner s = new Scanner(line);
		int width = s.nextInt();            //third line contains height and width
		int height = s.nextInt();           //third line contains height and width

		line = d.readLine();
		s = new Scanner(line);
		int intensityLevel = s.nextInt();       //quarter line contains maxVal

		super.setName(name);
		super.setIntensity(intensityLevel);

		int[][]  pixels = new int[height][width];
		int[][] Rpixels = new int[height][width];
		int[][] Gpixels = new int[height][width];
		int[][] Bpixels = new int[height][width];

		String rgb[] = new String[3];
		int key=0;
		int count=0;

		System.out.println("PNMImage:: format "+magicNumber);
		System.out.println("PNMImage:: Name "+name+"."+extension);
		System.out.println("PNMImage:: Width "+width);
		System.out.println("PNMImage:: Height "+height);
		System.out.println("PNMImage:: maxvalue "+intensityLevel);
		System.out.println("PNMImage:: start Read array... \n");
		
		if (magicNumber.equals("P1")){
			    throw new IOException( "not read PBM file " );
		}else if (magicNumber.equals("P2")){
			super.setFileExtension("pgm");
			super.setTypePWImage(PWImageType.TYPE_GRAYSCALE);
			while((line = d.readLine()) != null) {
				String splitdata[] = line.split("\\s");
				for (String pixel : splitdata) {
					if(!pixel.equals("")){
						pixels[(count/width)][(count%width)]=Integer.parseInt(pixel);
						count++;
					}
				}
			}
			super.setGrayPixels(pixels);
        }else if(magicNumber.equals("P3")){
			super.setFileExtension("ppm");
			super.setTypePWImage(PWImageType.TYPE_RGB);
			while((line = d.readLine()) != null) {
				String splitdata[] = line.split("\\s");
				for (String pixel : splitdata) {
					if(!pixel.equals("")){
						rgb[key] = pixel;
						key++;
						if(key == 3) {
							Rpixels[(count/width)][(count%width)]= Integer.parseInt(rgb[0]);
							Gpixels[(count/width)][(count%width)]= Integer.parseInt(rgb[1]);
							Bpixels[(count/width)][(count%width)]= Integer.parseInt(rgb[2]);
							count++;
							key = 0;
						}
					}
				}
			}
			super.setRPixels(Rpixels);
			super.setGPixels(Gpixels);
			super.setBPixels(Bpixels); 
		}else if(magicNumber.equals("P4")){
			    throw new IOException( "not read PBM file" );		
		}else if(magicNumber.equals("P5")){
			super.setFileExtension("pgm");
			super.setTypePWImage(PWImageType.TYPE_GRAYSCALE);
			FileInputStream fis = new FileInputStream(imgPath);  				
            for (int i=0;i<height;i++){
                for (int j=0;j<width;j++) {
                	int pixel = fis.read();
                    pixels[i][j] = (pixel << 16) & 0xff| (pixel << 8) & 0xff| pixel & 0xff;
                }
            }
			super.setGrayPixels(pixels);	                
		}else if(magicNumber.equals("P6")){
			super.setFileExtension("ppm");
			super.setTypePWImage(PWImageType.TYPE_RGB);						
			FileInputStream fis = new FileInputStream(imgPath);  
            for (int i=0;i<height;i++){
                for (int j=0;j<width;j++) {
                	Color c = new Color((fis.read() & 0xff),(fis.read() & 0xff),(fis.read() & 0xff));
                    Rpixels[i][j] = c.getRed();
                    Gpixels[i][j] = c.getGreen();
                    Bpixels[i][j] = c.getBlue();
                }
            }
			super.setRPixels(Rpixels);
			super.setGPixels(Gpixels);
			super.setBPixels(Bpixels); 			
		}
        System.out.println("PNMImage:: End Read image ");
	}
	  
//	/**
//	* Write to <code&gt;fn</code&gt; file the <code&gt;data</code&gt; using the
//	* <code&gt;width, height</code&gt; variables. Data is assumed to be 8bit RGB.
//	* @param 	path 	destination by file
//	* @param	intensity max intensity value	
//	* @param	Rpixels	array the pixels Red channel (x,y) 
//	* @param	Rpixels	array the pixels green channel (x,y) 
//	* @param	Rpixels	array the pixels blue channel (x,y) 		
//	* @throws   java.io.FileNotFoundException   if the directory/image specified is wrong
//	* @throws   java.io.IOException  if there are problems writer or reading in the file.
//	*/
     public static void writeImage(String path,int intensity,int [][] Rpixels,int [][] Gpixels,int [][] Bpixels)throws FileNotFoundException,IOException{
		System.out.println("PNMImage:: start Write image... \n");
		int height = Rpixels.length;            
		int width = Rpixels[0].length;		
		FileWriter fw = new FileWriter(path+".ppm");
		PrintWriter pw= new PrintWriter(fw);
		String strComment="# CREATOR: PinkWizard PinkWiz Filter Version 1.3 by JAPeTo";
			pw.write("P3\n");
			pw.write(strComment+"\n");
			pw.write(width+" "+height+"\n");
			pw.write(intensity+"\n");
			for(int indexRow=0;indexRow<height;indexRow++){
				for(int indexCol=0;indexCol<width;indexCol++){
					pw.write(Rpixels[indexRow][indexCol]+" ");
					pw.write(Gpixels[indexRow][indexCol]+" ");
					pw.write(Bpixels[indexRow][indexCol]+" ");
				}
				pw.write("\n");
			}
		pw.flush( );
		pw.close( );                
		System.out.println("PNMImage:: end write image "+path);
	}
//	/**
//	* Write to <code&gt;fn</code&gt; file the <code&gt;data</code&gt; using the
//	* <code&gt;width, height</code&gt; variables. Data is assumed to be 8bit RGB.
//	* @param 	path 	destination by file
//	* @param	intensity max intensity value
//	* @param 	pixels 	array pixels	
//	* @throws   java.io.FileNotFoundException   if the directory/image specified is wrong
//	* @throws   java.io.IOException  if there are problems writer or reading in the file.
//	*/
	public static void writeImage(String path,int intensity,int [][] pixels)throws FileNotFoundException,IOException{
		System.out.println("PNMImage:: start Write image... \n");
		int height = pixels.length;            
		int width = pixels[0].length;
		FileWriter fw = new FileWriter(path+".pgm");
		PrintWriter pw= new PrintWriter(fw);
		String strComment="# CREATOR: PinkWizard PinkWiz Filter Version 1.3 by JAPeTo";
			pw.write("P2\n");
			pw.write(strComment+"\n");
			pw.write(width+" "+height+"\n");
			pw.write(intensity+"\n");
			for(int indexRow=0;indexRow<height;indexRow++){
				for(int indexCol=0;indexCol<width;indexCol++){
					pw.write(pixels[indexRow][indexCol]+" ");
				}
				pw.write("\n");
			}   
		pw.flush( );
		pw.close( );                
		System.out.println("PNMImage:: end write image "+path);			  
	}        

}



