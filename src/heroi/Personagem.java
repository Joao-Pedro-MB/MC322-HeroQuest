package heroi;
import mapa.*;



public class Personagem extends Prop {
	String nome, classe, simbolo;
	int dadosAtaque, dadosDefesa, pontosVida, pontosInteligencia;
	
	
	public Personagem(int x, int y, String nome, String simbolo, String classe, int dadosAtaque, int dadosDefesa, int pontosVida, int pontosInteligencia) {
		super(simbolo, x, y);
		this.classe = classe;
		this.nome = nome; 
		this.simbolo = simbolo;
		this.dadosAtaque = dadosAtaque;
		this.dadosDefesa = dadosDefesa;
		this.pontosVida = pontosVida;
		this.pontosInteligencia = pontosInteligencia;
	}
}