import java.io.Serializable;

public class Password implements Serializable {
    private String password;

    public Password(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String newPassword){
        this.password = newPassword;
    }
}
