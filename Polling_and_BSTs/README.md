<h1 style="text-align: center;">Lab 6: Polling Data and Binary Search Trees</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: *** </h3>


# Overview

For this assignment we will be working polling data for the 2020 U.S. presidential election. Before the two
main political parties put forward their nominees for president, the Democratic and Republican parties held primary
elections to determine who their nominee would be. The Republican nominee was essentially predetermined to be Donald
Trump. The Democratic party was converging on a decision, but over a year or so, the outcome was very
uncertain. In order to make repeated predictions about the likely outcome of the Democratic party nomination, pollsters
(statisticians) regularly conducted polls (surveys) to sample Democratic primary voters and asked who they planned to vote for.
These results were compiled, released, and eagerly tracked by the news media and public to determine which candidate
had the largest percentage of support.

In this assignment, your job will be to take the poll results given as input via CSV files and update the entries of a
binary search tree so that it stores the name and current polling percentage for each candidate.

There is an optional part
at the end to look at the “evolution” of the candidates over time.


# The Input
The website [FiveThirtyEight](https://data.fivethirtyeight.com/) makes polling data for presidential primary candidates
available. We have preprocessed this data for you so that only the relevant data is included and you will receive one
file per conducted poll.

Each polling data CSV has the following format:
```
answer,candidate,pct
Bennet,Michael F. Bennet,1.3
Biden,Joseph R. Biden Jr.,23.8
Bloomberg,Michael Bloomberg,1.8
Buttigieg,Pete Buttigieg,8.1
Gabbard,Tulsi Gabbard,3.1
Klobuchar,Amy Klobuchar,4.2
Patrick,Deval Patrick,0.0
Sanders,Bernard Sanders,30.8
Steyer,Tom Steyer,0.0
Warren,Elizabeth Warren,17.4
Yang,Andrew Yang,0.7
```
The first column gives the last name of the candidate, the second column gives the candidate’s full name, and the final
column is the percent the candidate is polling at in this poll. Note that the given percent can be a floating-point
number. As in the previous labs, read in the data from a file using the given CSVReader class, with the commands:
CSVReader reader = new CSVReader();
FileReader input = new FileReader(file);
ArrayList<String[]> myEntries = reader.read(input);
These csv files have a header, so this version of the CSVReader class is "Header Aware".

Each file is named something like `dempres_20190310_1.csv` where `dempres` indicates that these are polling results for the
Democratic party presidential primary, 20190310 indicates that the polling results were completed on March 10, 2019,
and `_1` indicates that these are the results for the first poll completed on that date (there may be multiple polls from
different sources). I have included about one file for each month, so you can see the evolution of the candidates.

Your job is to take the polling data in each file (indicated as a command-line argument) and insert it into the binary search tree.
Your resulting tree should contain the polling data for each candidate from the most recent date
for which there is data from the files given on the command line. Each polling result will only include some candidates.

# Classes to be Implemented

You are given the `BinarySearchTree` interface and one of your goals will be to create a Linked Binary Search Tree data structure
that implements it. Note that the interface requires that its elements are from a class that extends `Comparable`.
After that, the goal is to read polling data file as indicated in the command line argument and insert each candidate
into that tree. Finally, you should print the resulting tree in pre-, in- and post-orders.

## 1. `Candidate` class
This class should represent a candidate and should hold the information that each row of the CSV files contains.
It should also implement the `Comparable` interface so that polling data objects are put in order **based on the
candidate’s last name**. Finally, it should override the `toString` method to return a `String` with the following
formatting (for example):

```
Elizabeth Warren:17.4
```
where full name is first, then a colon, then the candidate’s polling percentage.

## 2. ` LinkedBinarySearchTree` class
You should implement the given BinarySearchTree interface as a `LinkedBinarySearchTree` so that generic objects that implement the
compareTo method from the `Comparable` interface can be inserted into your tree. Implement the methods required by the
interface according to what was discussed in class. Requirements are shown below:

1. Make sure the implementation is entirely recursive (except for methods `getRootElement` and `isEmpty`; `size` can
   be implemented recursively if you'd like) and is a linked data structure, i.e., **you should not use any for or while
   loops, arrays, `ArrayLists`, etc**. Note that the data structure itself should be recursive. In other words,
   the **left and right children of a tree should themselves be trees**. You should not have a `Node` class.

2. Insertion should be done using the `compareTo` method of the given element so that smaller elements are put into the left
   subtree and larger element are put into the right subtree. Insertion of elements that are already in the tree should
   *replace* the current element. When you put your polling data into the tree this will be equivalent to updating the
   poll numbers for a candidate (In reality we should also delete candidates who have dropped out, but we’ll omit this for
   now).

3. As part of implementing the `BinarySearchTree` interface you will implement all three orders for tree traversal.
   Your returned string should be in the form:
```
element1 element2  ... elementn
```
Where the order is determined by the correct order for the specific traversal.
Note that these methods should also use a recursive design. There should be one
space between each element.

4. You add a `toString` method to return a `String` that looks like the following (use `\t` for tab):
```
Tree:
Pre:    b a c
In:     a b c
Post:   a c b
```
Where the first traversal is a pre-order traversal, the second is in-order, and the last is post-order.

## 3. ` Main` class
The main file will just be responsible for receiving the CSV filenames from the command line arguments and adding each row
of the CSV files to a Binary Search Tree. At the end you should print that tree as stated above. You may be given multiple
filenames (an example is shown below). Ideally, you should process the given files in increasing date order, but you may
assume that the files are given in this order.


# Output

Here are some examples of input/output for this lab:

### First example
Testing in main:
```
BinarySearchTree<Integer> intTree = new LinkedBinarySearchTree<Integer>();
intTree.insert(8);
intTree.insert(11);
intTree.insert(5);
intTree.insert(17);
intTree.insert(1);
intTree.insert(9);
intTree.insert(3);
System.out.println(intTree);
```
Output:
```
Tree:
Pre:    8 5 1 3 11 9 17
In:     1 3 5 8 9 11 17
Post:   3 1 5 9 17 11 8
```

### Second example
Testing in main:
```
BinarySearchTree<Character> letterTree = new LinkedBinarySearchTree<Character>();
letterTree.insert('A');
letterTree.insert('C');
letterTree.insert('G');
letterTree.insert('B');
letterTree.insert('D');
letterTree.insert('G'); // inserting again, should replace
letterTree.insert('F');
letterTree.insert('E');
letterTree.insert('H');
letterTree.insert('I');
System.out.println("Size: " + letterTree.size());
System.out.println(letterTree);
```
Output:
```
Size: 9
Tree:
Pre:    A C B G D F E H I
In:     A B C D E F G H I
Post:   B E F D I H G C A
```

### Third example
Command line argument:
```
poll_data/dempres_20190103_1.csv poll_data/dempres_20190202_1.csv poll_data/dempres_20190302_1.csv
```
Output:
```
Tree:
Pre:    Bernard Sanders:21.1 Joseph R. Biden Jr.:37.0 Beto O'Rourke:5.0 Joseph Kennedy III:9.0 Kamala D. Harris:9.0 Hillary Rodham Clinton:3.0 Cory A. Booker:5.9 Michael Bloomberg:1.9 Sherrod Brown:0.9 Steve Bullock:0.0 Julián Castro:0.2 Pete Buttigieg:0.4 Kirsten E. Gillibrand:3.3 Andrew Cuomo:0.0 John K. Delaney:0.0 Eric Garcetti:0.0 Tulsi Gabbard:1.5 John Hickenlooper:1.0 Jay Robert Inslee:0.0 Eric H. Holder:0.0 John Kerry:1.0 Amy Klobuchar:0.9 Terry R. McAuliffe:0.0 Gavin Newsom:0.0 Richard Neece Ojeda:1.0 Elizabeth Warren:5.2 Tom Steyer:1.0 Howard Schultz:0.0 Eric Swalwell:0.0
In:     Joseph R. Biden Jr.:37.0 Michael Bloomberg:1.9 Cory A. Booker:5.9 Sherrod Brown:0.9 Steve Bullock:0.0 Pete Buttigieg:0.4 Julián Castro:0.2 Hillary Rodham Clinton:3.0 Andrew Cuomo:0.0 John K. Delaney:0.0 Tulsi Gabbard:1.5 Eric Garcetti:0.0 Kirsten E. Gillibrand:3.3 Kamala D. Harris:9.0 John Hickenlooper:1.0 Eric H. Holder:0.0 Jay Robert Inslee:0.0 Joseph Kennedy III:9.0 John Kerry:1.0 Amy Klobuchar:0.9 Terry R. McAuliffe:0.0 Gavin Newsom:0.0 Beto O'Rourke:5.0 Richard Neece Ojeda:1.0 Bernard Sanders:21.1 Howard Schultz:0.0 Tom Steyer:1.0 Eric Swalwell:0.0 Elizabeth Warren:5.2
Post:   Michael Bloomberg:1.9 Pete Buttigieg:0.4 Julián Castro:0.2 Steve Bullock:0.0 Sherrod Brown:0.9 Cory A. Booker:5.9 Tulsi Gabbard:1.5 Eric Garcetti:0.0 John K. Delaney:0.0 Andrew Cuomo:0.0 Kirsten E. Gillibrand:3.3 Hillary Rodham Clinton:3.0 Eric H. Holder:0.0 Jay Robert Inslee:0.0 John Hickenlooper:1.0 Kamala D. Harris:9.0 Gavin Newsom:0.0 Terry R. McAuliffe:0.0 Amy Klobuchar:0.9 John Kerry:1.0 Joseph Kennedy III:9.0 Richard Neece Ojeda:1.0 Beto O'Rourke:5.0 Joseph R. Biden Jr.:37.0 Howard Schultz:0.0 Eric Swalwell:0.0 Tom Steyer:1.0 Elizabeth Warren:5.2 Bernard Sanders:21.1
```

# Notes for Lab 6

- Use `.equals()` when comparing strings.  For this lab, you do not need to overwrite the `.equals()` method for lists
  or nodes, you can use `String` to compare two strings directly.
- Consult the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) to learn about
  the`.compareTo()` method for strings in Java.

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
