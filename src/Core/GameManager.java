package Core;

import Buildings.*;
import Enemies.*;
import MapSystem.GameMap;
import MapSystem.MovementController;
import MapSystem.Tile;
import Units.*;
import java.util.*;

public class GameManager {
    private ResourceManager resourceManager;
    private Player player;
    private Base base;
    private WaveManager waveManager;
    private List<Building> buildings;

    private GameMap gameMap;
    private MovementController movement;

    private List<Enemy> activeEnemies;

    public GameManager() {
        resourceManager = new ResourceManager();
        player = new Player();
        base = new Base(200);
        waveManager = new WaveManager();
        buildings = new ArrayList<>();
        activeEnemies = new ArrayList<>();

        gameMap = new GameMap(13, 20);
        movement = new MovementController(gameMap);

        Map<String, Integer> farmCost = new HashMap<>();
        farmCost.put("Wood", 10);
        buildings.add(new Farm(farmCost, 1, 5));

        Map<String, Integer> mineCost = new HashMap<>();
        mineCost.put("Wood", 15);
        buildings.add(new Mine(mineCost, 1, 3));

        Map<String, Integer> sawmillCost = new HashMap<>();
        sawmillCost.put("Stone", 10);
        buildings.add(new Sawmill(sawmillCost, 1, 4));

        Map<String, Integer> campCost = new HashMap<>();
        campCost.put("Wood", 20);
        campCost.put("Stone", 10);
        buildings.add(new TrainingCamp(campCost, 2));
    }

    // -------------------------
    // Full wave resolution in one turn
    // -------------------------
    public void playTurn() {
        System.out.println("\n--- New Turn ---");

        // 1) Production
        for (Building b : buildings) {
            if (!(b instanceof TrainingCamp)) b.function(resourceManager);
        }
        resourceManager.showResources();
        player.showArmy();

        // 2) Spawn new wave
        List<Enemy> newWave = waveManager.spawnWave();
        activeEnemies.addAll(newWave);
        int laneRow = gameMap.getPlayerSpawn()[0];
        int leftCol = gameMap.getPlayerSpawn()[1];
        int rightCol = gameMap.getEnemySpawn()[1];

        for (Enemy e : newWave) {
            int spawnCol = findNearestEmptyCol(laneRow, rightCol, false);
            Tile spawnTile = gameMap.getTile(laneRow, spawnCol);
            if (spawnTile.isEmpty() && spawnTile.getType() == 'G') {
                spawnTile.placeEnemy(e);
                System.out.println("Enemy " + e.getName() + " spawned at column " + spawnCol);
            } else {
                System.out.println("No space to spawn " + e.getName());
            }
        }

        // 3) Continuous resolution
        boolean baseDestroyed = false;

        while (true) {
            activeEnemies.removeIf(e -> !e.isAlive());
            if (!base.isAlive()) { baseDestroyed = true; break; }
            if (activeEnemies.isEmpty()) break;

            boolean anyUnitAlive = player.getArmy().stream().anyMatch(Unit::isAlive);

            if (anyUnitAlive) {
                for (Unit u : player.getArmy()) {
                    if (!u.isAlive()) continue;

                    Tile ut = findUnitTile(u);
                    if (ut == null) continue; // unit not on the map yet (recruit places)
                    int uCol = getTileCol(ut);

                    // Look ahead one step; if empty grass, move; if enemy ahead, fight
                    int nextCol = uCol + 1;
                    if (nextCol < gameMap.getWidth()) {
                        Tile nextTile = gameMap.getTile(laneRow, nextCol);

                        if (nextTile.isEmpty() && nextTile.getType() == 'G') {
                            ut.removeUnit();
                            nextTile.placeUnit(u);
                        } else if (nextTile.getEnemy() != null) {
                            movement.resolveEngagement(u, nextTile.getEnemy(), ut, nextTile, resourceManager);
                            if (u.isAlive() && nextTile.isEmpty() && nextTile.getType() == 'G') {
                                ut.removeUnit();
                                nextTile.placeUnit(u);
                            }
                        }
                        // else blocked by unit or impassable: do nothing
                    }
                }
            } else {
                // No units alive: enemies hit base once then die
                for (Enemy e : new ArrayList<>(activeEnemies)) {
                    if (!e.isAlive()) continue;
                    Tile et = findEnemyTile(e);
                    if (et != null) et.removeEnemy();

                    base.takeDamage(e.getAttack());
                    System.out.println(e.getName() + " attacked the base!");
                    e.takeDamage(99999);
                    activeEnemies.remove(e);

                    if (!base.isAlive()) { baseDestroyed = true; break; }
                }
            }
        }

        gameMap.displayMap();
        System.out.println("Base health: " + base.getHealth());

        if (baseDestroyed) {
            System.out.println("The base has been destroyed. Game over.");
        } else {
            System.out.println("Wave cleared.");
        }
    }
        // -------------------------
    // Helpers
    // -------------------------
    private Tile findUnitTile(Unit unit) {
        for (int r = 0; r < gameMap.getHeight(); r++) {
            for (int c = 0; c < gameMap.getWidth(); c++) {
                Tile t = gameMap.getTile(r, c);
                if (t.getUnit() == unit) return t;
            }
        }
        return null;
    }

