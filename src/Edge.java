/**
 * The Edge class handles how the edges between each Node and the destination works
 * This is done through various getter and setter methods
 *
 * @author Jayath Gunawardena
 * created on 19/05/2023
 */
public class Edge {


public class Edge {

    private Node destination;
    private Double distance;

    //Methods

    /**
     * Edge Class constructor method
     * @param destination Node which is the destination of the Edge
     * @param distance, Double which is the distance to the destination
     */
    public Edge(Node destination, double distance) {
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * GetDestination method
     * @return Node representation of the Edge's destination
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * GetDistance method
     * @return Double representation of the Edge's distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * toString method
     * Overrides, the default toString method
     * @return String, representation of the Edge, with all the attributes outlined
     */
    @Override
    public String toString() {
        return "Destination: " + destination + "\nDistance: " + distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return Objects.equals(destination, edge.destination) &&
                Objects.equals(distance, edge.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, distance);
    }
}
