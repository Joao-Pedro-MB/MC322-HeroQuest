package bauEarmadilha;
import mapa.*;
import heroi.Heroi;

public class Armadilha extends Prop{
	
	public Armadilha(int x, int y){
		super("░░", y, x);
	}
	
	public void ativar(Heroi heroi) {
		heroi.recebeDano(3);
	}
	
	public void revelado() {
		this.symbol = "XX";
	}
}
