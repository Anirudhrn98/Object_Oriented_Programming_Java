public class S {

    public int intS;        // what is the value of intS?

    public S() {
        System.out.println("S()");
    }

    public S method(int x) {
        intS = x;
        System.out.println("S method(int x)");
        return this;
    }

    public String toString() {
        return "S: " + intS;
    }

    public static void main(String args[]) {
        System.out.println("new S()     " + new S());
        S s = new S();
        S a = s.method(1);
        System.out.println("s.method(1)     " + a);

    }
}
