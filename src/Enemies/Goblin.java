package Enemies;

import java.util.HashMap;
import java.util.Map;

public class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 30, 5, 2, 10, createReward());
    }

    private static Map<String, Integer> createReward() {
        Map<String, Integer> reward = new HashMap<>();
        reward.put("Gold", 5);
        reward.put("Food", 2);
        return reward;
    }
}