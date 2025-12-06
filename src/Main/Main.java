package Main;
import Map.Map;

public class Main {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.startGame();

        Map map = new Map(9, 5);
        map.displayMap();
    }
}