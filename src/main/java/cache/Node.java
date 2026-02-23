package cache;

public class Node {
    Node prev;
    Node next;

    public void setPrev(Node prev) {
        this.prev = prev;
        prev.next = this;
    }

    public void setNext(Node next) {
        this.next = next;
        next.prev = this;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }
}
