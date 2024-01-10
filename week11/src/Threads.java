class Thread_0 extends Thread {
    private String info;
    static Object o = new Object();

    public Thread_0(String info) {
        this.info = info;
    }

    public void run() {
        System.err.println(info + " ---> ");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }
        System.err.println(info + " <--- ");
    }

    public static void main(String args[]) {
        Thread_0 aT4_0 = new Thread_0("first");
        Thread_0 aT4_1 = new Thread_0("second");

        aT4_0.start();
        aT4_1.start();
    }
}


class Thread_1 extends Thread {

    private int info;
    static int x = 0;

    public Thread_1(int info) {
        this.info = info;
    }

    public void run() {
        if (info == 1) {
            x = 3;
        } else
            x = 1;
    }

    public static void main(String args[]) {
        Thread_1 aT1 = new Thread_1(1);
        Thread_1 aT2 = new Thread_1(2);
        aT2.start();
        try {
            sleep(100);
        } catch (Exception e) {
        }
        aT1.start();
        try {
            sleep(100);
        } catch (Exception e) {
        }
        System.err.println(x);
    }
}


class Thread_2 extends Thread {

    static int value = 0;    // static?
    int id;

    public Thread_2(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void compute() {
        if (id == 1) {
            value = 1;
        }
        if (id == 2) {
            value = 2;
        }
    }

    public void run() {
        compute();
    }

    public static void main(String args[]) {
        Thread_2 aT1 = new Thread_2(1);
        Thread_2 aT2 = new Thread_2(2);

        aT1.start();
        aT2.run();    // would aT2.start(); make a difference?
        // not so fast with your answer!
        int aT2Value = aT2.getValue();
        int aT1Value = aT1.getValue();
        System.out.println(aT1Value + aT2Value);
    }
}


class Thread_3 implements Runnable {

    private String info;
    int x = 0;

    public Thread_3(String info) {
        this.info = info;
    }

    public void run() {
        x = 1;
        System.out.print(info);
    }

    public static void main(String[] args) {
        args = new String[5];
        if (args != null) {
            for (int n = 0; n < args.length; ++n) {
                new Thread(new Thread_3("" + n)).start();
            }
        }
    }
}

class Thread_4_InterruptExample extends Thread {
    public Thread_4_InterruptExample(String name) {
        setName(name);
    }

    public static void sleepForAbit(long sleepTime) {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + "" +
                    " was interrupted in sleepForAbit");
        }
    }

    public void run() {
        System.err.println(getName() + " has started!");
        double x = 1;
        while (x > 0) {        // forever loop
            x = x * 2 - x;        // x is constant
            System.err.println(x);
            sleepForAbit(200);
            if (isInterrupted()) {
                System.err.println(Thread.currentThread().getName() + "" +
                        "is interrupted in run");
                System.err.println("return");
                return;
            }
        }
        System.err.println(getName() + " has exited!");
    }

    public static void main(String args[]) {
        Thread_4_InterruptExample aThread =
                new Thread_4_InterruptExample("aThread");
        aThread.start();
        sleepForAbit(1000);    // should allow the thread to enter the while loop
        aThread.interrupt();
    }
}

class Thread_5_Join extends Thread {
    private String info;
    Thread_5_Join aT1;

    public Thread_5_Join(String info) {
        this.info = info;
    }

    public void run() {
        System.out.println(info + " is running");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println(info + ": exit run");

    }

    public static void main(String args[]) {
        Thread_5_Join aT1 = new Thread_5_Join("first");

        aT1.start();

        try {
            aT1.join();
            System.err.println("Got it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("main end");
    }
}

class Thread_6_Join_Evaluator extends Thread {
    // fuer jedes resultat ein der es ausrechnet
// auf das ende der ausrechner muss gewartet werden
    int i, j;
    final static int MAX = 2;

    Thread_6_Join_Evaluator() {
    }

    Thread_6_Join_Evaluator(int i, int j) {
        this.i = i;
        this.j = j;
    }

    static int a[][] = new int[MAX][MAX];
    static int b[][] = new int[MAX][MAX];
    static int c[][] = new int[MAX][MAX];

    public void run() {
        for (int index = 0; index < MAX; index++) {
            try {
                sleep(100);
            } catch (Exception e) {
            }
            c[i][j] += a[i][index] * b[index][j];
        }
    }

    public String print(int a[][], String whichOne) {
        String rValue = whichOne + ": \n";
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                rValue += a[i][j] + "  ";
            }
            rValue = rValue + "\n";
        }

        return rValue;
    }

