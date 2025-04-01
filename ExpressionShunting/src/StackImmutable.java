public interface StackImmutable<E> {
    StackImmutable<E> add(E element);  // return a stack with element at the top, other elements of "this", "this" unchanged
    E top();                  // return top element of "this", or (typically) throw exception if not there
    StackImmutable<E> belowTop();      // return "this" without it's top element, without changing "this"
    int size();
    boolean isEmpty();
}