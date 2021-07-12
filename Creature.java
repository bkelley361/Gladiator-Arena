import java.util.*;

public class Creature {
    int health;
    int strength;
    int spell;
    int status;
    Random rand = new Random();

    public Creature(int health, int strength, int spell) {
        this.health = health;
        this.strength = strength;
        this.spell = spell;
    }

    public int monsterSpell() {
        int spell = 0;
        int random = rand.nextInt(100);
        if (random > 93) {
            spell = rand.nextInt(4) + 1;
        }
        else {
            return spell;
        }
        return spell;
    }

    public void hurt(int damage) {
        health -= damage;
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



    
}
