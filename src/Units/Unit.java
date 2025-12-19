package Units;

import java.util.Map;

public abstract class Unit {
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int range;
    protected int speed;
    protected Map<String, Integer> cost;

    public Unit(String name, int health, int attack, int defense, int range, int speed, Map<String, Integer> cost) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.range = range;
        this.speed = speed;
        this.cost = cost;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getRange() { return range; }
    public int getSpeed() { return speed; }
    public Map<String, Integer> getCost() { return cost; }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }
}