package heroi;

import itens.magias.MissilMagico;

public class EsqueletoMago extends Monstro{
	
	public EsqueletoMago (int x, int y) {
		super(x,y,"esqueletoMago", "EM", 2, 2, 1, 0, 6);
		addMagia(new MissilMagico());
		addMagia(new MissilMagico());
		addMagia(new MissilMagico());
	}
	
	
}