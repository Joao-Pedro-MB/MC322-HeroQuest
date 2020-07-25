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
		System.out.println("A"+classe+"A");
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
		boolean ruuning = true;
		Mapa mapa = new Mapa(19,26);
		Heroi heroi = criarPersonagem();
		mapa.addHeroi(pos, heroi);
		
		DefaultMap defaultmap = new DefaultMap(mapa);
		defaultmap.generateDefaultMap();
		
		
		mapa.printMap();
		heroi.printStatus();
//inicio do jogo---------------------------------------------------

		
//rodadas---------------------------------------------------------
		while(comando != "e" && ruuning) {
			//lê o movimento do jogador
			System.out.println("Qual sua ação?:");
			System.out.print("| Opção 1 - Movimentar        |\n");
			System.out.print("| Opção 2 - Atacar            |\n");
			System.out.print("| Opção 3 - Usar Item         |\n");
			System.out.print("| Opção 4 - Buscar Item       |\n");
			comando = menu.nextLine();	
			//movimenta o heroi
			heroi.move(comando, mapa);
			//imprime Mapa
			mapa.printMap();
			//imprime status do herói
			heroi.printStatus();
		}
//rodadas---------------------------------------------------------
		
		menu.close();
		
	}
}
