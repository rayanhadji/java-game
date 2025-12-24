import Core.GameManager;

public class Main {
    public static void main(String[] args) {
        GameManager game = new GameManager();

        int turns = 5; // play 5 turns for demo
        for (int i = 0; i < turns; i++) {
            game.playTurn();
            if (game.isGameOver()) {
                System.out.println("Game Over! The base has fallen.");
                break;
            }
        }
    }
}