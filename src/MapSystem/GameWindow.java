import javax.swing.*;
import MapSystem.Map;
import MapSystem.MapPanel;

public class GameWindow {

    public static void main(String[] args) {

        Map map = new Map(15, 20);
        map.fillmap();

        JFrame frame = new JFrame("Strategy Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        MapPanel panel = new MapPanel(map);
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}