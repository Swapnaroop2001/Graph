import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Graph
 */
public class Graph {

    //BFS
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

    //DFS
    public static ArrayList<Integer> Dfs(int V, boolean []vis, ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> ans){
        vis[V]= true;
        ans.add(V);
        for (Integer it : adj.get(V)) {
            if (vis[it]==false) {
                Dfs(it, vis, adj, ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int V=5;

        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);

        
        boolean[] visited = new boolean[V];
        ArrayList<Integer> traversalResult = new ArrayList<>();
        traversalResult = Dfs(0, visited, adj, traversalResult);

        // Printing the DFS result
        System.out.println("DFS Traversal: " + traversalResult);
    
    }
}