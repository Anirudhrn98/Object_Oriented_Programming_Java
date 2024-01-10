class NestedClassEx {

    public int inNestedClass;

    void inNestedClass() {
        System.out.println("NestedClass!inNestedClass");
        (new AinnerClass()).aInnerClassM2();
    }

    static class AstaticClass {
        static void aStaticClassM1() {
            System.out.println("AstaticClass!aStaticClassM1");
        }

        void aStaticClassM2() {
            System.out.println("AstaticClass!aStaticClassM2");
        }
    }

    class AinnerClass {
   /*
           static void aInnerClassM1()     {
                   System.out.println("AinnerClass!aInnerClassM1");
           }
      NestedClassEx.java:15: inner classes cannot have static declarations
           static void aInnerClassM1()     {
    */

        void aInnerClassM2() {
            System.out.println("AinnerClass!aInnerClassM2");
        }
    }

    public static void main(String args[]) {

        AstaticClass.aStaticClassM1();
        (new AstaticClass()).aStaticClassM2();

        (new NestedClassEx()).inNestedClass();
        // (new AinnerClass()).aInnerClassM2();
    }
}
