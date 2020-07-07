package mapa;
import heroi.Heroi;

public class Mapa {
	String[][] map;
	int heroiX = 0, heroiY = 0;
	
	public Mapa(){
		this.map = new String[25][25];
	
	}
	
	public void printMap() {
		for(String[] item : map) {
			for(String block : item) {
				if(block == null) {
					System.out.printf("..");
				}
				else {
					System.out.printf(block);
				}
			}
			System.out.println();
		}
	}
	
	public void addHeroi(int[] pos) {
		heroiX += pos[1];
		heroiY += pos[0];
		map[heroiY][heroiX] = "HM";
	}
	
	public void removeHeroi() {
		map[heroiY][heroiX] = "..";
	}
	
}
