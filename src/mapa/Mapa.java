package mapa;

public class Mapa {
	Prop[][] map;
	String[][] wallmap;
	int lineLength;
	int collumnLength;
	int heroiX = 0, heroiY = 0;
	
	public Mapa(int lineLength, int collumnLength){
		this.lineLength = lineLength;
		this.collumnLength = collumnLength;
		this.map = new Prop[lineLength][collumnLength];
		this.wallmap = new String[lineLength][collumnLength];	
		for (int i = 0 ; i < lineLength ; i++) {
			for (int j = 0 ; j < collumnLength ; j++) {
				this.map[i][j] = null;
				this.wallmap[i][j] = "";
			}
		}
	}
	
	//adiciona itens ao mapa
	public void includeItem(Prop item) {
		
		int x = item.getPosX();
		int y = item.getPosY();
		
		try {
			map[y][x] = item;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
			System.out.println("Um item foi posicionado fora do mapa:");
			item.print();
		}
	}
	
	public void includeWall(int positionX, int positionY, String opcao) {
		wallmap[positionX][positionY] = opcao;
	}
	
	//imprime mapa de itens
	public void printMap () {
		System.out.println();
		System.out.println();
		for (int i = 0 ; i < map.length ; i++) {
			for (int j = 0 ; j < map[0].length ; j++) {
				try {
					this.map[i][j].print();
				}
				catch(NullPointerException e) {
					System.out.printf("░░");
				}
				
				if(wallmap[i][j].equals("lado") || wallmap[i][j].equals("baixolado")) {
					System.out.printf("║");
				}
				else {
					System.out.printf(" ");
				}
			}
			System.out.println();
			for(int j = 0; j < map[0].length ; j++) {
				if(wallmap[i][j].equals("baixo") || wallmap[i][j].equals("baixolado")) {
					System.out.printf("==");
				}
				else {
					System.out.printf("  ");
				}
				System.out.printf(" ");
			}
			System.out.println();
		}
	}
	
	public void addHeroi(int[] pos, Prop heroi) {
		heroiX += pos[1];
		heroiY += pos[0];
		map[heroiY][heroiX] = heroi;
	}
	
	public void removeHeroi() {
		map[heroiY][heroiX] = null;
	}
	
	//atualiza a posição do jogado no mapa
	public void movePlayer(int[] move, Prop heroi) {
		try {
			map[move[0]][move[1]] = heroi;
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("Movimento inválido");
			return;
		}
			
	}
	
	//pega o entorno do jogador
	public Prop[] getSurroudings(Prop heroi) {
		int x = heroi.getPosX();
		int y = heroi.getPosY();
		int n = 0;
		
		Prop[] surroudings = new Prop[4];
		
		for(int i = -1 ; i < 2 ; i+=2) {
			try {
				surroudings[n] = map[y][x+i];
				n++;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				surroudings[n] = new Prop("0", 0, 0);
				n++;
			}
		}
		
		for(int i = -1 ; i < 2 ; i+=2) {
			try {
				surroudings[n] = map[y+i][x];
				n++;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				surroudings[n] = new Prop("0", 0, 0);
				n++;
			}
		}
		
		return surroudings;
	}
	
	//delete props do mapa
	public void delete(Prop item) {
		try {
			map[item.getPosY()][item.getPosX()] = null;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
}


