import java.util.*;

public class Arena {

    Queue<Creature> north = new Queue<Creature>();
    Queue<Creature> east = new Queue<Creature>();
    Queue<Creature> south = new Queue<Creature>();
    Queue<Creature> west = new Queue<Creature>();
    Random rand = new Random();
    Player player = new Player(20, 10, 0);
    Scanner sc = new Scanner(System.in);

    public Arena(Queue<Creature> north, Queue<Creature> east, Queue<Creature> south, Queue<Creature> west,
            Player player, Random rand) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.player = player;
        this.rand = rand;
    }

    public Queue<Creature> getQueue() {
        Random rand = new Random();
        int queue = rand.nextInt(4);
        if (queue == 0) {
            return north;
        } else if (queue == 1) {
            return east;
        } else if (queue == 2) {
            return south;
        } else {
            return west;
        }
    }

    public void createMonster() {
        int begHealth = 0;
        int begStrength = 0;
        int healthRange = 20;
        int strengthRange = 4;
        int addOne = 1; // So that health and strength are not 0
        Random rand = new Random();
        Creature m = new Creature(begHealth, begStrength);
        m.health = rand.nextInt(healthRange) + addOne;
        m.strength = rand.nextInt(strengthRange) + addOne;
        m.giveSpell(m.giveSpell());
        m.baseHealth = m.health;
        getQueue().enqueue(m);
    }

    public void playersTurn() {
        int fireDamage = 2;
        if (player.getStatus() == "Frozen") {
            System.out.println("You are frozen\n");
        } else {
            if (player.getStatus() == "Burned\n") {
                player.hurt(fireDamage);
                if (player.isDead()) {

                }
            }
            surroundings(north);
            surroundings(east);
            surroundings(south);
            surroundings(west);
            Queue<Creature> direction = getDir();
            Creature victim = direction.peek();
            while (true) {
                System.out.println("Would you like to use a normal attack or a spell?");
                System.out.println("Please enter 1 for normal, 2 for spell, and 3 to see what spell you have\n");
                String type = sc.next();
                System.out.println(type);
                if (type.equals("1")) {
                    int damage = player.attack();
                    victim.hurt(damage);
                    System.out.println("You did " + Integer.toString(damage) + " points of to the monster\n");
                    break;
                } else if (type.equals("2")) {
                    int spellType = player.spellTypeToInt(player.spell);
                    if (spellType == 5) {
                        player.health += 5;
                        System.out
                                .println("You healed yourself. You now have " + player.health
                                        + " hitpoints remaining\n");
                    } else {
                        victim.attackedBySpell(spellType);
                        System.out.println("You casted a " + player.spell + " spell\n");
                    }
                    player.type = SpellType.None;
                    break;
                } else if (type.equals("3")) {
                    System.out.println("You have a " + player.spell + " spell in your inventory\n");
                }
            }
            player.decFreezeTimer();
            player.decFireTimer();
            if (victim.isDead()) {
                player.addXP(victim.baseHealth);
                System.out.println("The monster is dead. You have " + player.XP + " XP points\n");
                if (player.canLevelUp()) {
                    SpellType newSpell;
                    int randSpell = rand.nextInt(4);
                    if (randSpell == 0) {
                        newSpell = SpellType.Fire;
                    } else if (randSpell == 1) {
                        newSpell = SpellType.Frost;
                    } else if (randSpell == 2) {
                        newSpell = SpellType.Lightning;
                    } else {
                        newSpell = SpellType.Health;
                    }
                    player.levelUp(newSpell);
                }
                direction.dequeue();
            }
        }
    }

    public void monstersTurn() {
        monsterAttack(north);
        monsterAttack(east);
        monsterAttack(south);
        monsterAttack(west);
    }

    public void gameloop() {
        int spawnMonster = 4;
        int spawnMonsterChance = 3;
        int turnCounter = 0;

        boolean gameOver = false;
        while (gameOver == false) {
            if (rand.nextInt(spawnMonster) < spawnMonsterChance) {
                createMonster();
            }
            playersTurn();
            monstersTurn();
            turnCounter += 1;
            if (player.isDead()) {
                System.out.println("You lasted " + turnCounter + " turns. Honestly that is pretty bad");
                gameOver = true;
            }
        }
        sc.close();
    }

    public void surroundings(Queue<Creature> direction) {
        String dir = getDir(direction);
        if (!direction.isEmpty()) {
            Creature m = direction.peek();
            System.out.println("In the " + dir + " queue there is a monster with " + m.health + " health, " + m.strength
                    + " strength, and " + m.type + " spell. There are  " + direction.size()
                    + " monsters in that queue");
        } else {
            System.out.println("The " + dir + " is clear for the moment");
        }
    }

    public Queue<Creature> getDir() {
        while (true) {
            System.out.println("Which direction would you like to attack in?");
            System.out.println("Please enter N, E, S, and W for the direction\n");
            String dir = sc.next();
            if (dir.toUpperCase().equals("N") && !north.isEmpty()) {
                return north;
            } else if (dir.toUpperCase().equals("E") && !east.isEmpty()) {
                return east;
            } else if (dir.toUpperCase().equals("S") && !south.isEmpty()) {
                return south;
            } else if (dir.toUpperCase().equals("W") && !west.isEmpty()) {
                return west;
            }
        }
    }

    public void monsterAttack(Queue<Creature> dir) {
        if (dir.isEmpty()) {
            return;
        }
        Creature c = dir.peek();
        SpellType cast = c.type;
        String direction = getDir(dir);
        if (c.type != SpellType.None) {
            if (cast == SpellType.Fire) {
                player.fire();
            } else if (cast == SpellType.Frost) {
                player.freeze();
            } else if (cast == SpellType.Lightning) {
                player.hurt(7);
            } else {
                c.health += 5;
            }
            c.type = SpellType.None;
        } else {
            int damage = c.attack();
            player.hurt(damage);
            System.out.println("The creature in the " + direction + " attacked you and did " + damage
                    + " points of damage. You have " + player.health + " hitpoints left\n");
        }
        c.decFireTimer();
        c.decFreezeTimer();
    }

    public String getDir(Queue<Creature> direction) {
        if (direction == north) {
            return "North";
        } else if (direction == east) {
            return "East";
        } else if (direction == south) {
            return "South";
        } else {
            return "West";
        }
    }

}