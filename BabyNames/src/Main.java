/*
Name: Lindsey Turner
Date: 04/04/2024
Program Description: This program takes in names and file names and gives the ranking of each provided name.

 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> maleNames = new ArrayList<>();
        ArrayList<String> femaleNames = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<>();

// adds command line arguments to one of the 3 array lists
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-f") || args[i].equals("-m")) {
                // Check if the next argument exists and is not the last one
                if (i + 1 < args.length) {
                    if (args[i].equals("-f")) {
                        femaleNames.add(args[i + 1]);
                    } else {
                        maleNames.add(args[i + 1]);
                    }
                    i++; // Skip to the next argument
                } else {
                    System.out.println("Error: Missing baby name after -f or -m option.");
                }
            } else {
                // Assume it's a database filename
                fileNames.add(args[i]);
            }
        }
        DoublyLinkedList males = new DoublyLinkedList();
        DoublyLinkedList females = new DoublyLinkedList();


// populates doubly linked lists by reading the files in the array list
        try {
            if (fileNames.size() == 0) {
                throw new FileNotFoundException("No file name provided");
            }
            for (String fileName : fileNames) {

                CSVReader reader = new CSVReader();
                FileReader input = new FileReader(fileName);
                ArrayList<String[]> myEntries = reader.read(input);
                //reader.close();
                for (String[] line : myEntries) {
                    // if this name doesn't already exist in the list, add it
                    if (males.findPosition(line[1]) == -1) {
                        NameData newName = new NameData(line[1], Double.parseDouble(line[2]));
                        males.insertAlpha(newName);
                    } else {
                        males.fetch(line[1]).incrementNum(Double.parseDouble(line[2]));
                    }
                    // if this name doesn't already exist in the list, add it
                    if (females.findPosition(line[3]) == -1) {
                        NameData newName = new NameData(line[3], Double.parseDouble(line[4]));
                        females.insertAlpha(newName);
                    } else {
                        females.fetch(line[3]).incrementNum(Double.parseDouble(line[4]));
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File name not found");
        }
// goes through each name in array lists and gets the position and percentage of occurrences out of all names
        try {
            for (String name : maleNames) {
                Double numOccur = males.fetch(name).getNumOccurrences();
                Double total = males.getTotalCount();
                double percent = numOccur/total*100;
                System.out.println(name + ": " + numOccur + " occurrences in " + total + " names" + " (" + percent + "%)");
                System.out.println("Position of " + name + " in the linked list: " + males.findPosition(name));
            }
            for (String name : femaleNames) {
                try {
                    Double numOccur = females.fetch(name).getNumOccurrences();
                    Double total = females.getTotalCount();
                    double percent = numOccur/total*100;
                    System.out.println(name + ": " + numOccur + " occurrences in " + total + " names" + " (" + percent + "%)");
                    System.out.println("Position of " + name + " in the linked list: " + females.findPosition(name));
                }
                catch (NullPointerException e) {
                    System.out.println("Name not found");
                }

            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("No names to look up!");
        }

    }
}
