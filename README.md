# 🚀 Graph Algorithms in Java

This repository contains a collection of essential **Graph Algorithm Solutions** for common interview problems and coding practice — especially useful for platforms like **LeetCode, HackerRank, Codeforces, GFG** and more!

---

## 📚 Table of Contents

1. [BFS (Breadth-First Search)](#1️⃣-bfs-breadth-first-search)
2. [DFS (Depth-First Search)](#2️⃣-dfs-depth-first-search)
3. [Number of Islands 🌊](#3️⃣-number-of-islands-)
4. [Max Area of Island 🏝️](#4️⃣-max-area-of-island-)
5. [Clone Graph 🧬](#5️⃣-clone-graph-)
6. [Flood Fill 🎨](#6️⃣-flood-fill-)
7. [Rotten Oranges 🍊](#7️⃣-rotten-oranges-)
8. [Number of Distinct Islands 🏖️](#8️⃣-number-of-distinct-islands-)
9. [Distance of Nearest Cell Having 1](#9️⃣-distance-of-nearest-cell-having-1)
10. [Is Graph Bipartite?](#🔟-is-graph-bipartite)
11. [Topological Sort (DFS)](#1️⃣1️⃣-topological-sort-dfs)

---

## 1️⃣ BFS (Breadth-First Search)

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
