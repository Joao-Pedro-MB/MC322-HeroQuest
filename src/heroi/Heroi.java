package heroi;
import java.util.ArrayList;
import java.util.Scanner;
import itens.*;
import itens.magias.*;
import mapa.*;



public class Heroi extends Personagem {

	private int moedasOuro;
	private ArrayList<Arma> Armas = new ArrayList<Arma>();
	private ArrayList<Armadura> Armaduras = new ArrayList<Armadura>();
	private ArrayList<Magia> Magias = new ArrayList<Magia>();
	private Arma atualArma1 = null, atualArma2 = null;
	private Armadura atualArmadura = null;
	private boolean vivo;
	
	public Heroi(String nome, String classe, int dadosAtaque, int dadosDefesa, int pontosVida, int pontosInteligencia) {
		super(0, 0, nome, "HH", classe, dadosAtaque, dadosDefesa, pontosVida, pontosInteligencia);
		vivo = true;

		
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
		mapa.refreshHeroMap();
	}

	//avalia uma distancia pré definida na horizontal e vertical
	//depois realiza o ataque

	private void ataca(Mapa mapa) {
		int ataque = 0, bonus = 0;
		Dice dado = new Dice();
		
		if(atualArma1 != null) {
			bonus = atualArma1.getBonus();
		}
		else if(atualArma2 != null) {
			bonus = atualArma2.getBonus();
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
	
	public void sofreDano(int dano) {
		int defesa = defende();
		System.out.println("Você sofreu " + dano+" de dano e defendeu "+ defesa);
		
		if(dano > defesa) {
			this.pontosVida-=(dano-defesa);
		}
		
		if(this.pontosVida <= 0) {
			this.vivo = false;
		}
	}
	
	public boolean estavivo() {
		return this.vivo;
	}
	
	private int defende() {
		Dice defende = new Dice();
		return defende.rollHeroDefense(dadosDefesa);
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
			case "5":
				System.out.println("Você quer usar magias");
				if(classe.compareTo("Feiticeiro") == 0) {
					usaMagia(mapa);
				}else {
					System.out.println("Apenas magos e elfos podem usar magias");
				}
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
		
		for(Arma arma:Armas) {
            System.out.println(i++ + " " + arma.getNome());
        }
		for(Armadura armadura:Armaduras) {
            System.out.println(i++ + " " + armadura.getNome());
        }
		int usar = inventario.nextInt();
			
		if(usar == 0) {
			return;
		}
		else if(usar <= Armas.size()) {
			System.out.println("Você usou " + Armas.get(usar - 1).getNome());
			EquipaArma(Armas.get(usar - 1));
		}
		else if(usar <= Armas.size() + Armaduras.size()) {
			System.out.println("Você usou " + Armaduras.get(usar - Armas.size() - 1).getNome());
			EquipaArmadura(Armaduras.get(usar - Armas.size() - 1));
		}
		
	}
	
	private void usaMagia(Mapa mapa) {
		Scanner magias = new Scanner (System.in);
		System.out.println();
		System.out.println("||Suas Magias, para usar uma digite o número dela, ou 0 para sair||");
		int i = 1;
		
		for(Magia magia: Magias) {
            System.out.println(i++ + " " + magia.getNome());
        }
		
		int usar = magias.nextInt();
		
		if(usar == 0) {
			return;
		}
		
		if(usar <= Magias.size()) {
			System.out.println("Usou a magia " + Magias.get(usar - 1).getNome());
			Magias.get(usar - 1).usaMagia(this, mapa);
			Magias.remove(usar - 1);
		}
	}
	
	public void addMagia(Magia magia) {
		Magias.add(magia);
	}
	
	public void movimenta(Mapa mapa) {
		Scanner acao = new Scanner (System.in);
		Dice dice = new Dice();
		int moves = dice.rollDice(2);
		String move;
		
		for (int i = moves ; i > 0 ; i--) {
			System.out.println("Você pode avançar " + moves + " vezes");
			System.out.println("Utilize w,a,s,d para se movimentar e p para parar");
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
			else if(move.compareTo("p") == 0) {
				i= 0;
				mapa.addHeroi(pos, this);
				mapa.printMap();
				return;
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
	
	private boolean podeUsar(Arma arma) {
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
	
	private boolean atacaLonge() {
		if((atualArma1 != null && atualArma1.ehLongoAlcance()) ||
				(atualArma2 != null && atualArma2.ehLongoAlcance())) {
			return true;
		}
		return false;
	}
	
	private void checaConsumivel() {
		if (atualArma1!= null && atualArma1.ehConsumivel()) {
			System.out.println("Sua arma ficou presa no monstro e foi corroida, equipe outra");
			Armas.remove(atualArma1);
			atualArma1 = null;
		}
		else if (atualArma2!= null && atualArma2.ehConsumivel()) {
			System.out.println("Sua arma ficou presa no monstro e foi corroida, equipe outra");
			Armas.remove(atualArma2);
			atualArma2 = null;
		}
	}
	
}