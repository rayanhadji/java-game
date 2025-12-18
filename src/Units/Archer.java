package Units;

import Enemies.Enemy;

public class Archer extends Unit {
    public Archer() {
        super(80, 25, 5, 3, 60);
    }

    @Override
    public void attack(Enemy target) {
        int dealt = target.takeDamage(this.attack);
        System.out.println("Archer attacked for " + dealt + " damage.");
    }


}
