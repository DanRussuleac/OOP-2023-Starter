package ie.tudublin;

import java.util.ArrayList;

public class Word {
    // Instance variables for the word and its follows ArrayList
    private String word;
    private ArrayList<Follow> follows;

    // Constructor to initialize the word and create an empty follows ArrayList
    public Word(String word) {
        this.word = word;
        follows = new ArrayList<>();
    }

    // Getter for the word
    public String getWord() {
        return word;
    }

    // Getter for the follows ArrayList
    public ArrayList<Follow> getFollows() {
        return follows;
    }

    // Setter for the word
    public void setWord(String word) {
        this.word = word;
    }

    // Method to add a follow object to the follows ArrayList
    public void addFollow(Follow follow) {
        follows.add(follow);
    }

    // Method to find a follow object in the follows ArrayList by its word
    public Follow findFollow(String str) {
        for (Follow follow : follows) {
            if (follow.getWord().equals(str)) {
                return follow;
            }
        }
        return null;
    }

    // Overriding the toString method for custom string representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(": ");
        for (Follow follow : follows) {
            sb.append(follow.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
