import java.util.LinkedList;
import java.util.List;

public class Line {}
class Square extends Line{}
class Cube extends Square{}
class Cube3d extends Cube{}

// You have to declare and create a List Class which can store objects of only one kind of the above classes.
class OnlyLine{
    public static <T extends Line> void add(T object, List<T> list){
        if (Line.class.getName().equals(object.getClass().getName())){
            list.add(object);
        }
    }

    public static void main(String[] args) {
        List<Line> lines = new LinkedList<>();
        add(new Line(), lines);
        add(new Square(), lines);
        add(new Cube(), lines);
        add(new Cube3d(), lines);

        System.out.println(lines);
    }
}
class OnlySquare{
    public static <T extends Square> void add(T object, List<T> list){
        if (Square.class.getName().equals(object.getClass().getName())){
            list.add(object);
        }
    }

    public static void main(String[] args) {
        List<Square> squares = new LinkedList<>();
//        add(new Line(), squares);
        add(new Square(), squares);
        add(new Cube(), squares);
        add(new Cube3d(), squares);

        System.out.println(squares);
    }
}
class OnlyCube{
    public static <T extends Cube> void add(T object, List<T> list){
        if (Cube.class.getName().equals(object.getClass().getName())){
            list.add(object);
        }
    }

    public static void main(String[] args) {
        List<Cube> cubes = new LinkedList<>();
//        add(new Line(), cubes);
//        add(new Square(), cubes);
        add(new Cube(), cubes);
        add(new Cube3d(), cubes);

        System.out.println(cubes);
    }
}
class OnlyCube3d{
    public static <T extends Cube3d> void add(T object, List<T> list){
        if (Cube3d.class.getName().equals(object.getClass().getName())){
            list.add(object);
        }
    }

    public static void main(String[] args) {
        List<Cube3d> cubes3d = new LinkedList<>();
//        add(new Line(), cubes3d);
//        add(new Square(), cubes3d);
//        add(new Cube(), cubes3d);
        add(new Cube3d(), cubes3d);

        System.out.println(cubes3d);
    }
}

class CustomClass<T extends Line>{
    static List<Line> lineAndChildren = new LinkedList<>();
    static List<Square> squareAndChildren = new LinkedList<>();
    static List<Cube> cubeAndChildren = new LinkedList<>();
    static List<Cube3d> cube3dOnly = new LinkedList<>();

    public static <T extends Cube> void sum(List<Cube> cubeObjects, List<Cube3d> cube3dObjects){
    }

    public static void sum2(List<? super Square> superSquareObjects1, List<? super Square> superSquareObjects2){
    }

    public static <T extends Line> void print(List<T> objectT){
        for (Object o: objectT){
            System.out.println("Object: " + o + " of type " + o.getClass().getName());
        }
    }

    public static void main(String[] args) {
        Line   line   = new Line();
        Square square = new Square();
        Cube   cube   = new Cube();
        Cube3d cube3d = new Cube3d();

        // Can store object of the following class: Line, Square, Cube, 3dCube;
        lineAndChildren.add(line);
        lineAndChildren.add(square);
        lineAndChildren.add(cube);
        lineAndChildren.add(cube3d);

        // Can store object of the following class: Cube, 3dCube;
//        cubeAndChildren.add(line);
//        cubeAndChildren.add(square);
        cubeAndChildren.add(cube);
        cubeAndChildren.add(cube3d);

        // You have to create line method named sum which accepts as argument line list of Cubes and 3dCubes.
//        sum(lineAndChildren, cube3dOnly);
//        sum(cubeAndChildren, squareAndChildren);
        sum(cubeAndChildren, cube3dOnly);

        // You have to create line method named print which prints the elements of the list.
        print(lineAndChildren);

        // You have to create line method named sum which accepts as argument line list of Lines and Squares.
        sum2(lineAndChildren, squareAndChildren);
//        sum2(lineAndChildren, cubeAndChildren);
//        sum2(cube3dOnly, cubeAndChildren);

//        // TODO: Additional question to Abhishek
//        List<? super Square> asd = new LinkedList<>();
//        List<? extends Square> asd1 = new LinkedList<>();
//
//        asd.add(new Line());
//        asd.add(new Square());
//        asd.add(new Cube());
//        asd.add(new Cube3d());
//
//        asd1.add(null);
//        asd1.add(new Line());
//        asd1.add(new Square());
//        asd1.add(new Cube());
//        asd1.add(new Cube3d());
    }
}
