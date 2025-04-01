import java.util.Objects;

public class DoublyLinkedList {
    private Node header;
    private Node trailer;

    private int size;

    /**
     * constructor for doubly linked list
     */
    public DoublyLinkedList() {
        header = new Node(null, null, null);        // create header
        trailer = new Node(null, header, null);        // trailer is preceded by header
        header.setNext(trailer);                                // header is followed by trailer
        size = 0;
    }
    public int size( ) { return size; }

    public boolean isEmpty( ) { return size == 0; }

    public NameData first( ) {
        if (this.isEmpty())
            return null;
        return header.getNext( ).getData( );  // first element is beyond header
    }

    public NameData last( ) {
        if (isEmpty( ))
            return null;
        return trailer.getPrev( ).getData( );  // last element is before trailer
    }

    // private update methods

    /**
     * adds name to list between two nodes
     * @param data NameData object
     * @param predecessor node before new node
     * @param successor node after new node
     */
    private void addBetween(NameData data, Node predecessor, Node successor) { // create and link a new node
        Node newest = new Node(data, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        newest.setPrev(predecessor);
        newest.setNext(successor);
        size++;
    }

    /**
     * removes node
     * @param node
     * @return NameData object removed
     */
    private NameData remove(Node node) {
        Node predecessor = node.getPrev( );
        Node successor = node.getNext( );
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getData( );
    }

    // public update methods
    public void addFirst(NameData data) {
        addBetween(data, header, header.getNext( ));  // place node just after the header
    }
    public void addLast(NameData data) {
        addBetween(data, trailer.getPrev( ), trailer); // place node  just before the trailer
    }
    public NameData removeFirst( ) {
        if (isEmpty( )) return null;         // nothing to remove
        return remove(header.getNext( ));    // first element is beyond header
    }
    public NameData removeLast( ) {
        if (isEmpty( )) return null;              // nothing to remove
        return remove(trailer.getPrev( ));        // last element is before trailer
    }

    @Override
    public String toString() {
        String listString = "[";
        Node curr = header.getNext();
        while (curr != trailer) {
            listString = listString + curr.getData();
            curr = curr.getNext();
            if (curr != trailer) {
                listString = listString + ", ";
            }
        }
        listString = listString + "]";
        return listString;
    }

    /**
     * inserts NameData in alphabetical order
     * @param inputName
     */
    public void insertAlpha(NameData inputName) {
        if (isEmpty()) {
            addFirst(inputName);
        }
        else {
            Node curr = header.getNext();
            while (curr != trailer) {
                if (inputName.compareTo(curr.getData()) < 0) {
                    addBetween(inputName, curr.getPrev(), curr);
                    break;
                }
                curr = curr.getNext();
                if (curr == trailer) {
                    addLast(inputName);
                }
            }
        }
    }

    /**
     * Finds nameData for a specific name
     * @param name
     * @return NameData object
     */
    public NameData fetch(String name) {
        Node curr = header.getNext();
        if (isEmpty()) {
            return null;
        }
        while (curr != trailer) {
            if (Objects.equals(curr.getData().getName(), name)) {
                return curr.getData();
            }
            curr = curr.getNext();
        }
        return null;
    }

    /**
     * Finds position of node in DLL
     * @param name
     * @return position number
     */
    public int findPosition(String name) {
        if (isEmpty()) {
            return -1;
        }
        Node curr = header.getNext();
        int pos = 0;

        while (curr != trailer) {
            if (Objects.equals(curr.getData().getName(), name)) {
                return pos;
            }
            curr = curr.getNext();
            pos ++;
        }
        return -1;
    }

    /**
     * Gets the total number of occurrences of all names together
     * @return
     */
    public Double getTotalCount() {
        Double totalCount = 0.0;
        Node curr = header.getNext();
        if (isEmpty()) {
            return 0.0;
        }
        while (curr != trailer) {
            totalCount += curr.getData().getNumOccurrences();
            curr = curr.getNext();
        }
        return totalCount;
    }
}




