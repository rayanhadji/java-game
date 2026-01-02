package Enemies;

import java.util.Map;

public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int speed;
    protected Map<String, Integer> reward; // resources given when killed

    public Enemy(String name, int health, int attack, int defense, int speed, Map<String, Integer> reward) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.reward = reward;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public Map<String, Integer> getReward() { return reward; }

    public void takeDamage(int damage) {
        damage -= defense;
        if (damage < 1) damage = 1;
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }
}