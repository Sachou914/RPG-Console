package Game;

import Game.Clans.*;
import java.util.List;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Introduction de l'histoire
        System.out.println("Bienvenue dans le monde de Mythoria !");
        System.out.println("Vous êtes un héros choisi pour défendre le royaume contre les monstres et les obstacles qui menacent la paix.");
        System.out.println("Votre mission : trouver la sortie du labyrinthe, vaincre les ennemis et prouver votre valeur.");
        System.out.println("\nQue la légende de votre bravoure commence !");

        // Initialisation du joueur
        System.out.print("\nEntrez votre pseudo : ");
        String name = scanner.nextLine();

        int casteChoice = -1;
        while (casteChoice < 1 || casteChoice > 6) {
            System.out.println("\nChoisissez votre clan :");
            System.out.println("1. Archer - Agile et précis, ses flèches transpercent les ennemis.");
            System.out.println("2. Guerrier - Fort et résistant, un maître des armes lourdes.");
            System.out.println("3. Ogre - Puissant et destructeur, il écrase tout sur son passage.");
            System.out.println("4. Sorcier - Spécialiste des attaques magiques à distance.");
            System.out.println("5. Healer - Expert des soins, idéal pour les joueurs stratégiques.");
            System.out.println("6. Assassin - Rapide et furtif, infligeant des dégâts puissants.");
            System.out.print("\nEntrez le numéro du clan : ");

            if (scanner.hasNextInt()) {
                casteChoice = scanner.nextInt();
                if (casteChoice < 1 || casteChoice > 6) {
                    System.out.println("Veuillez entrer un numéro entre 1 et 6.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.next(); // Nettoyer l'entrée non valide
            }
        }

        Joueur p = createPlayer(name, casteChoice);

        WeaponStore store = new WeaponStore();
        Map map = new Map(p);

        boolean gameOver = false;

        map.displayMap();

        // Boucle principale du jeu
        while (!gameOver) {
            System.out.println("\nActions disponibles :");
            System.out.println("1. Déplacement");
            System.out.println("2. Attaque");
            System.out.println("3. Magasin");
            System.out.println("4. Inventaire");
            System.out.print("\nEntrez le numéro de l'action à effectuer : ");

            int action = -1;
            while (action < 1 || action > 4) {
                if (scanner.hasNextInt()) {
                    action = scanner.nextInt();
                    if (action < 1 || action > 4) {
                        System.out.println("Action invalide. Veuillez entrer un nombre entre 1 et 4.");
                    }
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                    scanner.next();
                }
            }

            switch (action) {
                case 1 -> handleMovement(scanner, map, p, gameOver);
                case 2 -> handleAttack(scanner, map);
                case 3 -> handleStore(scanner, store, p);
                case 4 -> handleInventory(p);
            }

            // Si le joueur a atteint la sortie
            if (map.isExitReached()) {
                gameOver = true;
            }
        }

        // Fin du jeu et affichage des statistiques
        System.out.println("\n--- Résultats de la partie ---");
        System.out.println("Héros : " + p.getName());
        System.out.println("Clan : " + p.getFaction().getNameF());
        System.out.println("Nombre d'ennemis tués : " + p.getEnemiesKilled());
        System.out.println("Nombre d'obstacles détruits : " + p.getObstaclesDestroyed());
        System.out.println("Or gagné : " + p.getMoney() + " pièces d'or");
        System.out.println("Points de vie restants : " + p.getHealthPoints() + " ♥");

        System.out.println("\nConclusion de l'histoire :");
        System.out.println("Après avoir traversé de nombreux dangers et surmonté des épreuves, " + p.getName() +
                " est enfin sorti du labyrinthe. Sa bravoure est désormais une légende racontée à travers tout Mythoria. Le royaume est en sécurité... pour l'instant.");
        System.out.println("Merci d'avoir joué !");
    }

    private static void handleMovement(Scanner scanner, Map map, Joueur p, boolean gameOver) {
        boolean backToMenu = false;
        while (!backToMenu) {
            List<Integer> possibleDirections = map.canMove();
            if (!possibleDirections.isEmpty()) {
                System.out.println("\nChoisissez une direction (ou appuyez sur 'E' pour revenir au menu) :");
                for (Integer direction : possibleDirections) {
                    if (direction == Direction.UP) System.out.println(Direction.UP + ". " + Direction.UP_DESCRIPTION);
                    if (direction == Direction.DOWN) System.out.println(Direction.DOWN + ". " + Direction.DOWN_DESCRIPTION);
                    if (direction == Direction.LEFT) System.out.println(Direction.LEFT + ". " + Direction.LEFT_DESCRIPTION);
                    if (direction == Direction.RIGHT) System.out.println(Direction.RIGHT + ". " + Direction.RIGHT_DESCRIPTION);
                }

                if (scanner.hasNextInt()) {
                    int chosenDirection = scanner.nextInt();
                    if (possibleDirections.contains(chosenDirection)) {
                        map.movePlayer(chosenDirection);
                        if (map.isExitReached()) {
                            System.out.println("Félicitations, " + p.getFaction().getNameF() + ", vous avez trouvé la sortie et prouvé votre valeur !");
                            gameOver = true;
                        }
                        map.displayMap();
                        break;
                    } else {
                        System.out.println("Direction invalide. Choisissez parmi : " + possibleDirections);
                    }
                } else if (scanner.hasNext() && scanner.next().equalsIgnoreCase("E")) {
                    backToMenu = true;
                } else {
                    System.out.println("Entrée invalide.");
                    scanner.next();
                }
            } else {
                System.out.println("Aucune direction possible !");
                break;
            }
        }
    }

    private static void handleAttack(Scanner scanner, Map map) {
        List<Integer> possibleAttacks = map.canAttack();
        if (!possibleAttacks.isEmpty()) {
            System.out.println("\nChoisissez une case à attaquer :");
            for (Integer attack : possibleAttacks) {
                if (attack == Direction.UP) System.out.println(Direction.UP + ". " + Direction.UP_DESCRIPTION);
                if (attack == Direction.DOWN) System.out.println(Direction.DOWN + ". " + Direction.DOWN_DESCRIPTION);
                if (attack == Direction.LEFT) System.out.println(Direction.LEFT + ". " + Direction.LEFT_DESCRIPTION);
                if (attack == Direction.RIGHT) System.out.println(Direction.RIGHT + ". " + Direction.RIGHT_DESCRIPTION);
            }

            int chosenDirection = -1;
            while (chosenDirection == -1 || !possibleAttacks.contains(chosenDirection)) {
                if (scanner.hasNextInt()) {
                    chosenDirection = scanner.nextInt();
                } else {
                    scanner.next();
                    System.out.println("Veuillez entrer un nombre valide.");
                }
                if (!possibleAttacks.contains(chosenDirection)) {
                    System.out.println("Direction invalide. Choisissez parmi : " + possibleAttacks);
                }
            }

            int x = map.getPlayer().getX();
            int y = map.getPlayer().getY();
            if (chosenDirection == Direction.UP) x--;
            if (chosenDirection == Direction.DOWN) x++;
            if (chosenDirection == Direction.LEFT) y--;
            if (chosenDirection == Direction.RIGHT) y++;
            map.attack(x, y);
        } else {
            System.out.println("Aucune cible à attaquer !");
        }

        map.displayMap();
    }

    private static void handleStore(Scanner scanner, WeaponStore store, Joueur p) {
        boolean backToMenu = false;
        while (!backToMenu) {
            store.printWeaponsList();
            System.out.println("\nVous avez " + p.getMoney() + " pièces d'or.");
            System.out.print("Quelle arme voulez-vous acheter ou 'Q' pour quitter le magasin : ");

            if (scanner.hasNextInt()) {
                int weaponSelected = scanner.nextInt();
                if (weaponSelected > 0 && weaponSelected <= store.getWeaponsList().size()) {
                    Weapon weapon = store.getWeapon(weaponSelected - 1);
                    p.buyWeapon(weapon);
                } else {
                    System.out.println("Numéro d'arme invalide.");
                }
            } else if (scanner.hasNext() && scanner.next().equalsIgnoreCase("Q")) {
                backToMenu = true;
            } else {
                System.out.println("Entrée invalide.");
                scanner.next();
            }
        }
    }

    private static void handleInventory(Joueur p) {
        System.out.println("\nVous avez " + p.getMoney() + " pièces d'or.");
        System.out.println("Votre inventaire :");
        for (Weapon weapon : p.getWeapons()) {
            int totalDamage = weapon.getDamage() + p.getFaction().getStrength();
            System.out.println("- " + weapon.getName() + " (Dégâts : " + totalDamage + ")");
        }
    }

    private static Joueur createPlayer(String playerName, int casteChoice) {
        Clan faction;
        switch (casteChoice) {
            case 1 -> faction = new Archer();
            case 2 -> faction = new Guerrier();
            case 3 -> faction = new Ogre();
            case 4 -> faction = new Sorcier();
            case 5 -> faction = new Healer();
            case 6 -> faction = new Assassin();
            default -> {
                System.out.println("Choix de clan extérieur...");
                faction = new Clan(playerName, 50);
            }
        }
        return new Joueur(playerName, faction);
    }
}
