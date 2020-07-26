package heroi;
import itens.armas.*;
import itens.Arma;

public class Goblin extends Monstro{
	
	public Goblin () {
		super("Goblin", "G", 2, 1, 1, 1, "arma");
		Arma punhal = new Punhal();
	}
}