package Units;

public class Archer extends Unit {
    public Archer() {
        super(80, 15, 5, 3, 75);
    }

    @Override
    public void move(int newRow, int newCol) {
        System.out.println("Archer moved to (" + newRow + ", " + newCol + ")");
    }

    @Override
    public void attack(Unit target) {
        int damage = this.attack - target.getDefense();
        if (damage < 0) damage = 1;
        target.takeDamage(damage);
        System.out.println("Archer attacked for " + damage + " damage.");
    }

}
