public class Mouse implements Comparable<Mouse> {
    // Initialize all the variables

    float tailLength;
    String name;

    // Initialize all the values in the constructor
    Mouse(String name, float tailLength) {
        this.name = name;
        this.tailLength = tailLength;
    }

    /**
     *
     * @param obj           the parameter with which the comparison is to be done
     * @return 1 or -1      based on the result of the comparison
     */
    public int compareTo(Mouse obj) {
        if (tailLength == obj.tailLength)
            return 0;
        else if (tailLength > obj.tailLength)
            return 1;
        else
        return -1;
    }

    /**
     *  Uses the toString method to give the output in a string value
     * @return the output statement of the tree
     */
    @Override
    public String toString() {return "Name: " + name + ", tail length: " + tailLength;}
}
