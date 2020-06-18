package heroi;



public class Heroi {
	private String nome;
	private int dadosAtaque;
	private int dadosAtaqueArma;
	private int dadosDefesa;
	private int pontosVida;
	private int pontosInteligencia;
	private int moedasOuro;
	// Armas, Armaduras, Poções, outros itens
	// Current Armor
	// Current Arma 1
	// Current Arma 2	
	private String[] Armas;
	private String[] Armaduras;
	private String[] Pocoes;
	private String[] Itens;
	private String[] Magias;
	private String atualArmadura;
	private String atualArma1;
	private String atualArma2;
	
	
	public Heroi(String nome, int dadosAtaque, int dadosDefesa, int pontosVida, int pontosInteligencia) {
		this.nome = nome; 
		this.dadosAtaque = dadosAtaque;
		this.dadosDefesa = dadosDefesa;
		this.pontosVida = pontosVida;
		this.pontosInteligencia = pontosInteligencia;
		
	}
	
	public void adicionaOuro(int quantidade) {
		this.moedasOuro+=quantidade;
	}
	
	public void diminuiOuro(int quantidade) {
		this.moedasOuro-=quantidade;
	}
	
	public void EquipaArma(String Arma) {
		
	}
	
	public void DesequipaArma() {
		
	}
	
	public void EquipaArmadura(String Armadura) {
		
	}
	
	public void DesequipaArmadura() {
		
	}
	
	public void addArma(String Arma) {
		
	}
	
	public void removeArma(int index) {
		
	}
	
	public void addArmadura(String Armadura) {
		
	}
	
	public void removeArmadura(int index) {
		
	}
	
	public void addPocao(String Pocao) {
		
	}
	
	public void removePocao(int index) {
		
	}
	
	public void addItem (String Item) {
		
	}
	
	public void removeItem(int index) {
		
	}
	
	public void addMagia(String Magia) {
		
	}
	
	public void removeMagia(int index) {
		
	}
}
