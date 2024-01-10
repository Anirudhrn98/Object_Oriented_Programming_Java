/*
 * Player.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This is a part of the implementation for the guessing game implemented for HW4.1
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */


/**
 * Helper class for implementing Game.java
 */
public class Player {
    String playerName;
    static int playerId;

    /**
     * Class Player constructor
     */
    public Player(String playerName){
        this.playerName = playerName;
    }

    /**
     * Method is used toggle between player after each turn.
     */
    public static void switchPlayer() {
        playerId = 1 - playerId;
    }

}
