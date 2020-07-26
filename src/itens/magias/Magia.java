package itens.magias;
import heroi.*;
import mapa.*;

public interface Magia {
		
	void usaMagia(Heroi heroi, Mapa mapa);
	void usaMagia(Monstro monstro, Mapa mapa);
	
	String getNome();
}
