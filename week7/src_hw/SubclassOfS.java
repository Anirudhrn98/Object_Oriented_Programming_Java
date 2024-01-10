public class SubclassOfS extends S {

    public int intS;

    public SubclassOfS(int x) {
        System.out.println("SubclassOfS ()");
    }

    public S method(int x) {
        intS = x;
        System.out.println("S method(int x)");
        super.method(9);
        System.out.println("4. super: " + super.toString());
        super.intS = 4;
        System.out.println("5. super: " + super.toString());
        return this;
    }

    public String toString() {
        return "SSubclassOfS: " + intS;
    }


    public static void main(String args[]) {
        SubclassOfS aSubclassOfS = new SubclassOfS(1); // remove 1 and explore output
        S aS = aSubclassOfS.method(42);
        System.out.println(aS);
        System.out.println(aSubclassOfS);
        System.out.println("1. SubclassOfS!intS      = " + aSubclassOfS.intS);
        System.out.println("2. ((S)SubclassOfS)!intS = " + ((S) aSubclassOfS).intS);
 	    aS.method(3);		// <--- what is the problem here ...
        System.out.println(aS);
        System.out.println(aSubclassOfS);

    }
}
