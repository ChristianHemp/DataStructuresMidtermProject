public class MySinglyLinkedList<T> {
    public Node<T> head;
    public int length;

    public MySinglyLinkedList() {
        this.head = null;
        length = 0;
    }

    // ---- INSERTS ----
    public void append(T data) {
        if(head == null) {
            head = new Node<>(data);
            this.length += 1;
            return; // must keep this
        }

        Node<T> curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(data);
        this.length += 1;
    }

    public void prepend(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = this.head;
        this.head = newNode;
        this.length++;
    }

    public void insertAfter(T data, T value) {
        Node<T> newNode = new Node<>(data);
        Node<T> curr = this.head;

        if(this.head == null) {
            System.out.println("List is empty");
            return;
        }

        while(curr != null && !curr.data.equals(value)) {
            curr = curr.next;
        }

        if(curr == null) {
            System.out.println("That value does not exist");
            return;
        }

        newNode.next = curr.next;
        curr.next = newNode;
        this.length++;
    }

    public void insertAt(T data, int index) {
        if(index == 0) {
            prepend(data);
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> curr = head;
        for(int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;
        this.length++;
    }

    // ----- REMOVE -----
    public T removeValue(T value) {
        if(this.head == null) {
            return null;
        }

        // remove head
        if(this.head.data == value) {
            T returnData = head.data;
            this.head = this.head.next;
            this.length--;
            return returnData;
        }
        // remove anywhere
        Node<T> curr = this.head;
        while(curr.next != null) {
            if(curr.next.data == value) {
                T returnData = curr.next.data;
                curr.next = curr.next.next;
                this.length--;
                return returnData;
            }
            curr = curr.next;
        }
        return null;
    }

    public T removeIndex(int index) {
        if(index >= this.length) {
            System.out.println("out of bounds");
            return null;
        }

        if(index == 0) {
            T returnData = head.data;
            this.head = this.head.next;
            this.length--;
            return returnData;
        }

        Node<T> curr = this.head;
        for(int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        T returnData = curr.next.data;
        curr.next = curr.next.next;
        this.length --;
        return returnData;
    }

    // ---- SEARCH ----
    public boolean search(T target) {
        // can change easily to different return type
        if(this.head == null) {
            return false;
        }

        Node<T> curr = this.head;
        while(curr != null) {
            if(curr.data == target) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // ---- UTILS ----
    public boolean isEmpty() {
        if(this.head == null) {
            return true;
        }
        return false;
    }

    public int getLength() {
        return this.length;
    }

    // extra method to help with getting the first data value
    public T getFirst() {
        if(head == null) {
            return null;
        }
        return head.data;
    }

    // ---- PRINT ----
    public void print(){
        if(this.head == null) {
            System.out.println("None");
            return;
        }
        Node<T> curr = this.head;
        while(curr.next != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println(curr.data);
    }

    public void printIds() {
        if(this.head == null) {
            System.out.println("None");
            return;
        }
        Node<T> curr = this.head;
        while(curr.next != null) {
            Vehicle vehicle = (Vehicle) curr.data;
            System.out.print(vehicle.getId() + " : " + vehicle.getMake() + " -> ");
            curr = curr.next;
        }
        Vehicle vehicle = (Vehicle) curr.data;
        System.out.println(vehicle.getId() + " : " + vehicle.getMake());
    }
}