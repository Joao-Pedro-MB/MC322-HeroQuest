package heroi;
import java.util.Scanner;
import itens.*;
import mapa.*;



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
	
	public void move(String move, Mapa mapa) {
		switch(move){
			case "1":
				System.out.println("Você quer se movimentar");
				movimenta(mapa);
				return;
			case "2":
				System.out.println("Você quer atacar");
				ataca(mapa);
				return;
			case "3":
				System.out.println("Você quer usar Item");
				usaItem(mapa);
				return;
			case "4":
				System.out.println("Você quer buscar Itens");
				buscaItem(mapa);
				return;
			default:
				return;
		}
	}
	
	//avalia uma distancia pré definida na horizontal e vertical
	//depois realiza o ataque
	//TODO
	private void ataca(Mapa mapa) {
		for (int i = 0 ; i < 5 ; i++) {
			if (mapa.getSurroudings(0, i) != null) {
				System.out.println("Tem algo aqui");
			}
			if (mapa.getSurroudings(i, 0) != null) {
				System.out.println("Tem algo aqui");
			}
		}
	}
	
	private void usaItem(Mapa mapa) {
		for (String item : Itens) {
			System.out.println(item);
		}
	}
	
	private void buscaItem(Mapa mapa) {
		for (int i = 0 ; i < 5 ; i++) {
			if (mapa.getSurroudings(0, i) != null) {
				System.out.println("Tem algo aqui");
			}
			if (mapa.getSurroudings(i, 0) != null) {
				System.out.println("Tem algo aqui");
			}
		}
	}
	
	public void movimenta(Mapa mapa) {
		Scanner acao = new Scanner (System.in);
		Dice dice = new Dice();
		int moves = dice.rollDice(1);
		System.out.println("Você pode avançar " + moves + " vezes");
		String move;
		
		for (int i = moves ; i > 0 ; i--) {
			mapa.removeHeroi();
			move = acao.nextLine(); 
			int[] pos = {0,0};
			
			if(move.compareTo("a") == 0) {
				pos[1]= -1;
				mapa.addHeroi(pos, this);
			}
			else if(move.compareTo("w") == 0) {
				pos[0]= -1;
				mapa.addHeroi(pos, this);
			}
			else if(move.compareTo("s") == 0) {
				pos[0]= 1;
				mapa.addHeroi(pos, this);
			}
			else if(move.compareTo("d") == 0) {
				pos[1]= 1;
				mapa.addHeroi(pos, this);
			}
			else {
				pos[0]= 1;
				mapa.addHeroi(pos, this);
			}
			mapa.printMap();
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
