package Buildings;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<String, Integer> resources;

    public ResourceManager() {
        resources = new HashMap<>();
        resources.put("Gold", 250);
        resources.put("Wood", 150);
        resources.put("Stone", 100);
        resources.put("Food", 200);
    }

    // Update resources amounts
    public void addResource(String type, int amount) {
        resources.put(type, resources.getOrDefault(type, 0) + amount);
    }

    public boolean spendResource(String type, int amount) {
        int current = resources.getOrDefault(type, 0);
        if (current >= amount) {
            resources.put(type, current - amount);
            return true;
        }
        return false;
    }

    // Show amount of resource
    public void showResources() {
        System.out.println("Resources: " + resources);
    }

    // Get current resources
    public Map<String, Integer> getResources() {
        return resources;
    }
}