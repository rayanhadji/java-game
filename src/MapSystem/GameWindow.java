package MapSystem;

import javax.swing.*;

public class GameWindow {
    public static void main(String[] args) {
        GameMap map = new GameMap(13, 20);   // GameMap object

        JFrame frame = new JFrame("GameMap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        MapPanel panel = new MapPanel(map);  // MapPanel expects GameMap
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        map.displayMap();
    }
}