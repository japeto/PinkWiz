/*
 * Middle.java
 *
 * Copyright 2013 Jefferson Amado Peña Torres <japeto@JAPeToCompaq>
 *
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
 *
 *
 * @version 1.0, 11-06-013
 * @author Jefferson Pe\~na Torres
*/

//package pinkWiz;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import basic.*;
import conversion.*;
import morphological.*;
import convulsion.*;
import geometric.*;
import image.*;

public class Middle{

        private PWImage objPWImageC;
        private Logical objLogicos;
        private Arimetics objArimetico;
        private Threshold objThreshold;
        private Histogram objHistogram;
        private Equalize objEqualize;
        private ColorToGray objColorToGray;
        private GrayToColor objGrayToColor;
        private RotateImage objRotimg;
        private FlipImage objFlip;
        private Geometrics objGeometrics;
        private Transformation objTransformation;
        private Convulsion objConvulsion;
        private GenConvultion objGenConvultion;
        private GenMorphological objGenMorphological;


        Middle(){
                objLogicos=new Logical();
                objArimetico=new Arimetics();
                objThreshold=new Threshold();
		objColorToGray=new ColorToGray();
		objGrayToColor=new GrayToColor();
                objRotimg= new RotateImage();
                objFlip=new  FlipImage();
                objGeometrics=new Geometrics();
                objEqualize = new Equalize();
                objTransformation = new Transformation();
                objConvulsion = new Convulsion();
                objGenConvultion = new GenConvultion();
                objGenMorphological = new GenMorphological();
        }
        void calculateAND(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objLogicos.and(objPWImageA,objPWImageB);

        }
        void calculateOR(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objLogicos.or(objPWImageA,objPWImageB);

        }
        void calculateXOR(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objLogicos.xor(objPWImageA,objPWImageB);
              
        }
        void calculateMAX(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objLogicos.max(objPWImageA,objPWImageB);
              
        }
        void calculateMIN(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objLogicos.min(objPWImageA,objPWImageB);
              
        }
        void calculateNOT(PWImage objPWImageA){
                objPWImageC=objLogicos.not(objPWImageA);   
        }        
        void calculateNEGATIVE(PWImage objPWImageA){
                objPWImageC=objArimetico.negative(objPWImageA);
              
        }
        void calculateSUM(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objArimetico.sum(objPWImageA,objPWImageB);
              
        }
        void calculateSUM_O(PWImage objPWImageA,int operand){
                objPWImageC=objArimetico.sum(objPWImageA,operand);
              
        }
        void calculateFUSION(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objArimetico.fusion(objPWImageA,objPWImageB);
              
        }
        void calculateREST(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objArimetico.rest(objPWImageA,objPWImageB);
              
        }
        void calculateREST_O(PWImage objPWImageA,int operand){
                objPWImageC=objArimetico.rest(objPWImageA,operand);
              
        }
        void calculateMULTIPLICATE(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objArimetico.multiplicate(objPWImageA,objPWImageB);
              
        }
        void calculateMULTIPLICATE_O(PWImage objPWImageA,int operand){
                objPWImageC=objArimetico.multiplicate(objPWImageA,operand);
              
        }
        void calculateDIVISION(PWImage objPWImageA,PWImage objPWImageB){
                objPWImageC=objArimetico.division(objPWImageA,objPWImageB);
              
        }
        void calculateTHRESHOLD(PWImage objPWImageA,int operand){
                objPWImageC=objThreshold.binarizeInt(objPWImageA,operand);

        }
        void calculateTHRESHOLDBIN(PWImage objPWImageA,int operand){
        
			switch(operand){
				case 1:                
    	            objPWImageC=objThreshold.binarizeAutomatic(objPWImageA);
                break; 
				case 2:                               
	                objPWImageC=objThreshold.binarizeMean(objPWImageA);
                break;                
				case 3:                
	                objPWImageC=objThreshold.binarizeOtsu(objPWImageA);                                
	            break;
			}
        }        
        void calculateGrayscale(PWImage objPWImageA,int operand){
                objPWImageC=objColorToGray.conversion(objPWImageA,operand);

        }
        void calculateColorice(PWImage objPWImageA,int operand){
                objPWImageC=objGrayToColor.conversion(objPWImageA,operand);
        }
        Histogram calculateHISTO(PWImage objPWImageA){
                objHistogram = new Histogram(objPWImageA);
                return objHistogram;
        } 
        void calculateEQU(PWImage objPWImageA){
                objPWImageC=objEqualize.calculateEqualize(objPWImageA);
        }     
        /***/       
        void calculateRotateCW(PWImage objPWImageA){
                objPWImageC=objRotimg.rotateClockwise(objPWImageA);
                              
        }
        void calculateRotateCCW(PWImage objPWImageA){
                objPWImageC=objRotimg.rotateCounterClockwise(objPWImageA);
                              
        }  
        void calculateFLIPV(PWImage objPWImageA){
                objPWImageC=objFlip.flipVertical(objPWImageA);
                              
        }
        void calculateFLIPH(PWImage objPWImageA){
                objPWImageC=objFlip.flipHorizontal(objPWImageA);
                              
        }       
        void calculateSCALE(PWImage objPWImageA,int operand){
                objPWImageC=objGeometrics.zoomA(objPWImageA,operand);
                              
        }   
        void calculateZOOM(PWImage objPWImageA,int operand){
                objPWImageC=objGeometrics.zoomR(objPWImageA,operand);
                              
        }   
        void calculateTRANS(PWImage objPWImageA,int operand){
                objPWImageC=objGeometrics.translate();
                              
        }
        /***/
        void calculateTLOG(PWImage objPWImageA,int operand){
                objPWImageC=objTransformation.tLogaritm(objPWImageA,operand);
                              
        }
        void calculateTPOW(PWImage objPWImageA,int operand,float operand1){
                objPWImageC=objTransformation.tPow(objPWImageA,operand,operand1);
                              
        }
        /***/
        
