public class LivingThing implements Comparable<LivingThing>{
    // Initialize all the variables
    String name;
    float weight;
    int age;

    // Initialize all the values in the constructor
    LivingThing(String name, float weight, int age){
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    /**
     *
     * @param obj           the parameter with which the comparison is to be done
     * @return 1 or -1      based on the result of the comparison
     */
    public int compareTo(LivingThing obj){
        if(age == obj.age)
            return 0;
        else if(age > obj.age)
            return 1;
        else
            return -1;
    }

    /**
     *  Uses the toString method to give the output in a string value
     * @return the output statement of the tree
     */
    @Override
    public String toString(){
        return "Name: " + name + ", Weight: " + weight;
    }
}





