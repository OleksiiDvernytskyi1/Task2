package Task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Text {
	
	/*
	 *  Удаление из каждого  слова текста букв совпадающих с первой или последней буквой слова 
	 */

	public static void main(String[] args) 

		 throws FileNotFoundException, IOException {

		        File file = new File( "D:\\test.txt" );
		        File outFile = new File("D:\\test.out");
		        
		        if(outFile.exists()){
		        	outFile.delete();
		        	outFile.createNewFile();
		        }

		        BufferedReader br = new BufferedReader (
		            new InputStreamReader(
		                new FileInputStream( file )
		            )
		        );
		        
		        
		        ObjectOutputStream output =new ObjectOutputStream( 
			            new FileOutputStream( outFile )
			    );
		        
		        
		        String line = null;
		        Sentence sentence = new Sentence();
		        
		        // читаем файл, и делим текст на предложения и записываем во временный файл
		        while ((line = br.readLine()) != null) {	
		            line = line.trim();
		                        
		            String[] wordArray = line.split(" ");
		            
		            for(String w : wordArray){
		            	w = w.trim();
	            		char[] word = w.toCharArray();
	            		sentence.add(new Word(word));
	            		
		            	if(w.endsWith(".") || w.endsWith("!") || w.endsWith("?") ){		            		
		            		output.writeObject(sentence);
		            		
		            		sentence = new Sentence();
		            	}
		            	
		            		
		            }
		               
		        }
		        br.close();
		        output.flush();
		        output.close();
		        
		        ObjectInputStream input =new ObjectInputStream( 
			            new FileInputStream( outFile )
			    );
		        
		        BufferedWriter bw = new BufferedWriter(
		        		new OutputStreamWriter(
		        				new FileOutputStream(
		        						new File("D:\\out.txt")
		        )));
		        
		        /* 
		         * читаем предложения з временного файла, и для каждого слова ищем повторы первой и последней буквы
		         * и найдя буквы которые нудно удалить вызываем метод removeSymbols
		         * итоговое предложение записываем в выходной текстовый файл.
		        */
		        try {
					while((sentence = (Sentence)input.readObject()) != null){
						
						for(int i = 0; i< sentence.length(); i++){
							
							Word w = sentence.getWord(i);
							Symbol first = w.getSymbol(0);
							Symbol last = w.getSymbol(w.length()-1);
							if( w.hasPunctuation() ){
								last = w.getSymbol(w.length()-2);
							}
							ArrayList<Integer> forRemove = new ArrayList();  
							int length = w.length();
							if(w.hasPunctuation()){
								length--;
							}
							for(int j= 1; j<length-1; j++){
								Symbol tmp = w.getSymbol(j);
								if(tmp.equalsIgnoreCase(first) || tmp.equalsIgnoreCase(last)){
									forRemove.add(j);
								}
							}
							if(! forRemove.isEmpty()){
								w.removeSymbols(forRemove);
							}
							
									
						}
						bw.write(sentence.toString());
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 		catch (EOFException e) {
				// TODO Auto-generated catch block
		 			bw.close();
		 		}
		        finally{
		        	bw.close();
		        }
		        
		  
		}
		
}




