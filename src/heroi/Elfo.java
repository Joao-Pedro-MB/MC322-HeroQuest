package heroi;
import itens.armas.*;
import itens.magias.*;
import itens.Arma;

public class Elfo extends Heroi{
	public Elfo(String nome) {
		super(nome,"Elfo", 2, 2, 6, 4);
		Arma espadaCurta = new EspadaCurta();
		this.EquipaArma(espadaCurta);
		this.addMagia(new CuraSimples());
	}
}
