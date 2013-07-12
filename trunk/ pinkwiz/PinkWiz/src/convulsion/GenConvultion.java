package convulsion;
import basic.*;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0, 19-06-013
 * @since 2013-06-16
*/
public  class GenConvultion{
        /**
        * Image to change
        */
        private PWImage rImage;
        /**
        * Constructor
        */
        public GenConvultion(){}
        /**
        *@param Aimage is image to modified with mask(kernel)
        *@param mask
        *@return  new Image
        */
        public PWImage getConvultion(PWImage Aimage, float[][] mask){
                rImage=Aimage.clone();
                if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
                        int [][] pixelsA = Aimage.getGrayPixels();

                        int outputWidth = pixelsA.length;// pixelsA.length-(mask.length/2);
                        int outputHeight = pixelsA[0].length;//pixelsA[0].length-(mask[0].length/2);
                        
                        int [][] pixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 0/*(mask.length/2)*/; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 0/*(mask[0].length/2)*/; indexCol < outputHeight; indexCol++){
                                        int  result=0,factor=0;                                
                                		if(indexRow<(mask.length/2)||indexCol<(mask[0].length/2)||indexRow>outputWidth-(mask.length/2)||indexCol>outputWidth-(mask[0].length/2)){// es el borde
	                                		pixelsTemp[indexRow][indexCol] = pixelsA[indexRow][indexCol];
                                		}else{
    	                                    for(int indexRowMask =((mask.length*-1)/2);indexRowMask< (mask.length/2);indexRowMask++){
                                                for(int indexColMask =((mask[0].length*-1)/2);indexColMask< (mask[0].length/2);indexColMask++){
                                             		factor += mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
													result += pixelsA[indexRow+indexRowMask][indexCol+indexColMask]* mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
                                                }
    	                                    }
                                        if(factor>0) result/=factor;

                                        if( result > 255 )
                                                result = Aimage.getIntensity();
                                        else if( result < 0 )
                                                result = 0;
                                                
                                                
                                        pixelsTemp [indexRow][indexCol]=result;     	                                    
    	                                }
                                }
                        }
                        rImage.setGrayPixels(pixelsTemp);
               }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                        int [][] RpixelsA = Aimage.getRPixels();
                        int [][] GpixelsA = Aimage.getGPixels();
                        int [][] BpixelsA = Aimage.getBPixels();

                        int outputWidth = RpixelsA.length;// pixelsA.length-(mask.length/2);
                        int outputHeight = RpixelsA[0].length;//pixelsA[0].length-(mask[0].length/2);
                        
                        int [][] RpixelsTemp= new int[outputWidth][outputHeight];
                        int [][] GpixelsTemp= new int[outputWidth][outputHeight];
                        int [][] BpixelsTemp= new int[outputWidth][outputHeight];

                        for (int indexRow = 0/*(mask.length/2)*/; indexRow < outputWidth; indexRow++){
                                for(int indexCol = 0/*(mask[0].length/2)*/; indexCol < outputHeight; indexCol++){
                                        int  resultR=0,resultG=0,resultB=0,result=0,factor=0;                                
                                		if(indexRow<(mask.length/2)||indexCol<(mask[0].length/2)||indexRow>outputWidth-(mask.length/2)||indexCol>outputWidth-(mask[0].length/2)){// es el borde
								                RpixelsTemp [indexRow][indexCol]=RpixelsA[indexRow][indexCol];;
								                GpixelsTemp [indexRow][indexCol]=GpixelsA[indexRow][indexCol];;
								                BpixelsTemp [indexRow][indexCol]=BpixelsA[indexRow][indexCol];;
                                		}else{
    	                                    for(int indexRowMask =((mask.length*-1)/2);indexRowMask< (mask.length/2);indexRowMask++){
                                                for(int indexColMask =((mask[0].length*-1)/2);indexColMask< (mask[0].length/2);indexColMask++){
													factor += mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
//													resultR += RpixelsA[indexRow+indexRowMask][indexCol+indexColMask]* mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
//													resultG += GpixelsA[indexRow+indexRowMask][indexCol+indexColMask]* mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
//													resultB += BpixelsA[indexRow+indexRowMask][indexCol+indexColMask]* mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
													result+= new Color(RpixelsA[indexRow+indexRowMask][indexCol+indexColMask],GpixelsA[indexRow+indexRowMask][indexCol+indexColMask],BpixelsA[indexRow+indexRowMask][indexCol+indexColMask]).getRGB()* mask[indexRowMask+(mask[0].length/2)][indexColMask+(mask[0].length/2)];
                                                }
    	                                    }
		                            		if(factor>0){
												result/=factor;
//		                                    	resultR/=factor;
//		                                    	resultG/=factor;
//		                                    	resultB/=factor;
		                            		}
//						                    if( resultR > 255 )
//						                            resultR = Aimage.getIntensity();
//						                    else if( resultR < 0 )
//						                            resultR = 0;

//						                    if( resultG > 255 )
//						                    resultG = Aimage.getIntensity();
//						                    else if( resultG < 0 )
//						                    resultG = 0;

//						                    if( resultB > 255 )
//						                    resultB = Aimage.getIntensity();
//						                    else if( resultB < 0 )
//						                    resultB = 0;
		                                    if(factor>0) result/=factor;

		                                    if( result > 255 )
		                                            result = Aimage.getIntensity();
		                                    else if( result < 0 )
		                                            result = 0;
                                                                                              
//						                    RpixelsTemp [indexRow][indexCol]=resultR;
//						                    GpixelsTemp [indexRow][indexCol]=resultG;
//						                    BpixelsTemp [indexRow][indexCol]=resultB;    	                                    
						                    RpixelsTemp [indexRow][indexCol]=new Color(result).getRed();
						                    GpixelsTemp [indexRow][indexCol]=new Color(result).getGreen();
						                    BpixelsTemp [indexRow][indexCol]=new Color(result).getBlue();  
    	                                }
                                }
                        }
                        rImage.setRPixels(RpixelsTemp);
                        rImage.setRPixels(GpixelsTemp);
                        rImage.setRPixels(BpixelsTemp);
                }
        	return rImage;
        }
        public float[][] getGaussMask(int size){
        		if(size<1) size=3;
				else if(size%2==0) size+=1;
				
                float[][] out=new float[size][size];                
                int binomial[]= new int[size];
                int[] a = new int[1];
                for (int i = 1; i <= size; i++) {
                        int[] x = new int[i];                
                        for (int j = 0; j < i; j++) {
                                if (j == 0 || j == (i - 1)) {
                                        x[j] = 1;
                                } else {
                                        x[j] = a[j] + a[j - 1];
                                }
                                binomial[j]=x[j];
                        }
                        a = x;
                }
                for(int i=0;i<out.length;i++){
                        for(int j=0;j<out[0].length;j++){
                                out[i][j]=binomial[i]*binomial[j];
//                                System.out.print(out[i][j]+" ");
                        }       
                        System.out.print("\n");                        
                }
                return out;
        }
