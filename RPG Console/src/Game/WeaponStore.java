package Game;

import Game.Armes.Axe;
import Game.Armes.Bow;
import Game.Armes.Hammer;
import Game.Armes.Potion;
import Game.Armes.Knive;

import java.util.ArrayList;

// Classe représentant une boutique d'armes
public class WeaponStore {

    // Liste des objets disponibles dans le magasin
    private ArrayList<Weapon> weaponsList;

    // Constructeur pour initialiser le magasin avec des armes prédéfinies
    public WeaponStore() {
        this.weaponsList = new ArrayList<>();
        this.weaponsList.add(new Bow());    // Ajout d'un arc
        this.weaponsList.add(new Hammer()); // Ajout d'un marteau
        this.weaponsList.add(new Axe());    // Ajout d'une hache
        this.weaponsList.add(new Potion()); // Ajout d'une potion
        this.weaponsList.add(new Knive()); // Ajout d'un couteau
    }

    // Getter pour obtenir la liste des objets disponibles
    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    // Affiche la liste des objets disponibles dans le magasin
    public void printWeaponsList() {
        System.out.println("Objets disponibles dans le magasin :");
        for (int i = 0; i < weaponsList.size(); i++) {
            Weapon weapon = weaponsList.get(i);
            System.out.println("[" + (i+1) + "] "
                    + weapon.getName()
                    + " (Dégâts : " + (weapon.getDamage() > 0 ? weapon.getDamage() : "N/A")
                    + ", Prix : " + weapon.getPrice() + " pièces d'or)"
                    + "\n" + weapon.ascii_art());
        }
    }

    // Récupère un objet dans la liste par son index
    public Weapon getWeapon(int index) {
        return this.weaponsList.get(index);
    }
}
