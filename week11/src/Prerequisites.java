//This program was made by Jesus A Acosta to solve
// HW5 Q2
// Input specification: the first line contains n, the number of courses.
// The courses are labeled with numbers 1, 2,..., n. Then n lines follow.
// The i-th line lists the prerequisites in Pi, separated by spaces,
// finished with number 0. You may assume that n is at most 100000
// and every course has at most 100 prerequisites.

// Output specification: the first line contains the number
// of courses in the longest prerequisite chain.


import java.util.Scanner;

public class Prerequisites {
    static LinkedListME list[];
    static int n;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        n = keyboard.nextInt();
        visited = new boolean[n + 1];
        //input Adjency list
        list = new LinkedListME[n + 1];

        //Intialize all the linked lists in Adjency table
        for (int b = 0; b < n + 1; b++) {
            list[b] = new LinkedListME();
        }
        //Each lines ends with 0 so you can while inton next == 0
        for (int a = 0; a < n; a++) {
            int first = keyboard.nextInt();
            LinkedListME meTEMP = list[first];
            System.out.print(first);
            //int tmp = 0;
            if (first != 0) {
                int tmp = keyboard.nextInt();
                //System.out.print(" "+tmp);
                while (tmp != 0) {
                    if (meTEMP.indexOf(tmp) == -1) {
                        meTEMP.add(tmp);
                    }
                    tmp = keyboard.nextInt();
                }
            }
            System.out.println();
            //Add Zero because for logical reason
            if (meTEMP.indexOf(0) == -1) {
                meTEMP.add(0);
            }
        }
        for (int c = 0; c < n + 1; c++) {
            System.out.print(c + " ");
            LinkedListME tmp = list[c];
            System.out.println(tmp.size());
            for (int a = 0; a < tmp.size(); a++) {
                System.out.print(tmp.get(a) + " ");
            }
            System.out.println();
        }

    }
}
