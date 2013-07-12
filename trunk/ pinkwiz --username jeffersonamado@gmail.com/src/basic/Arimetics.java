package basic;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 * The basic arithmetic operations are addition, subtraction, multiplication and division,
 * although this subject also includes more advanced operations, such as manipulations of percentages,
 * square roots, exponentiation, and logarithmic functions.
 * Arithmetic is performed according to an order of operations.
 *
 *- Suma: C(x,y) = A(x,y) + B(x,y)
 *      f(x,y)=(Ax + Bx,Ay + By)
 *- Suma: C(x,y) = A(x,y) + I
 *      f(x,y)=(Ax + I,Ay + I)
 *  fusion:C(x,y) = (A(x,y) + B(x,y))/2
 *      f(x,y)=(Ax+Bx,Ay+By) / 2
 *- Resta: C(x,y) = A(x,y) - B(x,y)
 *      f(x,y)=(Ax + Bx,Ay - By)
 *- Resta: C(x,y) = A(x,y) - I
 *      f(x,y)=(Ax - I,Ay - I)
 *- Producto: C(x,y) = A(x,y) * B(x,y)
 *      f(x,y)= (Ax * Bx,Ay * By)
 *- Producto: C(x,y) = A(x,y) * I
 *      f(x,y)= (Ax * I,Ay * I)
 *- División: C(x,y) = A(x,y) / B(x,y)
 *      f(x,y)= (Ax/Bx, Ay/By)
 *- Negative C(x,y) = ~A(x,y)
 *      f(x,y)= (-Ax, -Ay)
 *
 * @author  Jefferson Amado Pe\~na Torres <jeffersonamado@gmail.com>
 * @version 1.0, jun 2013
 * @see PWImage
 * @see PWImageType
 * @since 2013-06-11
