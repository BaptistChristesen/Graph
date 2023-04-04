import java.util.Arrays;
import java.util.*;

/**
 * Write a description of class weightegrapgh here.
 *
 * @author  (your name here)
 * @version (today's date)
 */
public class WeightedGraph<T> implements WeightedGraphInterface<T> {

    public static final int NULL_EDGE = 0;
    private static final int DEFCAP = 50;
    private int numVertices;
    private int maxVertices;
    private T[] vertices;
    private int[][] edges;
    private boolean[] marks;

    // Default constructor
    public WeightedGraph() {
        numVertices = 0;
        maxVertices = DEFCAP;
        vertices = (T[]) new Object[DEFCAP];
        marks = new boolean[DEFCAP];
        edges = new int[DEFCAP][DEFCAP];
    }

    // Constructor with capacity parameter
    public WeightedGraph(int maxV) {
        numVertices = 0;
        maxVertices = maxV;
        vertices = (T[]) new Object[maxV];
        marks = new boolean[maxV];
        edges = new int[maxV][maxV];
    }

    // Tests if graph is empty
    public boolean empty() {
        return (numVertices == 0);
    }

    // Tests if graph is full
    public boolean full() {
        return (numVertices == maxVertices);
    }

    // Adds vertex to graph
    public void addVertex(T vertex) {
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            edges[numVertices][i] = NULL_EDGE;
            edges[i][numVertices] = NULL_EDGE;
        }
        numVertices++;
    }

    // Returns true if graph contains vertex
    public boolean hasVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    // Adds an edge with the specified weight from fromVertex to toVertex
    public void setEdge(T fromVertex, T toVertex, int weight) {
        int row = getIndex(fromVertex);
        int col = getIndex(toVertex);
        edges[row][col] = weight;
    }

    // If edge from fromVertex to toVertex exists, return the weight of the edge;
    // Otherwise, returns a special "null-edge" value.
    public int weightIs(T fromVertex, T toVertex) {
        int row = getIndex(fromVertex);
        int col = getIndex(toVertex);
        return edges[row][col];
    }

    // Returns a qu eue of the vertices that are adjacent to the vertex
    public QueueInterface<T> getAdjacentVertices(T vertex) {
        QueueInterface<T> adjVertices = new LinkedQueue<>();
        int vertexIndex = getIndex(vertex);
        for (int i = 0; i < numVertices; i++) {
            if (edges[vertexIndex][i] != NULL_EDGE) {
                adjVertices.add(vertices[i]);
            }
        }
        return adjVertices;
    }

    // Sets marks for all vertices to false
    public void clearMarks() {
        Arrays.fill(marks, false);
    }

    // Sets mark for vertex to true
    public void markVertex(T vertex) {
        int index = getIndex(vertex);
        marks[index] = true;
    }

    // Returns true if vertex is marked
    public boolean isMarked(T vertex) {
        int index = getIndex(vertex);
        return marks[index];
    }

    // returns an unmarked vertex if any exist, otherwise returns null
    public T getUnmarked() {
        for (int i = 0; i < numVertices; i++) {
            if (!marks[i]) {
                return vertices[i];
            }
        }
        return null;
    }
    
    // return a String representation of this graph
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ");
        for (int i = 0; i < numVertices; i++) {
            sb.append(vertices[i].toString() + " ");
        }
        sb.append("\nEdges:\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                sb.append(edges[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    // Helper method to get the index of a vertex in the vertices array
    private int getIndex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

}