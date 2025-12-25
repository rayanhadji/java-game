import Core.GameManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameManager game = new GameManager();
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Start wave");
            System.out.println("2. Recruit unit");
            System.out.println("3. Upgrade building");
            System.out.println("4. Show resources");
            System.out.println("5. Show army");
            System.out.println("6. Show map");
            System.out.println("7. Exit game");
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> game.playTurn(); // triggers wave + combat
                case "2" -> game.recruitUnit(); // new method: trains one unit
                case "3" -> game.upgradeBuilding(scanner); // new method: upgrade menu
                case "4" -> game.getResourceManager().showResources();
                case "5" -> game.getPlayer().showArmy();
                case "6" -> game.getGameMap().displayMap();
                case "7" -> {
                    System.out.println("Exiting game...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

            if (game.isGameOver()) {
                System.out.println("Game Over! The base has fallen.");
            }
        }
    }
}