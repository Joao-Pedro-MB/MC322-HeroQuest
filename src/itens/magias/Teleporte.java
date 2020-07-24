package itens.magias;
import java.util.Scanner;
import mapa.Mapa;
import heroi.Heroi;

public class Teleporte {
	
	Teleporte(){};
	
	void usaMagia(Mapa mapa, Heroi heroi) {
		Scanner pos = new Scanner (System.in);
		
		try {
			int x = pos.nextInt();
			int y = pos.nextInt();
			mapa.teleportaHeroi(x, y);
		}
		catch(Exception e) {
			System.out.println("Input inválido");
		}
		pos.close();
	}
}
