package Game;

// Classe représentant un monstre, hérite de Destructible
public class Monster extends Destructible {

    // Constructeur pour initialiser le monstre
    public Monster(String name, int healthPoints, int x, int y) {
        super(name, healthPoints, x, y);
    }

    // Méthode pour infliger des dégâts au monstre
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage); // Appel de la méthode de la classe parente

        if (getHealthPoints() <= 0) {
            System.out.println(getName() + " a été vaincu !");
        } else {
            System.out.println(getName() + " a subi " + damage + " points de dégâts. Points de vie restants : " + getHealthPoints()+"♥");
        }
    }
}
