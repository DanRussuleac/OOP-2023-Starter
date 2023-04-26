package ie.tudublin;

public class Follow {
    // Instance variables for the word and its count
    private String word;
    private int count;

    // Constructor to initialize the word and its count
    public Follow(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // Getter for the word
    public String getWord() {
        return word;
    }

    // Getter for the count
    public int getCount() {
        return count;
    }

    // Setter for the word
    public void setWord(String word) {
        this.word = word;
    }

    // Setter for the count
    public void setCount(int count) {
        this.count = count;
    }

    // Method to increment the count by 1
    public void incrementCount() {
        this.count++;
    }

    // Overriding the toString method for custom string representation
    @Override
    public String toString() {
        return word + "(" + count + ")";
    }
}
