package basic;
import basic.*;
import conversion.*;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 * Thresholding is the simplest method of image segmentation.
 * From a grayscale image, thresholding can be used to create binary images
 * <a src="http://en.wikipedia.org/wiki/Thresholding_(image_processing)">Threshold Wikipedia</a>
 * @version 1.0, 14-06-013
 * @author Jefferson Pe\~na Torres
*/
public class Threshold{
		/**
		 * Temporal Image
		 */
        private PWImage rImage;
        /**
         * if iamge is P3,Color,RGB change format and binarize
         */
        private ColorToGray toPgm;
		/**
		 * Cosntructor
		 */
        public Threshold(){}
        /**
        * With this method calculates threshold 
        *@param Aimage is PWImage object with Image A
        *@param value int
        *@return a PWImage with result Image A 
        */
        public PWImage binarizeInt(PWImage Aimage,int value){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int pixel = pixelsA[indexRow][indexCol];
                                        if(pixel < value){
                                                pixelsTemp[indexRow][indexCol]=255;
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=0;
                                        }
                                }
                        }
                        rImage.setTypePWImage(PWImageType.TYPE_BIN);
                        
	                        if(!rImage.getFileExtension().equals("jpg"))rImage.setFileExtension("pgm");
	                        
                        rImage.setIntensity(255);
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        toPgm=new ColorToGray();
                        binarizeInt(toPgm.conversion(Aimage,1),value);
                }
                return rImage;
        }
        /**
        * With this method calculates threshold automatic - based histogram
        * @param Aimage image to thresholding
        *@return a PWImage with threshold Image A <PWImageType.TYPE_BIN>
        */
        public PWImage binarizeAutomatic(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int[] histogram =new Histogram(Aimage).getHistoArray();
					int maxValue = histogram.length - 1;
					double result, sum1, sum2, sum3, sum4;
					int threshold;
					int min = 0;
					while ((histogram[min] == 0) && (min < maxValue))
						min++;
					int max = maxValue;
					while ((histogram[max] == 0) && (max > 0))
						max--;
					if (min >= max) {
						threshold = histogram.length / 2;
					}else{
						int movingIndex = min;
						do {
							sum1 = sum2 = sum3 = sum4 = 0.0;
							for (int i = min; i <= movingIndex; i++) {
								sum1 += i * histogram[i];
								sum2 += histogram[i];
							}
							for (int i = (movingIndex + 1); i <= max; i++) {
								sum3 += i * histogram[i];
								sum4 += histogram[i];
							}
							result = (sum1 / sum2 + sum3 / sum4) / 2.0;
							movingIndex++;
						}while((movingIndex + 1) <= result && movingIndex < max - 1);
				
						threshold = (int) Math.round(result);
					}                
		            int [][] pixelsA = Aimage.getGrayPixels();
		            int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

		            for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
						for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
							int pixel = pixelsA[indexRow][indexCol];
							if(pixel > threshold){
								pixelsTemp[indexRow][indexCol]=255;
							}else{
								pixelsTemp[indexRow][indexCol]=0;
							}
						}
		            }
		            rImage.setTypePWImage(PWImageType.TYPE_BIN);
		            rImage.setIntensity(255);
		            rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        toPgm=new ColorToGray();
                        binarizeAutomatic(toPgm.conversion(Aimage,1));
                }
                return rImage;
        }
        /**
        * With this method calculates threshold automatic - otsu method
        * @param Aimage image to thresholding
        *@return a PWImage with threshold Image A <PWImageType.TYPE_BIN>
        */
		public PWImage binarizeOtsu(PWImage Aimage){
				rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
        				int[] histogram =new Histogram(Aimage).getHistoArray();
				// Total number of pixels
				int total = Aimage.getWidth()*Aimage.getHeight();
				float sum = 0;
					for (int t=0 ; t<256 ; t++) sum += t * histogram[t];
					float sumB = 0;
					int wB = 0;
					int wF = 0;
					float varMax = 0;
					int threshold = 0;
					for (int t=0 ; t<256 ; t++) {
					   wB += histogram[t];               // Weight Background
					   if (wB == 0) continue;

					   wF = total - wB;                 // Weight Foreground
					   if (wF == 0) break;

					   sumB += (float) (t * histogram[t]);

					   float mB = sumB / wB;            // Mean Background
					   float mF = (sum - sumB) / wF;    // Mean Foreground

					   // Calculate Between Class Variance
					   float varBetween = (float)wB * (float)wF * (mB - mF) * (mB - mF);

					   // Check if new maximum found
					   if (varBetween > varMax) {
						  varMax = varBetween;
						  threshold = t;
					   }
					}					
					
                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int pixel = pixelsA[indexRow][indexCol];
                                        if(pixel > threshold){
                                                pixelsTemp[indexRow][indexCol]=255;
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=0;
                                        }
                                }
                        }
                        rImage.setTypePWImage(PWImageType.TYPE_BIN);
                        rImage.setIntensity(255);
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        toPgm=new ColorToGray();
                        binarizeOtsu(toPgm.conversion(Aimage,1));
                }
                return rImage;
        }           
        /**
        * With this method calculates threshold automatic - mean method
        * @param Aimage image to thresholding
        * @return a PWImage with threshold Image A <PWImageType.TYPE_BIN>
        */
		public PWImage binarizeMean(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
        				int[] histogram =new Histogram(Aimage).getHistoArray();
                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                int threshold=0,count=0;
								double tot = 0, sum = 0;
								for (int i = 0; i < histogram.length; i++) {
									tot += histogram[i];
									sum += (i * histogram[i]);
								}
								threshold = (int) Math.floor(sum / tot);
                                int pixel = pixelsA[indexRow][indexCol];
                                        if(pixel < threshold){
                                                pixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=255;
                                        }
                                }
                        }
                        rImage.setTypePWImage(PWImageType.TYPE_BIN);
                        rImage.setIntensity(255);
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        toPgm=new ColorToGray();
                        binarizeMean(toPgm.conversion(Aimage,1));
                }
                return rImage;
        }
		                
}
