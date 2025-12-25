package MapSystem;

import Units.Unit;
import Enemies.Enemy;

/**
 * A Tile represents one cell on the map.
 * Each tile has:
 * - type: a character (G = Grass, W = Water, M = Mountain)
 * - accessible: true if units/enemies can stand on it
 * - bonus: a number (0 = neutral, negative = malus, positive = bonus)
 * - unit: the player unit currently on this tile (optional)
 * - enemy: the enemy currently on this tile (optional)
 *
 * Rules:
 * - Only one occupant per tile (either a Unit or an Enemy).
 * - A tile must be accessible and empty to accept a new occupant.
 */
public class Tile {
    private final char type;
    private final boolean accessible;
    private final int bonus;

    private Unit unit;    // occupant if it's a player unit
    private Enemy enemy;  // occupant if it's an enemy

    public Tile(char type, boolean accessible, int bonus) {
        this.type = type;
        this.accessible = accessible;
        this.bonus = bonus;
        this.unit = null;
        this.enemy = null;
    }

    // --- Properties ---

    public char getType() { return type; }

    // Tile is accessible to place a new occupant only if terrain is accessible and tile is empty.
    public boolean isAccessible() { return accessible && isEmpty(); }

    public int getBonus() { return bonus; }

    public Unit getUnit() { return unit; }
    public Enemy getEnemy() { return enemy; }

    public boolean isEmpty() { return unit == null && enemy == null; }

    // --- Placement ---

    public boolean placeUnit(Unit u) {
        if (u == null) return false;
        if (isAccessible()) {
            this.unit = u;
            return true;
        }
        System.out.println("Cannot place unit on tile (" + type + ").");
        return false;
    }

    public boolean placeEnemy(Enemy e) {
        if (e == null) return false;
        if (isAccessible()) {
            this.enemy = e;
            return true;
        }
        System.out.println("Cannot place enemy on tile (" + type + ").");
        return false;
    }

    // --- Removal ---

    public void removeUnit() { this.unit = null; }
    public void removeEnemy() { this.enemy = null; }

    @Override
    public String toString() {
        // For console visualization: U = unit, E = enemy, else tile type letter
        if (unit != null) return "U";
        if (enemy != null) return "E";
        return String.valueOf(type);
    }
}