package Main;

public class GameManager {
    private boolean isGameRunning;

    public void startGame() {
        isGameRunning = true;
        System.out.println("Game has started!");
    }

    public void endGame() {
        isGameRunning = false;
        System.out.println("Game has stopped!");
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

}
