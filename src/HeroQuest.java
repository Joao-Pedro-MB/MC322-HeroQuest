import java.util.Scanner;
import heroi.*;
import mapa.*;
import java.util.InputMismatchException;
public class HeroQuest {
	static Scanner menu = new Scanner (System.in);
	public static  Heroi criarPersonagem() {
		Heroi heroi = null;
		int classe = 0;
		
		System.out.print("##----Escolha uma classe-----##\n\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("| Opção 1 - Mago              |\n");
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
			heroi = new Mago(nome);
		case 2:
			heroi = new Barbaro(nome);
		case 3:
			heroi = new Elfo(nome);
		case 4:
			heroi = new Anao(nome);
		}
		return heroi;
	}
	
	public static void main(String[] args) {
		int[] pos = {0,0};
		String comando = "a";
		boolean ruuning = true;
		Mapa mapa = new Mapa(30);
		Heroi heroi = criarPersonagem();
		mapa.addHeroi(pos, heroi);
		System.out.println("Para onde irá se mover:");
		mapa.printMap();
		
		
		
		while(comando != "e") {
			mapa.removeHeroi();
			System.out.println("Para onde irá se mover:");
			comando = menu.nextLine();
			pos = heroi.move(comando);
			mapa.addHeroi(pos, heroi);
			mapa.printMap();
		}
		
		menu.close();
		
	}
}
