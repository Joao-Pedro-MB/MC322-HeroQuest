package heroi;
import itens.armas.*;
import mapa.Mapa;

import java.util.Random;


import itens.Arma;

public class Goblin extends Monstro{
	
	public Goblin (int x, int y) {
		super(x,y,"Goblin", "GG", 2, 1, 1, 1, 10);
		Arma punhal = new Punhal();
		this.equipaArma(punhal);
	}
	
	@Override
	public void movimenta(Mapa mapa) {
		Random rand = new Random();
		int moves = rand.nextInt(this.movimento);
		moves += 4;
		System.out.println("Monstro se movendo");
		
		int contmovimentoimpossivel=0;
		for (int i = moves ; i >= 0 ; i--) {
			moves = rand.nextInt(4);
			mapa.removeMonstro(this);
			int[] pos = {0,0};
			
			if(moves == 0  && mapa.getHeroiX()<this.posX) {
				pos[1]= -1;
			}
			else if(moves == 1 && mapa.getHeroiY()<this.posY) {
				pos[0]= -1;
			}
			else if(moves == 2 && mapa.getHeroiY()>this.posY) {
				pos[0]= 1;
			}
			else if(moves == 3&& mapa.getHeroiX()>this.posX) {
				pos[1]= 1;
			}

			int movimentoImpossivel = mapa.addMonstro(pos, this); //Retorna 1 caso não seja possível movimentar
			i += movimentoImpossivel;
			if(movimentoImpossivel == 1) {
				contmovimentoimpossivel++;
			}
			else {
				contmovimentoimpossivel = 0;
			}
			if(contmovimentoimpossivel == 20) {
				i = 0;
			}
		}		
	}
	
	
}