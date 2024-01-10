public class SortedStorageSet extends SortedStorage{
    /**
     * (Helper function) which adds a value into the binary search tree
     *
     * @param val the value to insert into the binary search tree
     * @return true/false  true if successfully added, else false
     */
    public boolean add(Comparable val) {
        if (val != null && !find(val)) {
            root = super.add(root, val);
            return true;
        } else {
            return false;
        }
    }
}
