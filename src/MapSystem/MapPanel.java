package MapSystem;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private final Map map;
    private static final int TILE_SIZE = 32;

    public MapPanel(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(
                mapWidth() * TILE_SIZE,
                mapHeight() * TILE_SIZE
        ));
    }

    private int mapWidth() {
        return map != null ? mapWidthReflection() : 0;
    }

    private int mapHeight() {
        return map != null ? mapHeightReflection() : 0;
    }

    // لأن width و height private
    private int mapWidthReflection() {
        try {
            var f = Map.class.getDeclaredField("width");
            f.setAccessible(true);
            return f.getInt(map);
        } catch (Exception e) {
            return 0;
        }
    }

    private int mapHeightReflection() {
        try {
            var f = Map.class.getDeclaredField("height");
            f.setAccessible(true);
            return f.getInt(map);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < mapHeight(); row++) {
            for (int col = 0; col < mapWidth(); col++) {
                Tile tile = map.getTile(row, col);

                switch (tile.getType()) {
                    case 'G' -> g.setColor(Color.GREEN);
                    case 'W' -> g.setColor(Color.BLUE);
                    case 'M' -> g.setColor(Color.GRAY);
                    default -> g.setColor(Color.BLACK);
                }

                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);

                if (tile.getUnit() != null) {
                    g.setColor(Color.RED);
                    g.fillOval(x + 8, y + 8, 16, 16);
                }
            }
        }
    }
}