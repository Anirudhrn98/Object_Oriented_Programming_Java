class  B {

    int    aInstanceInt;

    void  aInstanceIntSet(int aInstanceInt) {
        this.aInstanceInt =  aInstanceInt;
    }
    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()	{
        return "B: " + aInstanceIntGet();
    }
}

public class  C extends B {

    int    aInstanceInt;

    static C aC;
    static B aB;

    void  aInstanceIntSet(int aInstanceInt) {
        this.aInstanceInt =  aInstanceInt;
    }
    int  aInstanceIntGet() {
        return aInstanceInt;
    }
    public String toString()	{
        return "C: " + aInstanceIntGet();
    }
    public static void print(String location)	{
        System.out.println(location);
        System.out.println("	" + aB);
        System.out.println("	" + aC);
        System.out.println("	" + aB.aInstanceInt);
        System.out.println("	" + aC.aInstanceInt);
    }
    public static void main(String args[] )       {
        aC = new C();
        aB = (B)aC;

        aB.aInstanceIntSet(1);
        aB.aInstanceInt =  3;
        print("1: ");
    }
}
