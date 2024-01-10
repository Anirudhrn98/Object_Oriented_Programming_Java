interface BB {

    void a();

    default void b() {
        System.out.println("B.b()");
    }
}

interface AA {

    default void a(){
        System.out.println("A.a()");
    }

    default void b() {
        System.out.println("A.b()");
    }
        /*
     AAandBBuse.java:1: error: class AAandBBuse inherits unrelated defaults for b() from types AA and BB
     public class AAandBBuse implements AA, BB {
     */
}

public class AAandBBuse implements AA, BB {
    public void a() {           // interface
        System.out.println("AAandBBuse!a");
        AA.super.b();
    }

    public void b(){ // delete this method and read the java complaining about it.
        BB.super.b();
    }


    public static void main(String[] argv) {
        new AAandBBuse().a();
        new AAandBBuse().b();
    }
}


