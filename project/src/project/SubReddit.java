package project;

import java.util.HashMap;

/**
 * An abstract data type representing a subreddit
 * 
 * @author Shahroz Siddiqi
 */
public class SubReddit {

	private final String name; 							// subreddit name
	private final HashMap<String, Integer> comments; 	// subreddit comments
	private final int index; 							// subreddit index for graph

	/**
	 * Constructs a new SubReddit object
	 * 
	 * @param name     The name of the subreddit
	 * @param comments A hash map of comments in the subreddit
	 * @param index    The index of the subreddit, for graphing purposes
	 */
	public SubReddit(String name, HashMap<String, Integer> comments, int index) {
		this.name = name;
		this.comments = comments;
		this.index = index;
	}

	/**
	 * Returns the name of the subreddit
	 * 
	 * @return The name of the subreddit
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a hash map of comments in the subreddit
	 * 
	 * @return A hash map of comments in the subreddit
	 */
	public HashMap<String, Integer> getComments() {
		return comments;
	}

	/**
	 * Returns the index of the subreddit
	 * 
	 * @return The index of the subreddit
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Returns a string representation of a subreddit
	 */
	public String toString() {
		return String.format("%s", name);
	}
}
