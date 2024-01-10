public class Address implements Comparable<Address>{
    int houseNumber;
    String streetName;
    String nameOfTown;
    String state;
    int zipCode;

    /**
     * Address class constructor
     */
    public Address(int houseNumber, String streetName, String nameOfTown, String state, int zipCode){
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.nameOfTown = nameOfTown;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     *
     * @param o                the parameter with which the comparison is to be done
     * @return 1,0, or -1      based on the result of the comparison
     */
    @Override
    public int compareTo(Address o) {
        if (houseNumber == o.houseNumber && streetName.equals(o.streetName)
                && nameOfTown.equals(o.nameOfTown) && state.equals(o.state)
                && zipCode == o.zipCode)
            return 0;
        else if (zipCode > o.zipCode)
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
        return "Address{" +
                "houseNumber=" + houseNumber +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}