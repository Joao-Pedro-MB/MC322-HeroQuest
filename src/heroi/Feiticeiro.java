package heroi;
import itens.Arma;

public class Feiticeiro extends Heroi{

	public Feiticeiro (String nome) {
		super(nome, "Feiticeiro", 1, 2, 4, 6);
		// dado de ataque cada, consuvivel
		Arma punhal = new Arma("punhal", false, true, 1, true, false);
		this.addArma(punhal);
		this.addArma(punhal);
		this.addArma(punhal);
		this.EquipaArma(punhal);
		this.EquipaArma(punhal);
		//this.addMagia("Magic Missile");
		//this.addMagia("Magic Missile");
		//this.addMagia("Magic Missile");
		//this.addMagia("Fireball");
		//this.addMagia("Teleport");
	}
}
