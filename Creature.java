import java.util.*;

enum SpellType {
    None, Fire, Frost, Lightning, Health
}


public class Creature {
    int health;
    int strength;
    int status;
    int frozen;
    int fire;
    SpellType type;

    Random rand = new Random();

    public Creature(int health, int strength, SpellType type) {
        this.health = health;
        this.strength = strength;
        this.type = type;
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
        health -= damage;
    }

    public void giveSpell(SpellType type) {

    }

    public String getStatus() {
        if (status == 0) {
            return "Healthy";
        }
        else if (status == 1) {
            return "Burned";
        }
        else {
            return "Frozen";
        }
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
        if (cast == SpellType.Fire) {
            return 0;
        }
        else if (cast == SpellType.Frost) {
            return 1;
        }
        else if (cast == SpellType.Lightning) {
            return 7;
        }
        else {
            return 5;
        }
    }

    
}
