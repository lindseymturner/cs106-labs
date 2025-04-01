import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
Name: Lindsey Turner
Date: 4/25/24
Program Description: This program takes poll results from the csv files given in the command arguments and updates the entries of a binary search tree so that it stores the name and current polling percentage for each candidate.
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // creates a tree consisting of candidate objects
        LinkedBinarySearchTree<Candidate> tree = new LinkedBinarySearchTree<>();

        // goes through each csv file given in the command line
        for (String filename : args) {
            CSVReader reader = new CSVReader();
            FileReader input = new FileReader(filename);
            // creates an array list with each element containing a line from the csv file
            ArrayList<String[]> entries = reader.read(input);
            // takes each piece of info from that line and creates a candidate with that info, inserts candidate into the tree
            for (String[] entry : entries) {
                String answer = entry[0];
                String candidate = entry[1];
                double pct = Double.parseDouble(entry[2]);
                Candidate myCandidate = new Candidate(answer, candidate, pct);
                tree.insert(myCandidate);
            }
        }
        System.out.println(tree);


        //tests
//        BinarySearchTree<Integer> intTree = new LinkedBinarySearchTree<Integer>();
//        intTree.insert(8);
//        intTree.insert(11);
//        intTree.insert(5);
//        intTree.insert(17);
//        intTree.insert(1);
//        intTree.insert(9);
//        intTree.insert(3);
//        System.out.println(intTree);

//        BinarySearchTree<Character> letterTree = new LinkedBinarySearchTree<Character>();
//        letterTree.insert('A');
//        letterTree.insert('C');
//        letterTree.insert('G');
//        letterTree.insert('B');
//        letterTree.insert('D');
//        letterTree.insert('G'); // inserting again, should replace
//        letterTree.insert('F');
//        letterTree.insert('E');
//        letterTree.insert('H');
//        letterTree.insert('I');
//        System.out.println("Size: " + letterTree.size());
//        System.out.println(letterTree);





    }
}
