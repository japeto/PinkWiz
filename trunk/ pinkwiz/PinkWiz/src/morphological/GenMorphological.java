/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * *************************************************************
 * *************************************************************
 *
 * @author Jefferson Amado Pe\~na Torres
 * @version 1.0, 19-06-013
 * @since 2013-06-16
*/

package morphological;
import basic.*;
import image.*;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;

public  class GenMorphological{
        private int[][] mask = {{255,255,255,255,255},
        						{255,255,255,255,255},
        						{255,255,255,255,255},
        						{255,255,255,255,255},        						        						
        						{255,255,255,255,255}};
        						
        private int[][] mask1 = {{255,255,255},
								{255,255,255},
								{255,255,255}};        						
        /**
        * Image to change
        */
        private PWImage rImage;
        /**
        * Constructor
        */
        public GenMorphological(){}
        /**
        *@return  new Image
        */
        public PWImage getErosion(PWImage Aimage){
              rImage=Aimage.clone();
              if(Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
					int [][] pixelsA = Aimage.getGrayPixels();
					int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
					for(int indexRow=1;indexRow<pixelsA.length-1;indexRow++){
						for(int indexCol=1;indexCol<pixelsA[0].length-1;indexCol++){
				            if(pixelsA[indexRow-1][indexCol]==255 && pixelsA[indexRow+1][indexCol]==255 && pixelsA[indexRow][indexCol-1]==255 && pixelsA[indexRow][indexCol+1]==255){
                                    pixelsTemp [indexRow][indexCol]= 255;
							}else{pixelsTemp [indexRow][indexCol]=0;}
						}
					}
					rImage.setGrayPixels(pixelsTemp);
               }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int [][] pixelsA = Aimage.getGrayPixels();

					int outputWidth = pixelsA.length;
					int outputHeight = pixelsA[0].length;
					int [][] pixelsTemp= new int[outputWidth][outputHeight];

					for (int indexRow =0; indexRow < outputWidth; indexRow++){
						for(int indexCol = 0; indexCol < outputHeight; indexCol++){
                    		if(indexRow<(mask.length/2)||indexCol<(mask[0].length/2)||indexRow>outputWidth-(mask.length/2)||indexCol>outputWidth-(mask[0].length/2)){// es el borde
                        		pixelsTemp[indexRow][indexCol] = 0;//pixelsA[indexRow][indexCol];
                    		}else{
								int  result=256,min=0;
                                for(int indexRowMask =((mask.length*-1)/2);indexRowMask< (mask.length/2);indexRowMask++){
                                    for(int indexColMask =((mask[0].length*-1)/2);indexColMask< (mask[0].length/2);indexColMask++){
//System.out.println( " P [ "+(indexRow+indexRowMask-1)+" ][ "+(indexCol+indexColMask-1)+" ] : "+pixelsA[indexRow+indexRowMask-1][indexCol+indexColMask-1]);																														
//										System.out.println( " mask [ "+indexRowMask+" ][ "+indexColMask+" ] : "+ mask[indexRowMask][indexColMask]);									
										min=pixelsA[indexRow+indexRowMask][indexCol+indexColMask];
										if(min <= result &&mask[indexRowMask+(mask.length/2)][indexColMask+(mask[0].length/2)]==255){ 
											result = min;
//											System.out.println("#min> "+result);
										}
										//+= pixelsA[indexRow+indexRowMask-1][indexCol+indexColMask-1]* mask[indexRowMask][indexColMask];
									}
								}
								pixelsTemp [indexRow][indexCol]=result;
							}
							//System.out.println("< "+factor+" >");
						}
					}
					rImage.setGrayPixels(pixelsTemp);
               }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
					int [][] RpixelsA = Aimage.getRPixels();
					int [][] GpixelsA = Aimage.getGPixels();
					int [][] BpixelsA = Aimage.getBPixels();										

					int outputWidth = RpixelsA.length;
					int outputHeight = RpixelsA[0].length;
					int [][] RpixelsTemp= new int[outputWidth][outputHeight];
					int [][] GpixelsTemp= new int[outputWidth][outputHeight];
					int [][] BpixelsTemp= new int[outputWidth][outputHeight];										

