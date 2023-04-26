package ie.tudublin;

import java.util.ArrayList;
import processing.core.PApplet;

public class DANI extends PApplet {
	// Instance variable for the model ArrayList, which holds Word objects
	ArrayList<Word> model = new ArrayList<>();
	// Array to store the lines of the sonnet
	String[] sonnet;

	public void settings() {
		size(1000, 1000);
		// fullScreen(SPAN);
	}

	// Method to write a 14-line sonnet using the model
	public String[] writeSonnet() {
		String[] sonnet = new String[14];
		for (int i = 0; i < 14; i++) {
			sonnet[i] = writeLine();
		}
		return sonnet;
	}

	// Method to write a single line for the sonnet using random follow words
	public String writeLine() {
		// StringBuilder to store the current line
		StringBuilder sb = new StringBuilder();
		// Counter to ensure each line has no more than 8 words
		int wordCount = 0;
		// Pick a random starting word from the model
		Word currentWord = model.get((int) random(model.size()));

		// Continue adding words to the line until the maximum word count is reached or no follow word is found
		while (currentWord != null && wordCount < 8) {
			sb.append(currentWord.getWord()).append(" ");
			currentWord = pickRandomFollow(currentWord);
			wordCount++;
		}

		return sb.toString().trim();
	}

	// Method to pick a random follow word from the follows ArrayList of a given word
	public Word pickRandomFollow(Word word) {
		if (word.getFollows().size() == 0) {
			return null;
		}

		String randomFollowWord = word.getFollows().get((int) random(word.getFollows().size())).getWord();
		return findWord(randomFollowWord);
	}
	
	// Method to initialize the model with a given text file
	public void initializeModel(String filename) {
		loadFile(filename);
	}

	public void setup() {
		colorMode(HSB);
		// Load the text file into the model
		initializeModel("shakespere.txt");
		// Generate the initial sonnet
		sonnet = writeSonnet();
		// Print the sonnet to the console
		for (String line : sonnet) {
			println(line);
		}
	}

	// Method to generate a new sonnet on key press
	public void keyPressed() {
		if (key == ' ') {
			sonnet = writeSonnet();
		}
	}	

	float off = 0;

	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
		textAlign(CENTER, CENTER);
	
		// Calculate the lineHeight and yOffset for proper vertical alignment
		float lineHeight = height / 30;
		float yOffset = (height - lineHeight * sonnet.length) / 2;
	
		// Draw each line of the sonnet on the screen
		for (int i = 0; i < sonnet.length; i++) {
			text(sonnet[i], width / 2, yOffset + (i * lineHeight));
		}
	}
	
		// Method to load the text file into the model, creating Word and Follow objects as necessary
		public void loadFile(String filename) {
			String[] lines = loadStrings(filename);
			String prevWord = null;
	
			// Loop through each line in the text file
			for (String line : lines) {
				String[] words = split(line, ' ');
	
				// Loop through each word in the current line
				for (String w : words) {
					// Remove any non-word characters and convert the word to lowercase
					w = w.replaceAll("[^\\w\\s]", "").toLowerCase();
	
					// If there is a previous word, update the model with the current word as a follow word
					if (prevWord != null) {
						// Find the Word object for the previous word or create one if it doesn't exist
						Word wordObj = findWord(prevWord);
						if (wordObj == null) {
							wordObj = new Word(prevWord);
							model.add(wordObj);
						}
	
						// Find the Follow object for the current word or create one if it doesn't exist
						Follow followObj = wordObj.findFollow(w);
						if (followObj == null) {
							wordObj.addFollow(new Follow(w, 1));
						} else {
							// Increment the count of the Follow object if it already exists
							followObj.incrementCount();
						}
					}
					// Update the previous word to be the current word for the next iteration
					prevWord = w;
				}
			}
		}
	
		// Method to find a Word object in the model with a given string
		public Word findWord(String str) {
			for (Word wordObj : model) {
				if (wordObj.getWord().equals(str)) {
					return wordObj;
				}
			}
			return null;
		}
	
		// Method to print the model to the console for debugging purposes
		public void printModel() {
			for (Word wordObj : model) {
				println(wordObj.toString());
			}
		}
	
	}
	