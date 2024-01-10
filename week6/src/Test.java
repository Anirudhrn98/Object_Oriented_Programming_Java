/**
 * This test environment implementation is based on the week 5 test environment provided by H.P.
 */

public class Test {
    public static void test(Comparable[] toInsert, String what, SortedStorage aSortedStorage, boolean dealWithNull) {
        System.out.println("\t" + what + ":  ");
        for (Comparable comparable : toInsert) {
            if ((comparable != null) || (dealWithNull)) {
                System.out.println("\t\tadd(" + comparable + "): " + aSortedStorage.add(comparable));
            }
        }
        for (Comparable comparable : toInsert) {
            if ((comparable != null) || (dealWithNull)) {
                System.out.println("\t\tfind(" + comparable + "): " + aSortedStorage.find(comparable));
            }
        }
        System.out.println("\t---------------------------------------");
        System.out.println("\t	" + aSortedStorage);
        System.out.println("\t---------------------------------------");

        for (Comparable comparable : toInsert) {
            if ((comparable != null) || (dealWithNull)) {
                System.out.println("\t\tdelete(" + comparable + "): " + aSortedStorage.delete(comparable));
            }
        }
        for (Comparable comparable : toInsert) {
            if ((comparable != null) || (dealWithNull)) {
                System.out.println("\t\tfind(" + comparable + "): " + aSortedStorage.find(comparable));
            }
        }
        System.out.println("\t---------------------------------------");
        System.out.println("\t" + aSortedStorage);
        System.out.println("\t---------------------------------------\n\n");
    }

    public static void main(String args[]) {
        String[] toInsertStr = {"3", "3", "2", "4", null, null};
        Long[] longs = {3L, 3L, 2L, 4L, null, null};
        Integer[] ints = {3, 3, 2, 4, null, null};

        SortedStorageSetWithNulls aSortedStorageSetWithNulls = new SortedStorageSetWithNulls();

        System.out.println("Stings:");
        test(toInsertStr, "SortedStorageSetWithNulls", aSortedStorageSetWithNulls, true);

//        aSortedStorageSetWithNulls = new SortedStorageSetWithNulls();
//
//        System.out.println("Longs:");
//        test(longs, "SortedStorageSetWithNulls", aSortedStorageSetWithNulls, true);
//
//        aSortedStorageSetWithNulls = new SortedStorageSetWithNulls();
//
//        System.out.println("Ints:");
//        test(ints, "SortedStorageSetWithNulls", aSortedStorageSetWithNulls, true);

    }
}

