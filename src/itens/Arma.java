package itens;

public class Arma implements Itens{
	
	int dadosAtaque;
	boolean consumivel, longoAlcance, feiticeiroRestrito, duasMaos;
	String nome;
	public Arma(String nome, boolean duasMaos, boolean longoAlcance, int dadosAtaque, boolean consumivel, boolean feiticeiroRestrito) {
		this.nome = nome;
		this.duasMaos = duasMaos;
		this.longoAlcance = longoAlcance;
		this.dadosAtaque = dadosAtaque;
		this.consumivel = consumivel;
		this.feiticeiroRestrito = feiticeiroRestrito;
	}
	
	public int getBonus() {
		return dadosAtaque;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public boolean ehDuasMaos(){
		return this.duasMaos;
	}
	
	public boolean ehConsumivel(){
		return this.consumivel;
	}
	
	public boolean ehRestrita() {
		return feiticeiroRestrito;
	}
	
	public boolean ehLongoAlcance() {
		return longoAlcance;
	}
}
