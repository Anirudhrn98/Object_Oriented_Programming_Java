interface StorageInterface<T> {
    boolean add(T x);
    boolean find(T x);
    boolean includesNull();
    boolean delete(T x);
}
