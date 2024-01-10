import java.util.ArrayList;
import java.util.List;

public class Interference {

    public static class Thing<T> {

        private T aType;

        public void set(T aType) {
            this.aType = aType;
        }

        public T get() {
            return aType;
        }
    }

    public static class ThingThing<T> extends Thing<T> {
        public void set(T aType) {
            super.set(aType);
        }
    }

    public static <U> void insertThing(U u, List<Thing<U>> things) {
        Thing<U> thing = new Thing<>();
        thing.set(u);
        things.add(thing);
    }

    public static <U> void outputThinges(java.util.List<Thing<U>> things) {
        int counter = 0;
        for (Thing<U> thing : things) {
            U thingContents = thing.get();
            System.out.println("Thing #" + counter + " = " + thingContents);
            counter++;
        }
        System.out.println();

    }

    public static void main(String[] args) {
        ArrayList<Thing<Integer>> listOfIntegerThinges = new ArrayList<>();
        Interference.<Integer>insertThing(Integer.valueOf(10), listOfIntegerThinges);
        Interference.insertThing(Integer.valueOf(20), listOfIntegerThinges);
        Interference.insertThing(Integer.valueOf(30), listOfIntegerThinges);
        Interference.outputThinges(listOfIntegerThinges);

        ArrayList<Thing<String>> listOfStrings = new ArrayList<>();
        Interference.<String>insertThing("b", listOfStrings);
        Interference.<String>insertThing("a", listOfStrings);
        Interference.<String>insertThing("30", listOfStrings);
        Interference.outputThinges(listOfStrings);

        // Why does the following line not compile?
//        Interference.<String>insertThing(Integer.valueOf(30), listOfIntegerThinges);
//        Interference.outputThinges(listOfIntegerThinges);

    }
}
