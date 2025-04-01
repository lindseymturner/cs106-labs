import java.security.InvalidParameterException;

public class LinkedStackImmutable<E> implements StackImmutable<E> {
    private final Boolean isItEmpty;                  // if true, other fields don't matter
    private final E topElement;
    private final LinkedStackImmutable<E> belowTheTop;

    public LinkedStackImmutable() {  // NOTE --- no need for <E> here, it's implicit (and, error if you put it)
        isItEmpty = true;
        topElement = null;  // this and below just make the warnings go away, not really needed
        belowTheTop = null;
    }

    LinkedStackImmutable(E top, LinkedStackImmutable<E> belowTop) {
        isItEmpty = false;
        topElement = top;
        belowTheTop = belowTop;
    }

    @Override
    public LinkedStackImmutable<E> add(E element) {
        return new LinkedStackImmutable<E>(element, this);
    }

    @Override
    public E top() {
        if (isItEmpty) throw new InvalidParameterException("Empty stack has no top!");
        return topElement;
    }

    @Override
    public LinkedStackImmutable<E> belowTop() {
        if (isItEmpty) throw new InvalidParameterException("Empty stack has nothing below top!");
        return belowTheTop;
    }

    @Override
    public int size() {
        return isItEmpty ? 0 : 1+this.belowTop().size();
    }

    @Override
    public boolean isEmpty() {
        return isItEmpty;
    }

    @Override
    public String toString() {  // use notation from lecture slides, i.e., (3, 5) is 3 atop 5
        if (isItEmpty) {
            return "()";
        } else {
            return "(" + topElement + belowTheTop.toStringWithin() + ")";
        }
    }
    private String toStringWithin() { // generate the text when this stack is below something else
        // Note: PyCharm is worried about NullPointerExceptions but note that !isItEmpty implies belowTheTop != null
        if (isItEmpty) {
            return "";
        } else {
            return " " + topElement + belowTheTop.toStringWithin();
        }
    }
}
