package heroi;
import itens.armas.*;
import itens.Arma;

public class Esqueleto extends Monstro{
	
	public Esqueleto (int x, int y) {
		super(x, y, "esqueleto", "EE", 2, 2, 1, 0,6, "arma");
		Arma espadaCurta = new EspadaCurta();
	}
}
