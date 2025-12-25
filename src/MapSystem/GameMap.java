package MapSystem;

/**
 * The GameMap is a grid of Tiles.
 * - Each tile has type, accessibility, and bonus (from Tile).
 * - The map sets up terrain rules (grass, water, mountain).
 * - It also defines spawn points for player and enemies.
 */
public class GameMap {
    private final int width;
    private final int height;
    private final Tile[][] grid;

    private int playerSpawnRow, playerSpawnCol;
    private int enemySpawnRow, enemySpawnCol;

    public GameMap(int height, int width) {
        if (height <= 0 || width <= 0) throw new IllegalArgumentException("Invalid map size");
        this.height = height;
        this.width = width;
        this.grid = new Tile[height][width];
        fillDefault();
        setDefaultSpawns();
    }

    private void fillDefault() {
        int riverCol = width / 2;
        int bridgeRow = height / 2; // create a bridge in the middle row

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                boolean border = (r == 0 || r == height - 1 || c == 0 || c == width - 1);

                if (border) {
                    grid[r][c] = new Tile('M', false, 0); // mountains at borders
                } else if (c == riverCol) {
                    if (r == bridgeRow) {
                        // bridge through the river
                        grid[r][c] = new Tile('G', true, 0);
                    } else {
                        grid[r][c] = new Tile('W', false, -1);
                    }
                } else {
                    grid[r][c] = new Tile('G', true, 0);
                }
            }
        }
    }

    private void setDefaultSpawns() {
        playerSpawnRow = height / 2;   // same row as bridge
        playerSpawnCol = 1;
        enemySpawnRow = height / 2;    // same row as bridge
        enemySpawnCol = width - 2;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Tile getTile(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width)
            throw new IndexOutOfBoundsException("Invalid coords: (" + row + "," + col + ")");
        return grid[row][col];
    }

    public int[] getPlayerSpawn() { return new int[]{playerSpawnRow, playerSpawnCol}; }
    public int[] getEnemySpawn() { return new int[]{enemySpawnRow, enemySpawnCol}; }

    public void displayMap() {
        for (int r = 0; r < height; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < width; c++) {
                line.append(grid[r][c].toString());
            }
            System.out.println(line);
        }
    }
}