public class Ant implements Comparable<Ant> {
    // Initialize all the variables

    String name;
    float size;

    // Initialize all the values in the constructor
    Ant(String name, float size) {
        this.name = name;
        this.size = size;
    }

    /**
     *
     * @param obj           the parameter with which the comparison is to be done
     * @return 1 or -1      based on the result of the comparison
     */
    public int compareTo(Ant obj) {
        if (size == obj.size)
            return 0;
        else if (size> obj.size)
            return 1;
        else
            return -1;
    }

    /**
     *  Uses the toString method to give the output in a string value
     * @return the output statement of the tree
     */
    @Override
    public String toString() {return "Name: " + name + ", Size: " + size;} }

