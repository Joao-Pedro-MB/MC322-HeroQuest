package heroi;
import java.util.ArrayList;
import java.util.Scanner;
import itens.*;
import mapa.*;



public class Heroi extends Prop {
	private String nome;
	private int dadosAtaque, dadosAtaqueArma, dadosDefesa;
	private int pontosVida, pontosInteligencia, moedasOuro;
	int totalArmas = 0, totalMagias = 0;
	private ArrayList<Arma> Armas = new ArrayList<Arma>();
	private ArrayList<Armadura> Armaduras = new ArrayList<Armadura>();
	//private ArrayList<Pocao> Pocoes = new ArrayList<Pocao>();
	private ArrayList<String> Itens = new ArrayList<String>();
	private String classe;
	private Arma atualArma1 = null, atualArma2 = null;
	private Armadura atualArmadura = null;
	
	
	public Heroi(String nome, String classe, int dadosAtaque, int dadosDefesa, int pontosVida, int pontosInteligencia) {
		super("HH", 0, 0);
		this.classe = classe;
		this.nome = nome; 
		this.dadosAtaque = dadosAtaque;
		this.dadosDefesa = dadosDefesa;
		this.pontosVida = pontosVida;
		this.pontosInteligencia = pontosInteligencia;
		this.Itens.add("item1");
		
	}
	
	//TODO
	public void addItem (String Item) {
		
	}
	
	//TODO
	public void removeItem(int index) {
		
	}
	
	private void buscaItem(Mapa mapa) {
		for (int i = -2 ; i < 3 ; i++) {
			if (mapa.getSurroudings(0, i) != null && i != 0) {
				mapa.revelaBau(0, i);
				mapa.revelaArmadilha(0, i);
			}
			if (mapa.getSurroudings(i, 0) != null && i != 0) {
				mapa.revelaBau(i, 0);
				mapa.revelaArmadilha(i, 0);
			}
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

	public void addOuro(int quantidade) {
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
				usaItem();
				return;
			case "4":
				System.out.println("Você quer buscar Itens");
				buscaItem(mapa);
				return;
			default:
				return;
		}
	}
		
	private void usaItem() {
		Scanner inventario = new Scanner (System.in);
		System.out.println();
		System.out.println("||Seu inventário, para usar um item digite o número dele, ou 0 para sair||");
		int i = 1;
		
		for(String item:Itens) {
            System.out.println(i++ + " " + item);
        } 
		for(Arma arma:Armas) {
            System.out.println(i++ + " " + arma.getNome());
        }
		for(Armadura armadura:Armaduras) {
            System.out.println(i++ + " " + armadura.getNome());
        }
		System.out.println(Itens.size() + " " + Armas.size() + " " + Armaduras.size());
		int usar = inventario.nextInt();
			
		if(usar == 0) {
			return;
		}
		else if(usar <= Itens.size()) {
			System.out.println("Você usou " + Itens.get(i - 1));
		}
		else if(usar <= Itens.size() + Armas.size()) {
			System.out.println("Você usou " + Armas.get(usar - Itens.size() - 1).getNome());
			EquipaArma(Armas.get(usar - Itens.size() - 1));
		}
		else if(usar <= Itens.size() + Armas.size() + Armaduras.size()) {
			System.out.println("Você usou " + Armaduras.get(usar - Itens.size() - Armas.size() - 1).getNome());
			EquipaArmadura(Armaduras.get(usar - Itens.size() - Armas.size() - 1));
		}
		
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
		//acao.close();
		
	}
	
	
	public void printStatus() {
		System.out.println("Jogador(a): " + nome + ", Arma1: " + ((atualArma1 != null) ? atualArma1.getNome(): "punho") + " Arma 2: " + ((atualArma2 != null) ? atualArma2.getNome(): "punho") + ", Vida: " + pontosVida + ", ouro: " + moedasOuro);
		System.out.println("Classe: " + classe + ", Armadura: "+ ((atualArmadura != null) ? atualArmadura.getNome(): "trapos"));
	}
	
	
	public void addArma(Arma arma) {
		if(podeUsar(arma)) {
			this.Armas.add(arma);
		}
	}
	
	
	public boolean podeUsar(Arma arma) {
		if(!arma.ehRestrita() || classe.compareTo("Feiticeiro") != 0) {
			return true;
		}
		return false;
	}
	
	
	public void EquipaArma(Arma arma) {
		if(!Armas.contains(arma)) {
			Armas.add(arma);
		}
		if(arma.ehDuasMaos()) {
			atualArma1 = arma;
			atualArma2 = null;
		}
		else if(atualArma1 == null) {
			atualArma1 = arma;
				
		}
		else {
			atualArma2 = arma;
				
		}
	}
	
	
	public void DesequipaArma() {
		atualArma1 = null;
		atualArma2 = null;
	}
	
	
	public void EquipaArmadura(Armadura armadura) {
		if(!Armaduras.contains(armadura)) {
			Armaduras.add(armadura);
		}
		atualArmadura = armadura;
	}
	

	public void DesequipaArmadura() {
		atualArmadura = null;
	}
	
	
	public void removeArma(Arma arma) {
		if (Armas.contains(arma)) {
			Armas.remove(arma);
		}
	}
	
	
	public void addArmadura(Armadura armadura) {
		Armaduras.add(armadura);
	}
	
	
	public void removeArmadura(Armadura armadura) {
		if (Armaduras.contains(armadura)) {
			Armaduras.remove(armadura);
		}
	}
	

	public void addVida(int pontos) {
		this.pontosVida += pontos;
	}
	
	
	public void recebeDano(int dano) {
		pontosVida -= dano;
	}
	
	
	//public void removePocao(Pocao pocao) {
	//	if (Pocoes.contains(pocao)) {
	//		Pocoes.remove(pocao);
	//	}
	//}
}
