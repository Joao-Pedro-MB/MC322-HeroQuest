package heroi;
import itens.*;
import mapa.Prop;



public class Heroi extends Prop {
	private String nome;
	private int dadosAtaque, dadosAtaqueArma, dadosDefesa;
	private int pontosVida, pontosInteligencia, moedasOuro;
	int totalArmas = 0, totalMagias = 0;
	// Armas, Armaduras, Poções, outros itens
	// Current Armor
	// Current Arma 1
	// Current Arma 2	
	private String[] Armas;
	private String[] Armaduras;
	private String[] Pocoes;
	private String[] Itens;
	private String[] Magias;
	private String atualArmadura;
	private String atualArma1;
	private String atualArma2;
	
	
	public Heroi(String nome, int dadosAtaque, int dadosDefesa, int pontosVida, int pontosInteligencia) {
		super("HH", 0, 0);
		this.nome = nome; 
		this.dadosAtaque = dadosAtaque;
		this.dadosDefesa = dadosDefesa;
		this.pontosVida = pontosVida;
		this.pontosInteligencia = pontosInteligencia;
		this.Armas = new String[10];
		this.Armaduras = new String[10];
		this.Pocoes = new String[10];
		this.Itens = new String[10];
		this.Magias = new String[10];
		
	}
	
	public void adicionaOuro(int quantidade) {
		this.moedasOuro+=quantidade;
	}
	
	public void diminuiOuro(int quantidade) {
		this.moedasOuro-=quantidade;
	}
	
	public int[] move(String move) {
		int[] pos = {0,0};
		switch(move){
			case "a":
				pos[1]= -1;
				return pos;
			case "w":
				pos[0]= -1;
				return pos;
			case "s":
				pos[0]= 1;
				return pos;
			case "d":
				pos[1]= 1;
				return pos;
			default:
				return pos;
		}
	}
	
	public void printStatus() {
		System.out.println("Jogador(a): " + nome + ", Arma1: " + atualArma1 + " Arma 2: " + atualArma1 + ", Vida: " + pontosVida + ", ouro: " + moedasOuro);
	}
	
	//TODO
	public void EquipaArma(String Arma) {
		
	}
	
	//TODO
	public void DesequipaArma() {
		
	}
	
	//TODO
	public void EquipaArmadura(String Armadura) {
		
	}
	
	//TODO
	public void DesequipaArmadura() {
		
	}
	
	//TODO
	public void addArma(String Arma) {
		this.Armas[totalArmas] = Arma;
		totalArmas++;
	}
	
	//TODO
	public void removeArma(int index) {
		
	}
	
	//TODO
	public void addArmadura(String Armadura) {
		
	}
	
	//TODO
	public void removeArmadura(int index) {
		
	}
	
	//TODO
	public void addPocao(String Pocao) {
		
	}
	
	//TODO
	public void removePocao(int index) {
		
	}
	
	//TODO
	public void addItem (String Item) {
		
	}
	
	//TODO
	public void removeItem(int index) {
		
	}
	
	//TODO
	public void addMagia(String Magia) {
		this.Armas[totalMagias] = Magia;
		totalMagias++;
	}
	
	//TODO
	public void removeMagia(int index) {
		
	}
}
