public class D {

    public int instanceV = 1;
    public int asd = 1;

    public void set(int value) {
        instanceV = value;
    }

    public String get() {
        return "D: " + instanceV;
    }

    public String toString() {
        return "D: " + instanceV;
    }

    public void asd() {
        asd=2;
        System.out.println("Hello from D.");
    }

    public static void main(String args[]) {
        DDD aDDD = new DDD();
        DD aDD = (DD) aDDD;
        D aD = (D) aDD;
        Object o = (Object) aD;
        int counter = 1;
        System.out.println(counter++ + "       : " + o);
        System.out.println(counter++ + "       : " + aDD);
        System.out.println(counter++ + "       : " + aD.get());
        aD.asd();
        System.out.println(counter++ + "       : " + aDD.asd);

//        aD.set(22);
//        System.out.println(counter++ + "       : " + aD.get());
//        aD.instanceV = 33;
//        System.out.println(counter++ + "       : " + aDDD.instanceV);
//        System.out.println(counter++ + "       : " + aD.get());
//        System.out.println(counter++ + "       : " + aDD.get());
//        System.out.println(counter++ + "       : " + aDDD.get());
////        aDD.instanceV = 33;
////        System.out.println(counter++ + "       : " + aDD.get());
//        System.out.println(counter++ + "       : " + aDD.instanceV);
//        System.out.println(counter++ + "       : " + aDD == o);
//        System.out.println(counter++ + "       : " + aDD.equals(o));
    }
}

class DD extends D {

    public int instanceV = 2;

    public void set(int value) {
        instanceV = value;
    }

    public String get() {
        return "DD: " + instanceV;
    }

    public String toString() {
        return "DD: " + instanceV;
    }
}

class DDD extends DD {

    public int instanceV = 3;

    public void set(int value) {
        instanceV = value;
    }

    public String get() {
        return "DDD: " + instanceV;
    }

    public void asd() {
        System.out.println("Hello from DDD.");
    }

    public String toString() {
        return "DDD: " + instanceV;
    }
}