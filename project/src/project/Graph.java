/**
 * Algorithms and Data Structures from Sedgewick and Wayne's Algorithms (4th ed.)
 */

package project;

import java.util.List;

/**
 * Represents an undirected graph with vertices of type SubReddit.
 * 
 * @author Shahroz Siddiqi
 */
public class Graph {

	private final int V; // number of vertices
	private int E; // number of edges
	private Bag<SubReddit>[] adj; // adjacency list
	private List<SubReddit> subreddits; // list of SubReddit objects

	/**
	 * Constructs a graph using the list of subreddits and only adds edges between
	 * the subreddits with common words.
	 * 
	 * @param subreddits A list of SubReddit objects
	 */
	public Graph(List<SubReddit> subreddits) {
		this.V = subreddits.size();
		this.E = 0;
		this.subreddits = subreddits;
		adj = (Bag<SubReddit>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<SubReddit>();
		}

		// Add edges between subreddits containing the same words
		for (int i = 0; i < V - 1; i++)
			for (String a : subreddits.get(i).getComments().keySet())
				for (int j = i + 1; j < V; j++)
					for (String b : subreddits.get(j).getComments().keySet())
						if (a.equals(b))
							addEdge(subreddits.get(i), subreddits.get(j));
	}

	/**
	 * Returns the number of vertices.
	 * 
	 * @return the number of vertices
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges.
	 * 
	 * @return the number of edges
	 */
	public int E() {
		return E;
	}

	/**
	 * Adds an undirected edge between v and w.
	 * 
	 * @param v A SubReddit object to connect
	 * @param w A SubReddit object to connect
	 */
	public void addEdge(SubReddit v, SubReddit w) {
		adj[v.getIndex()].add(w);
		adj[w.getIndex()].add(v);
		E++;
	}

	/**
	 * Returns the vertices adjacent to v.
	 * 
	 * @param v A SubReddit object
	 * @return The vertices adjacent to vertex v, as an iterable
	 */
	public Iterable<SubReddit> adj(SubReddit v) {
		return adj[v.getIndex()];
	}

	/**
	 * Returns a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + "\n");
		for (SubReddit v : subreddits) {
			s.append(v + ": ");
			for (SubReddit w : adj[v.getIndex()]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
}
