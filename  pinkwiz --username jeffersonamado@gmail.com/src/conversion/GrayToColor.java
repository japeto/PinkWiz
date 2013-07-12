
package conversion;

import basic.*;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 * This class calculated conversion modifications reads a Gray scale image  as input
 * and produces a Color as output.
 * The color image is usually represented by three functions of space.
 * In most color formats, the three functions are for three
 * primary colors such as red, green and blue fr(x,y),fg(x,y)$, and fb(x,y),
 * or some other three parameters such as intensity, hue and saturation, fi(x,y), fh(x,y),
 * and fs(x,y).
 * The display of gray level, pseudo-color and true-color images on a monitor
 * screen through color-map (color lookup table) is illustrated below.
 * <img src="http://fourier.eng.hmc.edu/e161/lectures/image_display_a.gif">
 * <img src="http://fourier.eng.hmc.edu/e161/lectures/image_display_b.gif">
 *
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0,
 * @since 2013-06-15
 */
public class GrayToColor {
		/**
		 * Image to change
		 * @see pinkWiz.PWImage
		 */
        private PWImage rImage;
        /**
         * Palette of color
         */
        private Color[] palette;
        /**
         * Red channel
         */
        private int[][] Rpixels;
        /**
         * green channel
         */
        private int[][] Gpixels;
        /**
         * Blue channel
         */
        private int[][] Bpixels;
		/**
		 * Constructor
		 */
        public GrayToColor(){}
        /**
         * Change color according a pixel value, position and color map
         * @param position x pixel
         * @param position y pixel
         * @param pixel value
         * @param color map
         */
        private void calculatedColor(int x,int y,int pixelValue,int colorMapvalue){

                createColorMap(colorMapvalue);
//
                Color colorTemp = palette[pixelValue%palette.length];
//
//                Rpixels[x][y]=colorTemp.getRed();
//                Gpixels[x][y]=colorTemp.getGreen();
//                Bpixels[x][y]=colorTemp.getBlue();

                if (pixelValue >= 0 &&pixelValue< 42) {
                        Rpixels[x][y]=colorTemp.getRed();
                        Gpixels[x][y]=colorTemp.getGreen();
                        Bpixels[x][y]=0;
                } else if (pixelValue >= 43 &&pixelValue < 84) {
                        Rpixels[x][y]=colorTemp.getGreen();
                        Gpixels[x][y]=colorTemp.getRed();
                        Bpixels[x][y]=0;
                } else if (pixelValue >= 84 && pixelValue < 126) {
                        Rpixels[x][y]=0;
                        Gpixels[x][y]=colorTemp.getGreen();
                        Bpixels[x][y]=colorTemp.getBlue();
                } else if (pixelValue >= 126 && pixelValue < 168) {
                        Rpixels[x][y]=0;
                        Gpixels[x][y]=colorTemp.getBlue();
                        Bpixels[x][y]=colorTemp.getGreen();
                } else if (pixelValue >= 168 && pixelValue < 210) {
                        Rpixels[x][y]=colorTemp.getRed();
                        Gpixels[x][y]=0;
                        Bpixels[x][y]=colorTemp.getGreen();
                } else if (pixelValue >= 210 && pixelValue < 226) {
                        Rpixels[x][y]=colorTemp.getGreen();
                        Gpixels[x][y]=0;
                        Bpixels[x][y]=colorTemp.getRed();
                }
        }
        /***
         * Set color map implemeted:
         * Red Light->http://www.w3schools.com/html/html_colors.asp
         * Shades of Gray->http://www.w3schools.com/html/html_colors.asp
         * @apram value
         */
        private void createColorMap(int value){
                switch(value) {
                        case 1://Red Light->http://www.w3schools.com/html/html_colors.asp

                                        Color[] redLightPalette= {
                                        new Color(0,0,0),new Color(8,0,0),new Color(16,0,0),
                                        new Color(24,0,0),new Color(32,0,0),new Color(40,0,0),
                                        new Color(48,0,0),new Color(56,0,0),new Color(64,0,0),
                                        new Color(72,0,0),new Color(80,0,0),new Color(88,0,0),
                                        new Color(96,0,0),new Color(104,0,0),new Color(112,0,0),
                                        new Color(120,0,0),new Color(128,0,0),new Color(136,0,0),
                                        new  Color(144,0,0),new Color(152,0,0),new Color(160,0,0),
                                        new  Color(168,0,0),new Color(176,0,0),new Color(184,0,0),
                                        new Color(192,0,0),new Color(200,0,0),new Color(208,0,0),
                                        new Color(216,0,0),new Color(224,0,0),new Color(232,0,0),
                                        new Color(240,0,0),new Color(248,0,0),new Color(255,0,0),
                                        new Color(255,255,255)};
                                this.palette=redLightPalette;

                        break;
                        case 2://Shades of Gray->http://www.w3schools.com/html/html_colors.asp

                                        Color[] shadesGrayPalette={
                                        new Color(0,0,0),new Color(8,8,8),new Color(16,16,16),
                                        new Color(24,24,24),new Color(32,32,32),new Color(40,40,40),
                                        new Color(48,48,48),new Color(56,56,56),new Color(64,64,64),
                                        new Color(72,72,72),new Color(80,80,80),new Color(88,88,88),
                                        new Color(96,96,96),new Color(104,104,104),new Color(112,112,112),
                                        new Color(120,120,120),new Color(128,128,128),new Color(136,136,136),
                                        new Color(144,144,144),new Color(152,152,152),new Color(160,160,160),
                                        new Color(168,168,168),new Color(176,176,176),new Color(184,184,184),
                                        new Color(192,192,192),new Color(200,200,200),new Color(208,208,208),
                                        new Color(216,216,216),new Color(224,224,224),new Color(232,232,232),
                                        new Color(240,240,240),new Color(248,248,248),
                                        new Color(255,255,255)};
                                this.palette=shadesGrayPalette;
                        break;
                        case 3:
                                Color[] dynamicPalette= new Color[256];
                                        for(int i=0;i<dynamicPalette.length;i++){
                                                dynamicPalette[i]=new Color(i,0,0);
                                        }
                                this.palette=dynamicPalette;
                        break;
                        default:
                                System.out.println("PpmTopgm:: No hay ColorMap");
                        break;
                }
        }
        /**
         *Change the image grayscaled to rgb
         *@param Aimage Image in
         *@param colorMap change teh color map
         *@return new image RGB
         */
        public PWImage conversion(PWImage Aimage,int colorMap){
//                	System.out.println("conversion");
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA= Aimage.getGrayPixels();

                                Rpixels=new int[pixelsA.length][pixelsA[0].length];
                                Gpixels=new int[pixelsA.length][pixelsA[0].length];
                                Bpixels=new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        calculatedColor(indexRow,indexCol,pixelsA[indexRow][indexCol],colorMap);
                                }
                        }
                        rImage.setRPixels(Rpixels);
                        rImage.setGPixels(Gpixels);
                        rImage.setBPixels(Bpixels);

                }else{
                        System.out.println("Debe ser una imagen GRAYSCALE O BIN");
                }
                        rImage.setTypePWImage(PWImageType.TYPE_RGB);
    	                    if(!rImage.getFileExtension().equals("jpg"))rImage.setFileExtension("ppm");
                        rImage.setWidth(Aimage.getWidth());
                        rImage.setHeight(Aimage.getHeight());
                        rImage.setIntensity(255);
//	                rImage.setFileExtension(".ppm");
                return rImage;
        }
//        public static void main (String [] args){
//                try{
//                        PWImage a =new PWImage(args[0]);
//                        GrayToColor hg=new GrayToColor();
//                                hg.conversion(a,1).writeImage("redLight.ppm");
//                                hg.conversion(a,2).writeImage("ShadesGray.ppm");
//                                hg.conversion(a,3).writeImage("dinamic.ppm");
//                }catch(Exception e){
//                        System.out.println("Error:: "+e);
//                }
//        }

}
