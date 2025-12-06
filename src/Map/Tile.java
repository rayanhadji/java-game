package Map;
import Units.Unit;

public class Tile {
    private final char type;
    private final boolean accessible;
    private final int bonus;
    private Unit unit;

    public Tile(char type , boolean accessible, int bonus) {
        this.type = type;
        this.accessible = accessible;
        this.bonus = bonus;
        this.unit = null;
    }

    public char getType() {
        return type;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public int getBonus() {
        return bonus;
    }

    public Unit getUnit() {
        return unit;
    }

    public void placeUnit(Unit u) {
        if (accessible && this.unit == null) {
            this.unit = u;
        } else {
            System.out.println("Cannot place unit here.");
        }
    }

    public void removeUnit() {
        this.unit = null;
    }

    @Override
    public String toString() {
        if (unit != null) {
            return "U";
        }
        return String.valueOf(type);
    }

}
