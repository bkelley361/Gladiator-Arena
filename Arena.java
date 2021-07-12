import java.util.*;

public class Arena {
    
    Scanner sc = new Scanner(System.in);
    Queue<Creature> north = new Queue<Creature>();
    Queue<Creature> east = new Queue<Creature>();
    Queue<Creature> south = new Queue<Creature>();
    Queue<Creature> west = new Queue<Creature>();
    
    Player player = new Player(20, 10, 0);

    public Queue<Creature> getQueue() {
        Random rand = new Random();
        int queue = rand.nextInt(4);
        if (queue == 0) {
            return north;
        }
        else if (queue == 1) {
            return east;
        }
        else if (queue == 2) {
            return south;
        }
        else {
            return west;
        }
    }

    public void createMonster() {
        Random rand = new Random();
        Creature m = new Creature(0, 0, 0);
        m.health = rand.nextInt(20) + 1;
        m.strength = rand.nextInt(4) + 1;
        m.spell = m.monsterSpell();
        getQueue().enqueue(m);
    }

    public void playersTurn() {
        if (player.getStatus() == "Frozen") {
            System.out.println("You are frozen");
        }
        else if (player.getStatus() == "Burned") {
            player.hurt(1);
        }
        else {
            surroundings(north);
            surroundings(east);
            surroundings(south);
            surroundings(west);
            Creature victim = getDir().peek();
            boolean loop = true;
            while (loop == true) {
                System.out.println("Would you like to use a normal attack or a spell?");
                System.out.println("Please enter 1 for normal and 2 for spell");
                String type = sc.nextLine();
                if (type == "1") {
                    int damage = player.attack();
                    victim.hurt(damage);
                    System.out.println("You did " + Integer.toString(damage) + " damage to the monster");
                    loop = false;
                }
                else if (type == "2") {
                    System.out.println("You used a spell");
                    loop = false;
                }
                else {
                    loop = true;
                }
            }
            
        }
    }

    public void gameloop() {
        while (true) {
            createMonster();

        }

    }

    public void play() {

    }


    public void surroundings(Queue<Creature> direction) {
        if (! direction.isEmpty()) {
            Creature m = north.peek();
            System.out.print("There is a monster with " + m.health + " health, " + m.strength + ", and " + m.spell + " spells");
        }
        else {
            System.out.println("The " + direction.toString() + " is clear for the moment");
        }
    }

    public Queue<Creature> getDir() {
        while (true) {
            System.out.println("Which direction would you like to attack in?");
            System.out.println("Please enter N, E, S,  and W for the direction");
            String dir = sc.nextLine();
            if (dir == "N") {
                return north;
            }
            else if (dir == "E") {
                return east;
            }
            else if (dir == "S") {
                return south;
            }
            else if (dir == "W") {
                return west;
            }
        }
    }


}
