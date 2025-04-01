public class SinglyLinkedList<E> {
    private Node<E> head; // head node of the list (or null if empty)
    private Node<E> tail; // last node of the list (or null if empty)
    private int size; // number of nodes in the list
    public SinglyLinkedList() { // constructs an initially empty list
        head = null;
        tail = null;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0;}
    public E first() { // returns (but does not remove) the first element
        if (isEmpty())
            return null;
        return head.getElement();
    }
    public E last() { // returns (but does not remove) the last element
        if (isEmpty())
            return null;
        return tail.getElement();
    }

    public void addFirst(E e) { // adds element e to the front of the list
        Node<E> newest = new Node<E>(e, null);
        newest.setNext(head);
        head = newest;
        if (isEmpty())
            tail = head; // special case: new node becomes tail also
        size++;
    }

    public void addLast(E e) { // adds element e to the end of the list
        Node<E> newest = new Node<E>(e, null); // node will eventually be the tail
        if (isEmpty()) // special case: previously empty list
            head = newest;
        else
            tail.setNext(newest); // new node after existing tail
        tail = newest; // new node becomes the tail
        size++;
    }

    public E removeFirst() { // removes and returns the first element
        if (isEmpty()) // nothing to remove
            return null;
        E answer = head.getElement();
        head = head.getNext(); // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null; // special case as list is now empty
        return answer;
    }
    /*
    public void addAt(E element, int position) {
        Node temp = head;
        for (int i=0; i<position; i++) {
            temp = temp.getNext();
        }
        Node afterTemp = temp.getNext();
        Node newbie = new Node(element, afterTemp);
        temp.setNext(newbie);
    }
*/
    public void addAt(E element, int position) throws IndexOutOfBoundsException {
        if (position<0 || position>size) {
            throw new IndexOutOfBoundsException("Invalid position" + position);
        }
        if (position == 0) {
            this.addFirst(element);
        }
        else if (position==size) {
            this.addLast(element);
        }
        else {
            Node<E> curr = head;
            for (int i=0; i < position-1; i++) {
                curr = curr.getNext();
            }
            Node<E> newest = new Node<E>(element, curr.getNext());
            curr.setNext(newest);

        }
        size++;
    }

    public String toString() {
        if (isEmpty()){
            return "List is Empty";
        }
        Node<E> curr = head;
        String str = head.toString();
        while(curr.getNext() != null) {
            curr = curr.getNext();
            str = str + "->" + curr.toString();
        }
        return str;
    }

    public E getElement(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }

        return curr.getElement();
    }

    public void removeFrom(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + pos);
        }

        if (size == 1) {
            // Special case: removing the only element in the list
            head = tail = null;
        } else if (pos == 0) {
            // Removing the first element
            head = head.getNext();
        } else {
            // Removing an element in the middle or at the end
            Node<E> curr = head;
            for (int i = 0; i < pos - 1; i++) {
                curr = curr.getNext();
            }

            curr.setNext(curr.getNext().getNext());

            if (pos == size - 1) {
                // If removing the last element, update the tail
                tail = curr;
            }
        }

        size--;
    }
}
