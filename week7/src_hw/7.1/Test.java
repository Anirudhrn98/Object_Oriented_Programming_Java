public class Test {
    public static void testIt(Comparable[] Insert) {
        SortedStorageSetWithNulls storage = new SortedStorageSetWithNulls();
        for (Comparable item : Insert) {
            storage.add(item);
        }
        for (Comparable item : Insert) {
            storage.find(item);
        }
        System.out.println(storage);

        for (Comparable item : Insert) {
            storage.delete(item);
        }
        for (Comparable item : Insert) {
            storage.find(item);
        }
        System.out.println(storage + "\n");


    }

    public static void main(String[] args) {

        // Living Thing
        LivingThing Ash = new LivingThing("Ash", (float) 2.3, 22);
        LivingThing Shrunk = new LivingThing("Shrunk", (float) 1.2, 30);
        LivingThing Blanco = new LivingThing("Blanco", (float) 5.6, 23);

        LivingThing[] livingThing1 = {Ash, Shrunk, Blanco};
        testIt(livingThing1);


        // MOUSE
        Mouse Linguini = new Mouse("Linguini", (float) 45);
        Mouse Colette = new Mouse("Colette", (float) 23);
        Mouse Emile = new Mouse("Emile", (float) 34);

        Mouse[] mouse1 = {Linguini, Colette, Emile};
        testIt(mouse1);

        //ANT
        Ant redant = new Ant("Red ant", (float) 2);
        Ant blackant = new Ant("Black ant", (float) 4);
        Ant fireant = new Ant("Fire ant", (float) 3);

        Ant[] ant1 = {redant, blackant, fireant};
        testIt(ant1);

        //Fish
        Fish Marlin = new Fish("Marlin", (float) 5);
        Fish Dory = new Fish("Dory", (float) 12);
        Fish Bubbles = new Fish("Bubbles", (float) 7);

        Fish[] fish1 = {Marlin, Dory, Bubbles};
        testIt(fish1);

    }
}



