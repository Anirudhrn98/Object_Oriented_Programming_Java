
public abstract class NodeTest extends Number {

    public byte byteValue () {
        return (byte)longValue();
    }

    public short shortValue () {
        return (short)longValue();
    }

    public int intValue () {
        return (int)longValue();
    }

    public float floatValue () {
        return (float)doubleValue();
    }

    protected class MakingSureStaticClass{
        static void methodA(){
            System.out.println("Static method inside MakingSureStatic");
        }
    }

    protected abstract static class Binary extends NodeTest {

        protected Number left;

        protected Number right;

        protected Binary () {}

        protected Binary (Number left, Number right) {
            this.left = left; this.right = right;
        }
    }

    protected abstract static class Unary extends NodeTest {

        protected Number tree;

        protected Unary (){}

        protected Unary (Number tree) {
            this.tree = tree;
        }
    }

    public static class Add extends Binary {

        public Add (Number left, Number right) {
            super(left, right);
        }

        public long longValue () {
            return left.longValue() + right.longValue();
        }

        public double doubleValue () {
            return left.doubleValue() + right.doubleValue();
        }
    }

    public static class Mul extends Binary {
        public Mul (Number left, Number right) {
            super(left, right);
        }

        public long longValue () {
            return left.longValue() * right.longValue();
        }

        public double doubleValue () {
            return left.doubleValue() * right.doubleValue();
        }
    }

    public static class Minus extends Unary {
        public Minus (Number tree) {
            super(tree);
        }

        public long longValue () {
            return - tree.longValue();
        }

        public double doubleValue () {
            return - tree.doubleValue();
        }
    }

    public static void main(String args[])	{
//        MakingSureStaticClass mkscls = new MakingSureStaticClass(); // Error: Inner class cannot have static members

        NodeTest aNode = new NodeTest.Add(new Double(1), new Double(2));
        System.out.println("i) aNode = " + aNode.floatValue() );

        aNode = new NodeTest.Add(
                new NodeTest.Mul( new Double(2), new Double(3)),
                new NodeTest.Mul( new Double(4), new Double(5))
        );
        System.out.println("ii) aNode = " + aNode.floatValue() );
        aNode = new NodeTest.Add(
                new Double(1),
                new NodeTest.Minus(new Double(2))
        );
        System.out.println("iii) aNode = " + aNode.floatValue() );
    }
}
