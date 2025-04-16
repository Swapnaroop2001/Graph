# 🚀 Graph Algorithms in Java

This repository contains a collection of essential **Graph Algorithm Solutions** for common interview problems and coding practice — especially useful for platforms like **LeetCode, HackerRank, Codeforces, GFG** and more!

---

## 📚 Table of Contents

1. [BFS (Breadth-First Search)](#1-bfs-breadth-first-search)
2. [DFS (Depth-First Search)](#2-dfs-depth-first-search)
3. [Number of Islands 🌊](#3-number-of-islands-)
4. [Max Area of Island 🏝️](#4-max-area-of-island-)
5. [Clone Graph 🧬](#5-clone-graph-)
6. [Flood Fill 🎨](#6-flood-fill-)
7. [Rotten Oranges 🍊](#7-rotten-oranges-)
8. [Number of Distinct Islands 🏖️](#8-number-of-distinct-islands-)
9. [Distance of Nearest Cell Having 1](#9-distance-of-nearest-cell-having-1)
10. [Is Graph Bipartite?](#10-is-graph-bipartite)
11. [Topological Sort (DFS)](#11-topological-sort-dfs)

---

## 1. BFS (Breadth-First Search)

```java
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
            if (!vis[it]) {
                vis[it] = true;
                q.add(it);
            }
        }
    }
    return bfs;
}
```

## 2. DFS (Depth-First Search)

```java
public static void Dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ls) {
    vis[node] = true;
    ls.add(node);
    for (Integer it : adj.get(node)) {
        if (!vis[it]) {
            Dfs(it, vis, adj, ls);
        }
    }
}
```

## 3. Number of Islands 🌊🏝️

```java
public int numIslands(char[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == '1') {
                dfs(grid, i, j);
                count++;
            }
        }
    }
    return count;
}

void dfs(char[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;
    grid[i][j] = '0';
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
}
```

## 4. Max Area of Island 🏝️

```java
public int maxAreaOfIsland(int[][] grid) {
    int max = 0;
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
                max = Math.max(max, dfs(grid, i, j));
            }
        }
    }
    return max;
}

int dfs(int[][] grid, int i, int j) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) return 0;
    grid[i][j] = 0;

    return 1 + dfs(grid, i + 1, j) 
             + dfs(grid, i - 1, j) 
             + dfs(grid, i, j + 1) 
             + dfs(grid, i, j - 1);
}
```

## 5. Clone Graph 🧬

```java
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {}
    public Node(int val) { this.val = val; neighbors = new ArrayList<>(); }
    public Node(int val, List<Node> neighbors) { this.val = val; this.neighbors = neighbors; }
}

Map<Node, Node> map = new HashMap<>();

public Node cloneGraph(Node node) {
    if (node == null) return null;
    if (map.containsKey(node)) return map.get(node);
    Node copy = new Node(node.val);
    map.put(node, copy);
    for (Node n : node.neighbors) {
        copy.neighbors.add(cloneGraph(n));
    }
    return copy;
}
```

## 6. Flood Fill 🎨

```java
public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int currColor = image[sr][sc];
    if (currColor != color) dfs(image, sr, sc, currColor, color);
    return image;
}

private void dfs(int[][] image, int r, int c, int color, int newColor) {
    if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != color) return;
    image[r][c] = newColor;
    dfs(image, r + 1, c, color, newColor);
    dfs(image, r - 1, c, color, newColor);
    dfs(image, r, c + 1, color, newColor);
    dfs(image, r, c - 1, color, newColor);
}
```

## 7. Rotten Oranges 🍊

```java
public int orangesRotting(int[][] grid) {
    Queue<int[]> queue = new LinkedList<>();
    int fresh = 0, time = 0;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 2) queue.offer(new int[]{i,j});
            if (grid[i][j] == 1) fresh++;
        }
    }

    while (!queue.isEmpty() && fresh > 0) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] point = queue.poll();
            for (int[] dir : dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1) continue;
                grid[x][y] = 2;
                queue.offer(new int[]{x, y});
                fresh--;
            }
        }
        time++;
    }
    return fresh == 0 ? time : -1;
}
```

## 8. Number of Distinct Islands 🏖️

```java
public int numDistinctIslands(int[][] grid) {
    Set<String> set = new HashSet<>();
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
                StringBuilder path = new StringBuilder();
                dfs(grid, i, j, path, "O");
                set.add(path.toString());
            }
        }
    }
    return set.size();
}

private void dfs(int[][] grid, int i, int j, StringBuilder path, String dir) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) return;
    grid[i][j] = 0;
    path.append(dir);
    dfs(grid, i + 1, j, path, "D");
    dfs(grid, i - 1, j, path, "U");
    dfs(grid, i, j + 1, path, "R");
    dfs(grid, i, j - 1, path, "L");
    path.append("B");
}
```

## 9. Distance of Nearest Cell Having 1

```java
public int[][] nearest(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[][] dist = new int[n][m];
    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 1) {
                queue.add(new int[]{i, j});
                dist[i][j] = 0;
            } else {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    while (!queue.isEmpty()) {
        int[] cell = queue.poll();
        int x = cell[0];
        int y = cell[1];
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
    return dist;
}
```

## 10. Is Graph Bipartite?

```java
public boolean isBipartite(int[][] graph) {
    int[] color = new int[graph.length];
    Arrays.fill(color, -1);

    for (int i = 0; i < graph.length; i++) {
        if (color[i] == -1) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            color[i] = 0;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int neighbor : graph[node]) {
                    if (color[neighbor] == -1) {
                        color[neighbor] = 1 - color[node];
                        queue.offer(neighbor);
                    } else if (color[neighbor] == color[node]) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}
```

## 11. Topological Sort (DFS)

```java
public static void topoSort(int node, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {
    visited[node] = true;
    for (Integer neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            topoSort(neighbor, visited, stack, adj);
        }
    }
    stack.push(node);
}

public static int[] topologicalSort(int V, ArrayList<ArrayList<Integer>> adj) {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[V];

    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            topoSort(i, visited, stack, adj);
        }
    }

    int[] result = new int[V];
    int index = 0;
    while (!stack.isEmpty()) {
        result[index++] = stack.pop();
    }

    return result;
}
```

## 12. Walls and Gates ⛩️🧱

```java
    private static final int INF=2147483647;
    public void islandsAndTreasure(int[][] grid) {
        if (grid ==null || grid.length==0) return;

        int row=grid.length;
        int col=grid[0].length;

        Queue<int[]> q= new LinkedList<>();

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(grid[i][j]==0){
                    q.add(new int []{i,j});
                }
            }
        }

        int [][] directions= {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()){
            int [] curr= q.poll();
            int r= curr[0];
            int c= curr[1];

            for(int i=0; i< directions.length; i++){
                int newRow = r+ directions[i][0];
                int newCol = c+ directions[i][1];
                if(newRow >= 0 && newCol>=0 && newRow<row && newCol<col && grid[newRow][newCol]==INF){
                    grid[newRow][newCol]= grid[r][c]+1;
                    q.add(new int []{newRow, newCol});
                }
            }

        }

    }
```

## 13. Pacific Atlantic Water Flow 🌊🌍
```java
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result =new ArrayList<>();

        int rows= heights.length;
        int cols= heights[0].length;

        boolean[][] pacific= new boolean[rows][cols];
        boolean[][] atlantic= new boolean[rows][cols];

        // Left to right edge
        for(int i=0; i<rows; i++){
            dfs(heights, pacific, i, 0, heights[i][0]);
            dfs(heights, atlantic, i, cols-1, heights[i][cols-1] );
        }

        //Top to bottom edge
        for(int j=0; j<cols; j++){
            dfs(heights, pacific, 0, j, heights[0][j]);
            dfs(heights, atlantic, rows - 1, j, heights[rows - 1][j]);
        }

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] ocean, int r, int c, int prevHeight){
        if (r < 0 || c < 0 || r >= heights.length || c >= heights[0].length) return;
        if(ocean[r][c]) return;
        if(heights[r][c]< prevHeight) return;

        ocean[r][c] = true;

        dfs(heights, ocean, r + 1, c, heights[r][c]);  // Down
        dfs(heights, ocean, r - 1, c, heights[r][c]);  // Up
        dfs(heights, ocean, r, c + 1, heights[r][c]);  // Right
        dfs(heights, ocean, r, c - 1, heights[r][c]);  // Left

    }
}
```

## 14. Surrounded Regions ❌
```java

public void solve(char[][] board) {
        if (board == null || board.length==0) return;

        int rows=board.length;
        int cols=board[0].length;

        for(int i=0; i<rows; i++){
            dfs(board ,i, 0);
            dfs(board ,i, cols-1);
            
        }
        for(int j=0; j<cols; j++){
            dfs(board ,0, j );
            dfs(board ,rows-1, j);
        }

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }else if(board[i][j]=='#'){
                    board[i][j]='O';
                }
            }
        }
    }

    private void dfs(char [][] board, int r, int c){
        int rows = board.length;
        int cols = board[0].length;

        if (r < 0 || c < 0 || r >= rows || c >= cols || board[r][c] != 'O') return;
        board[r][c]='#';

        dfs(board, r + 1, c);  // Down
        dfs(board, r - 1, c);  // Up
        dfs(board, r, c + 1);  // Right
        dfs(board, r, c - 1);  // Left
    }
```

## 15. Course Schedule 📅🎓
```java
class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Step 1: Create an array to store the indegree of each course.
        int []indegree= new int[numCourses];

        // Step 2: Create an adjacency list to represent the graph.
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());  // Initialize empty lists for all courses.
        }

        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            adj.get(preCourse).add(course);

            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<numCourses; i++){
            if (indegree[i] == 0) {
                queue.offer(i);  
            }
        }

        int nodesVisited = 0;

        while(!queue.isEmpty()){
            int node=queue.poll();
            nodesVisited++;

            for(int neighbor : adj.get(node)){
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor); 
                }
            }
        }
        return nodesVisited == numCourses;
    }
}

```


