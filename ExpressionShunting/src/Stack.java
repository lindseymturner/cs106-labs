public interface Stack<E> {
    void push(E element);  // mutator: update "this" so that E is the top elements, others are "below" it
    E pop();        // mutator: update "this" to remove top element, returning that top element
    E peek();       // accessor: return top element without changing "this"; called "top" in some textbooks
    int size();     // accessor: how many elements are on the stack?
    boolean isEmpty(); // accessor: is size zero?
}