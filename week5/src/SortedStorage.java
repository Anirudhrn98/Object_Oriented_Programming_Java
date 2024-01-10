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
 * This is an implementation of Binary Search Tree, which uses String as input
 *
 * @author      Anirudh Narayanan
 * @author      Bardh Rushiti
 */
public class SortedStorage{
    Node root = null;

    public int compareTo(String a){
        return 1;
    }

    /**
     * (Helper function) which adds a String value into the binary search tree
     *
     * @param val the string value to insert into the binary search tree
     * @return true/false  true if successfully added, else false
     */
    public boolean add(String val) {
        if (val != null) {
            root = add(root, val);
            return true;
        }  else {
            return false;
        }
    }

    /**
     * Adds a String value into the binary search tree recursively
     *
     * @param node the binary search tree where we want to insert the String value
     * @param val  the string value to insert into the binary search tree
     * @return node         updated node
     */
    public Node add(Node node, String val) {
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
    public String minValR(Node node) {
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
    public String minValL(Node node) {
        if (node.right == null) {
            return node.val;
        } else {
            return minValR(node.right);
        }
    }

    /**
     * (Helper function) which deletes a String value from the binary search tree
     *
     * @param val the string value to delete into the binary search tree
     * @return true/false  true if successfully deleted, else false
     */
    public boolean delete(String val) {
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
     * @param val  the string value to delete from the binary search tree
     * @return true/false  true if successfully deleted, else false
     */
    public boolean deleteRec(Node node, String val) {
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
                String temp = minValR(node.left.right);
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
                String temp = minValR(node.right.right);
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
     * @param val the string value to find in the binary search tree
     * @return true/false  true if successfully deleted, else false
     */
    public boolean find(String val) {
        return findRec(root, val);
    }

    /**
     * Finds a String value from the binary search tree
     *
     * @param val the string value to find in the binary search tree
     * @return true/false  true if successfully found, else false
     */
    public boolean findRec(Node node, String val) {
        if (node == null) {
            return false;
        } else if (val.equals(node.val)) {
            return true;
        } else if (val.compareTo(node.val) > 0) {
            return findRec(node.right, val);
        } else if (val.compareTo(node.val) < 0) {
            return findRec(node.left, val);
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
     * @param node the string value to find in the binary search tree
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