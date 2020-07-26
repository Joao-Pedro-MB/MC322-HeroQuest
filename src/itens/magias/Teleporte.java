package itens.magias;
import java.util.Scanner;
import mapa.Mapa;
import heroi.Heroi;

public class Teleporte implements Magia {
	
	public Teleporte(){};
	
	public String getNome() {
		return "Teleporte";
	}
	
	public void usaMagia(Heroi heroi, Mapa mapa) {
		Scanner pos = new Scanner (System.in);
		
		try {
			System.out.println("Digite a posição x e y do seu teleporte:");
			int x = pos.nextInt();
			int y = pos.nextInt();
			mapa.teleportaHeroi(x, y);
		}
		catch(Exception e) {
			System.out.println("Input inválido");
		}
		//pos.close();
	}
}
