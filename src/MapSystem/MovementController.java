package MapSystem;

import Buildings.ResourceManager;
import Enemies.Enemy;
import Units.Unit;

/**
 * MovementController handles unit movement and combat resolution while
 * respecting single-occupant tiles. Units never get placed onto an occupied
 * tile. Combat is resolved via references, then movement happens only if the
 * destination tile becomes empty grass.
 */
public class MovementController {
    private GameMap gameMap;

    public MovementController(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    // -------------------------
    // Resolve combat without placing unit onto an occupied tile
    // -------------------------
    public void resolveEngagement(Unit unit,
                                  Enemy enemy,
                                  Tile unitTile,
                                  Tile enemyTile,
                                  ResourceManager resourceManager) {
        if (unit == null || enemy == null || unitTile == null || enemyTile == null) return;

        // Simple exchange of damage (no randomness for simplicity)
        enemy.takeDamage(unit.getAttack());
        unit.takeDamage(enemy.getAttack());

        System.out.println(unit.getName() + " fought " + enemy.getName() +
                           " (Unit HP: " + unit.getHealth() +
                           ", Enemy HP: " + enemy.getHealth() + ")");

        // Remove dead and reward resources if enemy dies
        if (!enemy.isAlive()) {
            enemyTile.removeEnemy();
            resourceManager.addResource("Gold", 5);
            System.out.println(enemy.getName() + " has been slain.");
        }
        if (!unit.isAlive()) {
            unitTile.removeUnit();
            System.out.println(unit.getName() + " has died.");
        }
    }
        // -------------------------
    // Move unit forward one tile if possible
    // -------------------------
    public void moveUnitForward(Unit unit, int laneRow, ResourceManager resourceManager) {
        Tile currentTile = findUnitTile(unit);
        if (currentTile == null) return;

        int currentCol = getTileCol(currentTile);
        if (currentCol < 0 || currentCol >= gameMap.getWidth() - 1) return;

        Tile nextTile = gameMap.getTile(laneRow, currentCol + 1);

        // Case 1: empty grass -> move
        if (nextTile.isEmpty() && nextTile.getType() == 'G') {
            currentTile.removeUnit();
            nextTile.placeUnit(unit);
            return;
        }

        // Case 2: enemy present -> resolve engagement first; move only if enemy died
        if (nextTile.getEnemy() != null) {
            resolveEngagement(unit, nextTile.getEnemy(), currentTile, nextTile, resourceManager);
            if (unit.isAlive() && nextTile.isEmpty() && nextTile.getType() == 'G') {
                currentTile.removeUnit();
                nextTile.placeUnit(unit);
            }
        }

        // Case 3: blocked by another unit or impassable -> do nothing
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

    private int getTileCol(Tile tile) {
        for (int r = 0; r < gameMap.getHeight(); r++) {
            for (int c = 0; c < gameMap.getWidth(); c++) {
                if (gameMap.getTile(r, c) == tile) return c;
            }
        }
        return -1;
    }
}