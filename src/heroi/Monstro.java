package heroi;


import mapa.Mapa;


public class Monstro extends Personagem {
	private String nome;
	private String simbolo;
	private int ataque, defesa, pontosVida, inteligencia;
	private String arma;
	
	public Monstro(String nome, String simbolo, int ataque, int defesa, int pontosVida, int inteligencia, String arma) {
		super(nome, simbolo, "monstro", ataque, defesa, pontosVida, inteligencia);
		this.nome = nome;
		this.simbolo = simbolo;
		this.ataque = ataque;
		this.defesa = defesa;
		this.pontosVida = pontosVida;
		this.inteligencia = inteligencia;
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