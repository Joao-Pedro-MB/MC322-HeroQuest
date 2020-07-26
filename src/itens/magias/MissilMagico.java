package itens.magias;
import heroi.Heroi;
import heroi.Monstro;
import mapa.Mapa;

public class MissilMagico implements Magia {
	
	public MissilMagico(){}
	
	public String getNome() {
		return "Missil Mágico";
	}
	
	public void usaMagia(Heroi heroi, Mapa mapa) {
		for(int i = -5 ; i < 6 ;  i++) {
			try {
				Monstro alvo = (Monstro)mapa.getSurroudings(heroi.getPosY()+i, heroi.getPosX());
				alvo.sofreDano(6);
				System.out.println("Voce causou 6 de dano");
			}
			catch(Exception e) {
				System.out.println("Inimigos fora de alcance");
			}
			try {
				Monstro alvo = (Monstro)mapa.getSurroudings(heroi.getPosY(), heroi.getPosX()+i);
				alvo.sofreDano(6);
				System.out.println("Voce causou 6 de dano");
				return;
			}
			catch(Exception e) {
				System.out.println("Inimigos fora de alcance");
			}
		}
	}
}
	
	
	
	
	
	
	