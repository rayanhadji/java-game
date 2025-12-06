package Units;

import Enemies.Enemy;

public class Soldier extends Unit {
    public Soldier() {
        super(100, 1, 2, 50);
    }

    @Override
    public void attack(Enemy target) {
        int dealt = target.takeDamage(this.attack);
        System.out.println("Archer attacked for " + dealt + " damage.");
    }


}
