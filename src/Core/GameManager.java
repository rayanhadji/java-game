package Core;

import Buildings.*;
import Enemies.*;
import Units.*;
import java.util.*;

public class GameManager {
    private ResourceManager resourceManager;
    private Player player;
    private Base base;
    private WaveManager waveManager;
    private List<Building> buildings;

    public GameManager() {
        resourceManager = new ResourceManager();
        player = new Player();
        base = new Base(200);
        waveManager = new WaveManager();
        buildings = new ArrayList<>();

        // Example starting buildings
        Map<String, Integer> farmCost = new HashMap<>();
        farmCost.put("Wood", 10);
        buildings.add(new Farm(farmCost, 1, 5));

        Map<String, Integer> mineCost = new HashMap<>();
        mineCost.put("Wood", 15);
        buildings.add(new Mine(mineCost, 1, 3));

        Map<String, Integer> sawmillCost = new HashMap<>();
        sawmillCost.put("Stone", 10);
        buildings.add(new Sawmill(sawmillCost, 1, 4));

        Map<String, Integer> campCost = new HashMap<>();
        campCost.put("Wood", 20);
        campCost.put("Stone", 10);
        buildings.add(new TrainingCamp(campCost, 2));
    }

    public void playTurn() {
        System.out.println("\n--- New Turn ---");

        // Buildings act
        for (Building b : buildings) {
            if (b instanceof TrainingCamp) {
                ((TrainingCamp) b).function(resourceManager, player); // pass player too
            } else {
                b.function(resourceManager);
            }
        }

        // Show resources
        resourceManager.showResources();

        // Show army
        player.showArmy();

        // Spawn enemies
        List<Enemy> enemies = waveManager.spawnWave();

        // Simple combat simulation
        if (!player.getArmy().isEmpty()) {
            for (Unit unit : player.getArmy()) {
                for (Enemy enemy : enemies) {
                    if (enemy.isAlive()) {
                        enemy.takeDamage(unit.getAttack());
                        System.out.println(unit.getName() + " attacked " + enemy.getName() + "!");
                        if (!enemy.isAlive()) {
                            System.out.println(enemy.getName() + " defeated!");
                            // Reward resources
                            for (Map.Entry<String, Integer> reward : enemy.getReward().entrySet()) {
                                resourceManager.addResource(reward.getKey(), reward.getValue());
                            }
                        }
                        break; // one attack per unit
                    }
                }
            }
        } else {
            // If no army, enemies damage base
            for (Enemy enemy : enemies) {
                base.takeDamage(enemy.getAttack());
                System.out.println(enemy.getName() + " attacked the base!");
            }
        }

        System.out.println("Base health: " + base.getHealth());
    }

    public boolean isGameOver() {
        return !base.isAlive();
    }
}