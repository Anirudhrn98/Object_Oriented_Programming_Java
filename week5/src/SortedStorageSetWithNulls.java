public class SortedStorageSetWithNulls extends SortedStorageSet{
    int childCntr;
    int nullUserInputs;
    int nullCntr = 1;

    /**
     * (Helper function) which adds a String value into the binary search tree
     *
     * @param val the string value to insert into the binary search tree
     * @return true/false  true if successfully added, else false
     */
    public boolean add(String val) {
        if (val != null && !find(val)) {
            root = add(root, val);
            return true;
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
    public Node add(Node node, String val) {
        if (node == null) {
            node = new Node(val);
            childCntr++;
            nullCntr++;
            return node;
        }
        // If the value is greater than then one in the node -> go to the right
        else if (val.compareTo(node.val) > 0) {
            node.right = add(node.right, val);
            return node;
        }
        // else if the value is lesser than then one in the node -> go to the left
        else {
            node.left = add(node.left, val);
            return node;
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
     * Checks to see if the user has inputted any "null" values
     *
     * @return true/false  true if the user has inputted some values, else false
     */
    public boolean includesNull() {
        return nullUserInputs > 0;
    }

    public boolean findRec(Node node, String val) {
        if (val == null) {
            return includesNull();
        } else if (node == null) {
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
}
