/**
 * This class implements a inner class.
 *
 * @author Axel T. Schreiner
 * @author hp bischof
 * <p>
 * Revisions:
 * $Log$
 * @version $Id$
 */
class InnerClass {
    static class A {
        static void hi() {
            System.err.println("4.hi");
        }
    }

    class B {
        void hi() {
            System.err.println("3.hi");
        }
    }

    void hi() {
        class C {
            void hi() {
                System.err.println("2.hi");
            }
        }
        Object o = new C() { // TODO: How can we, by creating an object, also create a body there?
            void hi() {
                System.err.println("1.hi");
            }
        };
        ((C) o).hi();
        new C().hi();
        new B().hi();
    }

    static public void main(String args[]) {
        new InnerClass().hi();
        A.hi();
    }
}
