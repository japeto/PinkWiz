 package basic;

import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 * This group of operators performs a boolean operation between two images
 * to produce a third image.
 * The corresponding pixels from the two source images are combined
 * according to the operation selected
 * and stored in the result image. The result image can be reversed.
 * -> AND The two pixel values are combined with the Boolean AND operation.
 *      R(x,y):= A(x,y) AND B(x,y)
 * -> OR The two pixel values are combined with the Boolean OR operation.
 *      R(x,y):= A(x,y) OR B(x,y)
 * -> XOR The two pixel values are combined with the Boolean XOR operation.
 *      R(x,y):= A(x,y) XOR B(x,y)
 * ->MAX  The larger of the two pixel values.
 * ->MIN  The smaller of the two pixel values.
 * ->NOT
 *
 * @author      Jefferson Amado Pe\~na Torres <jeffersonamado@gmail.com>
 * @version     1.0                 (current version number of program)
 * @since       2013-06-11          (the version of the package this class was first added to)
 */

public class Logical{
		/**
		 * Temporal Image
		 */
        private PWImage rImage;
        /**
         *Constructor
         */
        public Logical(){}
        /**
        * With this method calculates and
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A and Image B pixel to pixel
        */
        public PWImage and(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();
               int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());
                
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
//                                System.out.println("& "+(pixelsA[indexRow][indexCol] & pixelsB[indexRow][indexCol]));
                                pixelsTemp[indexRow][indexCol]=pixelsA[indexRow][indexCol]&pixelsB[indexRow][indexCol];

                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);

                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){

                        int [][] RpixelsA= Aimage.getRPixels();
                                int [][] RpixelsB= Bimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                                int [][] GpixelsB= Bimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                                int [][] BpixelsB=Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[outHeight][outWidth];
                        int [][] GpixelsTemp= new int[outHeight][outWidth];
                        int [][] BpixelsTemp= new int[outHeight][outWidth];
                        //Red channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        RpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]&RpixelsB[indexRow][indexCol];

                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        GpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]&RpixelsB[indexRow][indexCol];

                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        BpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]&RpixelsB[indexRow][indexCol];

                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);

                }
                return rImage;
        }
        /**
        * With this method calculates or
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A or Image B pixel to pixel
        */
        public PWImage or(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();
               int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());
                
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                pixelsTemp[indexRow][indexCol]=pixelsA[indexRow][indexCol] | pixelsB[indexRow][indexCol];

                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);

                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){

                        int [][] RpixelsA= Aimage.getRPixels();
                                int [][] RpixelsB= Bimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                                int [][] GpixelsB= Bimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                                int [][] BpixelsB=Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[outHeight][outWidth];
                        int [][] GpixelsTemp= new int[outHeight][outWidth];
                        int [][] BpixelsTemp= new int[outHeight][outWidth];
                        //Red channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        RpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol] | RpixelsB[indexRow][indexCol];

                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        GpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol] | RpixelsB[indexRow][indexCol];

                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        BpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol] | RpixelsB[indexRow][indexCol];

                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);

                }else{
                  System.out.println("++Logico:: NOT SUPPORT \n");
                }
                return rImage;
        }
        /**
        * With this method calculates and
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A and Image B pixel to pixel
        */
        public PWImage xor(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();
               int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());
                
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        pixelsTemp[indexRow][indexCol]=pixelsA[indexRow][indexCol] ^ pixelsB[indexRow][indexCol];

                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);

                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){

                        int [][] RpixelsA= Aimage.getRPixels();
                                int [][] RpixelsB= Bimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                                int [][] GpixelsB= Bimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                                int [][] BpixelsB=Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[outHeight][outWidth];
                        int [][] GpixelsTemp= new int[outHeight][outWidth];
                        int [][] BpixelsTemp= new int[outHeight][outWidth];
                        //Red channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        RpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]^RpixelsB[indexRow][indexCol];

                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        GpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]^RpixelsB[indexRow][indexCol];

                                }
                        }
                        //Blue channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        BpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]^RpixelsB[indexRow][indexCol];

                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);

                }
                return rImage;
        }
        /**
        * With this method calculates max
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Max(Image A , Image B)
        */
        public PWImage max(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();
               int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());
                
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        if(pixelsA[indexRow][indexCol]>pixelsB[indexRow][indexCol]){
                                                pixelsTemp[indexRow][indexCol]=pixelsA[indexRow][indexCol];
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=pixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){

                        int [][] RpixelsA= Aimage.getRPixels();
                                int [][] RpixelsB= Bimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                                int [][] GpixelsB= Bimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                                int [][] BpixelsB=Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[outHeight][outWidth];
                        int [][] GpixelsTemp= new int[outHeight][outWidth];
                        int [][] BpixelsTemp= new int[outHeight][outWidth];
                        //Red channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        if(RpixelsA[indexRow][indexCol] > RpixelsB[indexRow][indexCol]){
                                                RpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol];
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=RpixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        if(GpixelsA[indexRow][indexCol]>GpixelsB[indexRow][indexCol]){
                                                GpixelsTemp[indexRow][indexCol]=GpixelsA[indexRow][indexCol];
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=GpixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        if(BpixelsA[indexRow][indexCol]>BpixelsB[indexRow][indexCol]){
                                                BpixelsTemp[indexRow][indexCol]=BpixelsA[indexRow][indexCol];
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=BpixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }
                return rImage;
        }
        /**
        * With this method calculates min
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Min(Image A , Image B)
        */
        public PWImage min(PWImage Aimage,PWImage Bimage){
                 rImage=Aimage.clone();
               int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());
                
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        if(pixelsA[indexRow][indexCol]<pixelsB[indexRow][indexCol]){
                                                pixelsTemp[indexRow][indexCol]=pixelsA[indexRow][indexCol];
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=pixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){

                        int [][] RpixelsA= Aimage.getRPixels();
                                int [][] RpixelsB= Bimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                                int [][] GpixelsB= Bimage.getGPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                                int [][] BpixelsB=Aimage.getBPixels();

                        int [][] RpixelsTemp= new int[outHeight][outWidth];
                        int [][] GpixelsTemp= new int[outHeight][outWidth];
                        int [][] BpixelsTemp= new int[outHeight][outWidth];
                        //Red channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        if(RpixelsA[indexRow][indexCol] < RpixelsB[indexRow][indexCol]){
                                                RpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol];
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=RpixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){

                                        if(GpixelsA[indexRow][indexCol]<GpixelsB[indexRow][indexCol]){
                                                GpixelsTemp[indexRow][indexCol]=GpixelsA[indexRow][indexCol];
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=GpixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        if(BpixelsA[indexRow][indexCol]<BpixelsB[indexRow][indexCol]){
                                                BpixelsTemp[indexRow][indexCol]=BpixelsA[indexRow][indexCol];
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=BpixelsB[indexRow][indexCol];
                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }
                return rImage;
        }
        /**
        * With this method calculates not
        *@param Aimage is PWImage object with Image A
        *@return a PWImage with result Min(Image A , Image B)
        */
        public PWImage not(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
//                                        int value= pixelsA[indexRow][indexCol];
//                                        if(value >0){
                                        pixelsTemp[indexRow][indexCol]=~(pixelsA[indexRow][indexCol]);
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
                                        RpixelsTemp[indexRow][indexCol]=~(RpixelsA[indexRow][indexCol]);
                                        GpixelsTemp[indexRow][indexCol]=~(GpixelsA[indexRow][indexCol]);
                                        BpixelsTemp[indexRow][indexCol]=~(BpixelsA[indexRow][indexCol]);                                                                                
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
                        PWImage b =new PWImage(args[1]);
                        Logicos po=new Logicos();
                                po.and(a,b).writeImage("and.ppm");
                                po.or(a,b).writeImage("or.ppm");
                                po.xor(a,b).writeImage("xor.ppm");
                                po.max(a,b).writeImage("max.ppm");
                                po.min(a,b).writeImage("min.ppm");
                                po.not(a).writeImage("not.ppm");

                }catch(Exception e){
                        System.out.println("Error:: "+e);
                }
        }*/
}

