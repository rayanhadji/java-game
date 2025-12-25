package Buildings;

import java.util.Map;

public class CommandCenter extends Building {

    public CommandCenter(Map<String, Integer> cost, int buildTime) {
        super("Command Center", cost, buildTime);
    }

    @Override
    public void function(ResourceManager resourceManager) {
        System.out.println("Command Center is at level " + getLevel());
    }

    @Override
    public void upgrade(ResourceManager resourceManager) {
        super.upgrade(resourceManager); // use parent upgrade logic
    }
}