public class Player extends Creature {
    int health = 20;
    int srength = 10;
    int spell = 0;
    int XP = 0;
    int Status = 0;
    int spellAdd = 0;
    int baseHealth = 20;
    SpellType spells[];

    public Player(int health, int strength, SpellType type) {
        super(health, strength, type);
    }

    public String getStatus() {
        if (Status == 0) {
            return "Healthy";
        }
        else if (Status == 1) {
            return "Burned";
        }
        else {
            return "Frozen";
        }
    }

    public void addXP(int amt) {
        XP += amt;
    }

    public int getXP() {
        return XP;
    }

    public boolean canLevelUp() {
        if (XP >= 100) {
            return true;
        }
        return false;
    }

    public void levelUp(SpellType type) {
        if (canLevelUp() == true) {
            XP -= 100;
            spells[spellAdd] = type;
            spellAdd += 1;
            baseHealth += 10;
            health = baseHealth;
            strength += 5; 
        }
    }

}
