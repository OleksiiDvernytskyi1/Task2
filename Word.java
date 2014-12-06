package Task2;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Serializable{
	
	private Symbol[] word;
	
	public Word(Symbol[] word){
		this.word = word.clone();
	}
	
	public void removeSymbols(ArrayList<Integer> forRemove) {
		Symbol[] tmpWord = new Symbol[word.length - forRemove.size()];
		for(int i =0, j=0; i<word.length; i++){
				if(!forRemove.contains(i)){
					tmpWord[j] = word[i];
					j++;
				}	
		}
		word= tmpWord;
	}

	public Word(char[] word){
		this.word = new Symbol[word.length];
		for(int i = 0; i< word.length; i++){
			this.word[i]= new Symbol(word[i]);
		}
	}
	
	public Word(ArrayList<Symbol> word){
		this.word = word.toArray(this.word); 
	}
	
	public Symbol[] getWord(){
		return word;
	}
	
	public Symbol getSymbol(int i){
		return word[i];
	}
	
	public void setSymbol(int index, Symbol s){
		word[index] = s;
	}
	
	public void addSymbol(Symbol s){
		Symbol[] newWord = new Symbol[word.length+1];
		for(int i =0; i<word.length; i++){
			newWord[i] = word[i];
		}
		newWord[newWord.length-1] = s;
		word = newWord;
	}
	public boolean hasPunctuation(){
	
		Symbol last = word[word.length-1];
		if( last.getChar() ==',' || last.getChar() == ':' || last.getChar() == '-' 
				|| last.getChar() == '!' || last.getChar() == '?' || last.getChar() == '.'){
			return true;
		}
		else 
			return false;
	
	}
	public void removeSymbol(int index){
		Symbol[] newWord = new Symbol[word.length-2];
		for(int i =0; i<index-1; i++){
			newWord[i] = word[i];
		}
		for(int i = index; i<newWord.length; i++){
			newWord[i] = word[i+1];
		}
		word = newWord;
	}
	
	public int length(){
		return word.length;
	}
	
	@Override
	public String toString(){
		String string = "";
		
		for(Symbol s: word){
			string += s.toString();
		}
		
		return string;
		
	}
}