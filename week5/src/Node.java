/*
 * Node.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 */

/*
 * This class aids in the construction of Binary Search Tree for HW5.2
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

/**
 * Helper class for implementing SortedStorage.java
 */
class Node {
    String val;

    Node left;
    Node right;

    /**
     * Class Node constructor
     */
    public Node(String val) {
        this.val = val;
    }
}
