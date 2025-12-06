package Units;

import Enemies.Enemy;

public abstract class Unit {
    protected int attack;
    protected int range;
    protected int cooldown;
    protected int cost;

    public Unit(int attack, int range, int cooldown, int cost) {
        this.attack = attack;
        this.range = range;
        this.cooldown = cooldown;
        this.cost = cost;
    }

    public abstract void attack(Enemy target);

    public int getAttack() {
        return attack;
    }
    public int getCooldown() {
        return cooldown;
    }
    public int getRange() {
        return range;
    }
    public int getCost() {
        return cost;
    }
}
