package geometric;
import image.*;

import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.Math;
/**
 * This class rotate image clockwise or counterclockwise
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0,
 * @since 2013-06-18
 */
public class RotateImage {
	/**
	 * Image to change
	 */
	private PWImage rImage;
	/**
	 * Constructor
	 */
	public RotateImage(){}
	/**
	 *With this method rotate image clockwise
	 *@param Aimage image source
	 *@return Image rotate
	 */
	public PWImage rotateClockwise(PWImage Aimage){
				rImage=Aimage.clone();
//                rImage.setWidth(Aimage.getHeight());
//                rImage.setHeight( Aimage.getWidth());
//                rImage.setIntensityLevel(Aimage.getIntensity());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int [][] pixelsA = Aimage.getGrayPixels();
					int [][] pixelsTemp= new int[pixelsA[0].length][pixelsA.length];
					for (int indexRow = 0; indexRow < pixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < pixelsA[0].length; indexCol++){
							pixelsTemp [indexCol][indexRow]  = pixelsA[pixelsA.length-1-indexRow][indexCol];
						}
					}
					rImage.setGrayPixels(pixelsTemp);
               }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
						int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[RpixelsA[0].length][RpixelsA.length];
                        int [][] GpixelsTemp= new int[RpixelsA[0].length][RpixelsA.length];
                        int [][] BpixelsTemp= new int[RpixelsA[0].length][RpixelsA.length];
                        //Red channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							RpixelsTemp [indexCol][indexRow] = RpixelsA[RpixelsA.length-1-indexRow][indexCol];
						}
					}
                        //Green channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							GpixelsTemp [indexCol][indexRow]= GpixelsA[RpixelsA.length-1-indexRow][indexCol];
						}
					}
                        //blue channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							BpixelsTemp [indexCol][indexRow] = BpixelsA[RpixelsA.length-1-indexRow][indexCol];
						}
					}
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
				}
		return rImage;
	}
	/**
	 *With this method rotate image counterclockwise
	 *@param Aimage image source
	 *@return Image rotate
	 */
	public PWImage rotateCounterClockwise(PWImage Aimage){
				rImage=Aimage.clone();
//                rImage.setWidth(Aimage.getHeight());
//                rImage.setHeight( Aimage.getWidth());
//                rImage.setIntensityLevel(Aimage.getIntensity());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int [][] pixelsA = Aimage.getGrayPixels();
					int [][] pixelsTemp= new int[pixelsA[0].length][pixelsA.length];
					for (int indexRow = 0; indexRow < pixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < pixelsA[0].length; indexCol++){
							pixelsTemp [indexCol][indexRow]  = pixelsA[indexRow][pixelsA[0].length-1-indexCol];
						}
					}
					rImage.setGrayPixels(pixelsTemp);
				}else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
						int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[RpixelsA[0].length][RpixelsA.length];
                        int [][] GpixelsTemp= new int[RpixelsA[0].length][RpixelsA.length];
                        int [][] BpixelsTemp= new int[RpixelsA[0].length][RpixelsA.length];
                        //Red channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							RpixelsTemp [indexCol][indexRow] = RpixelsA[indexRow][RpixelsA[0].length-1-indexCol];
						}
					}
                        //Green channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							GpixelsTemp [indexCol][indexRow]= GpixelsA[indexRow][RpixelsA[0].length-1-indexCol];
						}
					}
                        //blue channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							BpixelsTemp [indexCol][indexRow] = BpixelsA[indexRow][RpixelsA[0].length-1-indexCol];
						}
					}
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
				}
		return rImage;
	}
//       public static void main (String [] args){
//                try{
//                        PWImage a =new PWImage(args[0]);
//                        RotateImage po=new RotateImage();
//                                po.rotateClockwise(a).writeImage("rotateCk"+a.getFileExtension());
//								po.rotateCounterClockwise(a).writeImage("rotateCCK"+a.getFileExtension());
//                }catch(Exception e){
//                        System.out.println("Error:: "+e);
//                }
//        }

}
