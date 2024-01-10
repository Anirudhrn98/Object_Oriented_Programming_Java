public interface AInterface {
    static int A = 1;
    int AB = 1;

    public void a();
    default void b(){};
    default double c(){return 0.;};
}

interface BInterface{
    static int B = 2;
    int AB = 2;

    public void b();
}

interface CInterface extends BInterface, AInterface{

    @Override
    default void a() {
        System.out.println("Ola!");
    }

    @Override
    default void b() {
        AInterface.super.b();
    }

    @Override
    default double c() {
        return AInterface.super.c();
    }

}
