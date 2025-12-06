package Units;

public class Soldier extends Unit {
    public Soldier() {
        super(100, 20, 10, 1, 50);
    }

    @Override
    public void move(int newRow, int newCol) {
        System.out.println("Soldier moved to (" + newRow + ", " + newCol + ")");
    }

    @Override
    public void attack(Unit target) {
        int damage = this.attack - target.getDefense();
        if (damage < 0) damage = 1;
        target.takeDamage(damage);
        System.out.println("Soldier attacked for " + damage + " damage.");
    }

}
