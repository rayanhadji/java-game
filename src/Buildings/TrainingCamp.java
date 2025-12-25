package Buildings;

import Units.Archer;
import Units.Cavalry;
import Units.Soldier;
import Units.Unit;

import java.util.Map;
import java.util.Scanner;

/**
 * TrainingCamp trains new units for the player.
 * - Costs resources to train.
 * - Can train Soldier, Archer, or Cavalry.
 * - Returns the trained Unit so GameManager can place it on the map.
 */
public class TrainingCamp extends Building {
    private final int trainingTime;

    public TrainingCamp(Map<String, Integer> cost, int trainingTime) {
        super("Training Camp", cost, trainingTime); // FIX: add name to match Building constructor
        this.trainingTime = trainingTime;
    }

    /**
     * Ask the player which unit to train, check resources, and return the new Unit.
     */
    public Unit trainUnit(ResourceManager resources) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose unit to train:");
        System.out.println("1. Soldier (cost: 10 Food, 5 Gold)");
        System.out.println("2. Archer (cost: 15 Food, 10 Gold)");
        System.out.println("3. Cavalry (cost: 25 Food, 20 Gold)");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        Unit newUnit = null;

        switch (choice) {
            case 1 -> {
                boolean ok = resources.spendResource("Food", 10) && resources.spendResource("Gold", 5);
                if (ok) {
                    newUnit = new Soldier();
                } else {
                    System.out.println("Not enough resources for Soldier.");
                }
            }
            case 2 -> {
                boolean ok = resources.spendResource("Food", 15) && resources.spendResource("Gold", 10);
                if (ok) {
                    newUnit = new Archer();
                } else {
                    System.out.println("Not enough resources for Archer.");
                }
            }
            case 3 -> {
                boolean ok = resources.spendResource("Food", 25) && resources.spendResource("Gold", 20);
                if (ok) {
                    newUnit = new Cavalry();
                } else {
                    System.out.println("Not enough resources for Cavalry.");
                }
            }
            default -> System.out.println("Invalid choice.");
        }

        if (newUnit != null) {
            System.out.println(newUnit.getName() + " trained successfully!");
        }
        return newUnit;
    }

    @Override
    public void function(ResourceManager resources) {
        // TrainingCamp doesn’t produce resources, so nothing here.
    }
}