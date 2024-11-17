package Game;

// Classe représentant un obstacle, hérite de Destructible
public class Obstacle extends Destructible {

    // Constructeur pour initialiser un obstacle
    public Obstacle(String name, int healthPoints, int x, int y) {
        super(name, healthPoints, x, y);
    }

    // Méthode pour infliger des dégâts à l'obstacle
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage); // Appel de la méthode takeDamage de la classe parente

        if (getHealthPoints() <= 0) {
            System.out.println(getName() + " a été détruit !");
        } else {
            System.out.println(getName() + " a subi " + damage + " points de dégâts. Points de vie restants : " + getHealthPoints());
        }
    }
}
