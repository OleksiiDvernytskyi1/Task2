package Task2;

import java.io.Serializable;

public class Symbol implements Serializable{
	private char symbol;
	
	public Symbol(char c){
		this.symbol = c;
	}
	
	public char getChar() {
		return symbol;
	}
	
	@Override
	public int hashCode() {
		
		return symbol;
	}
	
	public boolean equalsIgnoreCase(Object arg0){
		
		return this.toString().equalsIgnoreCase(((Symbol)arg0).toString());
	}
	
	@Override
	public boolean equals(Object arg0) {
		return this.symbol == ((Symbol)arg0).symbol;
	}
	
	@Override
	public String toString(){
		String string = "" + symbol;
		
		return string;
		
	}
	
	
}
