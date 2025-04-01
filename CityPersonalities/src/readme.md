<h1 style="text-align: center;">Lab 3: City Personalities</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: **</h3>


# Overview

For this assignment, you’ll put in practice what we've covered in class about OOP and try to solve an interesting problem
involving the personalities of the inhabitants of certain American cities. Your task will be to compute the average personality 
within an American state and output top and bottom states in terms of certain personality traits. 

# Background
From 2007 and 2012, Prof David Stillwell from the University of Cambridge, UK, (later joined by Michal Kosinski, now at
Stanford) conducted a research project to understand the personality of Facebook users. This project, called 
[myPersonality](https://sites.google.com/michalkosinski.com/mypersonality), envisioned measuring the user's 
personality traits based on an online questionnaire in the form of an app that also gathered demographics info about them. 
Each person's personality was measure in 5 dimensions (also called traits), following the acclaimed 
[Big 5](https://en.wikipedia.org/wiki/Big_Five_personality_traits) trait taxonomy: Openness to Experience (O), 
Conscientiousness (C), Extraversion (E), Agreeableness (A), Neuroticism (N) (in this lab, we'll refer to these values as OCEAN).
The app ended up gathering more 4 million datapoints and led to the publications of various 
[scientific papers](https://sites.google.com/michalkosinski.com/mypersonality/publications?authuser=0) in the fields of 
human psychology, health, and behavior. Unfortunately, the project was eventually closed due to issues in maintainability.

# The Input
While most of the data from the myPersonality project is not available anymore, one can still find a dataset of the average 
personality of many US cities, based on the project's original data. A simplified version of this dataset is presented in 
`big5ByCity.csv`. This file contains lines in the following format:

``City, State, E, N, A, C, O``

where the comma-separated fields have the following meanings:
- `City` and `State`: the src.City and the state it belongs to where the personality data was collected.
-  `E`, `N`, `A`, `C` and `O`: the average values for Extraversion, Neuroticism, Agreeableness, Conscientiousness and Openness,
respectively, for that particular src.City. The higher they are the more present that trait was in that given population on average.

The goal of this lab is to read and gather some simple statistics about this data.

# Classes to be Implemented

## 1. ` City` class
This class will model the data present in each row of `big5ByCity.csv`, i.e., each src.City. Because of that, we should assume
it should hold values for the src.City's name, state and levels of the OCEAN values. It should also behave in such a way that
it gives back to the user this data as it is requested.

### 1.1 Attributes
Create one attribute for the name of the src.City and for its state. Also create a separate attribute for each OCEAN value. 
Notice that these attributes should, as always be private.

### 1.2. Constructor method
You should implement a simple constructor that receives 7 inputs and gives value to each of its 7 attributes accordingly.

### 1.3. Other Methods
In this lab, you only need to implement the getters and setters for the src.City and state name attributes. For the trait attributes,
write a method `getTrait(String trait)` that should return the value of the trait in `trait`. Make sure to throw an 
exception if the trait is not one of the 5 possible. Also add a `toString()` method to this class.

## 2. `src.CityDataset` class

This is the class that will read in the dataset and create an ArrayList of cities. It will also implement methods to gather statistics about the data. 
In here, **use the enhanced `for` loop as often as possible**.

### 2.1. Attribute
In here, you'll need an ArrayList of cities (i.e., `ArrayList<City> cities`) as your only attribute. It will hold the data
from `big5ByCity.csv` dataset. Remember that it has to be initialized in a constructor method somehow as any other attribute.

# 2.2. Constructor method
Here, you should only receive a string containing the dataset file name. It should then read the CSV file (more details 
about it in the next lines) and fill in the `cities` ArrayList attribute with its row data. Note that, upon reading the
dataset, you'll have an `ArrayList<String[]>` not an `ArrayList<City>` as your attribute requires. 

# 2.3. Other Methods
You should also add the following methods:
- `getCityTrait(String trait, String cityName, String cityState)`: returns a given OCEAN trait of a given src.City.
- `getAverageStateTrait(String trait, String state)`: returns the average of a given OCEAN trait of a given state.
- `getTopState(String trait)`: returns a string with the name of the state with the highest average of a certain trait.
- `getBottomState(String trait)`: returns a string with the name of the state with the lowest average of a certain trait.
- `ArrayList<String> getStateNames()`:  returns an ArrayList of the (unique) state names found in our dataset.

Make sure to decide which methods should be public or private and to throw the necessary exceptions when needed.
Also, few free to add more methods, if you find it convenient. 

### About the CSVReader Class

In this lab project there is included a class called `CSVReader` that allows you to read in data. You can read in
data from `big5ByCity.csv` using the below code. You'll also need to add the appropriate imports (which IntelliJ 
includes automatically if requested). This class understands that the first row is the "header" and does
not include it in the data. The following code demonstrates how you should use it to read the data using `CSVReader`:

```Java
CSVReader reader = new CSVReader();
FileReader input = new FileReader(csvFileName);
ArrayList<String[]> myEntries = reader.read(input);
```
where `csvFileName` is the string containing the name of the CSV file you want to read. This will give you an ArrayList
(called `myEntries`), where each index is a String array holding the row data. The indices in the String array
correspond to the column indices. For example, the following is a visualization `myEntries`:

```
{"Abbeville", "Louisiana", "-0.0", "0.02", "0.04", "-0.03", "-0.04"},                 // row 1
{"Aberdeen", "Maryland", "-0.04", "0.08", "-0.07", "-0.03", "-0.07"},      // row 2
.
.
.
```
After you read the file and process it, don't forget to close it with

```
reader.close()
```
# 3. ` Main` class
In the "main" class, you will have a main method that will test the functionalities from the above methods. Make sure to 
at least test the methods in `src.CityDataset` in it, looking for extreme/corner cases.



# Sample Outputs

Here are some examples of input/output for this lab (you don't need to strictly use the error messages below or use 
the same examples):

### First example
Testing in `main`:
```
src.CityDataset cd = new src.CityDataset("big5ByCity.csv");
System.out.println(cd.getCityTrait("extroversion", "Providence", "Rhode Island"));
```
Output:
```
0.01
```

### Second example
Testing in `main`:
```
src.CityDataset cd = new src.CityDataset("big5ByCity.csv");
System.out.println(cd.getCityTrait("beauty", "Providence", "Rhode Island"));
```
Output:
```
Exception in thread "main" java.lang.IllegalArgumentException: Unavailable trait name!
	at City.getTrait(City.java:32)
	at src.CityDataset.getCityTrait(src.CityDataset.java:30)
	at Main.main(Main.java:8)
```

### Third example
Testing in `main`:
```
src.CityDataset cd = new src.CityDataset("big5ByCity.csv");
System.out.println(cd.getCityTrait("agreeableness", "New York", "New Hampshire"));
```
Output:
```
Exception in thread "main" java.lang.IllegalArgumentException: Wrong state or src.City!
	at src.CityDataset.getCityTrait(src.CityDataset.java:33)
	at Main.main(Main.java:8)
```

### Fourth example
Testing in `main`:
```
System.out.println(cd.getTopState("conscientiousness"));
```
Output:
```
New Mexico
```

# Notes for Lab 3

- Use `.equals()` or `.compareTo()` when comparing strings. Consult the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) if needed.
- Remember the ArrayList methods, such as `add`, `get`, `contains`, etc.

# General Style & Coding Tips

Below are some helpful general tips and style guidelines for all labs (they will be taken into account during grading):

-   Remember to use Javadoc style conventions while commenting.
    > For reference on Javadoc style, check out [this resource](https://www.tutorialspoint.com/java/java_documentation.htm).
    > Don't worry about tags, just look at the examples at the top. Plus, use [this code example](https://pythontutor.com/java.html#code=/**%0A%20*%20Java%20program%20that%20computes%20the%20sum%20of%20the%20first%20n%20numbers%0A%20*%20%40author%20Jeova%20Farias%0A%20*%20%40version%20January%2013,%202022%0A%20*/%0Apublic%20class%20Main%20%7B%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20int%20n%20%3D%2010%3B%0A%20%20%20%20%20%20%20%20int%20result%20%3D%20sumIntegers%28n%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28result%29%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Returns%20the%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*%20%40param%20n%20positive%20integer%20n%0A%20%20%20%20%20*%20%40return%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*/%0A%20%20%20%20public%20static%20int%20sumIntegers%28int%20n%29%7B%0A%20%20%20%20%20%20%20%20int%20total%20%3D%200%3B%20//%20Set%20up%20a%20variable%20for%20the%20total%0A%20%20%20%20%20%20%20%20for%20%28int%20i%20%3D%200%3B%20i%20%3C%3D%20n%3B%20i%2B%2B%29%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20total%20%2B%3D%20i%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20total%3B%0A%20%20%20%20%7D%0A%7D&cumulative=false&heapPrimitives=nevernest&mode=edit&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false)
    from class as a reference for what your style should look like.

-   Write your variables in CamelCase, and be sure to indent properly.

-   Don't forget to add comments! Include...
     - A document header (name, date, program description)
     - Comments at the top of each function describing what the function does
     - Comments within the functions as needed, to describe non-obvious steps

-   When working on larger programs, test as you go! That is, write
    a little of code, then make sure it works before building on it.

-   Focus on correctness rather than efficiency.