package itens.magias;
import heroi.Dice;
import mapa.Mapa;

public class MissilMagico {
	
	MissilMagico(){}
	
	void usaMagia(int x, int y, Mapa mapa) {
		mapa[y][x].dano(6);
	}
}