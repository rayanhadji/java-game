package Units;

import java.util.HashMap;
import java.util.Map;

public class Cavalry extends Unit {
    public Cavalry() {
        super("Cavalry", 50, 15, 7, 3, 5, createCost());
    }

    private static Map<String, Integer> createCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Food", 25);
        cost.put("Gold", 20);
        return cost;
    }
}
