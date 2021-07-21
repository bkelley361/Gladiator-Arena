import java.util.*;

enum SpellType {
    None, Fire, Frost, Lightning, Health
}


public class Creature {
    int health;
    int strength;
    int frozen;
    int fire;
    SpellType type;

    Random rand = new Random();

    public Creature(int health, int strength) {
        this.health = health;
        this.strength = strength; 
    }

    public SpellType monsterSpell() {
        SpellType spell = SpellType.None;
        int random = rand.nextInt(100);
        int type = rand.nextInt(4);
        if (random > 93) {
            if (type == 0) {
                spell = SpellType.Fire;
            }
            if (type == 1) {
                spell = SpellType.Frost;
            }
            if (type == 2) {
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
        if (fire > 0) {
            return "Burned";
        }
        else if (frozen > 0) {
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
        frozen = 2;
    }

    public void fire() {
        fire = 3;
    }

    public boolean isFrozen() {
        boolean answer = false;

        if (frozen >= 1) {
            return true;
        }

        return answer;
    }

    public void decFreezeTimer() {
        if (isFrozen() == true) {
            frozen -= 1;
        }
    }

    public boolean isOnFire() {
        boolean answer = false;

        if (fire >= 1) {
            return true;
        }

        return answer;
    }

    public void decFireTimer() {
        if (isOnFire() == true) {
            fire -= 1;
        }
    }

    public boolean isDead() {
        if (health <= 0) {
           return true; 
        }
        return false;
    }

    public int castSpell(SpellType cast) {
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
        if (attack == 3) {
            fire = attack;
        }
        else if (attack == 2) {
            frozen = attack;
        }
        else {
            hurt(attack);
        }
    }
    
}