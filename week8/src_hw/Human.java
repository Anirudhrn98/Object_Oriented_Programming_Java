import java.util.Date;

public class Human implements Comparable<Human>{
//    SimpleDateFormat dateOfBirth;
    Date dateOfBirth;
    String firstName;
    String userId;

    /**
     * Human class constructor
     */
    public Human(int dateOfBirth, String firstName, String userId) {
        this.dateOfBirth = new Date(dateOfBirth);
        this.firstName = firstName;
        this.userId = userId;
    }

    /**
     *
     * @param o                the parameter with which the comparison is to be done
     * @return 1,0, or -1      based on the result of the comparison
     */
    @Override
    public int compareTo(Human o) {
        if (userId.equals(o.userId))
            return 0;
        else if (userId.compareTo(o.userId)>0)
            return 1;
        else
            return -1;
    }

    /**
     *  Uses the toString method to give the output in a string value
     * @return the output statement of the tree
     */
    @Override
    public String toString() {
        return "Human{" +
                ", firstName='" + firstName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}