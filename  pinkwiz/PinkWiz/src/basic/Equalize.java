package basic;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import basic.*;
import image.*;
import conversion.*;
/**
 * <a src="http://en.wikipedia.org/wiki/Contrast_%28vision%29">Contrast Wikipedia</a>
 * @version 1.0, 14-06-013
 * @author Jefferson Pe\~na Torres
*/
public class Equalize{
		/**
		 * Channels in the image
		 */
        private int[] histoR,histoG,histoB,histoArray;
        /**
         * Temporal Image
         */
        private PWImage inImage;
        /**
        * Constructor
        * 
        */
        public Equalize(){}
        /**
         * This method produce a image whidth the Histogram equalized
         * 
         * @param imageIn a Object PWImage
         * @return new iamge width Histogram equalized
         * @see PWImage  
         * @see Histogram       
         */
        public PWImage calculateEqualize(PWImage imageIn){
                inImage=imageIn.clone();

                int minValue=0;
                int maxValue=inImage.getIntensity()+1;

                histoArray= new int[ maxValue];
                histoR= new int[ maxValue];
                histoG= new int[ maxValue];
                histoB= new int[ maxValue];
                
                if(inImage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        Histogram objHisto=new Histogram(inImage);
                        int [] histo=objHisto.getHistoArray();
                        
                        int[] histoNorm=new int[histo.length];
                        for(int i = 1; i < maxValue; i++) {
                            histoNorm[i] = histoNorm[i-1]+histo[i];
                        }
                        int LUT[] = new int[histo.length];
                        for(int i = 0; i < maxValue; i++) {
                            LUT[i] = (int)Math.floor(((histo.length)*histoNorm[i])/(inImage.getWidth()*inImage.getHeight()));
                        }
                        int [][] pixelsA = inImage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
                        for(int i = 0; i < pixelsA.length; i++) {
                                for(int j = 0; j < pixelsA[0].length; j++) {
                                        pixelsTemp[i][j] = (int)LUT[pixelsA[i][j]];
                                }
                        }
                        inImage.setGrayPixels(pixelsTemp);       
                }else if(inImage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        Histogram objHisto=new Histogram(inImage);
                                int [] histoR=objHisto.getHistoRArray();
                                int [] histoG=objHisto.getHistoGArray();
                                int [] histoB=objHisto.getHistoBArray();                                                                
                        int[] histoNormR=new int[histoR.length];
                        int[] histoNormG=new int[histoR.length];
                        int[] histoNormB=new int[histoR.length];                                                
                        for(int i = 1; i < maxValue; i++) {
                            histoNormR[i] = histoNormR[i-1]+histoR[i];
                            histoNormG[i] = histoNormG[i-1]+histoG[i];
                            histoNormB[i] = histoNormB[i-1]+histoB[i];                                                        
                        }                                
                        int LUTR[] = new int[histoR.length];
                        int LUTG[] = new int[histoR.length];
                        int LUTB[] = new int[histoR.length];                                                
                        for(int i = 0; i < maxValue; i++) {
                            LUTR[i] = (int)Math.floor(((histoR.length)*histoNormR[i])/(inImage.getWidth()*inImage.getHeight()));
                            LUTG[i] = (int)Math.floor(((histoR.length)*histoNormG[i])/(inImage.getWidth()*inImage.getHeight()));                            
                            LUTB[i] = (int)Math.floor(((histoR.length)*histoNormB[i])/(inImage.getWidth()*inImage.getHeight()));                            
                        }
                        int [][] RpixelsA = inImage.getRPixels();
                        int [][] GpixelsA = inImage.getGPixels();
                        int [][] BpixelsA = inImage.getBPixels();                                                
                        int [][] RpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        int [][] GpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];
                        int [][] BpixelsTemp= new int[RpixelsA.length][RpixelsA[0].length];                                                
                        for(int i = 0; i < RpixelsA.length; i++) {
                                for(int j = 0; j < RpixelsA[0].length; j++) {
                                        RpixelsTemp[i][j] = (int)LUTR[RpixelsA[i][j]];
                                        GpixelsTemp[i][j] = (int)LUTG[GpixelsA[i][j]];
                                        BpixelsTemp[i][j] = (int)LUTB[BpixelsA[i][j]];                                                                                
                                }
                        }
                        inImage.setRPixels(RpixelsTemp);
                        inImage.setGPixels(GpixelsTemp);
                        inImage.setBPixels(BpixelsTemp);                                                
                }
                return inImage;
        }
       
}

