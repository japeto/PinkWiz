import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import javax.swing.event.InternalFrameEvent;

import basic.*;
import image.*;
public class Principal {
        private Vector<PWImage> reads=new Vector<PWImage>();
        private Middle operador=new Middle();
        private final float[][] GUSSBLUR ={{1,2,1},{2,4,2},{1,2,1}};
        private final float[][] EMBOSS ={{2,0,0},{0,-1,0},{0,0,-1}};
        private final float[][] EDGEA ={{-1,-1,-1},{0,0,0},{1,1,1}};
        private final float[][] EDGEB= {{-1,0,1},{-1,0,1},{-1,0,1}};
        private final float[][] SOBELA = {{1,0,-1},{2,0,-2},{1,0,-1}};
        private final float[][] SOBELB = {{ 1, 2, 1},{ 0, 0, 0},{-1,-2,-1}};
        private final float[][] PREWITTA = {{1,0,-1},{1,0,-1},{1,0,-1}};
        private final float[][] PREWITTB = {{ 1, 1, 1},{ 0, 0, 0},{-1,-1,-1}};
        private final float[][] KIRSCHA = {{5,-3,-1},{5,-3,-1},{5,-3,-1}};
        private final float[][] KIRSCHB = {{ 5, 5, 5},{-3,-3,-3},{-3,-3,-3}};
        private final float[][] LAPLACIAN = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};        
		private final float[][] AvWEITH = {{1,1,1},{1,13,1},{1,1,1}};
		
        Principal(String app){
			switch (app) {
				case "-gui":
					VisibleGui();
				break;
				case "-i": 
					
				break;
				default:
					throw new IllegalArgumentException("Invalid argument");
			}        	
        }
        void VisibleGui(){
                final JFrame frame = new JFrame("PinkWiz");
                
                final JTextField textField1 = new JTextField();
                final JEditorPane textField2 = new JEditorPane();
                textField2.setEditable(false);
                Font font = new Font("Verdana", Font.BOLD, 12);
				textField2.setFont(font);
					textField2.setForeground(new Color(250,250,210));
					textField2.setBackground(new Color(100,149,237));
			        
                textField1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        System.out.println("< "+textField1.getText()+" >");
                        if(textField1.getText().contains("exit")){
                             System.exit(0);
                        }else if(textField1.getText().contains("clean;")){
                             textField1.setText("");
                             textField2.setText("");                             
                        }else if(textField1.getText().contains("clear;")){
                             textField1.setText("");
                             textField2.setText("");                             
                             reads.removeAllElements();
                        }else if(textField1.getText().contains("size();")){
                             textField2.setText(textField2.getText()+" load "+reads.size());                             
                        }else if(textField1.getText().equals("")){
                        textField2.setText(textField2.getText()+"\n"+
                                        "+++++++++++++++++++++++++++++++++++\n"+
                                        "Commands\n"+
                                        "+++++++++++++++++++++++++++++++++++\n"+
                                        "+load(IMAGE);\n"+
                                        "+loadall(FOLDER);\n"+
                                        "+write(FOLDER)\n"+
                                        "+writeall(FOLDER)\n"+                                        
                                        "+display(idImgae)\n"+                                        
                                        "+displayall()\n"+                                                                                
                                        "+size()\n"+                                             
                                        "+showHisto(idImage)\n"+                                        
                                        "+exit; or exit();\n"+ 
                                        "+++++++++++++++++++++++++++++++++++\n"+                                        
                                        "+and(idImage,idImage)\n"+
                                        "+or(idImage,idImage)\n"+
                                        "+xor(idImage,idImage)\n"+
                                        "+max(idImage,idImage)\n"+
                                        "+min(idImage,idImage)\n"+
                                        "+not(idImage)\n"+
                                        "+++++++++++++++++++++++++++++++++++\n"+
                                        "+sum(idImage,idImage)\n"+
                                        "+sumI(idImage,Int)\n"+
                                        "+rest(idImage,idImage)\n"+
                                        "+restI(idImage,Int)\n"+
                                        "+mul(idImage,idImage)\n"+
                                        "+mulI(idImage,Int)\n"+                                        
                                        "+fus(idImage,idImage)\n"+
                                        "+div(idImage,idImage)\n"+
                                        "+thr(idImage,INT)\n"+
                                        "+bin(idImage)\n"+                                        
                                        "+pow(idImage,Int)\n"+
                                        "+log(idImage,Int)\n"+                                        
                                        "+neg(idImage,idImage)\n"+
                                        "+++++++++++++++++++++++++++++++++++\n"+                                                                                                                        
                                        "+rotC(idImage)\n"+
                                        "+rotCc(idImage)\n"+
                                        "+flipv(idImage)\n"+
                                        "+fliph(idImage)\n"+
                                        "+scale(idImage,Int)\n"+
                                        "+//zoom(i(i,i))\n"+                                                                            
                                        "+//transl(i,(i,i))\n"+
                                        "+gray(idImage,Int)\n"+                                        
                                        "+colorice(idImage,Int)\n"+                                        
                                        "+++++++++++++++++++++++++++++++++++\n"+                                                                                                                                                       
                                        "+equ(idImage)\n"+
                                        "+gr(idImage)\n"+
                                        "+lp(idImage)\n"+
                                        "+sou(idImage,Int)\n"+
                                        "+avw(idImage)\n"+
                                        "+avar(idImage)\n"+                                        
                                        "+avarim(idImage)\n"+                                                                                                                        
                                        "+++++++++++++++++++++++++++++++++++\n"+                                                                                                                                                       
                                        "+gaus(idImage,INT)\n"+                                                                                         
                                        "+conv(idImage)\n"+                                        
                                        "+eros(idImage)\n"+                                        
                                        "+dila(idImage)\n"+                                        
                                        "+++++++++++++++++++++++++++++++++++\n"                                                                             
                                );
                        }else if(textField1.getText().contains(";") &&textField1.getText().startsWith("load(")){   

							String str=textField1.getText();
							String path= str.substring(str.indexOf("(") + 1, str.indexOf(")"));
							
							PWImage objRImage=null;
							try{
							if(path.contains(".png")){objRImage= new JPEGImage(path);}
								else if(path.contains(".jpg")){objRImage= new JPEGImage(path);textField2.setText(textField2.getText()+"\nPinkWiz::Open performed: "+path);}
								else if(path.contains(".pgm") || path.contains(".ppm")){objRImage= new PNMImage(path);textField2.setText(textField2.getText()+"\nPinkWiz::Open performed: "+path);}
								
								reads.add(objRImage);
								textField2.setText(textField2.getText()+"\nPinkWiz::idImage= "+(reads.size()-1)+"");
							}catch(Exception a){
								reads.remove(objRImage);
								System.err.println("Error: "+a);
								JOptionPane.showMessageDialog(frame, "<html>UPS!<br> Ha Ocurrido un error "
											+" de lectura con el archivo:<br><br>\"","Error ", JOptionPane.ERROR_MESSAGE);
							}
                        }else if(textField1.getText().contains(";") &&textField1.getText().startsWith("loadall(")){
							String str=textField1.getText();
								String dir= str.substring(str.indexOf("(") + 1, str.indexOf(")"));
								File folder = new File(dir);
								for (final File fileEntry : folder.listFiles()) {
									if (!fileEntry.isDirectory()) {
										System.out.println(dir+fileEntry.getName());
										String path =dir+fileEntry.getName();
										PWImage objRImage=null;
										try{
										if(path.contains(".jpg")){
											objRImage= new JPEGImage(path);textField2.setText(textField2.getText()+"\nPinkWiz::Open performed: "+path);
											reads.add(objRImage);
											textField2.setText(textField2.getText()+"\nPinkWiz::idImage= "+(reads.size()-1)+"");											
										}else if(path.contains(".pgm") || path.contains(".ppm")){
											objRImage= new PNMImage(path);textField2.setText(textField2.getText()+"\nPinkWiz::Open performed: "+path);
											reads.add(objRImage);
											textField2.setText(textField2.getText()+"\nPinkWiz::idImage= "+(reads.size()-1)+"");
										}

										}catch(Exception a){
											reads.remove(objRImage);
											JOptionPane.showMessageDialog(frame, "<html>UPS!<br> Ha Ocurrido un error "
															+" de lectura con el archivo:<br><br>\"","Error ", JOptionPane.ERROR_MESSAGE);
										}
									}
								}
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("write(")){     
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::write("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                String name=path.split(",")[1];              

								if(id<reads.size()){    
									try{
										PWImage rm=reads.get(id);
										operador.writeResult(rm,name);   
									}catch(Exception a){
										JOptionPane.showMessageDialog(frame, "<html>UPS!<br> Ha Ocurrido un error "
											+" de escritura con el archivo:<br><br>\"","Error ", JOptionPane.ERROR_MESSAGE);
									}      		                                 			
								}

                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("writeall(")){     
                                String str=textField1.getText();
								String path= str.substring(str.indexOf("(") + 1, str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::writeall("+path+")");                                
								try{
									for(int index=0;index<reads.size();index++){
										PWImage rm=reads.get(index);
										operador.writeResult(rm,path+rm.getName()+"_id"+index);                                     
									}
								}catch(Exception a){
									JOptionPane.showMessageDialog(frame, "<html>UPS!<br> Ha Ocurrido un error "
										+" de escritura con alguno de los archivos:<br><br>\"","Error ", JOptionPane.ERROR_MESSAGE);
								}      		                                 			

                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("display(")){     
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
        		                new ShowPinkWiz(rm.getRasterImage(),id,rm.getName()+"."+rm.getFileExtension(),rm.getTypePWImage()+"");      		                
                			textField2.setText(textField2.getText()+"\nPinkWiz::display: "+id);		                                                
                                }

                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("displayall(")){     
                        	for(int id=0;id<reads.size();id++){
        		                PWImage rm=reads.get(id);
        		                new ShowPinkWiz(rm.getRasterImage(),id,rm.getName()+"."+rm.getFileExtension(),rm.getTypePWImage()+"");      		                
	                			textField2.setText(textField2.getText()+"\nPinkWiz::display: "+id);		                                                    	
                        	}
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("and(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::and("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
		                operador.calculateAND(reads.get(id),reads.get(id2));
		                reads.add(operador.getResult());
        			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("or(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::or("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
		                operador.calculateOR(reads.get(id),reads.get(id2));
		                reads.add(operador.getResult());
        			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("xor(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::xor("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){                                
	        	                operador.calculateXOR(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
        			        textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
                                }        			
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("max(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::max("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){                                
	        	                operador.calculateMAX(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
        			        textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("min(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::min("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateMIN(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("neg(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::neg("+id+")");
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
	        	                operador.calculateNEGATIVE(rm);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
                        }else if(textField1.getText().contains(";")&& textField1.getText().startsWith("not(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::not("+id+")");
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
	        	                operador.calculateNOT(rm);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("sum(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::sum("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateSUM(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("sumI(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::sumI("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
        		                operador.calculateSUM_O(reads.get(id),id2);
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("rest(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::sum("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateREST(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("restI(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::restI("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
        		                operador.calculateREST_O(reads.get(id),id2);
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("mul(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::sum("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateMULTIPLICATE(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("mulI(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::mulI("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
        		                operador.calculateMULTIPLICATE_O(reads.get(id),id2);
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("fus(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::fus("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateFUSION(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("div(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::div("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateDIVISION(reads.get(id),reads.get(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("thr(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::thr("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
        		                operador.calculateTHRESHOLD(reads.get(id),(id2));
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("gray(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::toGray("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateGrayscale(reads.get(id),id2);
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("colorice(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::colorice("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                if(id<reads.size()){
        		                operador.calculateColorice(reads.get(id),id2);
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("showHisto(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::showHisto("+id+")");
                                
                                if(id<reads.size()){
		                        PWImage temp=reads.get(id);
		                        Histogram c=operador.calculateHISTO(temp);
				                if(temp.getTypePWImage().equals(PWImageType.TYPE_GRAYSCALE)|| temp.getTypePWImage().equals(PWImageType.TYPE_BIN)){

                                                JDialog f = new JDialog();
                                                f.setSize(530, 120);
                                                DrawHistogram dH= new DrawHistogram(c.getHistoArray(), temp.getName(),Color.BLACK);
                                                f.add(dH);
                                                f.setLocationRelativeTo(frame);
                                                f.setVisible(true);

		                        }else if(temp.getTypePWImage().equals(PWImageType.TYPE_RGB)){
                                                JDialog f = new JDialog();
                                                f.setSize(530, 400);
                                                JPanel pnl = new JPanel(new GridLayout(3, 0));
                                                pnl.add(new DrawHistogram(c.getHistoRArray(), temp.getName(),Color.RED));
                                                pnl.add(new DrawHistogram(c.getHistoGArray(), temp.getName(),Color.GREEN));
                                                pnl.add(new DrawHistogram(c.getHistoBArray(), temp.getName(),Color.BLUE));

                                                f.add(pnl);
                                                f.setLocationRelativeTo(frame);
                                                f.setVisible(true);
		                        }
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("scale(")){

                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::scale("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
        		                operador.calculateSCALE(reads.get(id),id2);
        		                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
        			
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("rotC(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::rotC("+id+")");
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
	        	                operador.calculateRotateCW(rm);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("rotCc(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::rotCc("+id+")");
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
	        	                operador.calculateRotateCCW(rm);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("flipv(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::flipv("+id+")");
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
	        	                operador.calculateFLIPV(rm);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("fliph(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::fliph("+id+")");
                                
                                if(id<reads.size()){
        		                PWImage rm=reads.get(id);
	        	                operador.calculateFLIPH(rm);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        			}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("equ(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::equ("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateEQU(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("gr(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::gr("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateCGr(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("lp(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::lp("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateCLp(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("sou(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::sou("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
    	    		                operador.calculateSCALE(reads.get(id),id2);
    	    		                reads.add(operador.getResult());
		                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		
                				}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("avw(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::avw("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateCAvWh(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("avar(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::avar("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateCAvArm(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("avarim(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::avarim("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateCAvAri(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("log(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::log("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){
        			                operador.calculateTLOG(reads.get(id),id2);
        			                reads.add(operador.getResult());
		                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("pow(")){
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::pow("+path.split(",")[0]+" , "+path.split(",")[1]+" , "+path.split(",")[2]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                float id3=Float.parseFloat(path.split(",")[2]);
                                
                                if(id<reads.size()){
        			                operador.calculateTPOW(reads.get(id),id2,id3);
        			                reads.add(operador.getResult());
		                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}
                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("gauss(")){     
                        
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::gauss("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){    
        		                PWImage rm=reads.get(id);
	        	                operador.calculateCONV(rm,id2);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                	                                 			
                                }

                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("conv(")){     
                        
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::conv("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateCONV(rm,AvWEITH);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}

                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("eros(")){     
                        
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::eros("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateMORPHER(rm);
//                								System.err.print("<<>>"+operador.getResult().getTypePWImage());
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}

                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("dilate(")){     
                        
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                int id=Integer.parseInt(path.split(",")[0]);
                                textField2.setText(textField2.getText()+"\nPinkWiz::dila("+id+")");
                                
                                if(id<reads.size()){
		    		                PWImage rm=reads.get(id);
			    	                operador.calculateMORPHDIL(rm);
			    	                reads.add(operador.getResult());
			            			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                
        						}

                        }else if(textField1.getText().contains(";")&& textField1.getText().contains("bin(")){     
                        
                                String str=textField1.getText();
                                String path= str.substring(str.indexOf("(")+1 , str.indexOf(")"));
                                textField2.setText(textField2.getText()+"\nPinkWiz::bin("+path.split(",")[0]+" , "+path.split(",")[1]+")");
                                int id=Integer.parseInt(path.split(",")[0]);
                                int id2=Integer.parseInt(path.split(",")[1]);
                                
                                if(id<reads.size()){    
        		                PWImage rm=reads.get(id);
	        	                operador.calculateTHRESHOLDBIN(rm,id2);
	        	                reads.add(operador.getResult());
                			textField2.setText(textField2.getText()+"\nPinkWiz::idImage: "+(reads.size()-1));		                	                                 			
                                }

                        }else{
	                		textField2.setText(textField2.getText()+"\nPinkWiz:: Command not performed");		                                        
                        }
                 textField1.setText("");
                }});
                JScrollPane scrollPane = new JScrollPane(textField2);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(textField1, BorderLayout.NORTH);
                frame.add(scrollPane, BorderLayout.CENTER);
                frame.setSize(400, 600);                
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
        }
        public static void main(String[] args) {
               Principal pr= (args.length==0) ? new Principal("-gui") : new Principal(args[0]);
        }
}
