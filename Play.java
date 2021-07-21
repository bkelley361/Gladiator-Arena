import java.util.*;

public class Play {
    
    public static void main(String[] args) {
        Queue<Creature> north = new Queue<Creature>();
        Queue<Creature> east = new Queue<Creature>();
        Queue<Creature> south = new Queue<Creature>();
        Queue<Creature> west = new Queue<Creature>();
        Random rand = new Random();
        Player player = new Player(20, 10, 0);
        Arena game = new Arena(north, east, south, west, player, rand);
        game.gameloop();
      }


}
