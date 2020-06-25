package heroi;

public class Feiticeiro extends Heroi{

	public Feiticeiro (String nome) {
		super(nome, 1, 2, 4, 6);
		this.addArma("Punhal");
		this.addArma("Punhal");
		this.addArma("Punhal");
		this.addMagia("Magic Missile");
		this.addMagia("Magic Missile");
		this.addMagia("Magic Missile");
		this.addMagia("Fireball");
		this.addMagia("Teleport");
	}
}
