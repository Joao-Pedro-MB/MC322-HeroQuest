package itens.magias;
import heroi.Dice;
import heroi.Heroi;
import heroi.Monstro;
import mapa.Mapa;

public class CuraSimples implements Magia {
	Dice cura;
	
	public CuraSimples(){
		cura = new Dice();
	}
	
	public String getNome() {
		return "Cura Simples";
	}
	
	public void usaMagia(Heroi heroi, Mapa mapa) {
		int pontos = cura.rollDice(1);
		heroi.addVida(pontos);
	}
	
	public void usaMagia(Monstro monstro, Mapa mapa) {
		int pontos = cura.rollDice(1);
		monstro.addVida(pontos);
	}
}
