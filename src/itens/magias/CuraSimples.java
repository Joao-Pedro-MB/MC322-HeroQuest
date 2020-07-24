package itens.magias;
import heroi.Dice;
import heroi.Heroi;

public class CuraSimples {
	Dice cura;
	
	CuraSimples(){
		cura = new Dice();
	}
	
	void UsaMagia(Heroi heroi) {
		int pontos = cura.rollDice(1);
		heroi.addVida(pontos);
	}
}
