import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.sql.SQLOutput;

class PasswordWrite {
    Password password;
    String fileName;

    public PasswordWrite(Password password, String fileName) {
        this.password = password;
        this.fileName = fileName;
    }

    static private String getEncryptedString(String pwd) {
        char[] enc = new char[pwd.length()];

        for (int i = 0; i < pwd.length(); i++) {
            if (i % 2 == 0) {
                enc[i] = (char) ((int) pwd.charAt(i) + 1);
            } else {
                enc[i] = (char) ((int) pwd.charAt(i) - 1);
            }
        }
        return new String(enc);

    }

    static protected String getDecryptedString(String enc) {
        char[] decrypt = new char[enc.length()];

        for (int i = 0; i < enc.length(); i++) {
            if (i % 2 == 0) {
                decrypt[i] = (char) ((int) enc.charAt(i) - 1);
            } else {
                decrypt[i] = (char) ((int) enc.charAt(i) + 1);
            }
        }

        return new String(decrypt);
    }

    public void writeToFile() throws IOException{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(password);
        outputStream.close();
    }

    public static void main(String[] args) {
        String fileName = PasswordRead.process_args(args);
        String password = "password";
        PasswordWrite pwdWrite = new PasswordWrite(new Password(PasswordWrite.getEncryptedString(password)), fileName);
        try {
            pwdWrite.writeToFile();
        } catch (IOException e){e.printStackTrace();}
    }
}
