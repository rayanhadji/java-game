package Buildings;

import java.util.Map;

public class Mine extends Building {
    private int production; // base amount of stone per turn

    public Mine(Map<String, Integer> cost, int buildTime, int production) {
        super("Mine", cost, buildTime);
        this.production = production;
    }

    @Override
    public void function(ResourceManager resourceManager) {
        int totalProduction = production * getLevel();
        resourceManager.addResource("Stone", totalProduction);
        System.out.println("Mine produced " + totalProduction + " Stone.");
    }

    @Override
    public void upgrade(ResourceManager resourceManager) {
        int oldLevel = getLevel();
        super.upgrade(resourceManager);
        if (getLevel() > oldLevel) { // only increase if upgrade succeeded
            production += 1; 
            System.out.println("Mine production increased to " + production + " per level.");
        }
    }
}