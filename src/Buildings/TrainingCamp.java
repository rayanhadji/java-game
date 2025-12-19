package Buildings;

import java.util.Map;
import Units.Soldier;
import Units.Unit;

public class TrainingCamp extends Building {

    public TrainingCamp(Map<String, Integer> cost, int buildTime) {
        super("Training Camp", cost, buildTime);
    }

    @Override
    public void function(ResourceManager resourceManager) {
        Unit unit = new Soldier();

        if (canAfford(unit, resourceManager)) {
            payCost(unit, resourceManager);
            System.out.println("Training Camp trained a " + unit.getName() + "!");
            // Later: add to Player army
        } else {
            System.out.println("Not enough resources to train " + unit.getName());
        }
    }

    private boolean canAfford(Unit unit, ResourceManager resourceManager) {
        for (Map.Entry<String, Integer> entry : unit.getCost().entrySet()) {
            String resource = entry.getKey();
            int required = entry.getValue();
            int available = resourceManager.getResources().getOrDefault(resource, 0);
            if (available < required) {
                return false;
            }
        }
        return true;
    }

    private void payCost(Unit unit, ResourceManager resourceManager) {
        for (Map.Entry<String, Integer> entry : unit.getCost().entrySet()) {
            String resource = entry.getKey();
            int required = entry.getValue();
            resourceManager.addResource(resource, -required);
        }
    }
}