package Task2;

import java.io.Serializable;
import java.util.LinkedList;

public class Sentence implements Serializable{
	
	private LinkedList<Word> sentence = new LinkedList<Word>();
	
	public Sentence(){
	}
	
	public void add(Word word){
		sentence.add(word);
	}
	
	public int length(){
		return sentence.size();
	}
	
	public Word getWord(int index){
		return  sentence.get(index);
	}
	
	public void setWord(int index, Word word ){
		sentence.set(index, word);
	}
	
	@Override
	public String toString(){
		String string  = "";
		for(Word w: sentence){
			string += w.toString() + " ";	
		}
		return string;	
	}
	
	public Word[] getSentence(){
		Word[] w = new Word[sentence.size()];
		for(int i =0; i<this.sentence.size(); i++){
			w[i] = sentence.get(i);
		}
		return w;
	}
	
}