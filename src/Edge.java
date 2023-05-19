import java.util.Objects;

public class Edge {

    private Node destination;
    private Double distance;

    public Edge(Node destination, double distance) {
        this.destination =destination;
        this.distance = distance;
    }

    public Node getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

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
