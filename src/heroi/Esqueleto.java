package heroi;
import itens.armas.*;
import itens.Arma;

public class Esqueleto extends Monstro{
	
	public Esqueleto () {
		super("esqueleto", "E", 2, 2, 1, 0, "arma");
		Arma espadaCurta = new EspadaCurta();
	}
}
