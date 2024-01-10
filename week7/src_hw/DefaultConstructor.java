public class DefaultConstructor {

    final int MINIMUM = 4;
    int thisNumbershouldAlwyasBeInitializedWithAvalueGreaterThan4;

    DefaultConstructor() {}

    DefaultConstructor(int aInt) {
        aInt = (aInt <= MINIMUM) ? MINIMUM : aInt;
        thisNumbershouldAlwyasBeInitializedWithAvalueGreaterThan4 = aInt;
    }

    public static void main(String args[]) {
        // DefaultConstructor aDefaultConstructor = new DefaultConstructor();
        DefaultConstructor aDefaultConstructor = new DefaultConstructor(3);
        System.out.println(aDefaultConstructor.thisNumbershouldAlwyasBeInitializedWithAvalueGreaterThan4);
    }
}

class SubClass extends DefaultConstructor {
    SubClass() {
        System.out.println(" SubClass()");
    }

    public static void main(String args[]) {
        SubClass aSubClass = new SubClass();
    }
}
