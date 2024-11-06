import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

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


    //Number of Distinct Islands
    public int numDistinctIslands(int[][] grid) {
        Set<List<String>> uniqueIslands = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1) {
                    List<String> shape= new ArrayList<>();
                    dfsHelperFornumDistinctIslands(grid, i, j, i, j, shape);
                    uniqueIslands.add(shape);
                }
            }
        }

        return uniqueIslands.size();
    }
    private static void dfsHelperFornumDistinctIslands(int [][]grid, int row, int col, int baseRow, int baseCol, List<String> shape){
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }

        grid[row][col]=0;

        shape.add((row-baseRow)+","+(col-baseCol));

        dfsHelperFornumDistinctIslands(grid, row-1, col, baseRow, baseCol, shape);
        dfsHelperFornumDistinctIslands(grid, row+1, col, baseRow, baseCol, shape);
        dfsHelperFornumDistinctIslands(grid, row, col-1, baseRow, baseCol, shape);
        dfsHelperFornumDistinctIslands(grid, row, col+1, baseRow, baseCol, shape);
    }

    // Distance of nearest cell having 1 | 0/1 Matrix 
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length; 
        int m = mat[0].length; 
        
        int[][] vis = new int[n][m]; 
        int[][] dist = new int[n][m];
        
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    dist[i][j] = 0; // distance to itself is 0
                    vis[i][j] = 1; // mark as visited
                    q.add(new int[]{i, j}); // add to queue
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // initialize as unreachable
                }
            }
        }
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            
            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && vis[nr][nc] == 0) {
                    dist[nr][nc] = dist[r][c] + 1; // update distance
                    vis[nr][nc] = 1; // mark as visited
                    q.add(new int[]{nr, nc}); // add to queue
                }
            }
        }
        
        return dist;   
    }


    //Is Graph Bipartite?
    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        int []colors=new int[n];
        Arrays.fill(colors, -1);
        for (int i = 0; i < n; i++) {
            if (colors[i]==-1) {
                Queue <Integer> q=new LinkedList<>();
                q.offer(i);
                colors[i]=0;


                while (!q.isEmpty()) {
                    int node= q.poll();
                    for (Integer neighbour : graph[node]) {
                        if (colors[neighbour]==-1) {
                            colors[neighbour] = 1 - colors[node];
                            q.offer(neighbour);
                        }
                        else if (colors[neighbour] == colors[node]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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