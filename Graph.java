import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Graph
 */
public class Graph {

    public static ArrayList<Integer> Bfs(int V, ArrayList<ArrayList<Integer>> adj){
        ArrayList < Integer > bfs = new ArrayList < > ();
        boolean vis[]=new boolean[V];
        Queue < Integer > q = new LinkedList < > ();

        q.add(0);
        vis[0]=true;

        while (!q.isEmpty()) {
            Integer node = q.poll();
            bfs.add(node);

            for (Integer it : adj.get(node)) {
                if (vis[it] == false) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }

        return bfs;
    }
    public static void main(String[] args) {
        int V = 6;

        // Creating the adjacency list for the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initializing the adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // Adding edges to create a graph with cycles
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(2).add(4);
        adj.get(3).add(4);
        adj.get(3).add(5);
        adj.get(4).add(5);
        adj.get(5).add(0); // Cycle edge back to node 0

        /*
          Graph looks like:
               0
              / \
             1 - 2
            /     \
           3 - 4 - 5
               ^   |
               |___|
        */

        // Perform BFS traversal
        ArrayList<Integer> bfsResult = Bfs(V, adj);

        // Printing the BFS result
        System.out.println("BFS Traversal: " + bfsResult);
    
    }
}