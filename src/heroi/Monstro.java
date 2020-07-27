package heroi;
import java.util.ArrayList;
import java.util.Random;
import itens.Arma;
import itens.Magia;
import mapa.Mapa;


public class Monstro extends Personagem {

	int movimento;
	private Arma arma;
	private boolean vivo;
	private ArrayList<Magia> Magias = new ArrayList<Magia>();
	
	public Monstro(int x, int y, String nome, String simbolo, int ataque, int defesa, int pontosVida, int inteligencia, int movimento) {
		super(x, y, nome, simbolo, nome, ataque, defesa, pontosVida, inteligencia);
		this.movimento = movimento;
		this.vivo = true;
	}
	
	protected void ataca(Mapa mapa) {
		if (this.nome.compareTo("esqueletoMago") == 0) {
		}
		
		int ataque = dadosAtaque;
		Dice dado = new Dice();

		for (int i = -5 ; i < 6 ; i++) {
			if (mapa.getSurroudings(0, i, this) != null) {
			
			//testa ataque a longa distancia para a horizontal
				if (atacaLonge()) {
					try {
						Heroi alvo = (Heroi)mapa.getSurroudings(0, i, this);
						System.out.println("Achei o Heroi");
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
				
			//testa ataque a longa distancia para a horizontal
				else if (i <= 1 && i >= -1) {
					try {
						Heroi alvo = (Heroi)mapa.getSurroudings(0, i, this);
						System.out.println("Achei o Heroi");
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
			}
			else if (mapa.getSurroudings(i, 0, this) != null) {
			
		//testa ataque a longa distancia para a vertical
				if (atacaLonge()) {
					try {
						Heroi alvo = (Heroi)mapa.getSurroudings(i, 0, this);
						System.out.println("Achei o Heroi");
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
		
		//testa ataque a curta distancia para a vertical
				else if (i <= 1 && i >= -1) {
					try {
						Heroi alvo = (Heroi)mapa.getSurroudings(i, 0, this);
						System.out.println("Achei o Heroi");
						alvo.sofreDano(dado.rollAttack(ataque));
					}
					catch(Exception e) {
					}
				}
			}
			
		//checa se o item é consumível
			if(ataque != 0) {
				checaConsumivel();
			}
		}
	}
		
	public void movimenta(Mapa mapa) {
		ataca(mapa);
		Random rand = new Random();
		int moves = rand.nextInt(this.movimento);
		
		int contmovimentoimpossivel=0;
		for (int i = moves ; i >= 0 ; i--) {
			moves = rand.nextInt(4);
			mapa.removeMonstro(this);
			int[] pos = {0,0};
			
			if(moves == 0) {
				pos[1]= -1;
			}
			else if(moves == 1) {
				pos[0]= -1;
			}
			else if(moves == 2) {
				pos[0]= 1;
			}
			else if(moves == 3) {
				pos[1]= 1;
			}
			else {
				pos[0]= 1;
			}
			int movimentoImpossivel = mapa.addMonstro(pos, this); //Retorna 1 caso não seja possível movimentar
			i += movimentoImpossivel;
			if(movimentoImpossivel == 1) {
				contmovimentoimpossivel++;
			}
			else {
				contmovimentoimpossivel = 0;
			}
			if(contmovimentoimpossivel == 20) {
				i = 0;
			}
		}
	}
	
	public void sofreDano(int dano) {
		int defesa = defende();
		System.out.println("Vida Antes " + pontosVida);
		if(dano > defesa) {
			this.pontosVida-=(dano-defesa);
		}
		System.out.println("Alvo recebeu " + dano + " de dano e defendeu " + defesa +" pontos");
		System.out.println("Vida Depois " + pontosVida);
		if(this.pontosVida <= 0) {
			this.vivo = false;
		}
	}
	
	public boolean estavivo() {
		return this.vivo;
	}
	
	private int defende() {
		Dice defende = new Dice();
		return defende.rollMonsterDefense(dadosDefesa);
	}
	
	public void equipaArma(Arma arma) {
		if(this.arma == null) {
			this.arma = arma;
		}
	}
	
	private void checaConsumivel() {
		if (arma!= null && arma.ehConsumivel()) {
			arma = null;
		}
	}
	
	private boolean atacaLonge() {
		if(arma != null && arma.ehLongoAlcance()) {
			return true;
		}
		return false;
	}
	
	private void usaMagia(Mapa mapa, Monstro monstro) {

		if(Magias.size() > 0) {
			Magias.get(0).usaMagia(this, mapa);
			Magias.remove(0);
		}
	}
	
	public void addMagia(Magia magia) {
		Magias.add(magia);
	}
	
	public void addVida(int pontos) {
		this.pontosVida += pontos;
	}
	
}