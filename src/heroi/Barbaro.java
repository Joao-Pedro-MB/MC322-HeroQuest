package heroi;
import itens.armas.*;
import itens.Arma;

public class Barbaro extends Heroi{

	public Barbaro (String nome) {
		super(nome,"Bárbaro", 3, 2, 8, 2);
		Arma espadaLonga = new EspadaLonga();
		this.EquipaArma(espadaLonga);
	}
}
