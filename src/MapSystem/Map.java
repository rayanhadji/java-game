package MapSystem;

public class Map {
    private final int width;
    private final int height;
    private final Tile[][] grid;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Tile[height][width];
        fillmap();
    }
    
    private void fillmap() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col == width / 2) {
                    grid[row][col] = new Tile('W', false, 0); 
                } else if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
                    grid[row][col] = new Tile('M', false, 0); 
                } else {
                    grid[row][col] = new Tile('G', true, 0); 
                }
            }
        }
    }

    public Tile getTile(int row, int col) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            return grid[row][col];
        } else {
            throw new IndexOutOfBoundsException("Invalid tile coordinates: (" + row + "," + col + ")");
        }
    }

    public void displayMap() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(grid[row][col].toString());
            }
            System.out.println();
        }
    }

}
