package itens.magias;
import heroi.Heroi;
import heroi.Monstro;
import itens.Magia;
import mapa.Mapa;

public class MissilMagico implements Magia {
	
	public MissilMagico(){}
	
	public String getNome() {
		return "Missil Mágico";
	}
	
	public void usaMagia(Heroi heroi, Mapa mapa) {
		for(int i = -5 ; i < 6 ;  i++) {
			try {
				Monstro alvo = (Monstro)mapa.getSurroudings(0, i);
				alvo.sofreDano(6);
				System.out.println("Voce causou 6 de dano");
			}
			catch(Exception e) {
				System.out.println("Inimigos fora de alcance");
			}
			try {
				Monstro alvo = (Monstro)mapa.getSurroudings(i, 0);
				alvo.sofreDano(6);
				System.out.println("Voce causou 6 de dano");
				return;
			}
			catch(Exception e) {
				System.out.println("Inimigos fora de alcance");
			}
		}
	}
	
	public void usaMagia(Monstro monstro, Mapa mapa) {
		for(int i = -5 ; i < 6 ;  i++) {
			try {
				Heroi alvo = (Heroi)mapa.getSurroudings(0, i, monstro);
				alvo.sofreDano(6);
				System.out.println("Voce causou 6 de dano");
			}
			catch(Exception e) {
				System.out.println("Inimigos fora de alcance");
			}
			try {
				Heroi alvo = (Heroi)mapa.getSurroudings(i, 0, monstro);
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
	
	
	
	
	
	
	