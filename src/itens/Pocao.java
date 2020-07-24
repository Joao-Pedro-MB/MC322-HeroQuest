package itens;

public class Pocao implements Itens {
	
	int pontos;
	String nome, bonus;
	
	public Pocao(String nome, String bonus, int pontos) {
		this.nome = nome;
		this.bonus = bonus;
		this.pontos = pontos;

	}
	
	public String getBonus() {
		return bonus;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPontos() {
		return pontos;
	}

}
