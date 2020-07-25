package mapa;
import heroi.Heroi;
import java.lang.IndexOutOfBoundsException;
import exception.IllegalMoveException;

public class Mapa {
	Prop[][] map;
	String[][] wallmap;
	Boolean[][] heromap;
	int[][] roomsidentifier;
	int lineLength;
	int collumnLength;
	int heroiX = 0, heroiY = 0;
	
	public Mapa(int lineLength, int collumnLength){
		this.lineLength = lineLength;
		this.collumnLength = collumnLength;
		this.map = new Prop[lineLength][collumnLength];
		this.wallmap = new String[lineLength][collumnLength];
		this.heromap = new Boolean[lineLength][collumnLength];
		this.roomsidentifier = new int[lineLength][collumnLength];
		for (int i = 0 ; i < lineLength ; i++) {
			for (int j = 0 ; j < collumnLength ; j++) {
				this.map[i][j] = null;
				this.wallmap[i][j] = "";
				this.roomsidentifier[i][j] = 0;
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
	
	//adiciona paredes ao mapa
	public void includeWall(int positionX, int positionY, String opcao) {
		wallmap[positionX][positionY] = opcao;
	}
	
	public void includeDoor(int positionX, int positionY, String opcao) {
		wallmap[positionX][positionY] = opcao;
	}
	
	public void identifyRooms(int id, int yStart, int yEnd, int xStart, int xEnd) {
		for (int i=xStart; i<=xEnd; i++) {
			for(int j=yStart; j<=yEnd; j++) {
				roomsidentifier[i][j] = id;
			}
		}
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
					if(heromap[i][j]) {
						System.out.printf("░░");
					}
					else {
						System.out.printf("▓▓");
					}
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
	
	public int addHeroi(int[] pos, Prop heroi) {
		String move="";
		heroiX += pos[1];
		heroiY += pos[0];
		if(pos[1] == 1) {
			move = "direita";
		}
		else if(pos[1] == -1) {
			move = "esquerda";
		}
		else if(pos[0] == 1) {
			move = "baixo";
		}
		else if(pos[0] == -1) {
			move = "cima";
		}
		
		try {
			if((move.equals("baixo") && wallmap[heroiY-1][heroiX].equals("baixo")) || (move.equals("baixo") && wallmap[heroiY-1][heroiX].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			else if((move.equals("direita") && wallmap[heroiY][heroiX-1].equals("lado")) || (move.equals("direita") && wallmap[heroiY][heroiX-1].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			else if((move.equals("cima") && wallmap[heroiY][heroiX].equals("baixo")) || (move.equals("cima") && wallmap[heroiY][heroiX].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			else if((move.equals("esquerda") && wallmap[heroiY][heroiX].equals("lado")) || (move.equals("esquerda") && wallmap[heroiY][heroiX].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			map[heroiY][heroiX] = heroi;
		}
		catch(Exception e) {
			heroiX -= pos[1];
			heroiY -= pos[0];
			map[heroiY][heroiX] = heroi;
			refreshHeroMap();
			//printHeroMapAAA();			
			return 1;
 		}
		refreshHeroMap();
		//printHeroMapAAA();	
		return 0;
	}
	
	public void removeHeroi() {
		map[heroiY][heroiX] = null;
	}
	
	public void teleportaHeroi(int x, int y) {
		try {
			map[y][x] = null;
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Teleporte Inválido");
			return;
		}
		
		int[] pos= {y,x};
		Heroi heroi = (Heroi)map[heroiY][heroiX];
		removeHeroi();
		addHeroi(pos, heroi);
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
	
	public void refreshHeroMap() {
		for (int i = 0 ; i < lineLength ; i++) {
			for (int j = 0 ; j < collumnLength ; j++) {
				this.heromap[i][j] = false;
			}
		}
		
		int i,j;
		boolean canseefurther;
		
		
		// Visão horizontal para a direita
		j = heroiX;
		canseefurther = true;
		while( j < collumnLength && canseefurther) {
			heromap[heroiY][j] = true;
			//System.out.println(map[heroiY][j].symbol);
			if(map[heroiY][j] != null && j != heroiX) {
				canseefurther = false;
			}
			if(wallmap[heroiY][j] == "lado" || wallmap[heroiY][j] == "baixolado") {
				canseefurther = false;
			}
			else {
				j++;
			}
			
		}
		
		//Visão horizontal para a esquerda
		j = heroiX;
		canseefurther = true;
		while( j >= 0 && canseefurther) {
			if(map[heroiY][j] != null && j != heroiX) {
				canseefurther = false;
					}
			
			if(wallmap[heroiY][j] == "lado" || wallmap[heroiY][j] == "baixolado") {
				canseefurther = false;
			}
			else {
				heromap[heroiY][j] = true;
				j--;
			}
			
		}
		
		// Visão vertical para baixo
		i = heroiY;
		canseefurther = true;
		while( i < lineLength && canseefurther) {
			heromap[i][heroiX] = true;
			if(map[i][heroiX] != null && i != heroiY) {
				canseefurther = false;
			}
			
			if(wallmap[i][heroiX]  == "baixo" || wallmap[i][heroiX]  == "baixolado") {
				canseefurther = false;
			}
			else {
				i++;
			}
			
		}
		
		//Visão vertical para cima
		i = heroiY;
		canseefurther = true;
		while( i >= 0 && canseefurther) {
			if(map[i][heroiX] != null && i != heroiY) {
				canseefurther = false;
					}
			
			if(wallmap[i][heroiX]  == "baixo" || wallmap[i][heroiX]  == "baixolado") {
				canseefurther = false;
			}
			else {
				heromap[i][heroiX]  = true;
				i--;
			}
			
		}
		
		//Visão dentro de salas
		int id = roomsidentifier[heroiY][heroiX];
		if(id != 0) {
			for (int x = 0 ; x < lineLength ; x++) {
				for (int y = 0 ; y < collumnLength ; y++) {
					if(roomsidentifier[x][y] == id) {
						heromap[x][y] = true;
					}
				}
			}
		}
		
	}
	
	public void printHeroMapAAA() {
		for (int i = 0 ; i < lineLength ; i++) {
			for (int j = 0 ; j < collumnLength ; j++) {
				//System.out.printf(roomsidentifier[i][j]+" ");
			
				if(heromap[i][j] == true) {
					System.out.printf("1 ");
				}
				else {
					System.out.printf("0 ");
				}
				
			}
			System.out.println(" ");
		}
		
	}
	
	//pega o entorno do jogador
	public Prop getSurroudings(int y, int x) {
		return this.map[heroiY + y][heroiX + x];
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


