
public class Hero extends Character {
	
	Class heroClass;
	String name;
	
	public Hero(Class classe, String name) {
		this.name = name;
		this.heroClass = classe;
		makeClass();
	}
	
	private void makeClass() {
		switch (this.heroClass){
        case MAGE:
            System.out.println("Dez");
            break;
            
        case BARBARIAN:
            System.out.println("Numero muito grande");
            break;
            
        case ELF:
            System.out.println("Numero muito grande");
            break;
            
        case MIDGET:
            System.out.println("Numero muito grande");
            break;
        default:
        	System.out.println("Essa classe não existe, leia o manual");

    }
	}
}
