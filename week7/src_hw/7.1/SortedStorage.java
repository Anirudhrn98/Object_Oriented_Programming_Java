/*
 * SortedStorage.java
 *
 * Version:
 *      $Id$
 *
 * Revision:
 *      $Log$
 *
 */

/*
 * This is an implementation of Binary Search Tree, which uses [int, long, string] as input
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */

/**
 * Helper class for implementing SortedStorage.java
 */
class Node {
    Comparable val;

    Node left;
    Node right;

    /**
     * Class Node constructor
     */
    public Node(Comparable val) {
        this.val = val;
    }
}

public class SortedStorage {
    Node root = null;

    /**
     * (Helper function) which adds a value into the binary search tree
     *
     * @param val the value to insert into the binary search tree
     * @return true/false  true if successfully added, else false
     */
    public boolean add(Comparable val) {
        if (val != null) {
            root = add(root, val);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a String value into the binary search tree recursively
     *
     * @param node the binary search tree where we want to insert the String value
     * @param val  the value to insert into the binary search tree
     * @return node         updated node
     */
    public Node add(Node node, Comparable val) {
        if (node == null) {
            node = new Node(val);
            return node;
        }
        // If the value is greater than then one in the node -> go to the right
        else if (val.compareTo(node.val) >= 0) {
            node.right = add(node.right, val);
            return node;
        }
        // else if the value is lesser than then one in the node -> go to the left
        else {//(val.compareTo(node.val) < 0) {
            node.left = add(node.left, val);
            return node;
        }
    }

    /**
     * Find the minimum (left-most) value in the binary search tree
     *
     * @param node the binary search tree where we want to insert the String value
     * @return node         minimum value in the BTS
     */
    public Comparable minValR(Node node) {
        if (node.left == null) {
            return node.val;
        } else {
            return minValR(node.left);
        }
    }

    /**
     * Find the minimum (right-most) value in the binary search tree
     *
     * @param node the binary search tree where we want to insert the String value
     * @return node         minimum value in the BTS
     */
    public Comparable minValL(Node node) {
        if (node.right == null) {
            return node.val;
        } else {
            return minValR(node.right);
        }
    }

    /**
     * (Helper function) which deletes a String value from the binary search tree
     *
     * @param val the value to delete into the binary search tree
     * @return true/false  true if successfully deleted, else false
     */
    public boolean delete(Comparable val) {
        if (root.val == null) {
            return false;
        } else {
            return deleteRec(root, val);
        }
    }

    /**
     * Deletes a String value from the binary search tree recursively
     *
     * @param node the binary search tree where we want to delete the String value
     * @param val  the value to delete from the binary search tree
     * @return true/false  true if successfully deleted, else false
     */
    public boolean deleteRec(Node node, Comparable val) {
        if (node == null) {
            return false;
        }

        boolean foundLeft;
        boolean foundRight;

        try {
            foundLeft = node.left.val.compareTo(val) == 0;
        } catch (NullPointerException e) {
            foundLeft = false;
        }
        try {
            foundRight = node.right.val.compareTo(val) == 0;
        } catch (NullPointerException e) {
            foundRight = false;
        }

        if (foundLeft) {
            // No children
            if ((node.left.left == null) && (node.left.right == null)) {
                node.left = null;
                return true;
            }
            // One child
            else if ((node.left.left == null) || (node.left.right == null)) {
                node.left = node.left.right == null ? node.left.left : node.left.right;
                return true;
            } else {
                // Two children
                Comparable temp = minValR(node.left.right);
                deleteRec(node, temp);
                node.left.val = temp;
                return true;
            }
        } else if (foundRight) {
            // No children
            if ((node.right.left == null) && (node.right.right == null)) {
                node.right = null;
                return true;
            }
            // One child
            else if ((node.right.left == null) || (node.right.right == null)) {
                node.right = node.right.right == null ? node.right.left : node.right.right;
                return true;
            } else {
                // Two children
                Comparable temp = minValR(node.right.right);
                deleteRec(node, temp);
                node.right.val = temp;
                return true;
            }
        }

        // If value greater than node.val (check to the right)
        if (val.compareTo(node.val) > 0) {
            deleteRec(node.right, val);
        }
        // Else if value less than node.val (check to the left)
        else if (val.compareTo(node.val) < 0) {
            deleteRec(node.left, val);
        }
        // Else it means that the value we want to delete is root
        else {
            if (node.left != null) {
                node.val = minValL(node.left);
                node.left = null;
                return true;
            } else if (node.right != null) {
                node.val = minValR(node.right);
                node.right = null;
                return true;
            } else {
                // then it means that we're trying to remove the root
                node.val = null;
                return true;
            }
        }
        return false;
    }

    /**
     * (Helper function) which finds a String value from the binary search tree
     *
     * @param val       the value to find in the binary search tree
     * @return boolean  true if successfully deleted, else false
     */
    public boolean find(Comparable val) {
        return find(root, val);
    }

    /**
     * Finds a value from the binary search tree
     *
     * @param val          the value to find in the binary search tree
     * @return true/false  true if successfully found, else false
     */
    public boolean find(Node node, Comparable val) {
        if (node == null || node.val == null) {
            return false;
        } else if (val.compareTo(node.val) == 0) {
            return true;
        } else if (val.compareTo(node.val) > 0) {
            return find(node.right, val);
        } else if (val.compareTo(node.val) < 0) {
            return find(node.left, val);
        } else {
            return false;
        }
    }

    /**
     * (Helper function) which returns a String representation of the binary search tree
     *
     * @return String   which returns a String representation of the binary search tree
     */
    public String toString() {
        return "\tValues stores: " + toString(root);
    }

    /**
     * Returns a string representation of the binary search tree recursively
     *
     * @param node the node to find in the binary search tree
     * @return String       which returns a String representation of the binary search tree
     */
    public String toString(Node node) {
        String nodeLeft;
        String nodeRight;
        if (node != null) {
            if (node.left == null) {
                nodeLeft = " null ";
            } else {
                nodeLeft = " ";
            }
            if (node.right == null) {
                nodeRight = " null ";
            } else {
                nodeRight = " ";
            }
        } else {
            return "";
        }
        return " (l: " + toString(node.left) + nodeLeft + node.val + " r: " + toString(node.right) + nodeRight + ")";
    }
}