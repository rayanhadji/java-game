package Enemies;

import java.util.HashMap;
import java.util.Map;

public class Troll extends Enemy {
    public Troll() {
        super("Troll", 100, 15, 10, 3, createReward());
    }

    private static Map<String, Integer> createReward() {
        Map<String, Integer> reward = new HashMap<>();
        reward.put("Gold", 20);
        reward.put("Wood", 5);
        return reward;
    }
}