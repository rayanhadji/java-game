import Buildings.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ResourceManager resourceManager = new ResourceManager();

        // Cost to build TrainingCamp (example)
        Map<String, Integer> campCost = new HashMap<>();
        campCost.put("Wood", 20);
        campCost.put("Stone", 10);

        TrainingCamp camp = new TrainingCamp(campCost, 2);

        resourceManager.showResources();
        camp.function(resourceManager); // Try to train a Soldier
        resourceManager.showResources();
    }
}
