abstract class A {

    public abstract int isAbstract();

    public A concrete() {
        System.out.println("A!concrete()");
        return this;
    }
//    public int onlyC(){return 1;}
}

class B extends A {
    public B() {
        System.out.println("    B()");
    }

    public int isAbstract() {
        System.out.println("	B!isAbstract()");
        return 1;
    }

    public A concrete() {
        System.out.println("B!concrete()");
        return this;
    }

}


class C extends B {

    public C() {
        System.out.println("    C()");
    }

    public int isAbstract() {
        System.out.println("	C!isAbstract()");
        return 2;
    }

    // Check the difference with and without the method commented down below!
    public B concrete() {
        System.out.println("C!concrete()");
        return this;
    }

    public int onlyC() {
        System.out.println("onlyC");
        return 1;
    }

    public static void main(String args[]) {
//        B aB = new B();
//        C aC = new C();
//        B anewC = new C(); // At the end of the day, anewC is a C object, because we are defining a new C()
//
//        aB.isAbstract();
//        aC.isAbstract();
//        anewC.isAbstract();
//
//        (aB.concrete()).isAbstract();
//        (aC.concrete()).isAbstract();
//        (anewC.concrete()).isAbstract();
//        anewC.onlyC();

        // =============================================
        A aA = new C();
//        aA.onlyC(); // This will not work, because aA has variable type class A, and
        // class A doesn't have a method onlyC()
        ((C) aA).onlyC(); // However, because of down-casting we are able to call the method
        // onlyC(). We are casting aA to a class C variable type, and that's why it works


    }
}
