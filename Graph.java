import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.HashMap;

/**
 * Graph
 */
public class Graph {

    // BFS
    public static ArrayList<Integer> Bfs(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        vis[0] = true;

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

    // DFS
    public static ArrayList<Integer> Dfs(int V, boolean[] vis, ArrayList<ArrayList<Integer>> adj,
            ArrayList<Integer> ans) {
        vis[V] = true;
        ans.add(V);
        for (Integer it : adj.get(V)) {
            if (vis[it] == false) {
                Dfs(it, vis, adj, ans);
            }
        }
        return ans;
    }

    // Number of Islands
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;

        int numofIslands = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    numofIslands++;
                    dfsHelperFornumIslands(grid, i, j);
                }
            }
        }

        return numofIslands;
    }
    private static void dfsHelperFornumIslands(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        dfsHelperFornumIslands(grid, i + 1, j);
        dfsHelperFornumIslands(grid, i - 1, j);
        dfsHelperFornumIslands(grid, i, j + 1);
        dfsHelperFornumIslands(grid, i, j - 1);
    }

    //Max Area of Island
    public static int maxAreaOfIsland(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        int maxArea=0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j]==1){
                    int currArea=dfsHelperForMaxArea(grid, i, j);
                    maxArea=Math.max(currArea,maxArea);
                }
            }
        }
        return maxArea;
    }
    private static int dfsHelperForMaxArea(int[][] grid, int i, int j){
        if(  i>=grid.length || i<0 || j<0 || j>=grid[0].length || grid[i][j]==0){
            return 0;
        }
        grid[i][j]=0;
        int area=1;
        area+=dfsHelperForMaxArea(grid, i+1, j);
        area+=dfsHelperForMaxArea(grid, i-1, j);
        area+=dfsHelperForMaxArea(grid, i, j+1);
        area+=dfsHelperForMaxArea(grid, i, j-1);

        return area;
    }

    //Clone Graph
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    private HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node==null){
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }


    //Flood fill
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int baseColor=image[sr][sc];
        if (baseColor==color) {
            return image;
        }
        dfsforFloodFill(image,sr,sc,baseColor,color);
        return image;
    }

    private static void dfsforFloodFill(int[][] image, int sr, int sc,int baseColor, int color) {
         if (sr<0 || sr >= image.length || sc<0 || sc>=image[0].length || image[sr][sc]!= baseColor) {
            return;
        }

        image[sr][sc]=color;
        dfsforFloodFill(image, sr+1, sc,baseColor, color);
        dfsforFloodFill(image, sr-1, sc,baseColor, color);
        dfsforFloodFill(image, sr, sc+1,baseColor, color);
        dfsforFloodFill(image, sr, sc-1,baseColor, color);
    }

    //Rotten Oranges
    public int orangesRotting(int[][] grid) {
        int directions[][]={{-1,0},{1,0},{0,-1},{0,1} };
        int freshOranges=0;
        Queue <int[]> queue= new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1) {
                    freshOranges++;
                }
                else if(grid[i][j]==2){
                    queue.offer(new int[] {i,j});
                }
            }
        }

        if (freshOranges==0) return 0;
        int minute=-1;

        while (!queue.isEmpty()) {
            int size=queue.size();
            minute++;
            for(int k=0; k< size; k++){
                int [] pos =queue.poll();
                int x=pos[0];
                int y=pos[1];

            for (int [] dir : directions) {
                int newX=x+dir[0];
                int newY=y+dir[1];
                if (newX>=0 && newX < grid.length && newY>=0 && newY< grid[0].length && grid[newX][newY]==1) {
                    grid[newX][newY]=2;
                    queue.offer(new int[] {newX, newY});
                    freshOranges--;
                }
              }
            }            
        }

        return  freshOranges==0 ? minute: -1;
    }
    

    public static void main(String[] args) {
        int V = 5;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
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