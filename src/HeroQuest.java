import java.util.Scanner;
public class HeroQuest {
	
	public static  void criarPersonagem() {
		Scanner menu = new Scanner (System.in);
		
		System.out.print("##----Escolha uma classe-----##\n\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("| Opção 1 - Mago              |\n");
		System.out.print("| Opção 2 - Bárbaro           |\n");
		System.out.print("| Opção 3 - Elfo              |\n");
		System.out.print("| Opção 4 - Anão              |\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("Digite uma opção: ");
		int classe = menu.nextInt();
		System.out.println();
		System.out.print("##-Qual o nome do Personagem?-##");
		String buffer = menu.nextLine();
		String nome = menu.nextLine();
		menu.close();
	}
	
	public static void main(String[] args) {
		
		//escolherMapa();
		criarPersonagem();
		
		//while(!jogoFinalizado()) {
			//rodada();
		//}
		
	}
}
