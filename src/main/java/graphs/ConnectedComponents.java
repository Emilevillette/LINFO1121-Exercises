package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are asked to implement the ConnectedComponent class given a graph.
 * The Graph class available in the code is the one of the Java class API.
 * <p>
 * public class ConnectedComponents {
 * <p>
 * public static int numberOfConnectedComponents(Graph g){
 * // TODO
 * return 0;
 * }
 * }
 * Hint: Feel free to add methods or even inner-class (private static class) to help you but don't change the class name or the signature of the numberOfConnectedComponents method.
 * Don't forget to add the imports at the beginning of your code if you use objects from the Java API.
 */
public class ConnectedComponents {


    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        // TODO
        UF uf = new UF(g.V());
        for(int i =0;i<g.V();i++) {
            for(int j: g.adj(i)) {
                uf.union(i,j);
            }
        }
        return uf.count;
    }

    private static class UF {
        private int[] id; // access to component id (site indexed)
        private int count; // number of components

        public UF(int N) { // Initialize component id array.
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        private int find(int p) { // Find component name.
            while (p != id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) { // Give p and q the same root.
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            id[pRoot] = qRoot;
            count--;
        }

        // See page 222 (quick-find),page 224 (quick-union) andpage 228 (weighted).
    }

    static class Graph {

        private List<Integer>[] edges;

        public Graph(int nbNodes) {
            this.edges = (ArrayList<Integer>[]) new ArrayList[nbNodes];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        /**
         * @return the number of vertices
         */
        public int V() {
            return edges.length;
        }

        /**
         * @return the number of edges
         */
        public int E() {
            int count = 0;
            for (List<Integer> bag : edges) {
                count += bag.size();
            }

            return count / 2;
        }

        /**
         * Add edge v-w to this graph
         */
        public void addEdge(int v, int w) {
            edges[v].add(w);
            edges[w].add(v);
        }

        /**
         * @return the vertices adjacent to v
         */
        public Iterable<Integer> adj(int v) {
            return edges[v];
        }
    }

}
