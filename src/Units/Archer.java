package Units;

import Enemies.Enemy;

public class Archer extends Unit {
    public Archer{
    Enemy target;
    public Archer(int health,int attack,int defense, int range, int cost,Enemy target) {
        super(80, 25, 5, 3, 60);
        this.target = target;
    }

    @Override
    public void attack(Enemy target) {
        int dealt = target.takeDamage(5);
        System.out.println("Archer attacked for " + dealt + " damage.");
    }
public int targethp() {
        return target.health;
    }

}
