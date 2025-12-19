package Buildings;

import java.util.Map;

public class Farm extends Building {

    private int production; // amount of food per turn

    public Farm(Map<String, Integer> cost, int buildTime, int production) {
        super("Farm", cost, buildTime);
        this.production = production;
    }

    @Override
    public void function(ResourceManager ResourceManager) {
        System.out.println("Farm produced " + production + " Food.");
        ResourceManager.addResource("Food", production);
    }
}
