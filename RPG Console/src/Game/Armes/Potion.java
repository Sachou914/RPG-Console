package Game.Armes;

import Game.Weapon;

// Classe représentant une potion
public class Potion extends Weapon {

    public Potion() {
        super("Potion de soin", 50, 0); // Nom : Potion, Prix : 50 pièces, Pas de dégâts
    }

    // Représentation ASCII de la potion
    @Override
    public String ascii_art() {
        return "     \\   /\n"+
                "     /   \\ \n" +
                "    /     \\ \n" +
                "   /       \\\n" +
                "  /_________\\\n" +
                "  \\_________/\n";

    }

    // Effet de la potion sur le joueur
    public void use(Game.Joueur player) {
        int healingAmount = 50;
        player.heal(healingAmount);
        System.out.println("Vous avez utilisé une " + getName() + " et récupéré " + healingAmount + " points de vie !");
    }
}
