public class SortedStorageSet extends SortedStorage{
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
}
