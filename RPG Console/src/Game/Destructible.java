package Game;

// Classe de base pour les objets destructibles dans le jeu
public class Destructible {

    // Attributs de base d'un objet destructible
    private String name;         // Nom de l'objet
    private int healthPoints;    // Points de vie de l'objet
    private int x;               // Position X sur la carte
    private int y;               // Position Y sur la carte

    // Constructeur pour initialiser un objet destructible
    public Destructible(String name, int healthPoints, int x, int y) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.x = x;
        this.y = y;
    }

    // Getter pour le nom de l'objet
    public String getName() {
        return name;
    }

    // Getter pour les points de vie
    public int getHealthPoints() {
        return healthPoints;
    }

    // Méthode pour infliger des dégâts à l'objet
    public void takeDamage(int damage) {
        if (damage > 0) {
            healthPoints -= damage; // Réduit les points de vie si les dégâts sont positifs
        }
    }

    // Getter pour la coordonnée X
    public int getX() {
        return x;
    }

    // Getter pour la coordonnée Y
    public int getY() {
        return y;
    }
}
