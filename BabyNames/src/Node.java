public class Node {
    private NameData data;
    private Node next;
    private Node prev;

    public Node(NameData data, Node prev, Node next) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public NameData getData() {
        return data;
    }

    public void setData(NameData data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public String toString() {
        return data.toString();
    }
}