//        public static void main (String [] args){
//                try{
//                        PWImage a =new PWImage(args[0]);
//                        float[][] board = new float[][] {
//                                        {0.5f,0.5f,0.5f},
//                                        {0.5f,0.5f,0.5f},
//                                        {0.5f,0.5f,0.5f}};
//                        float[][]gaussianBlur ={
//                                        {1,2,1},
//                                        {2,4,2},
//                                        {1,2,1}};
//                        float[][] emboss ={
//                                        {2,0,0},
//                                        {0,-1,0},
//                                        {0,0,-1}};
//                        float[][] edge1 ={
//                                        {-1,-1,-1},
//                                        {0,0,0},
//                                        {1,1,1}};
//						float[][] edge2 = {
//                                        {-1,0,1},
//                                        {-1,0,1},
//                                        {-1,0,1}};
//                        float[][] sobel1 = {
//                                        {1,0,-1},
//                                        {2,0,-2},
//                                        {1,0,-1}};
//                        float[][] sobel2 = {
//                                        { 1, 2, 1},
//                                        { 0, 0, 0},
//                                        {-1,-2,-1}};
//                        float[][]prewitt1 = {
//                                        {1,0,-1},
//                                        {1,0,-1},
//                                        {1,0,-1}};
//                       float[][] prewitt2 = {
//                                        { 1, 1, 1},
//                                        { 0, 0, 0},
//                                        {-1,-1,-1}};
//                       float[][] kirsch1 = {
//                                        {5,-3,-1},
//                                        {5,-3,-1},
//                                        {5,-3,-1}};
//                       float[][] kirsch2 = {
//                                        { 5, 5, 5},
//                                        {-3,-3,-3},
//                                        {-3,-3,-3}};
//						float[][] laplacian = {
//                                        {-1,-1,-1},
//                                        {-1,8,-1},
//                                        {-1,-1,-1}};
//                        Convultion po=new Convultion();
//                                        po.getConvultion(a,emboss).writeImage("convBD.pgm");
//					po.getConvultion(a,gaussianBlur).writeImage("convGb.pgm");
//                                        po.getConvultion(a,edge1).writeImage("convEd1.pgm");
//                                        po.getConvultion(a,edge2).writeImage("convEd2.pgm");
//                                        po.getConvultion(a,sobel1).writeImage("convs1.pgm");
//                                        po.getConvultion(a,sobel2).writeImage("convs2.pgm");
//                                        po.getConvultion(a,prewitt1).writeImage("convp1.pgm");
//                                        po.getConvultion(a,prewitt2).writeImage("convp2.pgm");
//                                        po.getConvultion(a,kirsch1).writeImage("convk1.pgm");
//                                        po.getConvultion(a,kirsch2).writeImage("convk2.pgm");
//										po.getConvultion(a,laplacian).writeImage("convLp.pgm");


//                }catch(Exception e){
//                        System.out.println("Error:: "+e);
//                }
//        }
}


