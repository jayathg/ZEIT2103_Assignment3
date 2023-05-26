import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph graph;
    private Node node1;
    private Node node2;
    private Node node3;

    @BeforeEach
    void setUp() {
        graph = new Graph();
        node1 = new Node(1234, "Suburb1");
        node2 = new Node(1235, "Suburb2");
        node3 = new Node(1236, "Suburb3");
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
    }

    @Test
    void addNode() {
        Node node4 = new Node(1237, "Suburb4");
        graph.addNode(node4);
        assertEquals(node4, graph.getNode("Suburb4"));
    }

    @Test
    void addInvalidNode() {
        Node invalidNode = null;
        assertThrows(NullPointerException.class, () -> graph.addNode(invalidNode));
    }

    @Test
    void addEdge() {
        graph.addEdge(node1, node2, 10.0);
        assertTrue(node1.getAdj().contains(new Edge(node2, 10.0)));
    }

    @Test
    void addEdge_NullNodes_False(){
        Node n1 = null;
        Node n2 = null;
        assertThrows(NullPointerException.class, () -> graph.addEdge(n1,n2, 10));
    }

    @Test
    void addInvalidEdge_DuplicateNode_False() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Duplicate Nodes
            graph.addEdge(node1, node1, 10.0);
        });
    }

    @Test
    void addInvalidEdge_InvalidDistance_False(){
        assertThrows(IllegalArgumentException.class, () -> {
            // Invalid Distance
            graph.addEdge(node1, node2, -10.0);
        });
    }

    @Test
    void removeEdge() {
        graph.addEdge(node1, node2, 10.0);
        graph.removeEdge(node1, node2, 10.0);
        assertFalse(node1.getAdj().contains(new Edge(node2, 10.0)));
    }

    @Test
    void removeEdge_NoSuchEdge_False(){
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(node1, node2, 10.0));
    }

    @Test
    void removeEdge_InvalidParameters(){
        Node n1 = null;
        Node n2 = null;
        assertThrows(NullPointerException.class, () -> graph.removeEdge(n1,n2, -3));
    }

    @Test
    void removeNode() {
        graph.removeNode(node1);
        assertNull(graph.getNode("Suburb1"));
    }

    @Test
    void removeNode_NoSuchNode_False(){
        Node n1 = new Node(1234, "Test Suburb");
        assertThrows(IllegalArgumentException.class, () -> graph.removeNode(n1));
    }

    @Test
    void getNode() {
        assertEquals(node1, graph.getNode("Suburb1"));
    }

    @Test
    void getNodeNames() {
        assertEquals(3, graph.getNodeNames().size());
    }

    @Test
    void addDuplicateNode() {
        graph.addNode(node1);
        assertEquals(3, graph.getNodeNames().size());
    }
}
