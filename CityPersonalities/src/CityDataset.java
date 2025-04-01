package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class CityDataset {
    ArrayList<City> cities;

    /**
     * Creates an array list cities that contains each city object.
     * @param fileName takes in a string containing the name of the csv file
     * @throws FileNotFoundException
     */
    public CityDataset(String fileName) throws FileNotFoundException {
        this.cities = new ArrayList<>();
        CSVReader reader = new CSVReader();
        FileReader input = new FileReader(fileName);
        // creates an array list, with a list containing the data for each line of the csv file
        ArrayList<String[]> myEntries = reader.read(input);
        // goes through all data (info in each line of csv file) and creates a city object using the parameters in each element of the data list. then adds the myCity list to the cities array.
        for (String[] data : myEntries) {
            City myCity = new City(data[0], data[1], Double.parseDouble(data[6]), Double.parseDouble(data[5]), Double.parseDouble(data[2]), Double.parseDouble(data[4]), Double.parseDouble(data[3]));
            cities.add(myCity);
        }
      //  System.out.println(cities);

      //  reader.close();
    }

    /**
     * returns the value of a certain trait in a certain city and state
     * @param trait
     * @param cityName
     * @param cityState
     * @return returns a double for whatever the value of this trait in this city and state is
     * @throws Exception
     */
    public Double getCityTrait(String trait, String cityName, String cityState) throws Exception {
        for (City myCity : cities) {
            if (myCity.getCity().equals(cityName) && myCity.getState().equals(cityState)) {
                return myCity.getTrait(trait);
            }
        }
        throw new IllegalArgumentException("City not found");
    }

    /**
     * Finds the average value of a particular trait in a particular state.
     * @param trait
     * @param state
     * @return returns a double for the avg value that is calculated
     * @throws Exception
     */
    public double getAverageStateTrait(String trait, String state) throws Exception {
        int count = 0;
        double totalTraitValue = 0;

        //goes through each element of the cities arraylist and adds to the totalTrait value if the state matches the given state, and adds to the count so an avg can be calculated
        for (City myCity : cities) {
            if (myCity.getState().equals(state)) {
                totalTraitValue += myCity.getTrait(trait);
                count++;
            }
        }

        if (count == 0) {
            throw new IllegalArgumentException("No cities found for the given state");
        }

        return totalTraitValue / count;
    }

    /**
     * Finds the state that has the highest average for a particular trait
     * @param trait
     * @return returns a string for the state with the highest average value for a particular trait
     * @throws Exception
     */
    public String getTopState(String trait) throws Exception {
        double maxStateTrait = 0;
        String maxState = null;

        // goes through each city in cities list and determines if the avg value for a trait is higher than the current max, if so, this state replaces maxState and the max value is updated
        for (City myCity : cities) {
            String state = myCity.getState();
            double avgTrait = getAverageStateTrait(trait, state);
            if (avgTrait >= maxStateTrait) {
                maxStateTrait = avgTrait;
                maxState = state;
            }
        }
        return maxState;
    }

    /**
     * Finds the state with the lowest average value for a particular trait
     * @param trait
     * @return returns a string for the state with the lowest average value for a particular trait
     * @throws Exception
     */
    public String getBottomState(String trait) throws Exception {
        double minStateTrait = 10000000;
        String minState = null;

        // goes through each city in cities list and determines if the avg value for a trait is lower than the current min, if so, this state replaces minState and the min value is updated
        for (City myCity : cities) {
            String state = myCity.getState();
            double avgTrait = getAverageStateTrait(trait, state);
            if (avgTrait < minStateTrait) {
                minStateTrait = avgTrait;
                minState = state;
            }
        }
        return minState;
    }

    /**
     * Creates an arrayList containing each state only once
     * @return returns the arrayList
     */
    public ArrayList<String> getStateNames() {
        ArrayList<String> stateNames = new ArrayList<>();

        for (City myCity : cities) {
            if (!stateNames.contains(myCity.getState())) {
                stateNames.add(myCity.getState());
            }
        }

        return stateNames;
    }
}

