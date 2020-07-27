package itens.magias;
import java.util.Scanner;
import java.util.Random;
import mapa.Mapa;
import heroi.Heroi;
import heroi.Monstro;
import itens.Magia;

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
	
	public void usaMagia(Monstro monstro, Mapa mapa) {
		Random pos = new Random(20);
		try {
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
