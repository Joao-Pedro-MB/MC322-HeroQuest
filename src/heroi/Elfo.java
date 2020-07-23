package heroi;

import itens.Arma;

public class Elfo extends Heroi{
	public Elfo(String nome) {
		super(nome,"Elfo", 2, 2, 6, 4);
		Arma espadaCurta = new Arma("espada curta", false, false, 2, false, false);
		this.EquipaArma(espadaCurta);
		//this.addMagia("Simple Heal");
	}
}
