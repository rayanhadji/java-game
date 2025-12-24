package Core;

import Units.Unit;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Unit> army;

    public Player() {
        army = new ArrayList<>();
    }

    public void addUnit(Unit unit) {
        army.add(unit);
    }

    public List<Unit> getArmy() {
        return army;
    }

    public void showArmy() {
        System.out.println("Player Army:");
        if (army.isEmpty()) {
            System.out.println("No units yet.");
        } else {
            for (Unit unit : army) {
                System.out.println("- " + unit.getName() + " (HP: " + unit.getHealth() + ")");
            }
        }
    }
}