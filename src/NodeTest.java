import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void getSuburb() {
        Node node = new Node(1234, "Test Suburb");
        assertEquals("Test Suburb", node.getSuburb());
    }

    @Test
    void getPostcode() {
        Node node = new Node(1234, "Test Suburb");
        assertEquals(1234, node.getPostcode());
    }

    @Test
    void setValidAdj() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        assertTrue(node1.getAdj().contains(edge));
    }

    @Test
    void setInvalidAdj() {
        Node node = new Node(1234, "Test Suburb");
        Edge edge = new Edge(node, 10.5); // node cannot be adjacent to itself
        assertThrows(IllegalArgumentException.class, () -> node.addAdj(edge));

        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge1 = new Edge(node2, 10.5);
        Edge edge2 = new Edge(node2, 10.5); // duplicate adjacency
        node.addAdj(edge1);
        assertThrows(IllegalArgumentException.class, () -> node.addAdj(edge2));
    }

    @Test
    void getAdj() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        assertTrue(node1.getAdj().contains(edge));
    }

    @Test
    void setValidPostcode() {
        Node node = new Node(1234, "Test Suburb");
        node.setPostcode(5432);
        assertEquals(5432, node.getPostcode());
    }

    @Test
    void setInvalidPostcode() {
        Node node = new Node(1234, "Test Suburb");
        assertThrows(IllegalArgumentException.class, () -> node.setPostcode(543)); // postcode less than 4 digits
        assertThrows(IllegalArgumentException.class, () -> node.setPostcode(54321)); // postcode more than 4 digits
        //assertThrows(NumberFormatException.class, () -> Integer.parseInt("abcd")); // non-numeric input
    }

    @Test
    void addAmenity() {
        // check
        Node node = new Node(12345, "Test Suburb");
        node.addAmenity("School");
        assertTrue(node.getAmenity().contains("School"));
    }

    @Test
    void getAmenity() {
        Node node = new Node(12345, "Test Suburb");
        node.addAmenity("School");
        assertTrue(node.getAmenity().contains("School"));
    }

    @Test
    void removeAmenity() {
        Node node = new Node(12345, "Test Suburb");
        node.addAmenity("School");
        node.removeAmenity("School");
        assertFalse(node.getAmenity().contains("School"));
    }

    @Test
    void testToString() {
        Node node = new Node(12345, "Test Suburb");
        String expected = "Node{" +
                "postCode=" + 12345 +
                ", suburb='Test Suburb" + '\'' +
                ", adj=" + new ArrayList<>() +
                ", amenity=" + new ArrayList<>() +
                '}';
        assertEquals(expected, node.toString());
    }

    @Test
    void testHashCode() {
        Node node = new Node(1234, "Test Suburb");
        assertNotEquals(0, node.hashCode());
    }

    @Test
    void testEquals() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(1234, "Test Suburb 1");
        assertEquals(node1, node2);
    }

    @Test
    void removeAdj() {
        Node node1 = new Node(1234, "Test Suburb 1");
        Node node2 = new Node(5432, "Test Suburb 2");
        Edge edge = new Edge(node2, 10.5);
        node1.addAdj(edge);
        node1.removeAdj(edge);
        assertFalse(node1.getAdj().contains(edge));
    }
}

