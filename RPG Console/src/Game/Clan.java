package Game;

// Classe de base pour les clans
public class Clan {

    // Attributs communs
    private String nameF;  // Nom du clan
    private int strength;  // Force du clan

    // Constructeur pour initialiser un clan avec son nom et sa force
    public Clan(String nameF, int strength) {
        this.nameF = nameF;
        this.strength = strength;
    }

    // Getter pour la force du clan
    public int getStrength() {
        return strength;
    }

    // Getter pour le nom du clan
    public String getNameF() {
        return nameF;
    }
}
