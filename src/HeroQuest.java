import java.util.Scanner;
import heroi.*;
import mapa.*;
import mapa.Prop;

import java.util.InputMismatchException;
public class HeroQuest {
	static Scanner menu = new Scanner (System.in);
	public static  Heroi criarPersonagem() {
		Heroi heroi = null;
		int classe = 0;
		
		System.out.print("##----Escolha uma classe-----##\n\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("| Opção 1 - Feiticeiro        |\n");
		System.out.print("| Opção 2 - Bárbaro           |\n");
		System.out.print("| Opção 3 - Elfo              |\n");
		System.out.print("| Opção 4 - Anão              |\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("Digite uma opção: ");
		try {
			classe = menu.nextInt();
		}
		catch(InputMismatchException e) {
			classe = 1;
			System.out.printf("Vejo que não segue instruções, pois entã será um Bárbaro");
		}
		System.out.println();
		System.out.print("##-Qual o nome do Personagem?-##");
		String buffer = menu.nextLine();
		String nome = menu.nextLine();
		
		switch(classe) {
		case 1:
			System.out.println("Feiticeiro");
			heroi = new Feiticeiro(nome);
			return heroi;
		case 2:
			heroi = new Barbaro(nome);
			return heroi;
		case 3:
			heroi = new Elfo(nome);
			return heroi;
		case 4:
			heroi = new Anao(nome);
			return heroi;
		default:
			return heroi;
		}
	}
	
	public static void main(String[] args) {
//inicio do jogo---------------------------------------------------
		int[] pos = {0,0};
		String comando = "a";
		boolean running = true;
		Mapa mapa = new Mapa(19,26);
		Heroi heroi = criarPersonagem();
		mapa.addHeroi(pos, heroi);
		
		int mapaopcao;
		System.out.print("\n");
		System.out.print("##----Em qual mapa deseja jogar-----##\n");
		System.out.print("| Opção 1 - Mapa Default        |\n");
		System.out.print("| Opção 2 - Mapa Randômico      |\n");
		System.out.print("Digite uma opção: ");
		try {
			mapaopcao = menu.nextInt();
			if(mapaopcao != 1 && mapaopcao != 2) {
				throw new InputMismatchException();
			}
		}
		catch(InputMismatchException e) {
			mapaopcao = 1;
			System.out.println("Valor inválido. Jogará com o mapa Default");
		}
		System.out.println("");
		
		if(mapaopcao == 1 ) {
			DefaultMap defaultmap = new DefaultMap(mapa);
			defaultmap.generateDefaultMap();
		}
		else {
			RandomMap randommap = new RandomMap(mapa);
			randommap.generateRandomMap();
		}

		String buffer = menu.nextLine();
		
		mapa.printMap();
		heroi.printStatus();
//inicio do jogo---------------------------------------------------

		
//rodadas---------------------------------------------------------
		while(comando != "e" && running) {
			
			for(int i = 0 ; i < 2 ; i++) {
				//lê o movimento do jogador
				boolean prosseguir = false;
				while(prosseguir != true) {
					System.out.println("Qual sua ação?:");
					System.out.print("| Opção 1 - Movimentar        |\n");
					System.out.print("| Opção 2 - Atacar            |\n");
					System.out.print("| Opção 3 - Usar Item         |\n");
					System.out.print("| Opção 4 - Buscar Item       |\n");
					System.out.print("| Opção 5 - Usar magia        |\n");
					comando = menu.nextLine();	
					if(comando.equals("1") || comando.equals("2") || comando.equals("3")
						|| comando.equals("4") || comando.equals("5")) {
					
						prosseguir = true;
					}
				}
				//movimenta o heroi
				heroi.move(comando, mapa);
				
				if(i == 0) {
					mapa.atualizaMonstros();
					//imprime Mapa
					mapa.printMap();
				}
				
				boolean haswon = mapa.haswon();
				if(haswon) {
					running = false;
				}
			}
			//movimento os monstros
			mapa.movimentaMonstros();
			//imprime Mapa
			mapa.printMap();
			//imprime status do herói
			heroi.printStatus();
			//imprime quanto falta para o jogo acabar
			boolean haslost = mapa.haslost(heroi);
			boolean haswon = mapa.haswon();
			if(haswon || haslost) {
				running = false;
			}
		}
//rodadas---------------------------------------------------------
		
		
		//Fim de jogo
		if(mapa.haslost(heroi)) {
			System.out.print("\n \n \n");
			System.out.print("| ▓▓      ▓▓▓▓▓▓  ▓▓▓▓▓▓  ▓▓▓▓▓▓  |\n");
			System.out.print("| ▓▓      ▓▓  ▓▓  ▓▓      ▓▓      |\n");
			System.out.print("| ▓▓      ▓▓  ▓▓  ▓▓▓▓▓▓  ▓▓▓▓▓▓  |\n");
			System.out.print("| ▓▓      ▓▓  ▓▓      ▓▓      ▓▓  |\n");
			System.out.print("| ▓▓▓▓▓▓  ▓▓▓▓▓▓  ▓▓▓▓▓▓  ▓▓▓▓▓▓  |\n");
		}
		else if(mapa.haswon()) {
			System.out.print("\n \n \n");
			System.out.print("| ▓▓    ▓▓  ▓▓  ▓▓   ▓▓  |\n");
			System.out.print("| ▓▓ ▓▓ ▓▓  ▓▓  ▓▓▓▓ ▓▓  |\n");
			System.out.print("| ▓▓ ▓▓ ▓▓  ▓▓  ▓▓ ▓▓▓▓  |\n");
			System.out.print("| ▓▓▓  ▓▓▓  ▓▓  ▓▓  ▓▓▓  |\n");
			System.out.print("| ▓▓    ▓▓  ▓▓  ▓▓   ▓▓  |\n");
		}
		
		
		
		menu.close();
		
	}
}
