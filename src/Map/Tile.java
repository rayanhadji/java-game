package Map;

public class Tile {
    private final char type;
    private final boolean accessible;
    private final int bonus;

    public Tile(char type , boolean accessible, int bonus) {
        this.type = type;
        this.accessible = accessible;
        this.bonus = bonus;
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

}
