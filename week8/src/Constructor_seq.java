class X_1 {
    int x1 = 1;
    int x2 = 11;

    public X_1() {
        System.out.println("    in X_1!X_1()");
    }

    public X_1(int x) {
        System.out.println("    in X_1!X_1(int x)");
    }

    public X_1(int x, int y) {
        System.out.println("    in X_1!X_1(int x, int y)");
    }

    public void call() {
        System.out.println("\tcall_X_1");
    }
}

class X_2 extends X_1 {
    int x1 = 2;
    int x2 = 22;


    public void call() {
        System.out.println(x1);
        System.out.println(this.x1);
        System.out.println(super.x1);

        System.out.println(x2);
        System.out.println(this.x2);
        System.out.println(super.x2);
    }

    public void asd(){
        call();
        super.call();
    }

    public X_2() {
        // super();	// default
        System.out.println("	in X_2!X_2()");
    }

    public X_2(int x) {
        // super();	// default
        super.call();
        System.out.println("	in X_2!X_2(int x)");
    }

    public X_2(int x, int y) {
        // super();	// default
        System.out.println("	in X_2!X_2(int x, int y)");
    }


    public static void main(String args[]) {
        X_2 aX_2 = new X_2();
        X_2 aaX_2 = new X_2(3);
        X_2 aaaX_2 = new X_2(3, 3);
        System.out.println("=====================");
        aX_2.call();
        System.out.println("=====================");
        aX_2.asd();
    }
}

public class Constructor_seq {
}
