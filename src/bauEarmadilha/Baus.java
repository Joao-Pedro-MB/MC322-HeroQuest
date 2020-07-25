package bauEarmadilha;
import mapa.*;
import heroi.Heroi;
import itens.*;
import itens.armas.*;
import itens.armaduras.*;
import java.util.Random;


public class Baus extends Prop{
	
	Random sorteio = new Random();
	
	Baus(int x, int y){
		super("CC", y, x);
		
	}
	
	void ativar(Heroi heroi) {
		int resultado = sorteio.nextInt(10); 
		
		switch(resultado) {
			case 0:
				heroi.addArma(new Punhal());
				break;
			case 1:
				heroi.addArma(new EspadaCurta());
				break;
			case 2:
				heroi.addArma(new EspadaLonga());
				break;
			case 3:
				heroi.addArmadura(new ArmaduraLeve());
				break;
			case 4:
				heroi.addArmadura(new ArmaduraPesada());
				break;
		}
		
		heroi.addOuro(resultado);
	}
}