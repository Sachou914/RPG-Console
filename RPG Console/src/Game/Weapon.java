package Game;

// Classe abstraite représentant une arme
public abstract class Weapon {

    // Attributs de l'arme
    private String name;    // Nom de l'arme
    private int price;      // Prix de l'arme
    private int damage;     // Dégâts de l'arme

    // Constructeur pour initialiser les attributs de l'arme
    public Weapon(String varName, int varPrice, int varDamage) {
        this.setName(varName);
        this.setPrice(varPrice);
        this.setDamage(varDamage);
    }

    // Méthodes : Getters
    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    // Méthodes : Setters
    public void setPrice(int price) {
        this.price = price;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Méthode abstraite pour obtenir une représentation ASCII de l'arme
    public abstract String ascii_art();
}
