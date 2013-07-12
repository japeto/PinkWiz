package convulsion;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.Math;
/**
 *
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0, 22-06-013
 * @since 2013-06-22
*/
public  class Convulsion{
		/**
		 * Image to change
		 */
        private PWImage rImage;
		/**
		 * Constructor
		 */
        public Convulsion(){}
        
        public PWImage getGradient(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int outputWidth = pixelsA.length-1;
                        int outputHeight = pixelsA[0].length-1;
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 0; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 0; indexCol < outputHeight; indexCol++){
                                        int Dpi=Math.abs(pixelsA[indexRow+1][indexCol]-pixelsA[indexRow][indexCol]);
                                        int Dpj=Math.abs(pixelsA[indexRow][indexCol+1]-pixelsA[indexRow][indexCol]);
                                        
                                        pixelsTemp [indexRow][indexCol]=(int) Math.sqrt(Math.pow(Dpi,2)+Math.pow(Dpj,2));
                                        //pixelsTemp [indexRow][indexCol]=(int)(1/2)*Math.sqrt(Math.pow(Dpi,2)+Math.pow(Dpj,2));
                                        //pixelsTemp [indexRow][indexCol]=(int) Math.max(Dpi+Dpj, 255);                                        
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                
                }
                return rImage;
        }
        public PWImage getLaplace(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int outputWidth = pixelsA.length-1;
                        int outputHeight = pixelsA[0].length-1;
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 1; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 1; indexCol < outputHeight; indexCol++){
                                        int result=4*pixelsA[indexRow][indexCol]
                                                                -pixelsA[indexRow-1][indexCol]
                                                                -pixelsA[indexRow+1][indexCol]
                                                                -pixelsA[indexRow][indexCol+1]
                                                                -pixelsA[indexRow][indexCol-1];
                                                                
                                        if(result<0) result=0;
                                        
                                        pixelsTemp [indexRow][indexCol]= result;

                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                
                }
                return rImage;
        }
        public PWImage getSoulager(PWImage Aimage,int value){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int outputWidth = pixelsA.length-1;
                        int outputHeight = pixelsA[0].length-1;
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 0; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 0; indexCol < outputHeight; indexCol++){
//                                        int Dpi=Math.abs(pixelsA[indexRow+1][indexCol]-pixelsA[indexRow][indexCol]);
//                                        int Dpj=Math.abs(pixelsA[indexRow][indexCol+1]-pixelsA[indexRow][indexCol]);
                                        
                                        pixelsTemp [indexRow][indexCol]=(value-1/2)+((pixelsA[indexRow+1][indexCol]-pixelsA[indexRow][indexCol])/2);
                                        
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                
                }
                return rImage;
        }
		public PWImage getAverageArimetic(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int outputWidth = pixelsA.length-1;
                        int outputHeight = pixelsA[0].length-1;
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 1; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 1; indexCol < outputHeight; indexCol++){
                                        int result=     pixelsA[indexRow-1][indexCol-1]/9
                                                        +pixelsA[indexRow-1][indexCol]/9
                                                        +pixelsA[indexRow-1][indexCol+1]/9
                                                        +pixelsA[indexRow][indexCol-1]/9
                                                        +pixelsA[indexRow][indexCol]/9
                                                        +pixelsA[indexRow+1][indexCol+1]/9
                                                        +pixelsA[indexRow-1][indexCol-1]/9                                                       
                                                        +pixelsA[indexRow-1][indexCol+1]/9
                                                        +pixelsA[indexRow+1][indexCol+1]/9;                                                        
                                                                
                                        if(result<0) result=0;
                                        
                                        pixelsTemp [indexRow][indexCol]= (int)result;
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                
                }
                return rImage;
        }  
        public PWImage getAverageWeighted(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int outputWidth = pixelsA.length-1;
                        int outputHeight = pixelsA[0].length-1;
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 1; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 1; indexCol < outputHeight; indexCol++){
                                        int result=(int)Math.pow(pixelsA[indexRow-1][indexCol-1]
                                                        *pixelsA[indexRow-1][indexCol]
                                                        *pixelsA[indexRow-1][indexCol+1]
                                                        *pixelsA[indexRow][indexCol-1]
                                                        *pixelsA[indexRow][indexCol]
                                                        *pixelsA[indexRow+1][indexCol+1]
                                                        *pixelsA[indexRow-1][indexCol-1]
                                                        *pixelsA[indexRow-1][indexCol+1]
                                                        *pixelsA[indexRow+1][indexCol+1],1/outputWidth*outputHeight);                                                        
                                                                
                                        if(result<0) result=0;
                                        
                                        pixelsTemp [indexRow][indexCol]= result;
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                
                }
                return rImage;
        } 
        public PWImage getAverageArmonic(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int outputWidth = pixelsA.length-1;
                        int outputHeight = pixelsA[0].length-1;
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 1; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 1; indexCol < outputHeight; indexCol++){
                                        double result=(1/pixelsA[indexRow-1][indexCol-1]
                                                        +1/pixelsA[indexRow-1][indexCol]
                                                        +1/pixelsA[indexRow-1][indexCol+1]
                                                        +1/pixelsA[indexRow][indexCol-1]
                                                        +1/pixelsA[indexRow][indexCol]
                                                        +1/pixelsA[indexRow+1][indexCol+1]
                                                        +1/pixelsA[indexRow-1][indexCol-1]
                                                        +1/pixelsA[indexRow-1][indexCol+1]
                                                        +1/pixelsA[indexRow+1][indexCol+1]);                                                        
                                        if(result<0) result=0;
                                        
                                        pixelsTemp [indexRow][indexCol]=(int)result;                                                        
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                
                }
                return rImage;
        }          
}
