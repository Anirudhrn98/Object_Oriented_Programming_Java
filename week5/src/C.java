class  A {

    int    aInstanceInt;

    A()	{
        aInstanceInt = 1;
    }
    int  aInstanceIntSet(int aInstanceInt) {
        this.aInstanceInt =  aInstanceInt;
        return aInstanceInt;
    }
    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()	{
        return "A: " + aInstanceIntGet();
    }
    public static void main(String args[] )       {
        A aA = new A();
        aA.aInstanceIntSet( 1 + aA.aInstanceIntSet(0));
        System.out.println(aA);
    }
}

class  B extends A {

    int    aInstanceInt;

    B()	{
        aInstanceInt = 2;
    }
    int  aInstanceIntSet(int aInstanceInt) {
        this.aInstanceInt =  aInstanceInt;
        return aInstanceInt;
    }
    int  aInstanceIntSet2(int aInstanceInt) {
        this.aInstanceInt =  aInstanceInt;
        return aInstanceInt;
    }

    int aInstanceB(){
        return 1;
    }

    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()	{
        return "B: " + aInstanceIntGet();
    }
    public static void main(String args[] )       {
        B aB = new B();
        aB.aInstanceIntSet( 1 + aB.aInstanceIntSet(0));
        System.out.println(aB);
    }
}

public class  C extends B {

    int    aInstanceInt;
    int    aInstanceInt2;

    static C aC;
    static B aB;
    static A aA;

    C()	{
        aInstanceInt = 3;
    }
    int  aInstanceIntSet(int aInstanceInt) {
        this.aInstanceInt =  aInstanceInt;
        return aInstanceInt;
    }
    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()	{
        return "C: " + aInstanceIntGet();
    }
    public static void print(String location)	{
        System.out.println(location);
        System.out.println("	" + aA);
        System.out.println("	" + aB);
        System.out.println("	" + aC);
        System.out.println("	" + aA.aInstanceInt);
        System.out.println("	" + aB.aInstanceInt);
        System.out.println("	" + aC.aInstanceInt);
    }
    public void aC(){
        System.out.println("We are in C");
    }
    public static void main(String args[] )       {
        aC = new C();
        aB = new B();
        aA = new A();

//        ((C) aB).aC();

//        aB = (B)aC;
//        aB.aC();
        aB = aC;
//        aB.aC();
        ((C) aB).aC(); // this is going to be compiled as a C object, but will not permanently chance type (B->C)
        C aBC = new C();
        aBC.aC();
//        aB.aC();


        aB.aInstanceIntSet(200);
        aA   = (A)aB;
        aA.aInstanceIntSet(100);
        print("1: ");

        aA.aInstanceInt = 42;
        aC.aInstanceInt = 44;
        print("2: ");

        aB.aInstanceInt = 43;
        print("3: ");

    }
}

