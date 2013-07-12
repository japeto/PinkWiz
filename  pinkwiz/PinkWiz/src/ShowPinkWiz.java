/*
 * InternalPinkWiz.java
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
 */

//package pinkWiz;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.color.ColorSpace;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ShowPinkWiz extends JFrame  implements MouseListener,MouseWheelListener  {
    private int width = 400;
    private int height = 400;
    private int idInternal,zoom=1;
    private Image drawImage;
    private JLabel img;

    public ShowPinkWiz(Image pixels,int index,String name,String format){

		this.width=pixels.getWidth(null);
		this.height=pixels.getHeight(null);
                this.idInternal=index;
		Box panel = Box.createVerticalBox();
		this.drawImage=pixels;
		img = new JLabel( new ImageIcon(drawImage) );
		addMouseListener(this);
		img.addMouseWheelListener(this);
                JScrollPane scroller = new JScrollPane(img, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scroller.setPreferredSize(new Dimension(width/2,height/2));
		     scroller.getViewport().add( img );   
        panel.add( scroller );

		JLabel labelInfo = new JLabel("<html>index: <span style='color:red'>"+index+"</span> <br> Name: <span style='color:green'>"+name+"</span> - "+(String)format+" <br> Size: <span style='color:blue'>"+width+"x"+height+"<br>");
//                Border border = BorderFactory.createLineBorder(Color.BLACK);
//                labelInfo.setBorder(border);
                panel.add( labelInfo );

		setTitle(" N° "+index+" - "+name);
        setResizable(true);
        getContentPane().add( panel );
        setSize(600,600);
        setVisible(true);

    } 
    public void mouseClicked(MouseEvent e) {   
        if(e.getButton()==1){
                                                        
        }//else{zoom++;}
        if (e.getButton()==3){
                                                            
        }
   
    }    
    public void mousePressed(MouseEvent e) {
//                System.out.println(">");
    }
     
    public void mouseReleased(MouseEvent e) {
//        try{
//                this.setSelected(false);
//        }catch(Exception a){
//                System.out.println("<");
//        }
    }
     
    public void mouseEntered(MouseEvent e) {
//        try{
//                this.setSelected(true);
//        }catch(Exception a){
//                System.out.println("<");
//        }
    }
     
    public void mouseExited(MouseEvent e) {
//                System.out.println("<");
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
                zoom+=10;
//                System.out.println("InternalPinkWiz:: ZoomOut"+zoom+" ");
                Image scaledImage = drawImage.getScaledInstance((int)width+zoom,  
                                                            (int)height+zoom,  
                                                            Image.SCALE_DEFAULT);                
                 img.setIcon( new ImageIcon(scaledImage) );    
        } else {
                zoom-=10;                
//                System.out.println("InternalPinkWiz::ZoomIn "+zoom+" ");
                Image scaledImage = drawImage.getScaledInstance((int)width+zoom,  
                                                            (int)height+zoom,  
                                                            Image.SCALE_DEFAULT);                
                 img.setIcon( new ImageIcon(scaledImage) );
        }
    }    
   public int getID(){
        return this.idInternal;    
    }
            private void setID(int idInternal){
                 this.idInternal= idInternal;    
            }      


}
