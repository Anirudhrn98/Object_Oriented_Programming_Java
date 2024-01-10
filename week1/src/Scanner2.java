import java.io.*;
import java.util.Scanner;

public class Scanner2 {
    public static void main( String[] args ) {
        Scanner sc  = null;
        if ( args.length > 0 )
            try {
                sc  = new Scanner(new File(args[0]) );
            } catch ( FileNotFoundException e ) {}
        else
            sc  = new Scanner( System.in);
        if ( args.length == 0 )
            System.out.printf("> ");
        while ( sc.hasNext() )  {
            Integer aInteger = sc.nextInt();
            System.out.printf("-%d-%n", aInteger );
            if ( args.length == 0 )
                System.out.printf("> ");
        }
        sc.close();
    }
}
