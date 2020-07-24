package itens.magias;
import heroi.Dice;
import mapa.Mapa;

public class BolaDeFogo {
	
	BolaDeFogo(){}
	
	void usaMagia(int x, int y, Mapa mapa) {
		mapa[y][x].dano(6);
		mapa[y+1][x].dano(3);
		mapa[y-1][x].dano(3);
		mapa[y][x+1].dano(3);
		mapa[y][x-1].dano(3);		
	}
}
