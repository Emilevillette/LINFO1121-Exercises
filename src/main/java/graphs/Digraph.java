package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implement the Digraph.java interface in the Digraph.java class using an adjacency list
 * data structure to represent directed graphs.
 */
public class Digraph {

    private int V;
    private int E;
    private ArrayList<ArrayList<Integer>> adj;

    public Digraph(int V) {
        // TODO
        this.adj = new ArrayList<ArrayList<Integer>>(V);
        this.V = V;
        this.E = 0;
        for (int i = 0; i < V; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    /**
     * The number of vertices
     */
    public int V() {
        // TODO
        return this.V;
    }

    /**
     * The number of edges
     */
    public int E() {
        // TODO
        return this.E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        // TODO
        ArrayList bla = this.adj.get(v);
        this.E++;
        bla.add(w);
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        // TODO
        return (Iterable<Integer>) this.adj.get(v);
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        // TODO
        Digraph retval = new Digraph(this.V);
        for (int i= 0; i<this.V;i++) {
            for(Integer j: this.adj.get(i)) {
                retval.addEdge(j,i);
            }
        }
        return retval;
    }

}
