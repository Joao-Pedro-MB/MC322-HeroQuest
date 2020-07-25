package bauEarmadilha;
import mapa.*;
import heroi.Heroi;

public class Armadilha extends Prop{
	
	Armadilha(int x, int y){
		super("XX", y, x);
	}
	
	void ativar(Heroi heroi) {
		heroi.recebeDano(3);
	}
}
