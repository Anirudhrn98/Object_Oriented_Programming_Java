public class TestComparable {
    public static void testIt(Comparable[] toInsert) {

        SortedStorageSetWithNulls aStorage = new SortedStorageSetWithNulls();

        if ( ! aStorage.add(toInsert[0]) )
            System.out.println("	add failed");
        if (   aStorage.add(toInsert[1]) )
            System.out.println("	add failed");
        if ( !  aStorage.find(toInsert[1]) )
            System.out.println("	find failed");
        if ( !  aStorage.delete(toInsert[1]) )
            System.out.println("	delete failed");
        if (    aStorage.delete(toInsert[1]) )
            System.out.println("	delete failed");
        if ( !  aStorage.add(null) )
            System.out.println("	add null failed");
        if ( !  aStorage.add(null) )
            System.out.println("	add null failed");
        if ( !  aStorage.delete(null) )
            System.out.println("	delete null failed");
        if ( !  aStorage.delete(null) )
            System.out.println("	delete null failed");
        if (   aStorage.delete(null) )
            System.out.println("	delete null failed");
        if ( !  aStorage.toString().equals("null") )
            System.out.println("	tree should be empty");

    }
    public static void main(String args[] )	{
        String strings[] = { "3", "3" };
        System.out.println("Strings");
        testIt(strings);
        Long longs[] = { Long.valueOf(3), Long.valueOf(3) };
        System.out.println("Longs");
        testIt(longs);
        Integer ints[] = { Integer.valueOf(3), Integer.valueOf(3) };
        System.out.println("Ints");
        testIt(ints);
    }
}

