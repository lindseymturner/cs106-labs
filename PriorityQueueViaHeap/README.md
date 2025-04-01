<h1 style="text-align: center;">Lab 7: Priority Queues and Heapsort</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: *** </h3>


# Overview

For this assignment, you’ll continue your previous investigation of Democratic primary polling data by using a heap to
sort the candidates by their polling numbers (to determine which candidates are currently in the lead). You'll also 
implement a Priority Queue in this lab.


# The Input
The input for this lab will be the same as in the previous lab: some CSV files of polling data for presidential primary
candidates.


# Classes to be Implemented

## 1. `Candidate` class
This class is the same as last week's `Candidate` class with one change:  modify the compareTo method to instead **first 
compare the candidate’s polling numbers**. If there is a tie, then use the candidate’s last names.

## 2. ` ArrayHeap` class

In this class you will implement a priority queue based on a binary heap, and you'll also add a method that performs 
heapsort using the methods from the priority queue interface. This class should be generic with a type parameter `E` 
that is `Comparable<E>` (make sure to specify this correctly in the class signature). Unlike last week when we were using recursion,
you’re encouraged to use loops in your heap implementation. You may find it useful to use private or public helper 
methods that are called from the publicly defined methods in the interface. However, you still do not need a Node class,
the entire data structure will be contained within the `ArrayHeap` class.
### 2.1 Attribute
You should have only one instance variable, the array representing the heap. Since we do want to be able to add elements 
(thus resizing the array), we will use an `ArrayList` for this array.

### 2.2. Constructor  
You should implement one constructor:
1. `ArrayHeap()`: creates an empty priority queue/heap.

### 2.3. Methods
Since this class implements `PriorityQueue`, you should implement the following methods from that interface:
1. `size()`.
2. `isEmpty()`.
3. `max()`: return the root of the heap. If the heap is empty, you can either return null or throw an exception.
4. `insert(E element)`: first add the element to the end of the array. Then we need to “bubble up” the element until we 
have “re-heapified” our data structure. You do not need to deal with duplicates (i.e. if you add the same element 
twice, it will appear twice in the heap).
5. `removeMax()`: Returns and removes the heap's root element. _Hint:_ Wait to write this until you have tested `insert` 
and `toString` (see examples below).

Here are some suggested/optional extra helper methods for this class:

1. `swap(int i, int j)`: it swaps the two elements in the locations i and j.
2. `parent(int i)`: takes in a child index and returns the index of the parent
3. `leftChild(int i)`: takes in the index of a parent and returns the index of their left child (note that this could be 
off the end of the array).
4. `rightChild(int i)`: takes in the index of a parent and returns the index of their right child (note that this could
be off the end of the array)
5. `bubbleUp(int i)`: this should bubble up the element at the given index.

You should also add two methods that are related to the heapsort algorithm:
1. `buildMaxHeap(ArrayList<E> array)`: The set the arraylist attribute to the class to be a heap built from `array`. 
This method shouldn't return anything.
2. `sort(ArrayList<E> array)`: This method implements the heapsort algorithm as discussed in class and makes
use of the `buildMaxHeap`. It should return an arraylist of sorted elements.

Finally, you should override `toString()`:
5. `toString()`: this method should print out each level of the tree (as shown above). For example, if the array
   representation of a heap is [9 1 6 -3 -2 2 3 -7], `toString()` should return a string like:
```aidl
9
1 6
-3 -2 2 3
-7
```


