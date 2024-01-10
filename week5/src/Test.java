public class Test {
    public static void testIt(String what, SortedStorage aSortedStorage, boolean dealWithNull) {

        String toInsert[] = {"3", "3", "2", "4", null, null};

        System.out.println(what + ":  ");
        for (int index = 0; index < toInsert.length; index++) {
            if ((toInsert[index] != null) || (dealWithNull))
                System.out.println("	add(" + toInsert[index] + "): " + aSortedStorage.add(toInsert[index]));
        }
        for (int index = 0; index < toInsert.length; index++) {
            if ((toInsert[index] != null) || (dealWithNull))
                System.out.println("	find(" + toInsert[index] + "): " + aSortedStorage.find(toInsert[index]));
        }
        System.out.println("	" + aSortedStorage);
        System.out.println("---------------------------------------");

        for (int index = 0; index < toInsert.length; index++) {
            if ((toInsert[index] != null) || (dealWithNull))
                System.out.println("delete(" + toInsert[index] + "): " + aSortedStorage.delete(toInsert[index]));
        }

        System.out.println("All should be deleted");
        System.out.println("	" + aSortedStorage);
    }

    public static void main(String args[]) {
        SortedStorage aSortedStorage = new SortedStorage();
        testIt("SortedStorage", aSortedStorage, false);

        SortedStorageSet aSortedStorageSet = new SortedStorageSet();
        testIt("SortedStorageSet", aSortedStorageSet, false);

        SortedStorageSetWithNulls aSortedStorageSetWithNulls = new SortedStorageSetWithNulls();
        testIt("SortedStorageSetWithNulls", aSortedStorageSetWithNulls, true);
    }
}

