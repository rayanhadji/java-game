package Buildings;

import java.util.Map;
import Units.Soldier;

public class TrainingCamp extends Building {
    public TrainingCamp(Map<String, Integer> cost, int buildTime) {
        super("Training Camp", cost, buildTime);
    }

    @Override
    public void function(ResourceManager ResourceManager) {
        Soldier soldier = new Soldier();
        System.out.println("Training Camp produced a Soldier!");
    }
}