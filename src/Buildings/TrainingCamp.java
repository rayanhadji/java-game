package Buildings;

import Core.Player;
import Units.Soldier;
import Units.Unit;
import java.util.Map; // import Player

public class TrainingCamp extends Building {

    public TrainingCamp(Map<String, Integer> cost, int buildTime) {
        super("Training Camp", cost, buildTime);
    }

    // Required by Building (abstract method)
    @Override
    public void function(ResourceManager resourceManager) {
        // Keep this for compatibility
        System.out.println("Training Camp requires a Player reference to train units.");
    }

    // Overloaded method: actually trains units and adds them to Player
    public void function(ResourceManager resourceManager, Player player) {
        Unit unit = new Soldier();

        if (canAfford(unit, resourceManager)) {
            payCost(unit, resourceManager);
            player.addUnit(unit); // <-- add to army
            System.out.println("Training Camp trained a " + unit.getName() + "!");
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