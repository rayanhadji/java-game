import Core.GameManager;
import MapSystem.Map;
import Units.Soldier;

public class Main {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.startGame();

        Map map = new Map(9, 5);
        map.displayMap();

        System.out.println("Placing a soldier at (2,2):");

        Soldier s1 = new Soldier();
        map.getTile(2, 2).placeUnit(s1);
        map.displayMap();

        gm.endGame();
    }
}