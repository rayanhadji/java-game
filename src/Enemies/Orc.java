package Enemies;

import java.util.HashMap;
import java.util.Map;

public class Orc extends Enemy {
    public Orc() {
        super("Orc", 60, 10, 5, 6, createReward());
    }

    private static Map<String, Integer> createReward() {
        Map<String, Integer> reward = new HashMap<>();
        reward.put("Gold", 10);
        reward.put("Stone", 3);
        return reward;
    }
}