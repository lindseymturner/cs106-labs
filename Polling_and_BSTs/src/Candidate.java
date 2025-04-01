public class Candidate implements Comparable<Candidate>{
    public String answer;
    public String candidate;
    public double pct;

    /**
     * Constructs a candidate object
     * @param answer the candidate's last name
     * @param candidate the candidate's full name
     * @param pct the candidate's voting percentage
     */
    public Candidate(String answer, String candidate, double pct) {
        this.answer = answer;
        this.candidate = candidate;
        this.pct = pct;
    }

    public int compareTo(Candidate person) {
        return this.answer.compareTo(person.answer);
    }

    public double getPct() {
        return pct;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setPct(double pct) {
        this.pct = pct;
    }

    @Override
    public String toString() {
        return candidate + ": " + pct;
    }
}
