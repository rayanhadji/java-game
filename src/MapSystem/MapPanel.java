package MapSystem;

import java.awt.*;
import javax.swing.*;

public class MapPanel extends JPanel {
    private final GameMap map;   // GameMap
    private static final int TILE_SIZE = 32;

    public MapPanel(GameMap map) {   // constructor expects GameMap
        this.map = map;
        setPreferredSize(new Dimension(map.getWidth() * TILE_SIZE, map.getHeight() * TILE_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {
                Tile tile = map.getTile(row, col);

                Color color;
                switch (tile.getType()) {
                    case 'G' -> color = Color.GREEN;
                    case 'W' -> color = Color.BLUE;
                    case 'M' -> color = Color.GRAY;
                    default -> color = Color.BLACK;
                }

                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                g.setColor(color);
                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

                g.setColor(Color.BLACK);
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);

                if (tile.getUnit() != null) {
                    g.setColor(Color.RED);
                    g.fillOval(x + 8, y + 8, 16, 16);
                } else if (tile.getEnemy() != null) {
                    g.setColor(Color.BLACK);
                    g.fillOval(x + 8, y + 8, 16, 16);
                }
            }
        }
    }
}