public class Player extends Creature {
    int health = 20;
    int srength = 10;
    int spell = 0;
    int XP = 0;
    int Status = 0;

    public Player(int health, int strength, int spell) {
        super(health, strength, spell);
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

    

}
