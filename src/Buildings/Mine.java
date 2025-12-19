package Buildings;

import java.util.Map;


public class Mine extends Building {
    private int production; // amount of stone per turn

    public Mine(Map<String, Integer> cost, int buildTime, int production) {
        super("Mine", cost, buildTime);
        this.production = production;
    }

    @Override
    public void function(ResourceManager ResourceManager) {
        System.out.println("Mine produced " + production + " Stone.");
        ResourceManager.addResource("Stone", production);
    }
}