        void calculateCGr(PWImage objPWImageA){
                objPWImageC=objConvulsion.getGradient(objPWImageA);
                              
        }
        void calculateCLp(PWImage objPWImageA){
                objPWImageC=objConvulsion.getLaplace(objPWImageA);
                              
        }
        void calculateCSou(PWImage objPWImageA,int operand){
                objPWImageC=objConvulsion.getSoulager(objPWImageA,operand);
                              
        }
        void calculateCAvAri(PWImage objPWImageA){
                objPWImageC=objConvulsion.getAverageArimetic(objPWImageA);                              
        }
        void calculateCAvWh(PWImage objPWImageA){
                objPWImageC=objConvulsion.getAverageWeighted(objPWImageA);                              
        }
        void calculateCAvArm(PWImage objPWImageA){
                objPWImageC=objConvulsion.getAverageArmonic(objPWImageA);                              
        }      
        /***/
        void calculateCONV(PWImage objPWImageA,int sizeMask){
                float[][] mask= objGenConvultion.getGaussMask(sizeMask);
                objPWImageC=objGenConvultion.getConvultion(objPWImageA,mask);
                              
        }
        void calculateCONV(PWImage objPWImageA,float[][] mask){
                objPWImageC=objGenConvultion.getConvultion(objPWImageA,mask);
                              
        }        
        void calculateMORPHER(PWImage objPWImageA){
                objPWImageC=objGenMorphological.getErosion(objPWImageA);              
        }                                       
        void calculateMORPHDIL(PWImage objPWImageA){
                objPWImageC=objGenMorphological.getDilatation(objPWImageA);              
        }              
        void writeResult(PWImage objPWImageA,String name)throws FileNotFoundException,IOException{
                objPWImageA.writeImage(name);
        }
                private void setResult(PWImage objPWImageC){
                        this.objPWImageC=objPWImageC;
                }       
        public PWImage getResult(){
                return this.objPWImageC;
        }
}



