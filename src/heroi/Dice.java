package heroi;
import java.util.Random;
public class Dice {
	
	Random dice;
	int result;
	
	public Dice() {
		dice = new Random();
	}
	
	//dado de movimento
	public int rollDice(int numOfDices) {
		result = 0;
		
		for(int i = 0 ; i < numOfDices ; i++) {
			result += dice.nextInt(6) + 1;
		}
		return result;
	}
	
	//dado de ataque com 50% de chance de ataque
	public int rollAttack(int numOfDices) {
		result = 0;
		
		for(int i = 0 ; i < numOfDices ; i++) {
			int roll= dice.nextInt(6) + 1;
			if(roll <= 3) {
				result++;
			}
		}
		return result;
	}
	
	//dado de defesa do heroi com 33% de chance de defesa
	int rollHeroDefense(int numOfDices) {
		result = 0;
		
		for(int i = 0 ; i < numOfDices ; i++) {
			int roll= dice.nextInt(6) + 1;
			if(roll <= 2) {
				result++;
			}
		}
		return result;
	}
	
	//dado de defesa do monstro com 16,7% de chance de defesa
	int rollMonsterDefense(int numOfDices) {
		result = 0;
		
		for(int i = 0 ; i < numOfDices ; i++) {
			int roll= dice.nextInt(6) + 1;
			if(roll == 1) {
				result++;
			}
		}
		return result;
	}
	
	//essas porcentagens são baseadas no dado original
}