    public void init() {
        for (int i = 0; i < MAX; i++) {            // i-->
            for (int j = 0; j < MAX; j++) {        // j
                a[i][j] = b[i][j] = 2 + i + j;        // |
            }                    // v
        }

    }

    public String toString() {
        String rValue = print(a, "A") + print(b, "B") + print(c, "C");
        return rValue;
    }

    public void multiply() {
        Thread_6_Join_Evaluator et[] = new Thread_6_Join_Evaluator[MAX * MAX];
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                new Thread_6_Join_Evaluator(i, j).start();
            }
        }
        System.out.println(this);
    }

    public static void main(String[] args) {
        Thread_6_Join_Evaluator eval = new Thread_6_Join_Evaluator();
        eval.init();
        eval.multiply();
    }
}

// es gibt immer nur einen ausrechner, aber das ergebniss is richtig
class Thread_6_Evaluator_2 extends Thread {
    int i, j;
    final static int MAX = 2;

    Thread_6_Evaluator_2() {
    }

    Thread_6_Evaluator_2(int i, int j) {
        this.i = i;
        this.j = j;
    }

    static int a[][] = new int[MAX][MAX];
    static int b[][] = new int[MAX][MAX];
    static int c[][] = new int[MAX][MAX];

    public void run() {
        for (int index = 0; index < MAX; index++) {
            c[i][j] += a[i][index] * b[index][j];
        }
    }

    public String print(int a[][], String whichOne) {
        String rValue = whichOne + ": \n";
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                rValue += a[i][j] + "  ";
            }
            rValue = rValue + "\n";
        }

        return rValue;
    }

    public void init() {
        for (int i = 0; i < MAX; i++) {            // i-->
            for (int j = 0; j < MAX; j++) {        // j
                a[i][j] = b[i][j] = 2 + i + j;        // |
            }                    // v
        }

    }

    public String toString() {
        String rValue = print(a, "A") + print(b, "B") + print(c, "C");
        return rValue;
    }

    public void multiply() {
        System.out.println(this);
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                Thread_6_Evaluator_2 et = new Thread_6_Evaluator_2(i, j);
                et.start();
                try {
                    et.join();
                } catch (Exception e) {
                }
            }
        }
        System.out.println(this);
    }

    public static void main(String[] args) {
        Thread_6_Evaluator_2 eval = new Thread_6_Evaluator_2();
        eval.init();
        eval.multiply();
    }
}


class Thread_6_Evaluator_3 extends Thread {
    int i, j;
    final static int MAX = 2;

    Thread_6_Evaluator_3() {
    }

    Thread_6_Evaluator_3(int i, int j) {
        this.i = i;
        this.j = j;
    }

    static int a[][] = new int[MAX][MAX];
    static int b[][] = new int[MAX][MAX];
    static int c[][] = new int[MAX][MAX];

    public void run() {
        for (int index = 0; index < MAX; index++) {
            c[i][j] += a[i][index] * b[index][j];
        }
    }

    public String print(int a[][], String whichOne) {
        String rValue = whichOne + ": \n";
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                rValue += a[i][j] + "  ";
            }
            rValue = rValue + "\n";
        }

        return rValue;
    }

    public void init() {
        for (int i = 0; i < MAX; i++) {            // i-->
            for (int j = 0; j < MAX; j++) {        // j
                a[i][j] = b[i][j] = 2 + i + j;        // |
            }                    // v
        }

    }

    public String toString() {
        String rValue = print(a, "A") + print(b, "B") + print(c, "C");
        return rValue;
    }

    public void multiply() {
        Thread_6_Evaluator_3 et[] = new Thread_6_Evaluator_3[MAX * MAX];
        System.out.println(this);
        for (int counter = 0; counter < MAX; counter++) {
            for (int j = 0; j < MAX; j++) {
                et[counter] = new Thread_6_Evaluator_3(i, j);
                et[counter].start();
            }
        }
        for (int counter = 0; i < MAX * MAX; i++) {
            try {
                et[counter].join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
        }
        System.out.println(this);
    }

    public static void main(String[] args) {
        Thread_6_Evaluator_3 eval = new Thread_6_Evaluator_3();
        eval.init();
        eval.multiply();
    }
}

