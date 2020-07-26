package mapa;
import heroi.Heroi;
import heroi.Monstro;
import bauEarmadilha.*;
import java.lang.IndexOutOfBoundsException;
import exception.IllegalMoveException;
import java.util.ArrayList;
import heroi.*;

public class Mapa {
	Prop[][] map;
	String[][] wallmap;
	Boolean[][] heromap;
	int[][] roomsidentifier;
	int lineLength;
	int collumnLength;
	int heroiX = 0, heroiY = 0;
	ArrayList<Monstro> monstros = new ArrayList();
	
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
	
	public void includeBau(int x, int y) {
		Prop bau = new Baus(x, y);
		map[y][x] = bau;
	}
	
	public void includeArmadilha(int x, int y) {
		Prop armadilha = new Armadilha(x, y);
		map[y][x] = armadilha;
	}
	
	public void includeEsqueleto(int x, int y) {
		Monstro monstro = new Esqueleto (y,x);
		monstros.add(monstro);
		map[y][x] = monstro;
	}
	
	public void includeEsqueletoMago(int x, int y) {
		Monstro monstro = new EsqueletoMago (y,x);
		monstros.add(monstro);
		map[y][x] = monstro;
	}
	
	public void includeGoblin(int x, int y) {
		Monstro monstro = new Goblin (y,x);
		monstros.add(monstro);
		map[y][x] = monstro;
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
					if(heromap[i][j]) {
						this.map[i][j].print();
					}
					else {
						System.out.printf("::");
					}
				}
				catch(NullPointerException e) {
					if(heromap[i][j]) {
						System.out.printf("░░");
					}
					else {
						System.out.printf("::");
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
		
		checaArmadilha((Heroi)heroi);
		checaBau((Heroi)heroi);
		
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
			if(map[heroiY][heroiX] != null && !map[heroiY][heroiX].symbol.equals("░░") && !map[heroiY][heroiX].symbol.equals("CC") && !map[heroiY][heroiX].symbol.equals("XX")) {
				throw new IllegalMoveException();
			}
			else if((move.equals("baixo") && wallmap[heroiY-1][heroiX].equals("baixo")) || (move.equals("baixo") && wallmap[heroiY-1][heroiX].equals("baixolado"))) {
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
			return 1;
 		}
		refreshHeroMap();	
		return 0;
	}
	
	public void removeHeroi() {
		map[heroiY][heroiX] = null;
	}
	
	public int addMonstro(int[] pos, Monstro monstro) {
		String move="";
		int id = monstros.indexOf(monstro);
		monstros.get(id).posX += pos[1];
		monstros.get(id).posY += pos[0];
		
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
			if(map[monstros.get(id).posY][monstros.get(id).posX] != null) {
				throw new IllegalMoveException();
			}
			else if((move.equals("baixo") && wallmap[monstros.get(id).posY-1][monstros.get(id).posX].equals("baixo")) || (move.equals("baixo") && wallmap[monstros.get(id).posY-1][monstros.get(id).posX].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			else if((move.equals("direita") && wallmap[monstros.get(id).posY][monstros.get(id).posX-1].equals("lado")) || (move.equals("direita") && wallmap[monstros.get(id).posY][monstros.get(id).posX-1].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			else if((move.equals("cima") && wallmap[monstros.get(id).posY][monstros.get(id).posX].equals("baixo")) || (move.equals("cima") && wallmap[monstros.get(id).posY][monstros.get(id).posX].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			else if((move.equals("esquerda") && wallmap[monstros.get(id).posY][monstros.get(id).posX].equals("lado")) || (move.equals("esquerda") && wallmap[monstros.get(id).posY][monstros.get(id).posX].equals("baixolado"))) {
				throw new IllegalMoveException();
			}
			map[monstros.get(id).posY][monstros.get(id).posX] = monstro;
		}
		catch(Exception e) {
			monstros.get(id).posX -= pos[1];
			monstros.get(id).posY -= pos[0];
			map[monstros.get(id).posY][monstros.get(id).posX] = monstro;			
			return 1;
 		}	
		return 0;
	}
	
	public void removeMonstro(Monstro monstro) {
		int id = monstros.indexOf(monstro);
		map[monstros.get(id).posY][monstros.get(id).posX] = null;
	}
	
	public void movimentaMonstros() {
		for(int i=0; i<monstros.size(); i++) {
			if(monstros.get(i).estavivo()) {
				monstros.get(i).movimenta(this);
			}
			else {
				removeMonstro(monstros.get(i));
			}
		}
		refreshHeroMap();
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
	
	public boolean haswon() {
		boolean win = false;
		int vivos = MonstrosRestantes();
		System.out.println("Restam "+vivos+" monstros vivos");
		if(vivos == 0) {
			win = true;
			return true;
		}
		return false;
	}
	
	public boolean haslost(Heroi heroi) {
		boolean loss = false;
		boolean heroivivo = heroi.estavivo();
		if(!heroivivo) {
			loss = true;
			return true;
		}
		
		return false;
	}
	
	public int MonstrosRestantes() {
		int vivos=monstros.size();
		for (int i=0; i<monstros.size(); i++) {
			if(!monstros.get(i).estavivo()) {
				vivos -= 1;
			}
		}
		
		return vivos;
	}
	
	//pega o entorno do jogador
	public Prop getSurroudings(int y, int x) {
		try {
			return this.map[heroiY + y][heroiX + x];
		}
		catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Prop getSurroudings(int y, int x, Monstro monstro) {
		try {
			return this.map[monstro.getPosY() + y][monstro.getPosX() + x];
		}
		catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void revelaArmadilha(int y, int x) {
		try {
			Armadilha armadilha = (Armadilha)this.map[heroiY + y][heroiX + x];
			armadilha.revelado();
		}
		catch(Exception e) {
			System.out.println("Voce não achou nada");
		}
	}
	
	public void revelaBau(int y, int x) {
		try {
			Baus bau = (Baus)this.map[heroiY + y][heroiX + x];
			bau.revelado();
		}
		catch(Exception e) {
			System.out.println("Voce não achou nada");
		}
	}
	
	//deleta props do mapa
	public void delete(Prop item) {
		try {
			map[item.getPosY()][item.getPosX()] = null;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	private void checaArmadilha(Heroi heroi) {
		try {
			Prop armadilha = map[heroiY][heroiX];
			Armadilha aux = (Armadilha)armadilha;
			aux.ativar(heroi);
			System.out.println("Você encontrou uma armadilha");
		}
		catch(Exception e) {
			return;
		}
	}

	private void checaBau(Heroi heroi) {
		try {
			Prop bau = map[heroiY][heroiX];
			Baus aux = (Baus)bau;
			aux.ativar(heroi);
			System.out.println("Você encontrou um bau");
		}
		catch(Exception e) {
			return;
		}
	}
	
	public int getHeroiX() {
		return heroiX;
	}
	
	public int getHeroiY() {
		return heroiY;
	}
}


