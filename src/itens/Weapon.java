package itens;
public class Weapon extends Itens{
	
	int range, attackBonus, hands;
	boolean breakable, mageRestricted;
	String name;
	
	Weapon(int hands, int range, int attackBonus, boolean breakable, boolean mageRestricted) {
		this.hands = hands;
		this.range = range;
		this.attackBonus = attackBonus;
		this.breakable = breakable;
		this.mageRestricted = mageRestricted;
	}
	
	int attack() {
		return attackBonus;
	}
	
	//tem que arrumar isso quando criar as classes
	boolean canUse(String hero) {
		if(hero != "mago" || !mageRestricted) {
			return true;
		}
		
		return false;
	}
	
	boolean broke() {
		return breakable;
	}
}
