package heroi;

public class Mago extends Heroi{

	public Mago (String nome) {
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
