package src;

import java.util.Objects;

public class City {
    private String name;
    private String state;
    private double E;
    public double O;
    private double C;
    private double A;
    private double N;


    /**
     * Constructs a city object containing a name, state, and the values for different traits.
     * @param name
     * @param state
     * @param O
     * @param C
     * @param E
     * @param A
     * @param N
     */
    public City(String name, String state, double O, double C, double E, double A, double N) {
        this.name = name;
        this.state = state;
        this.O = O;
        this.C = C;
        this.E = E;
        this.A = A;
        this.N = N;
    }

    /**
     * Gets the city name component of a city object
     * @return returns a string for the city name
     */
    public String getCity() {
        return name;
    }

    /**
     * Gets the state name component of a city object
     * @return returns a string for the state name
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the value of a particular trait within a city object
     * @param trait
     * @return returns the value of the specified trait
     * @throws Exception
     */
    public double getTrait(String trait) throws Exception {
        if (Objects.equals(trait, "openness")) {
            return O;
        }
        if (Objects.equals(trait, "conscientiousness")) {
            return C;
        }
        if (Objects.equals(trait, "extroversion")) {
            return E;
        }
        if (Objects.equals(trait, "agreeableness")) {
            return A;
        }
        if (Objects.equals(trait, "neuroticism")) {
            return N;
        }
        else {
            throw new Exception("Must be a trait");
        }
    }

    /**
     * Reassigns a new string to the city name component of a city object
     * @param name
     */
    public void setCity(String name) {
        this.name = name;
    }

    /**
     * Reassigns a new string to the state name component of a city object.
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Takes all the attributes of a city object and adds them to a string so that this information can be added to an arrayList called "cities"
     * @return
     */
    public String toString() {
        return "City:" + name + ", State:" + state + ", Openness:" + O + ", Conscientiousness:" + C + ", Extroversion:" + E + ", Agreeableness:" + A + ", Neuroticism:" + N + "\n";
    }
}

