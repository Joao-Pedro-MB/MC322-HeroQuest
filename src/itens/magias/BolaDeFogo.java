package itens.magias;
import heroi.Heroi;
import heroi.Monstro;
import mapa.Mapa;

public class BolaDeFogo implements Magia {
	
	public BolaDeFogo(){}
	
	public void usaMagia(Heroi heroi, Mapa mapa) {
		for(int i = -5 ; i < 6 ;  i++) {
			try {
				Monstro alvo = (Monstro)mapa.getSurroudings(0, i);
				alvo.sofreDano(6);
				checaLado(alvo, mapa);
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
	
	private void checaLado(Monstro monstro, Mapa mapa) {
		Monstro alvo1 = (Monstro)mapa.getSurroudings(monstro.getPosY()+1, monstro.getPosX());
		Monstro alvo2 = (Monstro)mapa.getSurroudings(monstro.getPosY()-1, monstro.getPosX());
		Monstro alvo3 = (Monstro)mapa.getSurroudings(monstro.getPosY(), monstro.getPosX()+1);
		Monstro alvo4 = (Monstro)mapa.getSurroudings(monstro.getPosY(), monstro.getPosX()-1);
		
		if(alvo1 != null) {
			alvo1.sofreDano(2);
		System.out.println("Voce causou 2 de dano");
		}
		else if(alvo2 != null) {
			alvo2.sofreDano(2);
		System.out.println("Voce causou 2 de dano");
		}
		else if(alvo3 != null) {
			alvo3.sofreDano(2);
		System.out.println("Voce causou 2 de dano");
		}
		else if(alvo4 != null) {
			alvo4.sofreDano(2);
		System.out.println("Voce causou 2 de dano");
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
	
	public String getNome() {
		return "Bola de Fogo";
	}
}
