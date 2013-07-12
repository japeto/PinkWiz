
package geometric;
import image.*;

import image.*;
import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.Throwable;
/**
 * This class implents transform to images
 * Homotecia, zoom
 * Traslación
 * Rotación, transformada de Hotelling
 * Warping , corrección de distorsiones
 * Morphing
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0,
 * @since 2013-06-19
 */

public class Geometrics{

        private PWImage rImage;
        /**
        * Constructor
        */
        public Geometrics(){}
        /**
        * With this method scale image
        * outPixel[dx,dy] = inPixel[x_Pixel * inWidth /outWidth, y_Pixel *inHeight /outHeight]
        *@param Aimage is PWImage object with Image A
        *@param value int
        *@return a Image scaled value
        */
        public PWImage zoomA(PWImage Aimage,int value){
                rImage=Aimage.clone();


                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[value * pixelsA.length][value * pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsTemp[0].length;indexCol++){
                                        pixelsTemp[indexRow][indexCol]= pixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                        int [][] RpixelsTemp= new int[value * RpixelsA.length][value * RpixelsA[0].length];
                        int [][] GpixelsTemp= new int[value * RpixelsA.length][value * RpixelsA[0].length];
                        int [][] BpixelsTemp= new int[value * RpixelsA.length][value * RpixelsA[0].length];
                        //Red channel
                        for(int indexRow=0;indexRow<RpixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsTemp[0].length;indexCol++){
                                        RpixelsTemp[indexRow][indexCol]= RpixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsTemp[0].length;indexCol++){
                                        GpixelsTemp[indexRow][indexCol]= GpixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<RpixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsTemp[0].length;indexCol++){
                                        BpixelsTemp[indexRow][indexCol]= BpixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }
                return rImage;
        }
        /**
        *
        */
        public PWImage  zoomR(PWImage Aimage,int value){
               rImage=Aimage.clone();
                if(value<=0)value=1;
                
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length/value][pixelsA[0].length/value];

                        for(int indexRow=1;indexRow<pixelsTemp.length-1;indexRow++){
                                for(int indexCol=1;indexCol<pixelsTemp[0].length-1;indexCol++){
//                                        ( P[ i − 1, j −1] + P[i −1, j ] + P[ i , j − 1] + P[i , j ]) / 4
                                pixelsTemp[indexRow-1][indexCol-1]= (pixelsA[indexRow-1][indexCol-1]
                                                                     +pixelsA[indexRow-1][indexCol]
                                                                     +pixelsA[indexRow][indexCol-1]
                                                                     +pixelsA[indexRow][indexCol])/4;
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        int [][] RpixelsA= Aimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                        int [][] RpixelsTemp= new int[value * RpixelsA.length][value * RpixelsA[0].length];
                        int [][] GpixelsTemp= new int[value * RpixelsA.length][value * RpixelsA[0].length];
                        int [][] BpixelsTemp= new int[value * RpixelsA.length][value * RpixelsA[0].length];
                        //Red channel
                        for(int indexRow=0;indexRow<RpixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsTemp[0].length;indexCol++){
                                        RpixelsTemp[indexRow][indexCol]= RpixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsTemp[0].length;indexCol++){
                                        GpixelsTemp[indexRow][indexCol]= GpixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<RpixelsTemp.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsTemp[0].length;indexCol++){
                                        BpixelsTemp[indexRow][indexCol]= BpixelsA[indexRow/value][indexCol/value];
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }
                return rImage;
        }        
        
        /**
        *
        */
        public PWImage  translate(){return rImage;}
        /**
        *
        */
        /*public static void main (String [] args){
                try{
                        PWImage a =new PWImage(args[0]);
                        int value = Integer.parseInt(args[1]);

                        Geometrics po=new Geometrics();
                                po.zoomA(a,value).writeImage("zoomA"+value+".ppm");;
                                po.zoomR(a,value).writeImage("zoomR"+value+".ppm");;                                

                }catch(Exception e){
                        System.out.println("Error:: "+e);
                }
        }*/


}



