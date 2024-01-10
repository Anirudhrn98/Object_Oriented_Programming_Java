import java.security.PrivilegedExceptionAction;

interface X {

    static double MIMUM_INCREASE = 1.6;    // %  final

    /*
     * Interface methods can't be native,
     * static, synchronized, final, private, or protected
       Abstract and native methods can't have a body.
     */
    static void volume() {
        System.out.println("xxxx");
    }
    default void volume2() {
        System.out.println("xxxx");
    }

    public void setPrice(int x);
}

class Example implements X{

    public void volume2(){

    }

    @Override
    public void setPrice(int x) {

    }

    public static void main(String[] args) {
        new Example().volume2();
    }
}

public interface InCommon {
    static double INCREASE       = 0.1;
    static double MINIMUM_VOLUME = 0;
    static double MAXIMUM_VOLUME = 10;
    static double DEFAULt_VOLUME = 3;

    public double getVolume();
    public boolean setVolume(double volumeValue);
    public boolean increaseVolumeBy(double deltaVolume);
}

class Phone implements InCommon {
    double currentVolume = 0;

    public boolean setVolume(double deltaVolume) {
        boolean rValue;
        if (rValue = (currentVolume + deltaVolume < MAXIMUM_VOLUME))
            currentVolume += deltaVolume;
        return rValue;
    }

    public double getVolume() {
        return currentVolume;
    }

    public boolean increaseVolumeBy(double deltaVolume) {
        setVolume(INCREASE);
        return false;
    }

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.setVolume(10);
        phone.increaseVolumeBy(0.3);
        System.out.println(phone.getVolume());
    }


/*
Phone.java:1: error: Phone is not abstract and does not override abstract method increaseVolumeBy(double) in InCommon
public class Phone implements InCommon {
*/
}


