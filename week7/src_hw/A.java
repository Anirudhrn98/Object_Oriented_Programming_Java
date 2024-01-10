abstract class A {

    public int x;

    abstract public A a(int x);

    public A aa(int x)	{
        System.out.print("- in A!aa");
        return this;
    }

}

class AX extends A {

    public int x;

    public A a(int x) {
        System.out.print("= in AX!a");
        return this;
    }
}

class AXX extends A {

    public int x;

    public A a(int x)	{
        System.out.println("    in AX!a");
        return this;
    }

    public static void main(String args[])	{
        AX  aAX   = new AX();
        AXX aAXX  = new AXX();

        System.out.println("aAX.a(42)	" + aAX.a(42) );
        System.out.println("aAXX.a(43)	" + aAXX.a(43) );
    }
}
