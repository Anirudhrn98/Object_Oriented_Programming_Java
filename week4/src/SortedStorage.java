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
public class SortedStorage {
    Node root = null;
    int childCntr;
    int nullUserInputs;
    int nullCntr = 1;
    boolean[] asd = new boolean[Integer.MAX_VALUE / 10];

    /**
     * (Helper function) which adds a String value into the binary search tree
     *
     * @param val the string value to insert into the binary search tree
     * @return true/false  true if successfully added, else false
     */
    public boolean add(String val) {
        if (val != null && !asd[Integer.parseInt(val)]) {
            root = addRec(root, val);
            asd[Integer.parseInt(val)] = true;
            return asd[Integer.parseInt(val)];
        } else if (val == null && nullCntr > 0) {
            nullUserInputs++;
            nullCntr--;
            return true;
        } else {
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
    public Node addRec(Node node, String val) {
        if (node == null) {
            node = new Node(val);
            childCntr++;
            nullCntr++;
            return node;
            // If the value is greater than then one in the node -> go to the right
        } else if (val.compareTo(node.val) > 0) {
            node.right = addRec(node.right, val);
            return node;
            // If the value is lesser than then one in the node -> go to the left
        } else if (val.compareTo(node.val) < 0) {
            node.left = addRec(node.left, val);
            return node;
        } else {
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
        if (val == null && nullUserInputs > 0) {
            nullUserInputs--;
            nullCntr++;
            return true;
        } else if (val == null && nullUserInputs == 0) {
            return false;
        } else if (root.val == null) {
            return false;
        } else {
            boolean deletedNode = deleteRec(root, val);
            if (deletedNode) {
                nullCntr--;
                childCntr--;
            }
            return deletedNode;
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
                node.left = node.right.right == null ? node.right.left : node.right.right;
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

            // Else if value less than node.val (check to the left)
        } else if (val.compareTo(node.val) < 0) {
            deleteRec(node.left, val);

            // Else it means that the value is the same
        } else {
            if (node.left != null) {
                String temp = minValL(node.left);
                deleteRec(node, temp);
                node.val = temp;
                return true;
            } else if (node.right != null) {
                String temp = minValR(node.right);
                deleteRec(node, temp);
                node.val = temp;
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
        if (val.compareTo(node.val) == 0) {
            return true;
        } else if (val.compareTo(node.val) > 0) {
            return findRec(node.right, val);
        } else if (val.compareTo(node.val) < 0) {
            return findRec(node.left, val);
        }
        return false;
    }

    /**
     * Checks to see if the user has inputted any "null" values
     *
     * @return true/false  true if the user has inputted some values, else false
     */
    public boolean includesNull() {
        return nullUserInputs > 0;
    }

    /**
     * (Helper function) which returns a String representation of the binary search tree
     *
     * @return String   which returns a String representation of the binary search tree
     */
    public String toString() {
        System.out.println("\tincludes so many null values = " + nullUserInputs);
        return "\tValues stores: (" + toString(root) + ")";
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
