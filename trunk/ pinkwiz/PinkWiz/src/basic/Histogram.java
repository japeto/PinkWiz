package basic;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import basic.*;
import conversion.*;
import image.*;
/**
 *A histogram is a graphical representation of the distribution of data.
 * <a src="http://en.wikipedia.org/wiki/Contrast_%28vision%29">Contrast Wikipedia</a> 
 * @version 1.0, 14-06-013
 * @author Jefferson Pe\~na Torres
*/
public class Histogram{
		/**
		 *Channels in the image
		 */
        private int[] histoR,histoG,histoB,histoArray;
        /**
         * Min and max value
         */
        private int maxValue, minValue;
        /**
         * Level dominant in the histogram
         */
        private int dominantLevel,dominantLevelR,dominantLevelG,dominantLevelB;
        /**
         * Temporal Image
         */
        private PWImage inImage;
        /**
        *private Constructor
        */
        private Histogram(){}
        /**
        *Constructor
        *@param imageIn a Object PWImage
        *@see PWImage
        */
        public Histogram(PWImage imageIn){
                inImage=imageIn.clone();
                minValue=0;
                maxValue=inImage.getIntensity()+1;

                histoArray= new int[ maxValue];
                histoR= new int[ maxValue];
                histoG= new int[ maxValue];
                histoB= new int[ maxValue];

                calculateHisto();
        }
        /**
         * This method calculate Histogram by grayscaled images and RGB
         */
        private void calculateHisto(){
                if(inImage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| inImage.getTypePWImage().equals(PWImageType.TYPE_BIN)){

                        int [][] pixelsA = inImage.getGrayPixels();

                        for(int indexRow=0;indexRow<inImage.getHeight();indexRow++){
                                for(int indexCol=0;indexCol<inImage.getWidth();indexCol++){
                                        int value= pixelsA[indexRow][indexCol];
                                        if(value <  maxValue){
                                                histoArray[value]++;
                                        }
                                }
                        }
                        for(int index=0;index<histoArray.length;index++){
							if(dominantLevel<histoArray[index]){
								dominantLevel=index;
							}
						}

                }else if(inImage.getTypePWImage().equals(PWImageType.TYPE_RGB)){

                        int [][] RpixelsA = inImage.getRPixels();
                        int [][] GpixelsA = inImage.getGPixels();
                        int [][] BpixelsA = inImage.getBPixels();


                        int totalPixels=inImage.getHeight()*inImage.getWidth();
                        for(int indexRow=0;indexRow<inImage.getHeight();indexRow++){
                                for(int indexCol=0;indexCol<inImage.getWidth();indexCol++){
                                        int valueR= RpixelsA[indexRow][indexCol];
                                        int valueG= GpixelsA[indexRow][indexCol];
                                        int valueB= BpixelsA[indexRow][indexCol];

//                                        System.out.println("> "+valueR+" "+valueG+" "+valueB);
                                        if(valueR <  maxValue){
                                                histoR[valueR]++;
                                        }
                                        if(valueG <  maxValue){
                                                histoG[valueG]++;
                                        }
                                        if(valueB <  maxValue){
                                                histoB[valueB]++;
                                        }

                                }
                        }
						for(int index=0;index<histoR.length;index++){
							if(dominantLevelR<histoR[index]){
								dominantLevelR=index;
							}
						}
						for(int index=0;index<histoG.length;index++){
							if(dominantLevelG<histoR[index]){
								dominantLevelG=index;
							}
						}
						for(int index=0;index<histoB.length;index++){
							if(dominantLevelB<histoR[index]){
								dominantLevelB=index;
							}
						}

                }
        }
        /**
         * @return dominant gray level
         */
        public int getDominantLevel(){
                return this.dominantLevel;
        }
				/**
				 * Set dominant gray level
				 * @param dominantLevel dominant level value
				 */
				public void  setDominantLevel(int dominantLevel){
					this.dominantLevel=dominantLevel;
				}
        /**
         * @return int dominant red level
         */
        public int getDominantLevelR(){
                return this.dominantLevelR;
        }
				/**
				 * Set dominant red level
				 * @param dominantLevelR dominant red level
				 */
				public void  setDominantLevelR(int dominantLevelR){
					this.dominantLevelR=dominantLevelR;
				}
        /**
         * @return int dominant green level
         */
        public int getDominantLevelG(){
                return this.dominantLevelG;
        }
				/**
				 * Set dominant green level
				 *@param dominantLevelG dominant green level
				 */
				public void  setDominantLevelG(int dominantLevelG){
					this.dominantLevelG=dominantLevelG;
				}
        /**
         * @return int dominant blue level
         */
        public int getDominantLevelB(){
                return this.dominantLevelB;
        }
				/**
				 * Set dominant blu level
				 *@param dominantLevelB dominant blue level
				 */
				public void  setDominantLevelB(int dominantLevelB){
					this.dominantLevelB=dominantLevelB;
				}
        /**
        * With this method obtain maximun value (L-1) inside  range [0, L-1] -> [0,255] return 255
        *@return maximun value
        */
        public int getMaxValue(){return this.maxValue;}
                /**
                * With this method set maximun value (L-1) inside  range [0, L-1] -> [0,255]
                *@param  maxValue maximun value
                */
                public void setMaxValue(int maxValue){
                        this.maxValue=maxValue;
                        calculateHisto();
                }
        /**
        * With this method obtain minimun value (L-1) inside  range [0, L-1] -> [0,255] return 0
        *@return minimun value
        */
        public int getMinValue(){return this.minValue;}
                /**
                * With this method set maximun value (L-1) inside  range [0, L-1] -> [0,255]
                *@param  minValue minimun value
                */
                public void setMinValue(int minValue){
                        this.minValue=minValue;
                        calculateHisto();
                }
        /**
        * With this method obtain the array of values
        *@return histoArray with values
        */
        public int[] getHistoArray(){return this.histoArray;}
                /**
                * With this method set array of values
                *@param  histoArray array with
                */
                public void setHistoArray(int[] histoArray){
                        this.histoArray=histoArray;
                }
        /**
        * With this method obtain the array of values red channel
        *@return histoArray with values
        */
        public int[] getHistoRArray(){return this.histoR;}
                /**
                * With this method set array of values red channel
                *@param  histoArray array with
                */
                public void setHistoRArray(int[] histoArray){
                        this.histoR=histoArray;
                }
        /**
        * With this method obtain the array of values green channel
        *@return histoArray with values
        */
        public int[] getHistoGArray(){return this.histoG;}
                /**
                * With this method set array of values red channel
                *@param  histoArray array with
                */
                public void setHistoGArray(int[] histoArray){
                        this.histoG=histoArray;
                }
        /**
        * With this method obtain the array of values green channel
        *@return histoArray with values
        */
        public int[] getHistoBArray(){return this.histoB;}
                /**
                * With this method set array of values red channel
                *@param  histoArray array with
                */
                public void setHistoBArray(int[] histoArray){
                        this.histoB=histoArray;
                }
        /*public static void main (String [] args){
                try{

                        PWImage a =new PWImage(args[0]);
                        Histogram c =new Histogram(a);

                         System.out.println("<<<<<<<<<<<<RED>>>>>>>>>>>");
                        int[] imgR=c.getHistoRArray();
                        for(int i=0;i<imgR.length;i++){
                                System.out.println("R :: "+i+" "+imgR[i]);
                        }
                         System.out.println("<<<<<<<<<<<<GREEN>>>>>>>>>>>");
                        int[] imgG=c.getHistoGArray();
                        for(int i=0;i<imgG.length;i++){
                                System.out.println("G :: "+i+" "+imgG[i]);
                        }
                         System.out.println("<<<<<<<<<<<<BLUE>>>>>>>>>>>");
                        int[] imgB=c.getHistoBArray();
                        for(int i=0;i<imgB.length;i++){
                                System.out.println("B :: "+i+" "+imgB[i]);
                        }
                }catch(Exception e){
                        System.out.println("Error:: "+e);
                }
        }*/

}

