package mapa;

public class Prop {
	
	public int posX, posY;
	public String symbol;
	
	public Prop(String symbol, int posY, int posX) {
		this.posX = posX;
		this.posY = posY;
		this.symbol = symbol;
	}
	
	//retorna posição Y atual do item
	public int getPosY(){
		return this.posY;
	}
	
	//retorna posição X atual do item
	public int getPosX(){
		return this.posX;
	}
	
	//imprime simbolo do item
	public void print() {
		System.out.printf(symbol);
	}
}
