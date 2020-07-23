package itens;
public class Arma extends Itens{
	
	int dadosAtaque;
	boolean consumivel, longoAlcance, mageRestricted, duasMaos;
	String nome;
	
	public Arma(String nome, boolean duasMaos, boolean longoAlcance, int dadosAtaque, boolean consumivel, boolean mageRestricted) {
		this.nome = nome;
		this.duasMaos = duasMaos;
		this.longoAlcance = longoAlcance;
		this.dadosAtaque = dadosAtaque;
		this.consumivel = consumivel;
		this.mageRestricted = mageRestricted;
	}
	
	int attack() {
		return dadosAtaque;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public boolean ehDuasMaos(){
		return this.duasMaos;
	}
	
	//tem que arrumar isso quando criar as classes
	boolean canUse(String heroi) {
		if(heroi != "mago" || !mageRestricted) {
			return true;
		}
		
		return false;
	}
}
