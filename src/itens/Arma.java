package itens;

public class Arma implements Itens{
	
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
	
	int getBonus() {
		return dadosAtaque;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public boolean ehDuasMaos(){
		return this.duasMaos;
	}
	
	boolean canUse(String heroi) {
		if(mageRestricted && heroi.compareTo("Feiticeiro") == 0) {
			return false;
		}
		
		return true;
	}
}
