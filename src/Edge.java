public class Edge {

    //Attributes

    private Node destination;

    private Double distance;

    //Methods

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
}
