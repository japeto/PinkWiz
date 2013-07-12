package image;

import java.awt.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.awt.*;
import java.awt.color.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * The PWImageType enum set types by images
 * @author  Jefferson Amado Pe\~na Torres <jeffersonamado@gmail.com>
 * @version 1.0, jun 2013
 * @see PWImage
 * @see PWImageType
 *
 */
public enum PWImageType{
	/**
	* Image gray scale with values [0 ... 255] by 8 bits
	*/
	TYPE_GRAYSCALE,
	/**
	* Image with values 0 by black and 255 by white
	*/
	TYPE_BIN,
	/**
	* Image with three channels {Red,Green,Blue} 
	*/
	TYPE_RGB
}