## 3. ` Main` class
The main file will just be responsible for receiving a CSV filename (this time you can assume the user will only insert 
one CSV filename, i.e., he'll only use one polling dataset) from the command line arguments and add each row
of the CSV files to a Priority Queue. Then, you should remove the elements of maximum priority from that list one by one,
printing them as you go. This main class should also demonstrate the heapsort execution (just start with an unsorted array 
and print the result of the application of `sort()` on it) with an example of choice (you can simply use the example in 
the next section).  So your Main method should print out the candidates in order of priority, (as in the fourth example below), and it should also include a test of the sort() method on a heap of your choice (similar to the third example).



# Output

Here are some examples of input/output for this lab:

### First example
Testing in main:
```
Integer[] arr = {-2,3,9,-7,1,2,6,-3};

// TODO: create a new heap of Integers
// TODO: use a for loop to insert the elements above into the heap

System.out.println(heap);
```
Output:
```
9
1 6
-3 -2 2 3
-7
```

### Second example
Testing in main:
```
ArrayHeap<Character> letterHeap = new ArrayHeap<Character>();
letterHeap.insert('A');
letterHeap.insert('C');
letterHeap.insert('G');
letterHeap.insert('B');
letterHeap.insert('D');
letterHeap.insert('G'); // inserting again, will still keep both copies
letterHeap.insert('F');
letterHeap.insert('E');
letterHeap.insert('H');
letterHeap.insert('I');
System.out.println("size:" + letterHeap.size());
System.out.println(letterHeap);
```
Output:
```
size: 10
I
H G
E G C F
A D B
```

### Third example
Testing in main:
```
// create an ArrayList using static method "asList"
Integer[] arr = { -2, 3, 9, -7, 1, 2, 6, -3 };
ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));

// make a new heap out of the array
ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
ArrayList<Integer> sortedArray = heap.sort(array);
System.out.println(sortedArray + "\n");
```
Output:
```
[9, 6, 3, 2, 1, -2, -3]
```

### Fourth example
Command line argument:
```
poll_data/dempres_20190718_3.csv
```
Output:
```
Joseph R. Biden Jr.:27.0
Bernard Sanders:20.0
Elizabeth Warren:18.0
Kamala D. Harris:12.0
Pete Buttigieg:7.0
Beto O'Rourke:2.0
Tulsi Gabbard:2.0
Andrew Yang:1.0
Tom Steyer:1.0
Amy Klobuchar:1.0
John Hickenlooper:1.0
Kirsten E. Gillibrand:1.0
John K. Delaney:1.0
Julián Castro:1.0
Cory A. Booker:1.0
Bill de Blasio:0.0
Marianne Williamson:0.0
Joe Sestak:0.0
Tim Ryan:0.0
Seth Moulton:0.0
Wayne Messam:0.0
Jay Robert Inslee:0.0
Mike Gravel:0.0
Steve Bullock:0.0
Michael F. Bennet:0.0
```

# Notes for Lab 7

- Use `.equals()` when comparing strings.  For this lab, you do not need to overwrite the `.equals()` method for lists
  or nodes, you can use `String` to compare two strings directly.
- Consult the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) to learn about
  the`.compareTo()` method for strings in Java.
- You can convert a Java array into an ArrayList, using the static method `asList` from the library `Arrays` (which you 
should import into your project in order to use it). Example:
```
Integer[] arr = {-2,3,9,-7,1,2,6,-3};
ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));
```

# General Style & Coding Tips

Below are some helpful general tips and style guidelines for all labs (they will be taken into account during grading):

-   Remember to use Javadoc style conventions while commenting.
    > For reference on Javadoc style, check out [this resource](https://www.tutorialspoint.com/java/java_documentation.htm).
    > Don't worry about tags, just look at the examples at the top. Plus, use [this code example](https://pythontutor.com/java.html#code=/**%0A%20*%20Java%20program%20that%20computes%20the%20sum%20of%20the%20first%20n%20numbers%0A%20*%20%40author%20Jeova%20Farias%0A%20*%20%40version%20January%2013,%202022%0A%20*/%0Apublic%20class%20Main%20%7B%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20int%20n%20%3D%2010%3B%0A%20%20%20%20%20%20%20%20int%20result%20%3D%20sumIntegers%28n%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28result%29%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Returns%20the%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*%20%40param%20n%20positive%20integer%20n%0A%20%20%20%20%20*%20%40return%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*/%0A%20%20%20%20public%20static%20int%20sumIntegers%28int%20n%29%7B%0A%20%20%20%20%20%20%20%20int%20total%20%3D%200%3B%20//%20Set%20up%20a%20variable%20for%20the%20total%0A%20%20%20%20%20%20%20%20for%20%28int%20i%20%3D%200%3B%20i%20%3C%3D%20n%3B%20i%2B%2B%29%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20total%20%2B%3D%20i%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20total%3B%0A%20%20%20%20%7D%0A%7D&cumulative=false&heapPrimitives=nevernest&mode=edit&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false)
    from class as a reference for what your style should look like.

-   Write your variables in CamelCase, and be sure to indent properly.

-   Don't forget to add comments! Include...
    > - A document header (name, date, program description)
    > - Comments at the top of each function describing what the
        >   function does
    > - Comments within the functions as needed, to describe
        >   non-obvious steps

-   When working on larger programs, test as you go! That is, write
    a little of code, then make sure it works before building on it.

-   Focus on correctness rather than efficiency.