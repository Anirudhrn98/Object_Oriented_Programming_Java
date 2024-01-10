import java.util.ArrayList;
import java.util.Date;

public class Test {
    public static <T extends Comparable<T>> void testIt(ArrayList<T> Insert) {
        SortedStorageSetWithNulls<T> storage = new SortedStorageSetWithNulls<T>();
        for (T item : Insert) {
            storage.add(item);
        }
        for (T item : Insert) {
            storage.find(item);
        }
        System.out.println(storage);

        for (T item : Insert) {
            storage.delete(item);
        }
        for (T item : Insert) {
            storage.find(item);
        }
        System.out.println(storage + "\n");
    }

    public static void main(String[] args) {
        // Integer
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(10);
        ints.add(20);
        ints.add(0);
        ints.add(40);
        Test.testIt(ints);

        // String
        ArrayList<String> strings = new ArrayList<>();
        strings.add("10");
        strings.add("20");
        strings.add("0");
        strings.add("40");
        Test.testIt(strings);

        // Human
        ArrayList<Human> humans = new ArrayList<>();
        humans.add(new Human(new Date(19971020), "Bardh", "0000000001"));
        humans.add(new Human(new Date(19981520), "Mallika", "0000000002"));
        humans.add(new Human(new Date(19980313), "Ani", "0000000003"));
        humans.add(new Human(new Date(19980313), "Ani", "0000000003"));
        Test.testIt(humans);

        // MusicsLP
        ArrayList<MusicLP> musics = new ArrayList<>();
        musics.add(new MusicLP(123, "Bardh", "RIT2021", 3.21F, 6));
        musics.add(new MusicLP(123, "Bardh", "RIT2021", 3.21F, 6));
        musics.add(new MusicLP(123, "Mallika", "RIT2021", 3.11F, 3));
        musics.add(new MusicLP(123, "Ani", "RIT2021", 3.1F, 9));
        musics.add(new MusicLP(123, "Drishya", "RIT2021", 2.41F, 7));
        Test.testIt(musics);

        // Address
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address(123, "RIT2021", "Rochester", "NY", 14623));
        addresses.add(new Address(234, "RIT2021", "Rochester", "NY", 14598));
        addresses.add(new Address(13, "RIT2021", "Rochester", "NY", 14345));
        addresses.add(new Address(24, "RIT2021", "Rochester", "NY", 14765));
        addresses.add(new Address(24, "RIT2021", "Rochester", "NY", 14765));
        addresses.add(new Address(52, "RIT2021", "Rochester", "NY", 14888));
        Test.testIt(addresses);

        // Integer
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(20.);
        doubles.add(10.);
        doubles.add(0.);
        doubles.add(40.);
        Test.testIt(doubles);


    }
}



