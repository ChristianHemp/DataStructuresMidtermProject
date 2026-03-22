public class MyQueue<T> {
    private T[] data;
    private int size;
    private int head;
    private int tail;

    public MyQueue(int capacity) {
        data = (T[]) new Object[capacity];
        this.size = 0;
        head = 0;
        tail = 0;
    }

    public void enqueue(T item) {
        if(size == data.length) {
            System.out.println("Vehicle's queue is full, we do not have enough capacity for more than 5 waitlists");
        } else {
            this.data[this.tail] = item;
            this.tail = (this.tail + 1) % data.length;
            this.size++;
        }
    }

    public T dequeue() {
        if(size == 0) {
            // System.out.println("Cannot remove from empty queue");
            return null;
        } else {
            T returnValue = (T) this.data[this.head];
            this.data[this.head] = null;
            this.head = (this.head + 1) % data.length;
            size--;
            return returnValue;
        }
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return (T) data[head];
    }

    public int getSize() {
        return size;
    }

    public void print() {
        System.out.print("[");
        for(int i = 0; i < this.size; i++) {
            int index = (head + i) % data.length;
            System.out.print(data[index]);
            if(i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void printIds() {
        System.out.print("[");
        for(int i = 0; i < this.size; i++) {
            int index = (head + i) % data.length;
            Customer customer = (Customer) data[index];
            System.out.print(customer.getId());
            if(i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void printIdAndName() {
        System.out.print("[");
        for(int i = 0; i < this.size; i++) {
            int index = (head + i) % data.length;
            Customer customer = (Customer) data[index];
            System.out.print(customer.getId() + " : " + customer.getName());
            if(i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
