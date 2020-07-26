package heroi;
import itens.armas.*;
import itens.Arma;

public class Goblin extends Monstro{
	
	public Goblin (int x, int y) {
		super(x,y,"Goblin", "GG", 2, 1, 1, 1, 10, "arma");
		Arma punhal = new Punhal();
	}
}