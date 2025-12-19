package Units;

import java.util.HashMap;
import java.util.Map;

public class Soldier extends Unit {
    public Soldier() {
        super("Soldier", 30, 10, 5, 1, 2, createCost());
    }

    private static Map<String, Integer> createCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Food", 10);
        cost.put("Gold", 5);
        return cost;
    }
}
