package project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Helper class that allows us to search on the parsed data to find appropriate subreddits for the user to advertise on.
 * 
 * @author Shahroz Siddiqi
 * @author Ahmad Gharib
 * @author Arshan Khan
 */
public class RedditSearch {
	/**
	 * @param hashmap - A hashmap that represents the subreddits with their respective word occurences 
	 * @param keyword - The word that we are considering to find the occurences for in each subreddit.
	 */
	public static void getTop5(HashMap<String, HashMap<String, Integer>> hashmap, String keyword) {
		HashMap<String, Integer> hashmap2 = new HashMap<String, Integer>();
		
		// Populate map2
		for (String element : hashmap.keySet()) {
			if (hashmap.get(element).containsKey(keyword)) {
				hashmap2.put(element, hashmap.get(element).get(keyword));
			}
		}
        
		// Stores the options (subreddits) and occurences (number of word occurences per subreddit)
        String[] options = new String[5];
        int[] occurences = new int[5];

        // Stores the list of the top 5 subreddits
        ArrayList<Entry<String, Integer>> top5Subreddits = findHighestValues(hashmap2, 5);

        // Add the values to the options and occurences to be displayed to GUI
        int counter = 0;
        for (Entry<String, Integer> element : top5Subreddits) {
            options[counter] = element.getKey();
            occurences[counter] = element.getValue();
            counter++; 
        }
        
        // Show the results on the GUI
        GUI.showResults(options, occurences);
	}

	/**
	 * @param hashmap - A hashmap that represents the subreddits with their respective word occurences 
	 * @param numberOfHighestValues - The number of top subreddits that are required. Typically this is set to 5 as we are looking for 5 best subreddits.
	 * @return finalvalue - arraylist that contains n number of highest suited subreddits where n is the numberOfHighestValues that was passed as an argument 
	 */
	public static <Key, Value extends Comparable<? super Value>> ArrayList<Entry<Key, Value>> findHighestValues(HashMap<Key, Value> hashmap, int numberOfHighestValues) {
		// Comparator for the priority queue using helper method
		Comparator<? super Entry<Key, Value>> cmp = compareHelper();
		
		// Gets the priority queue based on the function described above
		PriorityQueue<Entry<Key, Value>> greatestValuesPQ = new PriorityQueue<Entry<Key,Value>>(numberOfHighestValues, cmp);
		
		// For every value in the map
		for (Entry<Key, Value> element : hashmap.entrySet()) {
			// Insert the element into the priority queue
			greatestValuesPQ.offer(element);
			
			// Poll while the size of the priority queue is still less than the number of values needed
			// (numberOfHighestValues is usually 5 because we are looking for the 5 best subreddits to advertise on)
			while (greatestValuesPQ.size() > numberOfHighestValues) {
				greatestValuesPQ.poll();
			}
		}
		
		// Array list that stores the final values
		ArrayList<Entry<Key, Value>> finalValue = new ArrayList<HashMap.Entry<Key,Value>>();
		
		while (greatestValuesPQ.size() > 0) {
			// Add to the final value list
			finalValue.add(greatestValuesPQ.poll());
		}
		
		return finalValue;
	}
	
	/**
	 * Anonymous function to reduce the number of classes required for comparing values within the hashmap that compares two entries in the hashmap
	 * @return cmp - The integer result after comparing the two elements considered
	 */
	public static <Key, Value extends Comparable<? super Value>> Comparator<? super Entry<Key, Value>> compareHelper() {
		
		// Nested anonymous function that implements the comparator interface
		Comparator<? super Entry<Key, Value>> cmp =  new Comparator<Entry<Key, Value>>() {
        	public int compare(Entry<Key, Value> firstElement, Entry<Key, Value> secondElement) {
        		// Get the values of both elements
            	Value secondValue = secondElement.getValue();
            	Value firsValue = firstElement.getValue();
            	
            	// Return the comparison between these two values
            	return firsValue.compareTo(secondValue);
        	}
		};
		
		return cmp;
	}
	

	/**
	 * Prints out the list of Subreddits that are related to the item entered as
	 * input
	 */
	public static void getSubreddits() {
		HashMap<String, HashMap<String, Integer>> subreddits = DataParser.parseData();
		//Graph G = createGraph(subreddits);
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("What are you trying to sell: ");
		String item = input.nextLine();
		System.out.println("\nGood Subreddits:");
		for (String i : subreddits.keySet()) {
			if (subreddits.get(i).containsKey(item)) {
				System.out.printf("Subreddit: %s, Number of times item appears: %d\n",
						                                  i, subreddits.get(i).get(item));
			}
		}
		
		System.out.println("Related Subreddits:");
		// print out graph content
	}

	/**
	 * Converts hash map representation into list of SubReddit objects to make it
	 * easier to put in graph. Then creates a graph using that list.
	 * 
	 * @param hashmap The hash map of subreddits and their comments
	 * @return A Graph containing the subreddits as nodes
	 */
	private static Graph createGraph(HashMap<String, HashMap<String, Integer>> hashmap) {
		// Initialize list of SubReddit objects
		List<SubReddit> subreddit_list = new ArrayList<SubReddit>();

		// Iterate through hash map of subreddits, create new SubReddit object and add
		// to list
		int j = 0;
		for (String i : hashmap.keySet()) {
			subreddit_list.add(new SubReddit(i, hashmap.get(i), j++));
		}

		// Return a new graph containing the subreddit objects as nodes
		return new Graph(subreddit_list);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GUI();
	}
}
