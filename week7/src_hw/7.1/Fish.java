public class Fish implements Comparable<Fish> {
    // Initialize all the variables

    String name;
    float weight;

    // Initialize all the values in the constructor
    Fish(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     *
     * @param obj           the parameter with which the comparison is to be done
     * @return 1 or -1      based on the result of the comparison
     */
    public int compareTo(Fish obj) {
        if (weight == obj.weight)
            return 0;
        else if (weight > obj.weight)
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
        return "Name: " + name + ", Weight: " + weight;
    }

}

