package heroi;
import itens.armas.*;
import itens.Arma;

public class Anao extends Heroi{

	public Anao(String nome) {
		super(nome,"Anão", 2, 2, 7, 3);
		Arma espadaCurta = new EspadaCurta();
		this.EquipaArma(espadaCurta);
	}
}
