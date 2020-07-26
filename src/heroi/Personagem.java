package heroi;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import itens.*;
import mapa.*;



public class Personagem extends Prop {
	private String nome, classe, simbolo;
	private int dadosAtaque, dadosDefesa, pontosVida, pontosInteligencia;
	
	
	public Personagem(String nome, String simbolo, String classe, int dadosAtaque, int dadosDefesa, int pontosVida, int pontosInteligencia) {
		super(simbolo, 0, 0);
		this.classe = classe;
		this.nome = nome; 
		this.simbolo = simbolo;
		this.dadosAtaque = dadosAtaque;
		this.dadosDefesa = dadosDefesa;
		this.pontosVida = pontosVida;
		this.pontosInteligencia = pontosInteligencia;
	}

	//TODO
	private void ataca(Mapa mapa) {

	}
	
	private void defende() {
		
	}

	private void sofreDano(int dano) {
		pontosVida-=dano;
	}

	
	public void movimenta(Mapa mapa) {
		Scanner acao = new Scanner (System.in);
		Dice dice = new Dice();
		int moves = dice.rollDice(2);
		System.out.println("Você pode avançar " + moves + " vezes");
		String move;
		
		for (int i = moves ; i > 0 ; i--) {
			mapa.removeHeroi();
			move = acao.nextLine(); 
			int[] pos = {0,0};
			
			if(move.compareTo("a") == 0) {
				pos[1]= -1;
			}
			else if(move.compareTo("w") == 0) {
				pos[0]= -1;
			}
			else if(move.compareTo("s") == 0) {
				pos[0]= 1;
			}
			else if(move.compareTo("d") == 0) {
				pos[1]= 1;
			}
			else {
				pos[0]= 1;
			}
			int movimentoImpossivel = mapa.addHeroi(pos, this); //Retorna 1 caso não seja possível movimentar
			i += movimentoImpossivel;
			
			mapa.printMap();
		}
		acao.close();
		
	}
	
	public void movimenta(Mapa mapa, int maximo) {
		Random rand = new Random();
		int moves = rand.nextInt(maximo);
		System.out.println("Monstro se movendo");
		
		for (int i = moves ; i > 0 ; i--) {
			moves = rand.nextInt(4);
			//mapa.removeHeroi();
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
			int movimentoImpossivel = mapa.addHeroi(pos, this); //Retorna 1 caso não seja possível movimentar
			i += movimentoImpossivel;
			
			mapa.printMap();
		}		
	}
	
	
}
