import java.util.*;

enum SpellType {
    None, Fire, Frost, Lightning, Health
}


public class Creature {
    int health;
    int strength;
    int frozenTimer;
    int fireTimer;
    int baseHealth;

    public static final int CAST_FIRE = 3;      //Returns so that the game knows a fire spell was used
    public static final int CAST_FROST = 2;     //Returns so that the game knows a frost spell was used
    public static final int CAST_LIGHTNING = 7; //Returns so that the game knows a lightning spell was used and the damage it deals
    public static final int CAST_HEAL = 5;      //Returns so the game knows a Heal spell was used and the health that was restored
    SpellType type;

    Random rand = new Random();

    public Creature(int health, int strength) {
        this.health = health;
        this.strength = strength; 
    }

    public SpellType giveSpell() {
        int between100 = 100;   // For a random number between 0 and 99
        int spellTypes = 4;     // Gets a random nuber between 0 and 3 for spell types
        int fireSpell = 0;      // For a fire spell
        int frostSpell = 1;     // For a frost spell
        int lightningSpell = 2; // For a lightning spell
        SpellType spell = SpellType.None;
        int random = rand.nextInt(between100);
        int type = rand.nextInt(spellTypes);
        if (random > 93) {
            if (type == fireSpell) {
                spell = SpellType.Fire;
            }
            if (type == frostSpell) {
                spell = SpellType.Frost;
            }
            if (type == lightningSpell) {
                spell = SpellType.Lightning;
            }
            else {
                spell = SpellType.Health;
            }
        }
        else {
            return spell;
        }
        return spell;
    }

    public void hurt(int damage) {
        this.health -= damage;
    }

    public void giveSpell(SpellType spell) {
        type = spell;
    }

    public String getStatus() {
        if (fireTimer > 0) {
            return "Burned";
        }
        else if (frozenTimer > 0) {
            return "Frozen";
        }

        return "Healthy";
    }

    public int attack() {
        if (getStatus() == "Frozen") {
            return 0;
        }
        return rand.nextInt(strength);
    }

    public void freeze() {
        frozenTimer = 2;
    }

    public void fire() {
        fireTimer = 3;
    }

    public boolean isFrozen() {
        return (frozenTimer >= 1);
    }

    public void decFreezeTimer() {
        if (isFrozen() == true) {
            frozenTimer -= 1;
        }
    }

    public boolean isOnFire() {
        return (fireTimer >= 1);
    }

    public void decFireTimer() {
        if (isOnFire() == true) {
            fireTimer -= 1;
        }
    }

    public boolean isDead() {
        if (health <= 0) {
           return true; 
        }
        return false;
    }

    public int spellTypeToInt(SpellType cast) {
        int castFire = 3;      //Returns so that the game knows a fire spell was used
        int castFrost = 2;     //Returns so that the game knows a frost spell was used
        int castLightning = 7; //Returns so that the game knows a lightning spell was used and the damage it deals
        int castHeal = 5;      //Returns so the game knows a Heal spell was used and the health that was restored
        if (cast == SpellType.Fire) {
            return castFire;
        }
        else if (cast == SpellType.Frost) {
            return castFrost;
        }
        else if (cast == SpellType.Lightning) {
            return castLightning;
        }
        else {
            return castHeal;
        }
    }

    public void attackedBySpell(int attack) {
        if (attack == CAST_FIRE) {
            fireTimer = attack;
        }
        else if (attack == CAST_FROST) {
            frozenTimer = attack;
        }
        else {
            hurt(attack);
        }
    }
}