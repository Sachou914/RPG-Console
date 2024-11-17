package Game;

import java.util.*;

public class Map {
    private final int GRID_SIZE_X = 10;
    private final int GRID_SIZE_Y = 10;
    private int[][] grid;
    private Joueur player;
    private List<Monster> monsters;
    private List<Obstacle> obstacles;

    // Légende pour les symboles
    private static final String[] LEGEND = {
            "P : Joueur",
            "M : Monstre",
            "O : Obstacle",
            "E : Sortie",
            "■ : Mur",
            ". : Case vide"
    };

    public Map(Joueur player) {
        this.player = player;
        this.monsters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        initializeMap();
        placePlayer();
        placeExit();
        placeWall();
        placeMonsters(); // Placement initial des monstres
        placeObstacles(); // Placement initial des obstacles
    }

    // Initialisation de la carte avec des cases vides
    private void initializeMap() {
        grid = new int[GRID_SIZE_X][GRID_SIZE_Y];
        for (int[] row : grid) {
            Arrays.fill(row, 0);
        }
    }

    // Placement de la sortie (en bas à droite)
    private void placeExit() {
        grid[GRID_SIZE_X - 1][GRID_SIZE_Y - 1] = 4;
    }

    // Placement initial du joueur (en haut à gauche)
    private void placePlayer() {
        grid[player.getX()][player.getY()] = 1;
    }

    // Placement des murs à des positions fixes
    private void placeWall() {
        int[][] wallPositions = {
                {0, 2}, {1, 0}, {1, 4}, {1, 5}, {1, 7}, {1, 8}, {1, 9},
                {2, 3}, {2, 4}, {2, 8}, {2, 9}, {3, 1}, {3, 3}, {3, 6},
                {4, 3}, {4, 4}, {4, 5}, {4, 8}, {5, 0}, {5, 1}, {5, 7},
                {5, 8}, {6, 0}, {6, 1}, {6, 3}, {6, 4}, {6, 5}, {6, 7},
                {7, 3}, {7, 7}, {7, 8}, {7, 9}, {8, 1}, {8, 5}, {8, 8},
                {8, 9}, {9, 1}, {9, 2}, {9, 5}, {9, 6}
        };
        for (int[] pos : wallPositions) {
            grid[pos[0]][pos[1]] = 5;
        }
    }

