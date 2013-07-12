package conversion;

import basic.*;
import image.*;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.Math;
/**
 * This class calculated conversion modifications reads a Color image as input
 * and produces a Gray scale image as output. The output is a 'black and white' rendering of the original image,
 * as in a black and white photograph. The quantization formula
 *      -> Standard { Gray = (Red * 0.299 + Green * 0.587 + Blue * 0.114) }
 *      -< Luma { Gray = (Red * 0.2126 + Green * 0.7152 + Blue * 0.0722) }
 *      -> Lightness { Gray= (max(RGB)+min(RGB))/2 }
 *      -> average { Gray = (Red + Green + Blue) / 3 }
 *      -> luminuysity { Gray= 0.21R +0.71G+0.074B }
 *      -> gimp { Gray = (Red * 0.3 + Green * 0.59 + Blue * 0.11) }
 *      -> Maximum decomposition { Gray = Max(Red, Green, Blue) }
 *      -> Minimum decomposition { Gray = Min(Red, Green, Blue) }
 *      -> gray shades { Gray = Integer((((Red + Green + Blue) / 3) / ConversionFactor) + 0.5) * 255 / (NumberOfShades - 1)}
 * getChanels (*.ppm)->(*.pgm)
 *      -> getRedChannel
 *      -> getGChannel
 *      -> getBRhannel
 * http://www.tannerhelland.com/3643/grayscale-image-algorithm-vb6/
 * @version 1.0, 15-06-013
 * @author Jefferson Pe\~na Torres 
 */

public class ColorToGray {
		/**
		 * Image in
		 */
        private PWImage rImage;
		/**
		 * Constructor
		 */
        public ColorToGray(){}
		/**
		 * This method calculate the new value by pixel
		 *@param R red channel
		 *@param G green channel
		 *@param B blue channel
		 *@return new pixel value
		 */
        private int calculatedGray(int value,int R,int G,int B){
                int result=0;
                switch(value) {
                        case 1: //luma,luminuysity
                                result=(int)(R * 0.299 + G * 0.587 + B * 0.114);
                        break;
                        case 2: //Old luma, luminuysity
                                result=(int)(R * 0.2126 + G * 0.7152 + B * 0.0722);
                        break;
                        case 3: //Desaturation or Lightness
                                result=(int)(Math.max(Math.max(R,G),B)+Math.min(Math.min(R,G),B))/2;
                        break;
                        case 4: //Average
                                result= (int)(R+G+B)/3;
                        break;
                        case 5: //Maximum decomposition
                                result= (int)Math.max(Math.max(R,G),B);
                        break;
                        case 6: //Minimum decomposition
                                result=(int) Math.min(Math.min(R,G),B);
                        break;
                        case 7: //Gray Shapes
                                int conversionFactor = 255 / (4/*NumberOfShades*/ - 1);
                                result = (int)((((R+G+B) / 3) / conversionFactor) + 0.5) * conversionFactor;
                        break;
                        case 8: //R
                                result= (int)R;
                        break;
                        case 9: //G
                                result= (int)G;
                        break;
                        case 10://B
                                result= (int)B;
                        break;
                        case 11://Custom values
//                              result=(int)(Rcustom * 0.3 + Gcustom * 0.59 + Bcustom * 0.11);
                        default:
                                System.out.println("ColorToGray::No hay filtro para "+value+"");
                        break;
                }
                return result;
        }
        /**
         *Change the image rgb to grayscale
         *@param Aimage Image in
         *@param value algorithm by conversion
         *@return new image GRAYSCALED
         */
        public PWImage conversion(PWImage Aimage,int value){
//        	System.out.println("conversion");
                rImage=Aimage.clone();
				if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                        int [][] pixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                    pixelsTemp[indexRow][indexCol]=calculatedGray(value,RpixelsA[indexRow][indexCol],GpixelsA[indexRow][indexCol],BpixelsA[indexRow][indexCol]);
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);

                }else{
                        System.out.println("Debe ser una imagen RGB");
                        
                }
//                rImage.setMagicNumber("P2");
                rImage.setTypePWImage(PWImageType.TYPE_GRAYSCALE);
                    if(!rImage.getFileExtension().equals("jpg"))rImage.setFileExtension("pgm");
                rImage.setWidth(Aimage.getWidth());
                rImage.setHeight(Aimage.getHeight());
                rImage.setIntensity(255);
//                rImage.setFileExtension(".pgm");
                return rImage;
        }
//        public static void main (String [] args){
//                try{
//                        PWImage a =new PWImage(args[0]);
//                        ColorToGray hg=new ColorToGray();
//                                hg.conversion(a,1).writeImage("grayStandard.pgm");
//                                hg.conversion(a,2).writeImage("grayLuma.pgm");
//                                hg.conversion(a,3).writeImage("grayDesaturation.pgm");
//                                hg.conversion(a,4).writeImage("grayAverage.pgm");
//                                hg.conversion(a,5).writeImage("grayMaxDecomposition.pgm");
//                                hg.conversion(a,6).writeImage("grayMinDecomposition.pgm");
//                                hg.conversion(a,7).writeImage("grayShapes.pgm");
//                                hg.conversion(a,8).writeImage("grayR.pgm");
//                                hg.conversion(a,9).writeImage("grayG.pgm");
//                                hg.conversion(a,10).writeImage("grayB.pgm");
//                }catch(Exception e){
//                        System.out.println("Error:: "+e);
//                }
//        }
}


