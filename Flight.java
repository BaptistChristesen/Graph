public class Flight implements Comparable<Flight>
{
    protected String fromVertex;
    protected String toVertex;
    protected int distance;

    public Flight(String fromVertex, String toVertex, int distance)
    {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.distance = distance;
    }

    public String getFromVertex()
    {
        return fromVertex;
    }

    public String getToVertex()
    {
        return toVertex;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setFromVertex(String vertex)
    {
        fromVertex = vertex;
    }

    public void setToVertex(String vertex)
    {
        toVertex = vertex;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public int compareTo(Flight other)
    {
        return (this.distance - other.distance); // shorter is better 
    }

    public String toString()
    {
        return (fromVertex + "    " + toVertex + "    " + distance);
    }
}
