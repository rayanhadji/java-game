package Buildings;

import java.util.Map;

public abstract class Building {
    protected String name;
    protected Map<String, Integer> cost;
    protected int buildTime;
    protected int level;   // track upgrade level

    public Building(String name, Map<String, Integer> cost, int buildTime) {
        this.name = name;
        this.cost = cost;
        this.buildTime = buildTime;
        this.level = 1;    // start at level 1
    }

    // What the building does (produce, train, etc.)
    public abstract void function(ResourceManager resourceManager);

    // ✅ Upgrade method using your ResourceManager
    public void upgrade(ResourceManager resourceManager) {
        boolean canUpgrade = true;

        // Try to spend resources for upgrade
        for (Map.Entry<String, Integer> entry : cost.entrySet()) {
            if (!resourceManager.spendResource(entry.getKey(), entry.getValue())) {
                canUpgrade = false;
                break;
            }
        }

        if (canUpgrade) {
            level++;
            System.out.println(name + " upgraded to level " + level);
        } else {
            System.out.println("Not enough resources to upgrade " + name);
        }
    }

    public String getName() { return name; }
    public Map<String, Integer> getCost() { return cost; }
    public int getBuildTime() { return buildTime; }
    public int getLevel() { return level; }
}