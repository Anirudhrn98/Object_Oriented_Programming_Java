/*
 * This program simulates the game of marble-grabbing through multiple players (threads).
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

import java.util.ArrayList;
import java.util.List;

class Player {
    String player;
    int grabbedSoManyMarbles;

    /**
     *  Player class constructor
     */
    public Player(int player) {
        this.player = "player " + player;
    }
}


public class GrabIt extends Thread {
    private static final String marble = "marble";
    private Player player; // Number of players? Array
    private static int numberOfPlayers = 10;
    private static int limitReached;
    private static int soManyMarbles = 100;

    /**
     *  GrabIt class constructor
     */
    public GrabIt(Player player) {
        this.player = player;
    }

    /**
     * Method that contains a synchronized block that allows only 1 player to access the the marble at one time
     * till the game ends.
     */
    private void grabIt() throws InterruptedException {
        synchronized (marble) {
            if (limitReached < soManyMarbles) {
                this.player.grabbedSoManyMarbles += 1;
                limitReached += 1;
            }
        }
    }

    /**
     * The run method for the threads
     */
    @Override
    public void run() {
        try {                                        
            while (limitReached < soManyMarbles) {
                sleep((int) (10 * Math.random()));
                grabIt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * The main method that initializes all the threads, starts them and prints the output when all the threads
     * have completed
     * @param       args        command line argument
     */
    public static void main(String[] args) throws InterruptedException {
        List<Player> players = new ArrayList<>(numberOfPlayers);
        List<GrabIt> grabIts = new ArrayList<>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) { // Why not do this part at the constructor of GrabIt()
            Player p = new Player(i);
            GrabIt t = new GrabIt(p);
            players.add(p);
            grabIts.add(t);
        }
        for (GrabIt t: grabIts){
            t.start();
        }
        for (GrabIt t: grabIts){
            t.join();
        }

        for (Player p : players){
            System.out.println(p.player + "\tgrabbed so many: " + p.grabbedSoManyMarbles + " marbles");

        }
    }
}