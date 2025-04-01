/*
Name: Lindsey Turner
Date: 3/22/24
Program Description: This program reads through a CSV file to create an array list containing a list with entries for city, state, and avg values for the traits of that specific city. It can compute average
personality within a state, along with top and bottom states in terms of personality traits.

 */
package src;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        CityDataset cd = new CityDataset("/homes/lturner/cs106/CityPersonalities/src/big5ByCity.csv");
        System.out.println(cd.getCityTrait("extroversion", "Providence", "Rhode Island"));
        System.out.println(cd.getCityTrait("beauty", "Providence", "Rhode Island"));
        System.out.println(cd.getCityTrait("agreeableness", "New York", "New Hampshire"));
        System.out.println(cd.getTopState("conscientiousness"));
    }
}



