import java.io.*;

public class Modify {

    public static void main(String[] args) throws IOException {
        String fileName = PasswordRead.process_args(args);
        Password temp = null;
        ObjectInputStream inputObj = null;
        try {
            inputObj = new ObjectInputStream(new FileInputStream(fileName));
            temp = (Password) inputObj.readObject();
            temp.setPassword("passwor1");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert inputObj != null;
            inputObj.close();
        }

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(temp);
        outputStream.close();
        System.out.println("Successfully modified the password.");
    }
}
