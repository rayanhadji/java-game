package Buildings;

import java.util.Map;

public class Farm extends Building {
    private int production; // base amount of food per turn

    public Farm(Map<String, Integer> cost, int buildTime, int production) {
        super("Farm", cost, buildTime);
        this.production = production;
    }

    @Override
    public void function(ResourceManager resourceManager) {
        int totalProduction = production * getLevel();
        resourceManager.addResource("Food", totalProduction);
        System.out.println("Farm produced " + totalProduction + " Food.");
    }

    @Override
    public void upgrade(ResourceManager resourceManager) {
        int oldLevel = getLevel();
        super.upgrade(resourceManager);
        if (getLevel() > oldLevel) { // only increase if upgrade succeeded
            production += 2; 
            System.out.println("Farm production increased to " + production + " per level.");
        }
    }
}