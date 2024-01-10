import java.io.*;

public class PasswordRead {
    // Read the serialized back
    public static void readFile(String fileName, Password pwd) throws IOException, ClassNotFoundException {
        ObjectInputStream inputObj = new ObjectInputStream(new FileInputStream(fileName));
        Password temp = (Password) inputObj.readObject();

        if (PasswordWrite.getDecryptedString(temp.getPassword()).equals(pwd.getPassword())) {
            System.out.println("The password has not been tempered with!");
            System.out.println("Password: " + pwd.getPassword());
        } else
            System.out.println("The password has been tempered with!");

        inputObj.close();
    }

    public static String process_args(String[] args){
        if(args.length != 1){
            System.err.println("Please make sure to input the right arguments");
            System.exit(-1);
        } else if (!args[0].contains("ser")){
            System.err.println("Please make sure to input a `.ser` file.");
            System.exit(-1);}
        return args[0];
    }

    public static void main(String[] args) {
        String fileName = process_args(args);
        String myActualPwd = "password";
        try {
            readFile(fileName, new Password(myActualPwd));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
