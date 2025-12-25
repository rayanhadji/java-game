package Buildings;

import java.util.Map;

public class Sawmill extends Building {
    private int production; // base amount of wood per turn

    public Sawmill(Map<String, Integer> cost, int buildTime, int production) {
        super("Sawmill", cost, buildTime);
        this.production = production;
    }

    @Override
    public void function(ResourceManager resourceManager) {
        int totalProduction = production * getLevel();
        resourceManager.addResource("Wood", totalProduction);
        System.out.println("Sawmill produced " + totalProduction + " Wood.");
    }

    @Override
    public void upgrade(ResourceManager resourceManager) {
        int oldLevel = getLevel();
        super.upgrade(resourceManager);
        if (getLevel() > oldLevel) { // only increase if upgrade succeeded
            production += 2; 
            System.out.println("Sawmill production increased to " + production + " per level.");
        }
    }
}