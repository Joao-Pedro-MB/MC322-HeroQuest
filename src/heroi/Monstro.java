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
	
	public void ataca(int dano) {
		
	}
	
	public void sofreDano(int dano) {
		this.pontosVida-=pontosVida;
	}
	
	public void defende() {
		//rola dado de defesa
	}
	
	
	
}