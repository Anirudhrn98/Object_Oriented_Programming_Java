interface StorageInterface<T> {
    boolean add(T x);
    boolean find(T x);
    boolean includesNull();
    boolean delete(T x);
}

interface StringInterface<T extends String>{}
interface IntegerInterface<T extends Integer>{}


class Super{
    public <T extends Super & StringInterface<String> & IntegerInterface<Integer>> void compareTo(T val) {
    }
}