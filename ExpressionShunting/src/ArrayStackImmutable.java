import java.security.InvalidParameterException;
import java.util.ArrayList;

public class ArrayStackImmutable<E> implements StackImmutable<E> {
    private ArrayList<E> myElements;  // @ToDo: in an immutable type, we should be using "final" fields, if we can.

    public ArrayStackImmutable() {  // NOTE --- no need for <E> here, it's implicit (and, error if you put it)
        this.myElements = new ArrayList<E>();
    }

    @Override
    public ArrayStackImmutable<E> add(E element) {
        // Used web site https://www.tutorialspoint.com/what-is-deep-copy-explain-with-an-example-in-java
        ArrayList<E> elementsForResult = (ArrayList<E>) this.myElements.clone();  // @ToDo: this is awful
        elementsForResult.add(0, element);
        ArrayStackImmutable<E> theResult = new ArrayStackImmutable<E>();  // create it empty
        theResult.myElements = elementsForResult;  // then mutate it
        // @ToDo: better than this would be to have another constructor that we can give the ArrayList,
        //        and then we wouldn't need to change myElements after creating the object;
        //        when we do that, we can mark the myElements field (above) as a "final" field.
        //        Note that such a constructor could be private rather than public, so it's only called from the class.
        return theResult;
    }

    @Override
    public E top() {
        if (isEmpty()) throw new InvalidParameterException("Empty stack has no top!");
        return this.myElements.get(0);
    }

    @Override
    public ArrayStackImmutable<E> belowTop() {
        ArrayList<E> elementsForResult =  (ArrayList<E>) this.myElements.clone();  // @ToDo: this is awful
        elementsForResult.remove(0);
        ArrayStackImmutable<E> theResult = new ArrayStackImmutable<E>();  // create it empty
        theResult.myElements = elementsForResult;  // then mutate it
        return theResult;
        // See above note about private constructor
    }

    @Override
    public int size() {
        return this.myElements.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {  // use notation from lecture slides, i.e., (3 5) is 3 atop 5
        if (this.isEmpty()) {
            return "()";
        } else {
            String result = "(";
            // Found iterator notation in Java, sort of like a Python for loop through objects in a list:
            //   https://stackoverflow.com/questions/18410035/ways-to-iterate-over-a-list-in-java
            for (E element : this.myElements.subList(0, this.myElements.size() - 1)) {
                result = result + element + " ";  // @ToDo maybe: StringBuilder could make this more efficient
            }
            result = result + this.myElements.get(this.myElements.size() - 1) + ")";
            return result;
        }
    }
}
