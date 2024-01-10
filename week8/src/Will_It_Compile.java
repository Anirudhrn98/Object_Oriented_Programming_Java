import java.util.Arrays;

class Yellow {
    private String yellowPrivate = "yellowPrivate";

    public static void main(String args[]) {

        System.out.println(new Yellow().yellowPrivate);
    }
}

class Red {
    public static void main(String[] args) {
        Yellow yellow = new Yellow();
//        yellow.yellowPrivate;
    }
}

class Blue {
    private String redPrivate = "redPrivate";
}


class Green {
    private String greenPrivate = "greenPrivate";

//    public boolean isLess(Red aRed) {
//        return greenPrivate == aRed.redPrivate; // redPrivate is a private variable, we can't access those outside class
//    }

    public static void main(String args[]) {
        Red aRed = new Red();
        Green aGreen = new Green();
//        aGreen.isLess(aRed);
    }
}

class Coke {
    private String cokePrivate = "cokePrivate";
    private String s;
    private static String cokePrivateS = "cokePrivateS";

    public void m() {
        cokePrivate = "java";
    }

    public void change(String cokePrivate) {
        cokePrivate = "hello";
    }

    public void print() {
        System.out.println("1. cokePrivate  = " + cokePrivate);
        System.out.println("2. cokePrivateS = " + cokePrivateS);
        System.out.println("----------------------------------");
    }

    public static void main(String args[]) {
        Coke aCoke = new Coke();
        aCoke.m();
        aCoke.print();
        aCoke.change("t");
        aCoke.print();
    }
}


class H {
    private String hPrivate = "hPrivate";
    private static String hPrivateS = "hPrivateS";

//    public H(){
//         this = this("RIT"); // java: cannot assign to 'this'
//    }

    public H(String hPrivate) {
        this.hPrivate = hPrivate;
    }

    public void knete() {
    }

    public void print(String tag) {
        System.out.println(tag + "hPrivate  = " + hPrivate);
    }

//    public static void main(String args[])	{
//        H aH = new H();
//        aH.print("1.");
//        aH.knete();
//        aH.print("2.");
//    }
}

class Bauer {
    private String bauerPrivate = "bauerPrivate";
    private static String bauerPrivateS = "bauerPrivateS";

    public Bauer() {
    }

    public Bauer(String bauerPrivate) {
        this.bauerPrivate = bauerPrivate;
    }

//    public void knete()	{
//        this = new Bauer("RIT");  // java: cannot assign to 'this'
//    }

    public static void main(String args[]) {
        Bauer aBauer = new Bauer();
    }
}

class Oh {
    public static void intMethod(int intArg) {
        intArg = 22;
    }

    public static void intArrayMethod(int[] intArg) {
        intArg[0] = 22;
    }



    public static void main(String[] args) {
        int intVariable = 2;
        int[] intArray = {2, 2, 2};
//        int[] intArray2 = new int[3];

        System.out.println("1. " + intVariable);
        intMethod(intVariable);
        System.out.println("2. " + intVariable);

        System.out.println("3. " + intArray[0]);
        intArrayMethod(intArray); // https://stackoverflow.com/questions/21653048/changing-array-in-method-changes-array-outside
                                  // http://www.javadude.com/articles/passbyvalue.htm
        System.out.println("4. " + intArray[0]);


        System.out.println("5. " + intArray[1]);
        intMethod(intVariable);
        System.out.println("6. " + intArray[1]);
    }
}

//class LitmusTest {
//
//    public static void swap(int a, int b) {
//        int temp = a;
//        a = b;
//        b = temp;
//    }
//
//    public static void main(String[] args) {
//        int a = 1;
//        int b = 2;
//        System.out.println(a + ", " + b);
//        LitmusTest.swap(a, b);
//        System.out.println(a + ", " + b);
//
//        System.out.println("----------------------------------");
//
//        int[] intArray = {1, 2, 3};
//        System.out.println(intArray[0] + ", " + intArray[1]);
//        LitmusTest.swap(intArray[0], intArray[1]);
//        System.out.println(intArray[0] + ", " + intArray[1]);
//
//
//    }
//}
// =====================================

interface Ic {
    static int iStatic = 1;
    public int iPublic = 6;

    public void furyo();
}

//class Ic1 implements Ic {
//
//    public void furyo() {
////        iPublic = 4;
////        iStatic = 2;
//    }
//}

// =====================================
interface I {
    static int iStatic = 1;
    public int iPublic = 6;
}
//    private int iPrivate = 6; // Interfaces can only have public, default and static variables/ methods
//                              // not private or protected
//    protected int iProtected = 6; 

class Bier {
    private int bier;

    public Bier() {
        bier++;
    }

    public void print() {
        System.out.println("bier  = " + bier);
    }

    public static void main(String args[]) {
        Bier aBier = new Bier();
        for (int i = 0; i < 1000; i++)
            aBier = new Bier();
        aBier.print();
    }
}


 class SEqual {

    public static void main(String args[]) {
        String s = "a";
        String b = "a";

        if (s == b)
            System.out.println("1. s == b ");
        if (s.equals(b))
            System.out.println("1. s.equals(b) ");

        if ("Furyo" == "Furyo")
            System.out.println("2. \"Furyo\" == \"Furyo\" ");

        s = new String("a");
        b = new String("a");

        if (s == b)
            System.out.println("3. s == b ");
        if (s.equals(b))
            System.out.println("4. s.equals(b) ");
    }

}