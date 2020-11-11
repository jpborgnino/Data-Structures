public class UnionFind {

    public int[] Array;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        Array = new int[n];
        for(int i = 0; i < n; i++) {
            Array[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex > Array.length || vertex < 0){
            throw new RuntimeException("Vertex is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
            return -Array[find(v1)];
        }


    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        int r = v1;
        if (Array[v1] < 0 ) {return Array[v1];}
        return Array[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
            return (find(v1) == find(v2));
        }


    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (connected(v1, v2)) {
            return;
        }
        int p1 = find(v1);
        int p2 = find(v2);
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);

        if (s1 > s2) {
            Array[p1] = -(s1 + s2);
            Array[p2] = p1;
        } else {
            Array[p2] = -(s1 + s2);
            Array[p1] = p2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if (vertex > Array.length) { throw new RuntimeException("Vertex is not a valid index.");}
        if (Array[vertex] >= 0) {
            return Array[vertex] = find(Array[vertex]);
        } else {
            return vertex;
        }

    }

}
