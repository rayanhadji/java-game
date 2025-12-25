package Units;

import java.util.HashMap;
import java.util.Map;

public class Archer extends Unit {
    public Archer() {
        super("Archer", 25, 15, 5, 5, 2, createCost());
    }

    private static Map<String, Integer> createCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Food", 15);
        cost.put("Gold", 10);
        return cost;
    }
}
