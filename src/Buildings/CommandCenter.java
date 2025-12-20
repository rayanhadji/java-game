package Buildings;

import java.util.Map;

public class CommandCenter extends Building {
    private static int level;

    public CommandCenter(Map<String, Integer> cost, int buildTime) {
        super("Command Center", cost, buildTime);
        this.level = 1;
    }

    @Override
    public void function(ResourceManager ResourceManager) {
        System.out.println("Command Center is at level " + level);
    }

    public void upgrade() {
        level++;
        System.out.println("Command Center upgraded to level " + level);
    }
}