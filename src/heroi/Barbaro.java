package heroi;

import itens.Arma;

public class Barbaro extends Heroi{

	public Barbaro (String nome) {
		super(nome,"Bárbaro", 3, 2, 8, 2);
		Arma espadaLonga = new Arma("espada longa", true, false, 2, false, true);
		this.EquipaArma(espadaLonga);
	}
}
