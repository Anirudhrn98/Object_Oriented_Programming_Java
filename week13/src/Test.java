public class Test {
    public static void main(String args[]) {
        Storage theStorage = new Storage();
        int soManyP = 5;
        int soManyC = 5;

        System.out.println("# producer = " + soManyP);
        System.out.println("# consumer = " + soManyC);


        for (int id = 1; id <= soManyP; id++) {
            new MatchProducer(id, theStorage).start();
        }

        for (int id = 1; id <= soManyP; id++) {
            new BoxProducer(id, theStorage).start();
        }

        for (int id = 1; id <= soManyC; id++) {
            new Consumer(id, theStorage).start();
        }
    }
} 
