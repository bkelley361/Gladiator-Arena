public class Player extends Creature {
    int health = 20;
    int srength = 10;
    int XP = 0;
    int level = 1;
    int baseHealth = 20;
    int maxXP = 100;
    SpellType spell;


    public Player(int health, int strength, int XP) {
        super(health, strength);
        this.XP = XP;
    }

    public void addXP(int amt) {
        XP += amt;
    }

    public int getXP() {
        return XP;
    }

    public boolean canLevelUp() {
        if (XP >= maxXP) {
            return true;
        }
        return false;
    }

    public void levelUp(SpellType type) {
        if (canLevelUp() == true) {
            XP -= 100;
            baseHealth += 10;
            health = baseHealth;
            strength += 5;
            level++; 
        }
    }

}