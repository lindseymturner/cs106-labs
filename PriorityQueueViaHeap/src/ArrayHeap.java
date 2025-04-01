import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    ArrayList<E> heapArray;

    /**
     * Constructs new array list
     */
    public ArrayHeap() {
        heapArray = new ArrayList<E>();
    }

    public int size() {return heapArray.size();}

    public boolean isEmpty() {return size() == 0;}

    /**
     *
     * @return Returns the root of the heapArray
     */
    public E max() {
        if (this.isEmpty()) {
            return null;
        }
        return heapArray.get(0);
    }

    /**
     * Adds an element to the heapArray
     * @param element
     */
    public void insert(E element) {
        heapArray.add(element);
        // need to re-heapify it using bubbleUp
        bubbleUp(heapArray.size()-1);
    }

    /**
     * Removes and returns the root of the heapArray
     * @return
     */
    public E removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        // swaps the root with the most recently added element
        swap(0, heapArray.size()-1);
        // removes the max element from the heapArray, which is now the last element
        E max = heapArray.remove(heapArray.size()-1);
        // need to re-heapify using bubbleDown
        bubbleDown(0);
        return max;
    }

    /**
     * swaps two elements in heapArray
     * @param i index of an element
     * @param j index of another element
     */
    public void swap(int i, int j) {
        E temp = heapArray.get(i);
        heapArray.set(i, heapArray.get(j));
        heapArray.set(j, temp);
    }

    /**
     * Returns the parent of any element
     * @param i index of an element
     * @return
     */
    public int parent(int i) {
        // formula for finding parent
        return ((i-1)/2);
    }

    /**
     * Returns the left child of any element
     * @param i index of an element
     * @return
     */
    public int leftChild(int i) {
        // checks if element actually has a left child using formula for finding left child
        if ((2*i)+1 < heapArray.size()) {
            return (2*i)+1;
        }
        else {
            return -1;
        }
    }

    /**
     * Returns the right child of any element
     * @param i index of an element
     * @return
     */
    public int rightChild(int i) {
        // checks if element actually has a right child using formula for right child
        if ((2*i)+2 < heapArray.size()) {
            return (2*i)+2;
        }
        else {
            return -1;
        }
    }


    /**
     * Swaps an element with its parent until it is less than its parent
     * @param i index of an element
     */
    public void bubbleUp(int i) {
        while (heapArray.get(i).compareTo(heapArray.get(parent(i))) > 0) {
            swap(i, parent(i));
            // if the index of i's parent is not 0, the index i is replaced with the index of i's parents b/c they swapped, and the while loop continues
            if (parent(i) != 0) {
                i = parent(i);
            }
            else {
                break;
            }
        }
    }

    /**
     * Swaps an element with its left or right child until it is less than its parent
     * @param i index of an element
     */
    private void bubbleDown(int i) {
        // if there are no left or right children
        if (leftChild(i) == -1 && rightChild(i) == -1) {
            return;
        }
        // if there's no left child, compare the element with its right child and swap them if the child is greater
        else if (leftChild(i) == -1) {
            if (heapArray.get(rightChild(i)).compareTo(heapArray.get(i)) > 0) {
                swap(i, rightChild(i));
                // recursive call to bubble down the element until it is re-heapified
                bubbleDown(rightChild(i));
            }
        }
        // opposite of above
        else if (rightChild(i) == -1) {
            if (heapArray.get(leftChild(i)).compareTo(heapArray.get(i)) > 0) {
                swap(i, leftChild(i));
                bubbleDown(leftChild(i));
            }
        }
        else {
            // if right child is greater than left child, and right child is greater than its parent then swap them and recursive call on bubble down until it is re-heapified
            if (heapArray.get(rightChild(i)).compareTo(heapArray.get(leftChild(i)))>0) {
                if (heapArray.get(rightChild(i)).compareTo(heapArray.get(i)) > 0) {
                    swap(rightChild(i), i);
                    bubbleDown(rightChild(i));
                }
            }
            // opposite of above
            else {
                if (heapArray.get(leftChild(i)).compareTo(heapArray.get(i)) > 0) {
                    swap(leftChild(i), i);
                    bubbleDown(leftChild(i));
                }
            }
        }
    }

    /**
     * Takes elements from an arrayList and adds them to a heapArray
     * @param array
     */
    public void buildMaxHeap(ArrayList<E> array) {
        for (E element : array) {
            this.insert(element);
        }
    }

    /**
     * Creates a heap array and then goes through the array, removes the max of the heapArray, and then sets the element at the current index of the array to the max that was removed from the heapArray
     * @param array
     * @return Returns the sorted array
     */
    public ArrayList<E> sort(ArrayList<E> array) {
        heapArray.clear();
        buildMaxHeap(array);
        for (int i = 0; i < heapArray.size(); i++) {
            array.set(i, this.removeMax());
        }
        return array;
    }


    /**
     * Converts the heapArray to a string in the format of a heap
     * @return
     */
    @Override
    public String toString() {
       String myString = "";
       // formula for determining which elements are on each line of the string based on index
       for (int i = 0; i < (int)(((Math.log(heapArray.size())/ Math.log(2))+1)); i++) {
           for (int j = 0; j < Math.pow(2, i); j++) {
               if ((int)Math.pow(2,i)-1+j >= heapArray.size()) {
                   break;
               }
               myString += heapArray.get((int)Math.pow(2,i)-1+j) + "        ";
           }
           myString += "\n";
       }
       return myString;
    }


}
