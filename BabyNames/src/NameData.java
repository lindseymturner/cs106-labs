public class NameData {
    private String name;
    private Double numOccurrences;
    public NameData(String name, Double numOccurrences) {
        this.name = name;
        this.numOccurrences = numOccurrences;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNumOccurrences() {
        return numOccurrences;
    }

    public void setNumOccurrences(Double numOccurrences) {
        this.numOccurrences = numOccurrences;
    }

   public String toString() {
        return "Name: " + name + ", Number of occurrences: " + numOccurrences;
    }
    public int compareTo(NameData otherData) {
        return this.name.compareTo(otherData.getName());
    }

    public void incrementNum(Double value) {
        numOccurrences += value;
    }
}