    // Placement aléatoire des monstres
    private void placeMonsters() {
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int x, y;
            do {
                x = random.nextInt(GRID_SIZE_X);
                y = random.nextInt(GRID_SIZE_Y);
            } while (grid[x][y] != 0); // Assurez-vous que la case est vide

            grid[x][y] = 2;
            monsters.add(new Monster("Monster " + (i + 1), 80, x, y));
        }
    }

    // Placement aléatoire des obstacles
    private void placeObstacles() {
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int x, y;
            do {
                x = random.nextInt(GRID_SIZE_X);
                y = random.nextInt(GRID_SIZE_Y);
            } while (grid[x][y] != 0); // Assurez-vous que la case est vide

            grid[x][y] = 3;
            obstacles.add(new Obstacle("Obstacle " + (i + 1), 15, x, y));
        }
    }

    // Affiche la carte actuelle avec la légende à droite et les points de vie du joueur
    public void displayMap() {
        for (int i = 0; i < grid.length; i++) {
            // Affiche chaque ligne de la carte
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(getSymbol(grid[i][j]) + " ");
            }

            // Affiche la ligne correspondante de la légende si elle existe
            if (i < LEGEND.length) {
                System.out.print("\t" + LEGEND[i]);
            }
            System.out.println();
        }

        // Si la légende a plus de lignes que la carte
        if (grid.length < LEGEND.length) {
            for (int i = grid.length; i < LEGEND.length; i++) {
                System.out.println("\t\t" + LEGEND[i]);
            }
        }

        // Affiche les points de vie du joueur en dessous de la légende
        System.out.println("\nPoints de vie du joueur : " + player.getHealthPoints()+"♥");
    }

    // Retourne un symbole pour chaque type d'entité
    private String getSymbol(int value) {
        switch (value) {
            case 0: return "."; // Case vide
            case 1: return "P"; // Joueur
            case 2: return "M"; // Monstre
            case 3: return "O"; // Obstacle
            case 4: return "E"; // Sortie
            case 5: return "■"; // Mur
            default: return "?"; // Inconnu
        }
    }

    // Retourne les directions dans lesquelles le joueur peut se déplacer
    public List<Integer> canMove() {
        List<Integer> possibleDirections = new ArrayList<>();
        int x = player.getX(), y = player.getY();

        if (x > 0 && (grid[x - 1][y] == 0 || grid[x - 1][y] == 4)) possibleDirections.add(Direction.UP);
        if (x < GRID_SIZE_X - 1 && (grid[x + 1][y] == 0 || grid[x + 1][y] == 4)) possibleDirections.add(Direction.DOWN);
        if (y > 0 && (grid[x][y - 1] == 0 || grid[x][y - 1] == 4)) possibleDirections.add(Direction.LEFT);
        if (y < GRID_SIZE_Y - 1 && (grid[x][y + 1] == 0 || grid[x][y + 1] == 4)) possibleDirections.add(Direction.RIGHT);

        return possibleDirections;
    }

    // Retourne les directions dans lesquelles le joueur peut attaquer
    public List<Integer> canAttack() {
        List<Integer> possibleAttacks = new ArrayList<>();
        int x = player.getX(), y = player.getY();

        if (x > 0 && (grid[x - 1][y] == 2 || grid[x - 1][y] == 3)) possibleAttacks.add(Direction.UP);
        if (x < GRID_SIZE_X - 1 && (grid[x + 1][y] == 2 || grid[x + 1][y] == 3)) possibleAttacks.add(Direction.DOWN);
        if (y > 0 && (grid[x][y - 1] == 2 || grid[x][y - 1] == 3)) possibleAttacks.add(Direction.LEFT);
        if (y < GRID_SIZE_Y - 1 && (grid[x][y + 1] == 2 || grid[x][y + 1] == 3)) possibleAttacks.add(Direction.RIGHT);

        return possibleAttacks;
    }

    // Déplace le joueur dans une direction valide
    public void movePlayer(int direction) {
        if (canMove().contains(direction)) {
            grid[player.getX()][player.getY()] = 0; // Efface la position actuelle

            switch (direction) {
                case Direction.UP: player.setX(player.getX() - 1); break;
                case Direction.DOWN: player.setX(player.getX() + 1); break;
                case Direction.LEFT: player.setY(player.getY() - 1); break;
                case Direction.RIGHT: player.setY(player.getY() + 1); break;
            }
            grid[player.getX()][player.getY()] = 1; // Met à jour la nouvelle position
        }
    }

    // Gère l'attaque d'un monstre ou obstacle
    public void attack(int x, int y) {
        if (grid[x][y] == 3) {
            handleObstacleAttack(x, y);
        } else if (grid[x][y] == 2) {
            handleMonsterAttack(x, y);
        }
    }

    private void handleObstacleAttack(int x, int y) {
        Obstacle obstacleToRemove = null;
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == x && obstacle.getY() == y && player.attack(obstacle)) {
                grid[x][y] = 0;
                obstacleToRemove = obstacle;
                break;
            }
        }
        if (obstacleToRemove != null) obstacles.remove(obstacleToRemove);
    }

    private void handleMonsterAttack(int x, int y) {
        Monster monsterToRemove = null;
        for (Monster monster : monsters) {
            if (monster.getX() == x && monster.getY() == y && player.attack(monster)) {
                grid[x][y] = 0;
                monsterToRemove = monster;
                break;
            }
        }
        if (monsterToRemove != null) monsters.remove(monsterToRemove);
    }

    // Vérifie si le joueur est à la sortie
    public boolean isExitReached() {
        return player.getX() == GRID_SIZE_X - 1 && player.getY() == GRID_SIZE_Y - 1;
    }

    public Joueur getPlayer() {
        return player;
    }
}
