import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;


class NumberCounter {
    int[] array = new int[90];

    public NumberCounter(String processed_data) {
        counter(processed_data);
    }

    private void counter(String processed_data) {
        String[] oneRow;

        String[] processed_data_rows = processed_data.split("\n");

        for (String processed_row : processed_data_rows) {

            // check to see if the first element in line is actually an integer
            try {
                Integer.parseInt(Character.toString(processed_row.charAt(0)));
            } catch (Exception e) {
                continue;
            }

            processed_row = processed_row.split(",")[1];

            oneRow = processed_row.split(" ");

            for (String elem : oneRow) {

                int index = elem.contains("\r") ? Integer.parseInt(elem.substring(0, 2)) : Integer.parseInt(elem);

                array[index]++;

            }
        }
    }

    public void print() {
        for (int id = 1; id < array.length; id++) {
            if (array[id] == 0) {
                continue;
            }
            System.out.print(id + ":\t" + array[id] + "\t");
            if (id % 5 == 0) {
                System.out.println();
            }
        }
    }

    private static String handle_gz_file_case(byte[] bytes, String data, InputStream input, File path){
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(path))));

            String temp = in.readLine();
            while (temp != null) {
                data += temp + "\n";
                temp = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static String handle_normal_file_case(byte[] bytes, String data, InputStream input, File path) {
        try {
            input = new FileInputStream(path);

            bytes = new byte[input.available()];
            input.read(bytes);

            data = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Please input a valid filepath. " + e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }


    private static String handle_system_in_case(byte[] bytes, String data, InputStream input) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String temp = reader.readLine();
            while (temp != null) {
                data += temp + "\n";
                temp = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public static String process_arguments(String[] args) {
        byte[] bytes = null;
        InputStream input = null;

        if (args.length != 0 && args.length != 1) {
            System.err.println("Please make sure you have inputted correct arguments!");
            System.exit(-1);
            return "";
        } else if (args.length == 0) {
            // handle stdin
            return handle_system_in_case(bytes, "", input);

        } else {
            String arg = args[0];
            File path = new File(arg).getAbsoluteFile();

            if (arg.contains(".gz") || arg.contains(".zip")){
                return handle_gz_file_case(bytes, "", input, path);
//                return "";
            }else{
                // handle normal filepath
                return handle_normal_file_case(bytes, "", input, path);
            }

        }
    }

    public static void main(String[] args) {
        String processed_data = process_arguments(args);
        NumberCounter numCntr = new NumberCounter(processed_data);
        numCntr.print();
    }
}
