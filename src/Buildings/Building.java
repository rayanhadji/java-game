package Buildings;

import java.util.Map;

public abstract class Building {
    protected String name;
    protected Map<String, Integer> cost;  
    protected int buildTime;               

    public Building(String name, Map<String, Integer> cost, int buildTime) {
        this.name = name;
        this.cost = cost;
        this.buildTime = buildTime;
    }

    public abstract void function(ResourceManager ResourceManager); // what the building does (produce, train, upgrade)

    public String getName() { return name; }
    public Map<String, Integer> getCost() { return cost; }
    public int getBuildTime() { return buildTime; }
}