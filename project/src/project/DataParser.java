package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

/**
 * Parses the data from the JSON file and is stored into Hashmaps
 * 
 * @author Ahmad Gharib
 */
public class DataParser {
	private static final String datasetPath = "..\\data\\200ksample.json";

	// Returns all the subreddits with all the words used in their comments with the appropriate word-count
	public static HashMap<String, HashMap<String, Integer>> parseData() {
		// Initialize array of CommentT objects
		HashMap<String, HashMap<String, Integer>> hashmap = new HashMap<>();

		// Initialize scanner for reading file
		Scanner input = null;
		try {
			input = new Scanner(new File(datasetPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Loops through all the comments in the dataset
		while (input.hasNextLine()) {
			String line = input.nextLine();
			
			JSONObject obj = new JSONObject(line);
			String body = obj.getString("body");
			String subreddit = obj.getString("subreddit");
			int score = obj.getInt("score");
			
			// If the subreddit doesn't yet exist in the hashmap, add it
			if (hashmap.get(subreddit) == null) {
				hashmap.put(subreddit, new HashMap<String, Integer>());
			}
			
			// All the words in the comment
			String[] words = body.split("\\s+");

			// For every word in the comment, if the word doesn't exist in the corresponding subreddit, add it
			// if it already exists, increment the word-counter by one
			for (int i = 0; i < words.length; i++) {
				if (hashmap.get(subreddit).get(words[i]) == null) {
					
					// Only add the word if it is not "[deleted]"
					if (!words[i].equals("[deleted]")) {
						hashmap.get(subreddit).put(words[i], 0);
					}
				} else {
					hashmap.get(subreddit).put(words[i], hashmap.get(subreddit).get(words[i]) + 1);
				}
			}
		}
		
		return hashmap;
	}
}
