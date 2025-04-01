import org.apache.commons.collections.ArrayStack;

/*
Name: Lindsey Turner
Date: 5/14/24
Program Description: This program will use polling data from a CSV file and will use a heap to sort candidates by their polling numbers. It also implements a PriorityQueue.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("Have fun with the Binary Search Trees Lab");
        //Put your main program here, uncomment next line after creating your ArrayHeap class");
	    //tests12();
//        Integer[] arr = {-2,3,9,-7,1,2,6,-3};
//        ArrayHeap<Integer> heap = new ArrayHeap<>();
//        for (Integer num : arr) {
//            heap.insert(num);
//        }
//        System.out.println(heap);

//        ArrayHeap<Character> letterHeap = new ArrayHeap<Character>();
//        letterHeap.insert('A');
//        letterHeap.insert('C');
//        letterHeap.insert('G');
//        letterHeap.insert('B');
//        letterHeap.insert('D');
//        letterHeap.insert('G'); // inserting again, will still keep both copies
//        letterHeap.insert('F');
//        letterHeap.insert('E');
//        letterHeap.insert('H');
//        letterHeap.insert('I');
//        System.out.println("size:" + letterHeap.size());
//        System.out.println(letterHeap);

////        // create an ArrayList using static method "asList"
//        Integer[] arr = { -2, 3, 9, -7, 1, 2, 6, -3 };
//        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));
//
////// make a new heap out of the array
//        ArrayHeap<Integer> heap = new ArrayHeap<Integer>();
//        ArrayList<Integer> sortedArray = heap.sort(array);
//        System.out.println(sortedArray + "\n");


        // then read in the polling data from the given file (just one) and create a heap of candidates to be sorted by their polling numbers

        filesReader("poll_data/dempres_20181106_1.csv");

    }
    private static void tests12() {

//        System.out.println("Testing Integer Insertion: ");
//        Integer[] arr1 = {-2, 3, 9, -7, 1, 2, 6, -3 };
//        //Uncomment entire block below when ready to do testing - had to comment out so file would compile before the class was created
//
//        PriorityQueue<Integer> numberHeap = new ArrayHeap<Integer>();
//        for (int number : arr1) {
//            numberHeap.insert(number);
//        }
//        System.out.println(numberHeap);
//        // System.out.println(numberHeap + "\n"); // uncomment this if you have a nice heap-printing function

//        System.out.println("Testing removeMax() on Integers: ");
//        Integer topWas = numberHeap.removeMax();
//        System.out.println("Top should have been 9, was " + topWas);
//        topWas = numberHeap.removeMax();
//        System.out.println("Top should have been 6, was " + topWas);

    }

    public static void filesReader(String fileName) throws FileNotFoundException {
        CSVReader reader = new CSVReader();
        ArrayList<String[]> myEntries = new ArrayList<>();
        ArrayList<Candidate> candidates = new ArrayList<>();
        ArrayHeap<Candidate> heap = new ArrayHeap<>();
        try {
            // adds data from CSV file to myEntries
            FileReader input = new FileReader(fileName);
            myEntries = reader.read(input);
        }
        catch (FileNotFoundException e) {
            System.out.println("error: file not found");
        }
        // goes through each entry (line of csv file) and creates a Candidate object using the data in that line and adds it to candidates ArrayList
        for (String[] entry: myEntries) {
            candidates.add(new Candidate(entry[0], entry[1], Double.parseDouble(entry[2])));
        }
        // adds each Candidate object in the candidates array list to the heap
        for (Candidate person: candidates) {
            heap.insert(person);
        }
        System.out.println(heap);
    }
}
