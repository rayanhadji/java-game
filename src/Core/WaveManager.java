package Core;

import Enemies.*;
import java.util.ArrayList;
import java.util.List;

public class WaveManager {
    private int currentWave;

    public WaveManager() {
        currentWave = 0;
    }

    public List<Enemy> spawnWave() {
        currentWave++;
        List<Enemy> enemies = new ArrayList<>();

        if (currentWave == 1) {
            enemies.add(new Goblin());
            enemies.add(new Goblin());
        } else if (currentWave == 2) {
            enemies.add(new Orc());
            enemies.add(new Goblin());
        } else if (currentWave == 3) {
            enemies.add(new Troll());
        } else {
            enemies.add(new Troll());
            enemies.add(new Orc());
            enemies.add(new Goblin());
        }

        System.out.println("Wave " + currentWave + " spawned!");
        return enemies;
    }
}