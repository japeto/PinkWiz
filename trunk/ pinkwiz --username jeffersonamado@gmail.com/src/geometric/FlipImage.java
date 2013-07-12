package geometric;
import image.*;

import image.*;
import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.Math;

/**
 * This class transform image in a image flip vertocal or horizontal
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0,
 * @since 2013-06-18
 */

public class FlipImage {
	/**
	 * Image to change
	 */
	private PWImage rImage;
	/**
	 * Constructor
	 */
	public FlipImage(){}
	/**
	 *With this method rotate image clockwise
	 *@param Aimage image source
	 *@return Image rotate
	 */
	public PWImage flipHorizontal(PWImage Aimage){
				rImage=Aimage.clone();

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int [][] pixelsA = Aimage.getGrayPixels();
					int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

					for (int indexRow = 0; indexRow < pixelsA.length; indexRow++){
						int indexColTemp=pixelsA[0].length-1;
						for(int indexCol = 0; indexCol < pixelsA[0].length; indexCol++){
							pixelsTemp [indexRow][indexCol]  = pixelsA[indexRow][ indexColTemp ];
							//System.out.println(pixelsTemp [indexRow][indexCol]);
							//System.out.println(indexRow+" : "+indexColTemp+" <- "+indexRow+" : "+indexCol+" _ "+pixelsA.length+" * "+pixelsA[indexRow].length+" _ "+pixelsTemp.length+" * "+pixelsTemp[indexRow].length);
							indexColTemp--;
						}
					}
					rImage.setGrayPixels(pixelsTemp);
				}else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
						int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        int [][] GpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        int [][] BpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        //Red channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						int indexColTemp=RpixelsA[0].length-1;
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							RpixelsTemp [indexRow][indexCol]  = RpixelsA[indexRow][ indexColTemp ];
							indexColTemp--;
						}
					}
                    //Green channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						int indexColTemp=RpixelsA[0].length-1;
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							GpixelsTemp [indexRow][indexCol]  = GpixelsA[indexRow][ indexColTemp ];
							indexColTemp--;
						}
					}
                        //blue channel
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						int indexColTemp=RpixelsA[0].length-1;
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							BpixelsTemp [indexRow][indexCol]  = BpixelsA[indexRow][ indexColTemp ];
							indexColTemp--;
						}
					}
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
				}
		return rImage;
	}
	/**
	 *With this method refelct image counterclockwise
	 *@param Aimage image source
	 *@return Image rotate
	 */
	public PWImage flipVertical(PWImage Aimage){
				rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int [][] pixelsA = Aimage.getGrayPixels();
					int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

					int indexRowTemp=pixelsA.length-1;
					for (int indexRow = 0; indexRow < pixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < pixelsA[0].length; indexCol++){
							pixelsTemp[indexRowTemp][indexCol]  = pixelsA[indexRow][ indexCol ];
							//System.out.println(indexRowTemp+" : "+indexCol+" <- "+indexRow+" : "+indexCol);
						}
						indexRowTemp--;
					}
					rImage.setGrayPixels(pixelsTemp);
				}else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
						int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        int [][] GpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        int [][] BpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        //Red channel
					int indexRowRTemp=RpixelsA.length-1;
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							RpixelsTemp[indexRowRTemp][indexCol]  = RpixelsA[indexRow][ indexCol ];
							//System.out.println(indexRowTemp+" : "+indexCol+" <- "+indexRow+" : "+indexCol);
						}
						indexRowRTemp--;
					}
                        //Green channel
					int indexRowGTemp=GpixelsA.length-1;
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							GpixelsTemp[indexRowGTemp][indexCol]  = GpixelsA[indexRow][ indexCol ];
							//System.out.println(indexRowTemp+" : "+indexCol+" <- "+indexRow+" : "+indexCol);
						}
						indexRowGTemp--;
					}
                        //blue channel
					int indexRowBTemp=BpixelsA.length-1;
					for (int indexRow = 0; indexRow < RpixelsA.length; indexRow++){
						for(int indexCol = 0; indexCol < RpixelsA[0].length; indexCol++){
							BpixelsTemp[indexRowBTemp][indexCol]  = BpixelsA[indexRow][ indexCol ];
							//System.out.println(indexRowTemp+" : "+indexCol+" <- "+indexRow+" : "+indexCol);
						}
						indexRowBTemp--;
					}
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
				}
		return rImage;
	}
    /*  public static void main (String [] args){
                try{
                        PWImage a =new PWImage(args[0]);
                        FlipImage po=new FlipImage();
                                po.flipHorizontal(a).writeImage("mirorH"+a.getFileExtension());
								po.flipVertical(a).writeImage("rmirorV"+a.getFileExtension());

                }catch(Exception e){
                        System.out.println("Error:: "+e);
                }
        }*/
}