    private Tile findEnemyTile(Enemy enemy) {
        for (int r = 0; r < gameMap.getHeight(); r++) {
            for (int c = 0; c < gameMap.getWidth(); c++) {
                Tile t = gameMap.getTile(r, c);
                if (t.getEnemy() == enemy) return t;
            }
        }
        return null;
    }

    private int getTileCol(Tile tile) {
        for (int r = 0; r < gameMap.getHeight(); r++) {
            for (int c = 0; c < gameMap.getWidth(); c++) {
                if (gameMap.getTile(r, c) == tile) return c;
            }
        }
        return -1;
    }

    private Enemy findNearestEnemy(int laneRow, int fromCol) {
        for (int c = fromCol + 1; c < gameMap.getWidth(); c++) {
            Tile t = gameMap.getTile(laneRow, c);
            if (t.getEnemy() != null) return t.getEnemy();
        }
        return null;
    }

    // Find nearest empty tile from spawn going inward
    private int findNearestEmptyCol(int row, int startCol, boolean searchRight) {
        if (searchRight) {
            for (int c = startCol; c < gameMap.getWidth() - 1; c++) {
                Tile t = gameMap.getTile(row, c);
                if (t.isEmpty() && t.getType() == 'G') return c;
            }
        } else {
            for (int c = startCol; c > 0; c--) {
                Tile t = gameMap.getTile(row, c);
                if (t.isEmpty() && t.getType() == 'G') return c;
            }
        }
        return startCol; // fallback
    }

    public boolean isGameOver() { return !base.isAlive(); }

    // -------------------------
    // Recruit logic (place once)
    // -------------------------
    public void recruitUnit() {
        for (Building b : buildings) {
            if (b instanceof TrainingCamp camp) {
                Unit newUnit = camp.trainUnit(resourceManager);
                if (newUnit != null) {
                    player.addUnit(newUnit);
                    int laneRow = gameMap.getPlayerSpawn()[0];
                    int leftCol = gameMap.getPlayerSpawn()[1];
                    int spawnCol = findNearestEmptyCol(laneRow, leftCol, true);
                    Tile spawnTile = gameMap.getTile(laneRow, spawnCol);
                    if (spawnTile.isEmpty() && spawnTile.getType() == 'G') {
                        spawnTile.placeUnit(newUnit);
                        System.out.println("Recruited and placed " + newUnit.getName() + " at column " + spawnCol);
                    }
                    return;
                }
            }
        }
        System.out.println("No TrainingCamp available or not enough resources.");
    }

    // -------------------------
    // Upgrade logic
    // -------------------------
    public void upgradeBuilding(java.util.Scanner scanner) {
        System.out.println("Choose building to upgrade:");
        for (int i = 0; i < buildings.size(); i++) {
            Building b = buildings.get(i);
            if (b instanceof TrainingCamp) continue;
            System.out.println(i + ": " + b.getName() +
                " (Level " + b.getLevel() + ", Cost: " + b.getCost() + ")");
        }

        System.out.print("Enter building number: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 0 && choice < buildings.size()) {
                Building selected = buildings.get(choice);
                if (selected instanceof TrainingCamp) {
                    System.out.println("Training Camp cannot be upgraded.");
                    return;
                }
                selected.upgrade(resourceManager);
            } else {
                System.out.println("Invalid building number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // -------------------------
    // Accessors
    // -------------------------
    public ResourceManager getResourceManager() { return resourceManager; }
    public Player getPlayer() { return player; }
    public GameMap getGameMap() { return gameMap; }
}