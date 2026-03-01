package co.edu.uptc.model.structures;

public class SimpleNode<T> {
    private T value;
    private SimpleNode<T> next;

    public SimpleNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SimpleNode<T> getNext() {
        return next;
    }

    public void setNext(SimpleNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "SimpleNode [value=" + value + ", next=" + next + "]";
    }

}
