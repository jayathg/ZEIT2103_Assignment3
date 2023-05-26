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

    @Test
    void equals_SameObject_True(){
        Node n1 = new Node(5432, "Test Suburb");
        Edge e1 = new Edge(n1, 10);
        Edge e2 = new Edge(n1, 10);
        assertTrue(e1.equals(e2));
    }

    @Test
    void equals_DifferentNode_False(){
        Node n1 = new Node(5432, "Test Suburb");
        Node n2 = new Node(1234, "New Suburb");
        Edge e1 = new Edge(n1, 10);
        Edge e2 = new Edge(n2, 10);
        assertFalse(e1.equals(e2));
    }
    @Test
    void equals_DifferentDistance_False(){
        Node n1 = new Node(5432, "Test Suburb");
        Edge e1 = new Edge(n1, 10);
        Edge e2 = new Edge(n1, 12);
        assertFalse(e1.equals(e2));
    }

    @Test
    void equals_DifferentObject_False(){
        Node n1 = new Node(5432, "Test Suburb");
        Edge e1 = new Edge(n1, 10);
        assertFalse(e1.equals(n1));
    }

    @Test
    void hashCode_ValidEdge_True(){
        Node n1 = new Node(5432, "Test Suburb");
        Edge e1 = new Edge(n1, 10);
        assertEquals(223607618, e1.hashCode());
    }
}
