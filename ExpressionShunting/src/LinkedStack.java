public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list;
    private Node<E> head; // head node of the list (or null if empty)
    private Node<E> tail; // last node of the list (or null if empty)

    public LinkedStack() {
        list = new SinglyLinkedList<E>(); // new stack relies on the initially empty list
    }
    public int size() { return list.size( ); }
    public boolean isEmpty() { return list.isEmpty( ); }
    public E peek() { return list.first( ); }
    public void push(E element) { list.addFirst(element); }
    public E pop() { return list.removeFirst( ); }

    public void clear() {
        while (!this.isEmpty()){
            this.pop();
        }
    }
    public String toString() {
        return list.toString();
    }
}
