package heroi;

import itens.Arma;

public class Anao extends Heroi{

	public Anao(String nome) {
		super(nome,"Anão", 2, 2, 7, 3);
		Arma espadaCurta = new Arma("espada curta", false, false, 2, false, false);
		this.EquipaArma(espadaCurta);
	}
}
