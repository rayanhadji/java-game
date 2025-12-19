package Buildings;

import java.util.Map;

public class Sawmill extends Building {

    private int production; // amount of wood per turn

    public Sawmill(Map<String, Integer> cost, int buildTime, int production) {
        super("Sawmill", cost, buildTime);
        this.production = production;
    }

    @Override
    public void function(ResourceManager ResourceManager) {
        System.out.println("Sawmill produced " + production + " Wood.");
        ResourceManager.addResource("Wood", production);
    }
}
