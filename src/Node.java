public class Node<T> {
    // Data
    public T data;

    // Link
    public Node<T> next;

    public Node<T> previous;

    Node(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
