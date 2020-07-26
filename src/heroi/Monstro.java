package heroi;


import java.util.Random;

import mapa.Mapa;


public class Monstro extends Personagem {
	private String nome;
	private String simbolo;
	private int ataque, defesa, pontosVida, inteligencia;
	private int movimento;
	private String arma;
	
	public Monstro(int x, int y, String nome, String simbolo, int ataque, int defesa, int pontosVida, int inteligencia, int movimento, String arma) {
		super(x, y, nome, simbolo, "monstro", ataque, defesa, pontosVida, inteligencia);
		this.nome = nome;
		this.simbolo = simbolo;
		this.ataque = ataque;
		this.defesa = defesa;
		this.pontosVida = pontosVida;
		this.inteligencia = inteligencia;
		this.movimento = movimento;
		this.arma = arma;
	}
	
	private void ataca(Mapa mapa) {
		for (int i = -5 ; i < 6 ; i++) {
			if (mapa.getSurroudings(0, i) != null) {
				if (arma.ehLongoAlcance()) {
					try {
						Dice dado = new Dice();
						Heroi alvo = (Heroi)mapa.getSurroudings(0, i);
						int ataque = ataque + arma.getBonus();
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
				else if (i <= 1) {
					try {
						Dice dado = new Dice();
						Heroi alvo = (Heroi)mapa.getSurroudings(0, i);
						int ataque = ataque + arma.getBonus();
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
			}
			else if (mapa.getSurroudings(i, 0) != null) {
				if (arma.ehLongoAlcance()) {
					try {
						Dice dado = new Dice();
						Heroi alvo = (Heroi)mapa.getSurroudings(0, i);
						int ataque = ataque + arma.getBonus();
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
				else if (i <= 1) {
					try {
						Dice dado = new Dice();
						Heroi alvo = (Heroi)mapa.getSurroudings(0, i);
						int ataque = ataque + arma.getBonus();
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
			}
		}
		
	public void movimenta(Mapa mapa) {
		Random rand = new Random();
		int moves = rand.nextInt(this.movimento);
		System.out.println("Monstro se movendo");
		
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
		}		
	}
	
	public void sofreDano(int dano) {
		int defesa = defende();
		
		this.pontosVida-=(dano-defesa);
	}
	
	private int defende() {
		Dice defende = new Dice();
		return defende.rollMonsterDefense(defesa);
	}
	
	
	
}