*/
public class Arimetics{
		/**
		 * Temporal Image
		 */
        private PWImage rImage;
		/**
		 * Constructor
		 */
        public Arimetics(){}
        /**
        * With this method calculates sum
        *@param Aimage is PWImage object with Image A
        *@param value int
        *@return a PWImage with result Image A + I
        */
        public PWImage sum(PWImage Aimage,int value){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int sum = pixelsA[indexRow][indexCol]+value;
                                        if(sum > Aimage.getIntensity()){
                                                pixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=sum;
                                        }
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
                                        int sum = RpixelsA[indexRow][indexCol]+value;
                                        if(sum > Aimage.getIntensity()){
                                                RpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int sum = GpixelsA[indexRow][indexCol]+value;
                                        if(sum > Aimage.getIntensity()){
                                                GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        //blue channel
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int sum = BpixelsA[indexRow][indexCol]+value;
                                        if(sum > Aimage.getIntensity()){
                                                BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=sum;
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
        * With this method calculates rest
        *@param Aimage is PWImage object with Image A
        *@param value int
        *@return a PWImage with result Image A + I
        */
        public PWImage rest(PWImage Aimage,int value){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int result = pixelsA[indexRow][indexCol]-value;

                                        if(result <0){
                                                pixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=result;
                                        }
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
                                        int result = RpixelsA[indexRow][indexCol]-value;
                                        if(result <0){
                                                RpixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=result;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int result = GpixelsA[indexRow][indexCol]-value;
                                        if(result <0){
                                                GpixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=result;
                                        }
                                }
                        }
                        //blue channel
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int result = BpixelsA[indexRow][indexCol]-value;
                                        if(result <0){
                                                BpixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=result;
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
        * With this method calculates rest
        *@param Aimage is PWImage object with Image A
        *@param value int
        *@return a PWImage with result Image A + I
        */
        public PWImage multiplicate(PWImage Aimage,int value){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        int result = pixelsA[indexRow][indexCol]*value;
                                        if(result > Aimage.getIntensity()){
                                                pixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=result;
                                        }
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
                                        int result = RpixelsA[indexRow][indexCol]*value;
                                        if(result > Aimage.getIntensity()){
                                                RpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=result;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int result = GpixelsA[indexRow][indexCol]*value;
                                        if(result > Aimage.getIntensity() ){
                                                GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=result;
                                        }
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        int result = BpixelsA[indexRow][indexCol]*value;
                                        if(result > Aimage.getIntensity()){
                                                BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=result;
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
        * With this method calculates sum
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A + Image B
        */
        public PWImage sum(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();
                int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int sum = pixelsA[indexRow][indexCol]+pixelsB[indexRow][indexCol];
                                        if(sum > Aimage.getIntensity()){
                                                pixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
//                        System.out.println("++PuntualOp::end Sum P2 image\n");
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                       // System.out.println("++PuntualOp:: Sum P3 image\n");
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
                                        int sum = RpixelsA[indexRow][indexCol]+RpixelsB[indexRow][indexCol];
                                        if(sum > Aimage.getIntensity()){
                                                RpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int sum = GpixelsA[indexRow][indexCol]+GpixelsB[indexRow][indexCol];
                                        if(sum > Aimage.getIntensity()){
                                                GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        //blue channel
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int sum = BpixelsA[indexRow][indexCol]+BpixelsB[indexRow][indexCol];
                                        if(sum > Aimage.getIntensity()){
                                                BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                        //System.out.println("++PuntualOp:: End Sum P3 image\n");
                }else{
                  System.out.println("++PuntualOp:: NOT SUPPORT \n");
                }
                return rImage;
        }
		/**
        * With this method calculates sum
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A + Image B
        */
        public PWImage fusion(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();

                int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int sum = (pixelsA[indexRow][indexCol]+pixelsB[indexRow][indexCol])/2;
                                        if(sum > Aimage.getIntensity()){
                                                pixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                    //    System.out.println("++PuntualOp::end Sum P2 image\n");
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
//                        System.out.println("++PuntualOp:: Sum P3 image\n");
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
                                        int sum = (RpixelsA[indexRow][indexCol]+RpixelsB[indexRow][indexCol])/2;
                                        if(sum > Aimage.getIntensity()){
                                                RpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int sum = (GpixelsA[indexRow][indexCol]+GpixelsB[indexRow][indexCol])/2;
                                        if(sum > Aimage.getIntensity()){
                                                GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        //blue channel
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int sum = (BpixelsA[indexRow][indexCol]+BpixelsB[indexRow][indexCol])/2;
                                        if(sum > Aimage.getIntensity()){
                                                BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=sum;
                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
//                        System.out.println("++PuntualOp:: End Sum P3 image\n");
                }else{
                  System.out.println("++PuntualOp:: NOT SUPPORT \n");
                }
                return rImage;
        }
       /**
        * With this method calculates rest
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A - Image B
        */
        public PWImage rest(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();

                int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int rest = pixelsA[indexRow][indexCol]-pixelsB[indexRow][indexCol];
                                        if(rest < 0){
                                                pixelsTemp[indexRow][indexCol]=255;
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=rest;
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
                                        int rest = RpixelsA[indexRow][indexCol]-RpixelsB[indexRow][indexCol];
                                        if(rest < 0 ){
                                                RpixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=rest;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int rest = GpixelsA[indexRow][indexCol]-GpixelsB[indexRow][indexCol];
                                        if(rest < 0 ){
                                                GpixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=rest;
                                        }
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int rest = BpixelsA[indexRow][indexCol]-BpixelsB[indexRow][indexCol];
                                        if(rest < 0 ){
                                                BpixelsTemp[indexRow][indexCol]=0;
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=rest;
                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }else{
                  System.out.println("++PuntualOp:: NOT SUPPORT \n");
                }
                return rImage;
        }
        /**
        * With this method calculates product
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A * Image B
        */
        public PWImage multiplicate(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();

                int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int result=(pixelsA[indexRow][indexCol]*pixelsB[indexRow][indexCol]);
                                        if(result > Aimage.getIntensity()){
                                                pixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=(result/255);
                                        }
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
                }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB) && Bimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        int [][] RpixelsA= Aimage.getRPixels();
                                int [][] RpixelsB= Bimage.getRPixels();
                        int [][] GpixelsA= Aimage.getGPixels();
                                int [][] GpixelsB= Bimage.getRPixels();
                        int [][] BpixelsA= Aimage.getBPixels();
                                int [][] BpixelsB= Bimage.getRPixels();
                        int [][] RpixelsTemp= new int[outHeight][outWidth];
                        int [][] GpixelsTemp= new int[outHeight][outWidth];
                        int [][] BpixelsTemp= new int[outHeight][outWidth];
                        //Red channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int result=(RpixelsA[indexRow][indexCol]*RpixelsB[indexRow][indexCol]);
                                        if(result > Aimage.getIntensity()){
                                                RpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=(result/255);
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int result=(GpixelsA[indexRow][indexCol]*GpixelsB[indexRow][indexCol]);
                                        if(result > Aimage.getIntensity()){
                                                GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=(result/255);
                                        }
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        int result=(BpixelsA[indexRow][indexCol]*BpixelsB[indexRow][indexCol]);
                                        if(result > Aimage.getIntensity()){
                                                BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity();
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=(result/255);
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
        * With this method calculates division
        *@param Aimage is PWImage object with Image A
        *@param Bimage is PWImage object with Image B
        *@return a PWImage with result Image A / Image B
        */
        public PWImage division(PWImage Aimage,PWImage Bimage){
                rImage=Aimage.clone();

                int outWidth= Math.min(Aimage.getWidth(),Bimage.getWidth());
                int outHeight= Math.min(Aimage.getHeight(),Bimage.getHeight());

                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Bimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN) || Bimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsB = Bimage.getGrayPixels();
                        int [][] pixelsTemp= new int[outHeight][outWidth];

                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        if(pixelsB[indexRow][indexCol] != 0){
                                                pixelsTemp[indexRow][indexCol]=pixelsA[indexRow][indexCol]/pixelsB[indexRow][indexCol];;
                                        }else if (pixelsA[indexRow][indexCol] != 0){
                                                pixelsTemp[indexRow][indexCol]=pixelsB[indexRow][indexCol]/pixelsA[indexRow][indexCol];
                                        }else{
                                                pixelsTemp[indexRow][indexCol]=128;
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
                                        if(RpixelsB[indexRow][indexCol]!=0){
                                                RpixelsTemp[indexRow][indexCol]=RpixelsA[indexRow][indexCol]/RpixelsB[indexRow][indexCol];
                                        }else if((RpixelsA[indexRow][indexCol]!=0)){
                                                RpixelsTemp[indexRow][indexCol]=RpixelsB[indexRow][indexCol]/RpixelsA[indexRow][indexCol];
                                        }else{
                                                RpixelsTemp[indexRow][indexCol]=128;
                                        }
                                }
                        }
                        //Green channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        if(GpixelsB[indexRow][indexCol]!=0){
                                                GpixelsTemp[indexRow][indexCol]=GpixelsA[indexRow][indexCol]/GpixelsB[indexRow][indexCol];
                                        }else if((GpixelsA[indexRow][indexCol]!=0)){
                                                GpixelsTemp[indexRow][indexCol]=GpixelsB[indexRow][indexCol]/GpixelsA[indexRow][indexCol];
                                        }else{
                                                GpixelsTemp[indexRow][indexCol]=128;
                                        }
                                }
                        }
                        //blue channel
                        for(int indexRow=0;indexRow<outHeight;indexRow++){
                                for(int indexCol=0;indexCol<outWidth;indexCol++){
                                        if(BpixelsB[indexRow][indexCol]!=0){
                                                BpixelsTemp[indexRow][indexCol]=BpixelsA[indexRow][indexCol]/BpixelsB[indexRow][indexCol];
                                        }else if((BpixelsA[indexRow][indexCol]!=0)){
                                                BpixelsTemp[indexRow][indexCol]=BpixelsB[indexRow][indexCol]/BpixelsA[indexRow][indexCol];
                                        }else{
                                                BpixelsTemp[indexRow][indexCol]=128;
                                        }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setGPixels(GpixelsTemp);
                        rImage.setBPixels(BpixelsTemp);
                }else{
                  System.out.println("++PuntualOp:: NOT SUPPORT \n");
                }
                return rImage;
        }
        /**
        * With this method calculates inverse or negative image g(x) = 255-p(x)
        *@param Aimage is PWImage object with Image A
        *@return a PWImage with result negative(Image A)
        */
        public PWImage negative(PWImage Aimage){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE) || Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
                        int [][] pixelsA = Aimage.getGrayPixels();
                        int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];

                        for(int indexRow=0;indexRow<pixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<pixelsA[0].length;indexCol++){
                                        pixelsTemp[indexRow][indexCol]=Aimage.getIntensity()-pixelsA[indexRow][indexCol];
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

                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
                                        //Red channel
                                        RpixelsTemp[indexRow][indexCol]=Aimage.getIntensity()-RpixelsA[indexRow][indexCol];
                                        //Green channel
										GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity()-GpixelsA[indexRow][indexCol];
										//Blue channel
                                        BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity()-GpixelsA[indexRow][indexCol];
                                }
                        }
//                        //Green channel
//                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
//                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
//                                        GpixelsTemp[indexRow][indexCol]=Aimage.getIntensity()-GpixelsA[indexRow][indexCol];
//                                }
//                        }
//                        //blue channel
//                        for(int indexRow=0;indexRow<RpixelsA.length;indexRow++){
//                                for(int indexCol=0;indexCol<RpixelsA[0].length;indexCol++){
//                                        BpixelsTemp[indexRow][indexCol]=Aimage.getIntensity()-BpixelsA[indexRow][indexCol];
//                                }
//                        }
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
                        Arimeticos po=new Arimeticos();
                                po.negative(a).writeImage("negative.ppm");;
                                po.sum(a,b).writeImage("sumaAB.ppm");
                                po.sum(a,4).writeImage("sumaAN.ppm");
                                po.fusion(a,b).writeImage("sumafuison.ppm");
                                po.rest(a,b).writeImage("restaAB.ppm");
                                po.rest(a,4).writeImage("restaAN.ppm");
                                po.multiplicate(a,b).writeImage("mulAB.ppm");
                                po.multiplicate(a,5).writeImage("mulAN.ppm");
                                po.division(a,b).writeImage("divAB.ppm");
                }catch(Exception e){
                        System.out.println("Error:: "+e);
                }
        }*/
}
