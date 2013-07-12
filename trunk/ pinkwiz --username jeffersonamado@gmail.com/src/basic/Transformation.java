package basic;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.Math;
/**
* The Transformation class implements pow and logarithmic algoritms apply to images
* @version 1.0, 25-06-013
* @author Jefferson Pe\~na Torres
*/
public class Transformation{
		/**
		 * Temporal Image
		 */
        private PWImage rImage;

		/**
		 * Cosntructor
		 */
        public Transformation(){}
        /**
        * With this method calculates T (r ) = cLog (1 + r )
        * @param Aimage image to log algoritm
        *@param valueR c value 
        *@return a PWImage with result Image
        */
        public PWImage tLogaritm(PWImage Aimage,int valueR){
//                if(0<valueR|| valueR>255) valueR=55;
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
		                int [][] pixelsA= Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int pixel = valueR*(int)Math.log(1+pixelsA[indexRow][indexCol]);
//                                        if(pixel < valueR){
                                                pixelsTemp[indexRow][indexCol]=pixel;
//                                        }
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
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int pixel = valueR*(int)Math.log(1+RpixelsA[indexRow][indexCol]);
//                                        if(pixel < valueR){
                                                RpixelsTemp[indexRow][indexCol]=pixel;
//                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int pixel = valueR*(int)Math.log(1+GpixelsA[indexRow][indexCol]);
//                                        if(pixel < valueR){
                                                GpixelsTemp[indexRow][indexCol]=pixel;
//                                        }
                                }
                        }
                        //blue channel
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int pixel = valueR*(int)Math.log(1+BpixelsA[indexRow][indexCol]);
//                                        if(pixel < valueR){
                                                BpixelsTemp[indexRow][indexCol]=pixel;
//                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }
                return rImage;
        }
/**
        * With this method calculates T (r) = c * (r ^ e)
        *@param  Aimage is image input
        *@param valueC c value 
        *@param valueE exponent value                
        *@return a PWImage with result Image
        */
        public PWImage tPow(PWImage Aimage,int valueC,float valueE){
//                if(0<valueC|| valueC>255) valueC=55;
                        
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int pixel =valueC*(int)Math.pow(pixelsA[indexRow][indexCol], valueE);
                                        //if(pixel < valueR){
                                                pixelsTemp[indexRow][indexCol]=pixel;
                                        //}
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
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int pixel =valueC*(int)Math.pow(RpixelsA[indexRow][indexCol], valueE);
//                                        if(pixel < valueR){
                                                RpixelsTemp[indexRow][indexCol]=pixel;
//                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int pixel =valueC*(int)Math.pow(GpixelsA[indexRow][indexCol], valueE);
//                                        if(pixel < valueR){
                                                GpixelsTemp[indexRow][indexCol]=pixel;
//                                        }
                                }
                        }
                        //blue channel
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int pixel =valueC*(int)Math.pow(BpixelsA[indexRow][indexCol], valueE);
//                                        if(pixel < valueR){
                                                BpixelsTemp[indexRow][indexCol]=pixel;
//                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }
                return rImage;
        }        
        /*public static void main (String [] args){
                try{

                        PWImage a =new PWImage(args[0]);
                        Transformation thr = new Transformation();
                                        thr.TLogaritm(a,Integer.parseInt(args[1])).writeImage("T1.pgm");
                                        thr.TPow(a,Integer.parseInt(args[1]),Integer.parseInt(args[1])).writeImage("T2.pgm");                                        
                }catch(Exception e){
                        System.out.println("Error:: "+e);
                }
        }*/
}
