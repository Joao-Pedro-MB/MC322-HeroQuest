package heroi;


import java.util.Random;
import itens.Arma;
import mapa.Mapa;


public class Monstro extends Personagem {
	private String nome;
	private String simbolo;
	private int ataque, defesa, pontosVida, inteligencia;
	protected int movimento;
	private String arma;
	private int movimento;
	private Arma arma;
	private boolean vivo;
	
	public Monstro(int x, int y, String nome, String simbolo, int ataque, int defesa, int pontosVida, int inteligencia, int movimento) {
		super(x, y, nome, simbolo, "monstro", ataque, defesa, pontosVida, inteligencia);
		this.movimento = movimento;
		this.vivo = true;
	}
	
	private void ataca(Mapa mapa) {
		int ataque = 0, bonus = 0;
		Dice dado = new Dice();
		
		if(arma != null) {
			bonus = arma.getBonus();
		}
		
		ataque = dadosAtaque + bonus;
		
		for (int i = -5 ; i < 6 ; i++) {
			if (mapa.getSurroudings(0, i) != null) {
			
			//testa ataque a longa distancia para a horizontal
				if (atacaLonge()) {
					try {
						Monstro alvo = (Monstro)mapa.getSurroudings(0, i);
						alvo.sofreDano(dado.rollAttack(ataque));
						System.out.println("Voce atacou um monstro a longa distancia em y, com i = "+i);
					}
					catch(Exception e) {
					}
				}
				
			//testa ataque a longa distancia para a horizontal
				else if (i <= 1 && i >= -1) {
					try {
						Monstro alvo = (Monstro)mapa.getSurroudings(0, i);
						alvo.sofreDano(dado.rollAttack(ataque));
						System.out.println("Voce atacou um monstro a curta distancia em y, com i = "+i);
					}
					catch(Exception e) {
					}
				}
			}
			else if (mapa.getSurroudings(i, 0) != null) {
			
		//testa ataque a longa distancia para a vertical
				if (atacaLonge()) {
					try {
						Monstro alvo = (Monstro)mapa.getSurroudings(i, 0);
						alvo.sofreDano(dado.rollAttack(ataque));
						System.out.println("Voce atacou um monstro a longa distancia em x, com i = "+i);
					}
					catch(Exception e) {
					}
				}
		
		//testa ataque a curta distancia para a vertical
				else if (i <= 1 && i >= -1) {
					try {
						Monstro alvo = (Monstro)mapa.getSurroudings(i, 0);
						alvo.sofreDano(dado.rollAttack(ataque));
						System.out.println("Voce atacou um monstro a curta distancia em x, com i = "+i);
					}
					catch(Exception e) {
					}
				}
			}
			
		//checa se o item é consumivel
			if(ataque != 0) {
				checaConsumivel();
			}
		}
	}
		
		
	public void movimenta(Mapa mapa) {
		Random rand = new Random();
		int moves = rand.nextInt(this.movimento);
		System.out.println("Monstro se movendo");
		
		int contmovimentoimpossivel=0;
		for (int i = moves ; i >= 0 ; i--) {
			moves = rand.nextInt(4);
			mapa.removeMonstro(this);
			int[] pos = {0,0};
			
			if(moves == 0) {
				pos[1]= -1;
			}
			else if(moves == 1) {
				pos[0]= -1;
			}
			else if(moves == 2) {
				pos[0]= 1;
			}
			else if(moves == 3) {
				pos[1]= 1;
			}
			else {
				pos[0]= 1;
			}
			int movimentoImpossivel = mapa.addMonstro(pos, this); //Retorna 1 caso não seja possível movimentar
			i += movimentoImpossivel;
			if(movimentoImpossivel == 1) {
				contmovimentoimpossivel++;
			}
			else {
				contmovimentoimpossivel = 0;
			}
			if(contmovimentoimpossivel == 20) {
				i = 0;
			}
		}		
	}
	
	public void sofreDano(int dano) {
		int defesa = defende();
		System.out.println("Vida Antes " + pontosVida);
		this.pontosVida-=(dano-defesa);
		System.out.println("Alvo recebeu " + dano + " de dano e defendeu " + defesa +" pontos");
		System.out.println("Vida Depois " + pontosVida);
		if(this.pontosVida < 0) {
			this.vivo = false;
		}
	}
	
	public boolean estavivo() {
		return this.vivo;
	}
	
	private int defende() {
		Dice defende = new Dice();
		return defende.rollMonsterDefense(dadosDefesa);
	}
	
	public void equipaArma(Arma arma) {
		if(this.arma == null) {
			this.arma = arma;
		}
	}
	
	private void checaConsumivel() {
		if (arma!= null && arma.ehConsumivel()) {
			System.out.println("Sua arma ficou presa no monstro e foi corroida, equipe outra");
			arma = null;
		}
	}
	
	private boolean atacaLonge() {
		if(arma != null && arma.ehLongoAlcance()) {
			return true;
		}
		return false;
	}
		
}