package itens.magias;
import heroi.*;
import mapa.*;

public interface Magia {
		
	void usaMagia(Heroi heroi, Mapa mapa);
	
	String getNome();
}
