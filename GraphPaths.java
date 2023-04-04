import java.util.*;
import java.util.Queue;

public class GraphPaths
{
    public static boolean isPathDF(WeightedGraphInterface<String> graph, String startVertex, String endVertex) {
        // Initialize the marks
        graph.clearMarks();
        // Mark the start vertex
        graph.markVertex(startVertex);
        // Recursively search for the end vertex
        return isPathDFHelper(graph, startVertex, endVertex);
    }

    private static boolean isPathDFHelper(WeightedGraphInterface<String> graph, String currentVertex, String endVertex) {
        // Base case: if we have found the end vertex, return true
        if (currentVertex.equals(endVertex)) {
            return true;
        }
        // Recursive case: visit all unvisited adjacent vertices
        QueueInterface<String> adjacentVertices = graph.getAdjacentVertices(currentVertex);
        while (!adjacentVertices.empty()) {
            String adjacentVertex = adjacentVertices.remove();
            if (!graph.isMarked(adjacentVertex)) {
                graph.markVertex(adjacentVertex);
                if (isPathDFHelper(graph, adjacentVertex, endVertex)) {
                    return true;
                }
            }
        }
        // If we have visited all reachable vertices and haven't found the end vertex, return false
        return false;
    }

    public static boolean isPathBF(WeightedGraphInterface<String> graph, String startVertex, String endVertex) {
        // Initialize the marks and the queue for the BFS algorithm
        graph.clearMarks();
        QueueInterface<String> queue = new LinkedQueue<>();
        // Mark the start vertex and enqueue it
        graph.markVertex(startVertex);
        queue.add(startVertex);
        // While the queue is not empty, dequeue the next vertex and visit all its unvisited adjacent vertices
        while (!queue.empty()) {
            String currentVertex = queue.remove();
            QueueInterface<String> adjacentVertices = graph.getAdjacentVertices(currentVertex);
            while (!adjacentVertices.empty()) {
                String adjacentVertex = adjacentVertices.remove();
                if (!graph.isMarked(adjacentVertex)) {
                    if (adjacentVertex.equals(endVertex)) {
                        // We have found the end vertex, return true
                        return true;
                    }
                    graph.markVertex(adjacentVertex);
                    queue.add(adjacentVertex);
                }
            }
        }
        // If we have visited all reachable vertices and haven't found the end vertex, return false
        return false;
    }
    
    public static QueueInterface<Flight> shortestPaths(WeightedGraphInterface<String> graph, 
                                                       String startVertex)
    // Determines the shortest distance from startVertex to every other reachable vertex in graph.
    {
        graph.clearMarks();
        QueueInterface<Flight> resultQueue = new Que<>();
        QueueInterface<String> vertexQueue = new Que<>();
        PriorityQueue<Flight> priorityQueue = new PriorityQueue<Flight>();
        Flight initialFlight = new Flight(startVertex, startVertex, 0);
        priorityQueue.add(initialFlight);
        while(priorityQueue.isEmpty() == false)
        {
            Flight currentFlight = priorityQueue.remove();
            if(!graph.isMarked(currentFlight.getToVertex()))
            {
                graph.markVertex(currentFlight.getToVertex());
                resultQueue.add(currentFlight);
                currentFlight.setFromVertex(currentFlight.getToVertex());
                int minDistance=currentFlight.getDistance(); 
                vertexQueue= graph.getAdjacentVertices(currentFlight.getFromVertex());
                while(vertexQueue.size() != 0)
                {
                    String vertex = vertexQueue.remove();
                    if(!graph.isMarked(vertex))
                    {
                        int newDistance = minDistance + graph.weightIs(currentFlight.getFromVertex(), vertex);
                        Flight newFlight = new Flight(currentFlight.getFromVertex(), vertex, newDistance);
                        priorityQueue.add(newFlight);
                    }
                }
            }
        }
        return resultQueue;
    }
}