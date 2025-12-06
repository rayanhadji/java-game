package Enemies;

import Units.Unit;

public abstract class Enemy {
    protected int health;
    protected int defense;
    protected int speed;

    public Enemy(int health, int defense, int speed) {
        this.health = health;
        this.defense = defense;
        this.speed = speed;
    }

    public abstract void move(int newRow, int newCol);
    public abstract void attack(Unit target);

    public boolean isAlive() {
        return health > 0;
    }

    public int takeDamage(int damage) {
        int effectiveDamage = damage - defense;
        if (effectiveDamage <= 0) {
            effectiveDamage = 1;
        }
        health -= effectiveDamage;
        if (health < 0) health = 0;
        return effectiveDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
}
