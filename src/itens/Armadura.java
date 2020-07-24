package itens;

public class Armadura implements Itens{
	int dadosDefesa, durabilidade;
	boolean mageRestricted;
	String nome;
	
	public Armadura(String nome, int dadosDefesa, int durabilidade, boolean mageRestricted) {
		this.nome = nome;
		this.dadosDefesa = dadosDefesa;
		this.durabilidade = durabilidade;
		this.mageRestricted = mageRestricted;
	}
	
	int getBonus() {
		return dadosDefesa;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getDurabilidade(){
		return this.nome;
	}
	
	boolean canUse(String heroi) {
		if(mageRestricted && heroi.compareTo("Feiticeiro") == 0) {
			return false;
		}
		
		return true;
	}
}