					for (int indexRow =0; indexRow < outputWidth; indexRow++){
						for(int indexCol = 0; indexCol < outputHeight; indexCol++){
                    		if(indexRow<(mask.length/2)||indexCol<(mask[0].length/2)||indexRow>outputWidth-(mask.length/2)||indexCol>outputWidth-(mask[0].length/2)){// es el borde
								RpixelsTemp [indexRow][indexCol]=RpixelsA[indexRow][indexCol];
								GpixelsTemp [indexRow][indexCol]=GpixelsA[indexRow][indexCol];
								BpixelsTemp [indexRow][indexCol]=BpixelsA[indexRow][indexCol];	
							}else{
								int  result = new Color(255,255,255).getRGB(),min=0;
								
                                for(int indexRowMask =((mask.length*-1)/2);indexRowMask< (mask.length/2);indexRowMask++){
                                    for(int indexColMask =((mask[0].length*-1)/2);indexColMask< (mask[0].length/2);indexColMask++){
										min=new Color(RpixelsA[indexRow+indexRowMask][indexCol+indexColMask],GpixelsA[indexRow+indexRowMask][indexCol+indexColMask],BpixelsA[indexRow+indexRowMask][indexCol+indexColMask]).getRGB();
										if(min <= result && mask[indexRowMask+(mask.length/2)][indexColMask+(mask[0].length/2)]==255){ 
											result = min;
										}
									}
								}
								RpixelsTemp [indexRow][indexCol]=new Color(result).getRed();
								GpixelsTemp [indexRow][indexCol]=new Color(result).getGreen();
								BpixelsTemp [indexRow][indexCol]=new Color(result).getBlue();
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
        *
        *<pre>
		*[n,m]=size(a);
		*	dilatada=zeros(size(a));
		*	for i=2:n-1
		*		for j=2:m-1
		*			dilatada(i,j)=a(i,j);
		*			if (a(i,j)==0 & (a(i,j-1)==1 | a(i-1,j)==1 | a(i,j+1)==1 | a(i+1,j)==1))
		*				dilatada(i,j)=1;
		*		end
		*	end
		*end
		*<pre/>
        *@param Aimage image input
        *@return  new Image
        */
        public PWImage getDilatation(PWImage Aimage){
              rImage=Aimage.clone();
              if(Aimage.getTypePWImage().equals(PWImageType.TYPE_BIN)){
					int [][] pixelsA = Aimage.getGrayPixels();
					int [][] pixelsTemp= new int[pixelsA.length][pixelsA[0].length];
					for(int indexRow=1;indexRow<pixelsA.length-1;indexRow++){
						for(int indexCol=1;indexCol<pixelsA[0].length-1;indexCol++){
				            if(pixelsA[indexRow-1][indexCol]==255 || pixelsA[indexRow+1][indexCol]==255 || pixelsA[indexRow][indexCol-1]==255 || pixelsA[indexRow][indexCol+1]==255){
                                    pixelsTemp [indexRow][indexCol]= 255;
							}else{pixelsTemp [indexRow][indexCol]=0;}
						}
					}
					rImage.setGrayPixels(pixelsTemp);
               }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)){
					int [][] pixelsA = Aimage.getGrayPixels();

					int outputWidth = pixelsA.length;
					int outputHeight = pixelsA[0].length;
					int [][] pixelsTemp= new int[outputWidth][outputHeight];

					for (int indexRow =0; indexRow < outputWidth; indexRow++){
						for(int indexCol = 0; indexCol < outputHeight; indexCol++){
                    		if(indexRow<(mask.length/2)||indexCol<(mask[0].length/2)||indexRow>outputWidth-(mask.length/2)||indexCol>outputWidth-(mask[0].length/2)){// es el borde
//								System.out.println(" [ "+indexRow+" ][ "+indexCol+" ] P "+pixelsA[indexRow][indexCol]);
								pixelsTemp [indexRow][indexCol]=0;//pixelsA[indexRow][indexCol];
							}else{
								int  result=0,max=0;
								
                                for(int indexRowMask =((mask.length*-1)/2);indexRowMask< (mask.length/2);indexRowMask++){
                                    for(int indexColMask =((mask[0].length*-1)/2);indexColMask< (mask[0].length/2);indexColMask++){
//System.out.println( " P [ "+(indexRow+indexRowMask-1)+" ][ "+(indexCol+indexColMask-1)+" ] : "+pixelsA[indexRow+indexRowMask-1][indexCol+indexColMask-1]);																														
//										System.out.println( " mask [ "+indexRowMask+" ][ "+indexColMask+" ] : "+ mask[indexRowMask][indexColMask]);									
										max=pixelsA[indexRow+indexRowMask][indexCol+indexColMask];
										if(max > result && mask[indexRowMask+(mask.length/2)][indexColMask+(mask[0].length/2)]==255){ 
											result = max;
//											System.out.println("#min> "+result);
										}
										//+= pixelsA[indexRow+indexRowMask-1][indexCol+indexColMask-1]* mask[indexRowMask][indexColMask];
									}
								}
								pixelsTemp [indexRow][indexCol]=result;
//								result=0;
							}
							//System.out.println("< "+factor+" >");
						}
					}
					rImage.setGrayPixels(pixelsTemp);
               }else if(Aimage.getTypePWImage().equals(PWImageType.TYPE_RGB)){
									int [][] RpixelsA = Aimage.getRPixels();
					int [][] GpixelsA = Aimage.getGPixels();
					int [][] BpixelsA = Aimage.getBPixels();										

					int outputWidth = RpixelsA.length;
					int outputHeight = RpixelsA[0].length;
					int [][] RpixelsTemp= new int[outputWidth][outputHeight];
					int [][] GpixelsTemp= new int[outputWidth][outputHeight];
					int [][] BpixelsTemp= new int[outputWidth][outputHeight];										

					for (int indexRow =0; indexRow < outputWidth; indexRow++){
						for(int indexCol = 0; indexCol < outputHeight; indexCol++){
                    		if(indexRow<(mask.length/2)||indexCol<(mask[0].length/2)||indexRow>outputWidth-(mask.length/2)||indexCol>outputWidth-(mask[0].length/2)){// es el borde
								RpixelsTemp [indexRow][indexCol]=0;//RpixelsA[indexRow][indexCol];
								GpixelsTemp [indexRow][indexCol]=0;//GpixelsA[indexRow][indexCol];
								BpixelsTemp [indexRow][indexCol]=0;//BpixelsA[indexRow][indexCol];	
							}else{
								int result=0,max=0;
                                for(int indexRowMask =((mask.length*-1)/2);indexRowMask< (mask.length/2);indexRowMask++){
                                    for(int indexColMask =((mask[0].length*-1)/2);indexColMask< (mask[0].length/2);indexColMask++){
										max=new Color(RpixelsA[indexRow+indexRowMask][indexCol+indexColMask],GpixelsA[indexRow+indexRowMask][indexCol+indexColMask],BpixelsA[indexRow+indexRowMask][indexCol+indexColMask]).getRGB();
										if(max > result && mask[indexRowMask+(mask.length/2)][indexColMask+(mask[0].length/2)]==255){ 
											result = max;
										}
									}
								}
								RpixelsTemp [indexRow][indexCol]=new Color(result).getRed();
								GpixelsTemp [indexRow][indexCol]=new Color(result).getGreen();
								BpixelsTemp [indexRow][indexCol]=new Color(result).getBlue();
//								max=result=0;
							}
						}
					}
					rImage.setRPixels(RpixelsTemp);
					rImage.setGPixels(GpixelsTemp);
					rImage.setBPixels(BpixelsTemp);										
               }
		       return rImage;
        }        
        public PWImage getOpen(PWImage Aimage){
			PWImage erode = getErosion(Aimage);
			rImage = getDilatation(erode);
			return rImage;
        }
        public PWImage getClosing(PWImage Aimage){
			PWImage dilate = getDilatation(Aimage);
			rImage = getErosion(dilate);
			return rImage;
        }        
}

