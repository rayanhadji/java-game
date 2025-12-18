package Units;
import Enemies.Enemy;

public abstract class Unit {
    protected int health;
    protected int defense;
    protected int attack;
    protected int range;
    protected int cost;

    public Unit(int health,int attack,int defense, int range, int cost) {
        this.health = health;
        this.defense = defense;
        this.attack = attack;
        this.range = range;
        this.cost = cost;
    }

    public abstract void attack(Enemy target);

    public abstract void move(int newRow, int newCol);

    
    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }
}
