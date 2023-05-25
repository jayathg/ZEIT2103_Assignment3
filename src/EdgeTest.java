import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void getDestination() {
        Node node = new Node(5432, "Test Suburb");
        Edge edge = new Edge(node, 10.5);
        assertEquals(node, edge.getDestination());
    }

    @Test
    void getDistance() {
        Node node = new Node(5432, "Test Suburb");
        Edge edge = new Edge(node, 10.5);
        assertEquals(10.5, edge.getDistance(), 0.001);
    }

    @Test
    void testToString() {
        Node node = new Node(5432, "Test Suburb");
        Edge edge = new Edge(node, 10.5);
        String expected = "Destination: Node{" +
                "postCode=" + 5432 +
                ", suburb='Test Suburb" + '\'' +
                "}\nDistance: " + 10.5;
        assertEquals(expected, edge.toString());
    }
}
