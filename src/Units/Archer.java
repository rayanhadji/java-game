package Units;

import java.util.HashMap;
import java.util.Map;

public class Archer extends Unit {
    public Archer() {
        super("Archer", 25, 15, 3, 5, 2, createCost());
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
