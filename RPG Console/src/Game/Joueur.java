package Game;

import Game.Armes.Bow;
import Game.Armes.Fist;

import java.util.ArrayList;
import java.util.Scanner;

// Classe représentant le joueur dans le jeu
public class Joueur implements ActionsPlayer {

    // Attributs du joueur
    private String name;
    private Clan faction; // Faction ou clan auquel appartient le joueur
    private ArrayList<Weapon> weapons; // Liste des armes du joueur
    private int healthPoints; // Points de vie du joueur
    private int money; // Argent du joueur
    private int experiencePoints; // Points d'expérience du joueur
    private int x; // Position X sur la carte
    private int y; // Position Y sur la carte
    private int enemiesKilled; // Nombre d'ennemis tués
    private int obstaclesDestroyed; // Nombre d'obstacles détruits
    Scanner scanner = new Scanner(System.in);

    // Constructeur
    public Joueur(String name, Clan faction) {
        this.name = name;
        this.faction = faction;
        this.money = 50; // Argent initial
        this.weapons = new ArrayList<>();
        this.weapons.add(new Fist()); // Le joueur commence avec un arc
        this.experiencePoints = 0;
        this.healthPoints = 100; // Points de vie initialement fixés à 100
        this.x = 0; // Position initiale sur la carte
        this.y = 0;
        this.enemiesKilled = 0;
        this.obstaclesDestroyed = 0;
    }

    // Getters
    public Clan getFaction() {
        return faction;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMoney() {
        return money;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public int getObstaclesDestroyed() {
        return obstaclesDestroyed;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Méthode pour gagner de l'expérience
    public void gainExperience(int amount) {
        experiencePoints += amount;
        System.out.println(name + " gagne " + amount + " points d'expérience.");
    }

    // Méthode pour gagner de l'argent
    public void gainMoney(int amount) {
        money += amount;
        System.out.println(name + " gagne " + amount + " pièces d'or.");
    }

    // Méthode pour incrémenter les statistiques
    public void incrementEnemiesKilled() {
        enemiesKilled++;
    }

    public void incrementObstaclesDestroyed() {
        obstaclesDestroyed++;
    }

    // Méthode pour acheter une arme ou utiliser une potion
    @Override
    public void buyWeapon(Weapon weapon) {
        if (weapon.getPrice() <= this.money) {
            if (weapon instanceof Game.Armes.Potion) {
                // Utiliser la potion immédiatement
                ((Game.Armes.Potion) weapon).use(this);
            } else {
                this.weapons.add(weapon);
                System.out.println("L'arme " + weapon.getName() + " a été ajoutée à votre inventaire.");
            }
            this.money -= weapon.getPrice();
            System.out.println("Il vous reste " + this.money + " pièces d'or.");
        } else {
            System.out.println("Vous n'avez pas assez de pièces d'or pour acheter cet objet !");
        }
    }

    // Méthode pour infliger des dégâts au joueur
    public void takeDamage(int damage) {
        if (damage > 0) {
            this.healthPoints -= damage;
            System.out.println(name + " a subi " + damage + " points de dégâts. Points de vie restants : " + this.healthPoints+" ♥");
            if (this.healthPoints <= 0) {
                System.out.println("Vous êtes mort. Fin de la partie.");
                System.exit(0); // Terminer le jeu si le joueur meurt
            }
        }
    }

    // Méthode pour soigner le joueur
    public void heal(int amount) {
        if (amount > 0) {
            this.healthPoints += amount;
            System.out.println(name + " récupère " + amount + " points de vie. Points de vie actuels : " + this.healthPoints);
        }
    }

    // Méthode pour attaquer un obstacle
    public boolean attack(Game.Obstacle obstacle) {
        Weapon selectedWeapon = selectWeaponForAttack();
        if (selectedWeapon != null) {
            int damage = selectedWeapon.getDamage();
            obstacle.takeDamage(damage);

            if (obstacle.getHealthPoints() <= 0) {
                gainExperience(5); // Gagner de l'expérience lorsque l'obstacle est détruit
                incrementObstaclesDestroyed(); // Incrémenter le compteur d'obstacles détruits
                System.out.println("Obstacle détruit !");
            }
        } else {
            System.out.println("Vous n'avez aucune arme pour attaquer.");
        }
        return obstacle.getHealthPoints() <= 0;
    }

    // Méthode pour attaquer un monstre
    public boolean attack(Game.Monster monster) {
        Weapon selectedWeapon = selectWeaponForAttack();
        if (selectedWeapon != null) {
            int damage = selectedWeapon.getDamage() + faction.getStrength();
            monster.takeDamage(damage);

            if (monster.getHealthPoints() <= 0) {
                gainExperience(10); // Gagner de l'expérience lorsque le monstre est vaincu
                gainMoney(25); // Gagner de l'argent lorsque le monstre est vaincu
                incrementEnemiesKilled(); // Incrémenter le compteur d'ennemis tués
                System.out.println("Monstre vaincu !");
            } else {
                // Le monstre contre-attaque
                int monsterDamage = 10 + (int) (Math.random() * 11); // Dégâts aléatoires entre 10 et 20
                takeDamage(monsterDamage);
            }
        } else {
            System.out.println("Vous n'avez aucune arme pour attaquer.");
        }
        return monster.getHealthPoints() <= 0;
    }

    // Méthode pour sélectionner une arme pour l'attaque
    private Weapon selectWeaponForAttack() {
        System.out.println("Choisissez une arme pour attaquer :");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i).getName());
        }

        int weaponIndex = scanner.nextInt() - 1;
        if (weaponIndex >= 0 && weaponIndex < weapons.size()) {
            return weapons.get(weaponIndex);
        } else {
            System.out.println("Choix d'arme invalide. Utilisation de l'arme par défaut.");
            return weapons.get(0);
        }
    }
}
