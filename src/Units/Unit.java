package Units;

public abstract class Unit {
    protected int health;
    protected int attack;
    protected int defense;
    protected int Range;
    protected int cost;

    public Unit(int health, int attack, int defense, int Range, int cost) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.Range = Range;
        this.cost = cost;
    }

    public abstract void move(int x, int y);
    public abstract void attack(Unit target);

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public int getHealth() {
        return health;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getRange() {
        return Range;
    }
    public int getCost() {
        return cost;
    }
}

