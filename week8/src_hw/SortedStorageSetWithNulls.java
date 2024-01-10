public class SortedStorageSetWithNulls<T extends Comparable<T>> extends SortedStorage<T> {
    int childCntr;
    int nullUserInputs;
    int nullCntr = 1;

    /**
     * (Helper function) which adds a value into the binary search tree
     *
     * @param val the value to insert into the binary search tree
     * @return true/false  true if successfully added, else false
     */
    @Override
    public boolean add(T val) {
        if (val != null && !find(val)) {
            root = super.add(root, val);
            childCntr++;
            nullCntr++;
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
     * (Helper function) which deletes a value from the binary search tree
     *
     * @param val the value to delete into the binary search tree
     * @return true/false  true if successfully deleted, else false
     */
    @Override
    public boolean delete(T val) {
        if (val == null && nullUserInputs > 0) {
            nullUserInputs--;
            nullCntr++;
            return true;
        } else if (val == null && nullUserInputs == 0) {
            return false;
        } else if (root.val == null) {
            return false;
        } else {
            boolean deletedNode = super.deleteRec(root, val);
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
    @Override
    public boolean includesNull() {
        return nullUserInputs > 0;
    }

    /**
     * Finds a value from the binary search tree
     *
     * @param val the value to find in the binary search tree
     */
    @Override
    public boolean find(T val) {
        if (val == null) {
            return includesNull();
        } else {
            return super.find(root, val);
        }
    }
}
